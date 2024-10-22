package net.htlgkr.concurrency.firstassignment;

import java.util.List;

public class RunnableTask implements Runnable{

    private final List<Integer> numbers;
    private final int divider;

    public RunnableTask(List<Integer> numbers, int divider) {
        this.numbers = numbers;
        this.divider = divider;
    }

    @Override
    public void run() {
        for (Integer number : numbers) {
            if (number % divider == 0) {
                System.out.println(number);
            }
        }
    }
}
