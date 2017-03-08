package com.yunbin.codec;

import org.apache.commons.codec.net.URLCodec;

import java.net.URLEncoder;

/**
 * Created by cloud.huang on 17/3/8.
 */
public class EncodeTest {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        com.ziesemer.utils.codec.impl.URLEncoder urlEncoder = new com.ziesemer.utils.codec.impl.URLEncoder();


        String result = URLEncoder.encode(Constant.URL, "utf8");
        String result1 = new String(URLCodec.encodeUrl(null, Constant.URL.getBytes("utf8")));
        String result2 = new String(urlEncoder.encodeToString(Constant.URL.getBytes("utf8")));

        String result3 = FastEncoder.encode(Constant.URL.getBytes("utf8"));

        if (result.equals(result1)) {
            System.out.println("commons encode ok");
        }

        if (result.equals(result2)) {
            System.out.println("ziesemer encode ok");
        }


        if (result.equals(result3)) {
            System.out.println("fast encode ok");
        }


    }
}
