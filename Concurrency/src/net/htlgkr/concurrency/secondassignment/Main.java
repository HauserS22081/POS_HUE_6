package net.htlgkr.concurrency.secondassignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n> ");
        int upperBound = Integer.parseInt(scanner.nextLine());

        ArrayList<Callable<Integer>> callables = new ArrayList<>();

        int iterator = upperBound / 100;
        if (upperBound / 100 != 0) iterator++;

        int tempLowerBound = 1;
        int tempUpperBound = 100;
        for (int i = 0; i < iterator; i++) {
            if (i == iterator -1 && upperBound / 100 != 0) tempUpperBound = upperBound % 100;

            callables.add(new CallableTask(tempLowerBound, tempUpperBound));

            tempLowerBound += 100;
            tempUpperBound += 100;

        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int sum = 0;

        try {
            List<Future<Integer>> results = executorService.invokeAll(callables);

            sum = results.stream()
                    .mapToInt(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException | ExecutionException exception) {
                            System.exit(-1);
                        }

                        return 0;
                    })
                    .sum();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Sum: " + sum);

        executorService.shutdown();
    }
}

