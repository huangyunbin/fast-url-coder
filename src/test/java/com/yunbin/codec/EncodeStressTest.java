package com.yunbin.codec;

import org.apache.commons.codec.net.URLCodec;

import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cloud.huang on 16/12/9.
 */
public class EncodeStressTest {

    /**
     * 单线程 跑5000次用时(ms)
     * java自带 4591
     * apache   2840
     * ziesemer 1972
     * 我自己写的 1262
     */
    public static void main(String[] args) throws Exception {
        int type = Integer.valueOf(args[0]);
        int threadNum = Integer.valueOf(args[1]);
        int runNum = Integer.valueOf(args[2]);

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        long start = System.currentTimeMillis();

        for (int j = 0; j < threadNum; j++) {


            new Thread() {
                com.ziesemer.utils.codec.impl.URLEncoder urlEncoder = new com.ziesemer.utils.codec.impl.URLEncoder();

                @Override
                public void run() {
                    for (int i = 0; i < runNum; i++) {
                        try {
                            String result = null;
                            switch (type) {
                                case 1:
                                    result = URLEncoder.encode(Constant.URL, "utf8");
                                    break;
                                case 2:
                                    result = new String(URLCodec.encodeUrl(null, Constant.URL.getBytes("utf8")));
                                    break;
                                case 3:
                                    result = urlEncoder.encodeToString(Constant.URL.getBytes("utf8"));
                                    break;
                                case 4:
                                    result = FastEncoder.encode(Constant.URL.getBytes("utf8"));
                                    break;

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    countDownLatch.countDown();
                }
            }.start();


        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);
    }

}
