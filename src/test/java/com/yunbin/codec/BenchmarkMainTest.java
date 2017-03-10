package com.yunbin.codec;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.UnsupportedEncodingException;

@State(value = Scope.Benchmark)
public class BenchmarkMainTest {

    static byte[] bytes;

    static {
        try {
            bytes = Constant.URL.getBytes("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkMainTest.class.getSimpleName())
//                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void myTest() {
        String result = FastEncoder.encode(bytes);
    }


}