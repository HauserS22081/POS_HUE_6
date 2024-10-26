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
        int sum = (upperBound - lowerBound + 1) / 2 * (lowerBound + upperBound);
        if ((upperBound - lowerBound) % 2 == 0) sum += (upperBound - lowerBound) / 2;

        return sum;
    }
}
