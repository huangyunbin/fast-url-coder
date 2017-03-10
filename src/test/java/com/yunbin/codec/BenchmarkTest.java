package com.yunbin.codec;

import org.openjdk.jmh.annotations.*;

import java.io.UnsupportedEncodingException;

@State(value = Scope.Benchmark)
public class BenchmarkTest {

    static byte[] bytes;
    static {
        try {
            bytes = Constant.URL.getBytes("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Benchmark
//    @Warmup(iterations = 5)
//    @Measurement(iterations = 2)
//    @Fork(10)
//    @Threads(1)
    // qps 7811.398 ± 258
    public void myTest() {
        String result = FastEncoder.encode(bytes);
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @Fork(2)
    @Threads(1)
    //qps  8290.500 ± 3655.672
    public void myBetaTest() {
        String result = FastEncoderBeta.encode(bytes);
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @Fork(2)
    @Threads(1)
    //qps  5048.619 ± 2622.357
    public void ziesemerTest() throws Exception {
        com.ziesemer.utils.codec.impl.URLEncoder urlEncoder = new com.ziesemer.utils.codec.impl.URLEncoder();
        String result = urlEncoder.encodeToString(bytes);
    }


}