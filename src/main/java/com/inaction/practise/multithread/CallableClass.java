package com.inaction.practise.multithread;

import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CallableClass implements Callable<String> {

    private String helloMessage;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return String.format("%s %s", this.getClass().getSimpleName(), helloMessage);
    }
}
