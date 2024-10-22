package net.htlgkr.concurrency.secondassignment;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

    private int upperBound;
    private int lowerBound;

    public CallableTask(int lowerBound, int upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    @Override
    public Integer call() throws Exception {
        int n = upperBound - lowerBound;

        return (upperBound - lowerBound) / 2 * (lowerBound + upperBound);
    }
}
