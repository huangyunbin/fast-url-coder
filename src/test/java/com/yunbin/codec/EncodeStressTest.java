package com.yunbin.codec;

import org.apache.commons.codec.net.URLCodec;

import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cloud.huang on 16/12/9.
 */
public class EncodeStressTest {

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
                                    result = new String(urlEncoder.encodeToString(Constant.URL.getBytes("utf8")));
                                    break;
                                case 4:
                                    result = FastEncoder.encode(Constant.URL.getBytes("utf8"));
                                    break;

                            }


                            System.out.println(result.length());
                            System.out.println(result.getBytes("utf8").length);
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
