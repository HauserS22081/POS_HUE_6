package net.htlgkr.concurrency.thirdassignment;

import java.util.Arrays;
import java.util.List;

public class JavaStreamsTester {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "hello", "xyz", "", "java", "fun");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Count of empty strings: " + getCountEmptyString(strings));
        System.out.println("Count of strings with length 3: " + getCountLength3(strings));
        System.out.println("List after deleting empty strings: " + deleteEmptyStrings(strings));
        System.out.println("Merged string with separator ', ': " + getMergedString(strings, ", "));
        System.out.println("Squares of numbers: " + getSquares(numbers));
        System.out.println("Maximum number: " + getMax(numbers));
        System.out.println("Minimum number: " + getMin(numbers));
        System.out.println("Sum of numbers: " + getSum(numbers));
        System.out.println("Average of numbers: " + getAverage(numbers));
    }

    private static int getCountEmptyString(List<String> strings) {
        return strings.stream()
                .filter(String::isEmpty)
                .mapToInt(s -> 1)
                .sum();
    }

    private static int getCountLength3(List<String> strings) {
        return strings.stream()
                .filter(s -> s.length() == 3)
                .mapToInt(s -> 1)
                .sum();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private static String getMergedString(List<String> strings, String separator) {
        return strings.stream()
                .reduce("", (s1, s2) -> s1 + separator + s2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * n)
                .toList();
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n)
                .max()
                .orElse(-1);
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n)
                .min()
                .orElse(-1);
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n)
                .sum();
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream()
                .mapToInt(n -> n)
                .average()
                .orElse(-1);
    }



}
