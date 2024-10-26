package net.htlgkr.concurrency.firstassignment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final String CSVFILE = "numbers.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = readInFile();

        System.out.print("chunks> ");
        int chunks = Integer.parseInt(scanner.nextLine());
        System.out.print("divider> ");
        int divider = Integer.parseInt(scanner.nextLine());
        System.out.println();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        ArrayList<Runnable> tasks = new ArrayList<>();

        int numbersToCheck = numbers.size() / chunks;
        int currentNumber = 0;
        int upperBound = numbersToCheck;

        for (int i = 0; i <= chunks; i++) {

            List<Integer> tempNumbers = new ArrayList<>();

            for (int j = currentNumber; j < upperBound; j++) {
                tempNumbers.add(numbers.get(j));
            }

            tasks.add(new RunnableTask(tempNumbers, divider));

            currentNumber += numbersToCheck;
            upperBound += numbersToCheck;

            if (i == chunks - 1){
                currentNumber += numbers.size() - 1 - currentNumber;
                upperBound = numbers.size() - 1;
            }
        }

        tasks.forEach(executorService::execute);

        executorService.shutdown();

    }

    private static List<Integer> readInFile() {
        try {
            return Files.lines(new File(CSVFILE).toPath())
                    .flatMap(a -> Arrays.stream(a.split(":")))
                    .filter(s -> !s.isEmpty())
                    .filter(s -> {
                        try {
                            int n = Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            return false;
                        }
                        return true;
                    })
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            System.out.println("Error in readInFile");
            throw new RuntimeException(e);
        }
    }
}