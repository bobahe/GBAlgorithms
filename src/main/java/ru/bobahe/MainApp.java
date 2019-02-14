package ru.bobahe;

import java.util.Random;

public class MainApp {

    enum Sort {
        BUBBLE,
        SELECTION,
        INSERTION
    }

    private static final int DEFAULT_ARRAY_LENGTH = 100_000;
    private static final int DEFAULT_NUMBER_OF_TESTS = 5;
    private static final int RESULTS_ARRAY_LENGTH = DEFAULT_NUMBER_OF_TESTS * 3;

    private static BasicList<Integer> list;

    public static void main(String[] args) {
        double[] tests = new double[RESULTS_ARRAY_LENGTH];
        Integer[] array = new Integer[DEFAULT_ARRAY_LENGTH];

        for (int i = 0; i < RESULTS_ARRAY_LENGTH; i++) {
            switch (i % 3) {
                case 0:
                    fillArray(array);
                    tests[i] = fillAndSortList(array, Sort.BUBBLE);
                    break;
                case 1:
                    tests[i] = fillAndSortList(array, Sort.SELECTION);
                    break;
                case 2:
                    tests[i] = fillAndSortList(array, Sort.INSERTION);
                    break;
                default:
                    break;
            }
        }

        printResults(tests);
    }

    private static void printResults(double[] tests) {
        StringBuilder sbResults = new StringBuilder();
        double avgBubble = 0;
        double avgSelection = 0;
        double avgInsertion = 0;

        sbResults
                .append("+------------+------------+------------+").append(System.lineSeparator())
                .append("|   Bubble   |  Selection |  Insertion |").append(System.lineSeparator())
                .append("+------------+------------+------------+").append(System.lineSeparator());

        for (int i = 0; i < RESULTS_ARRAY_LENGTH; i++) {
            sbResults.append(String.format("| %10.3f ", tests[i]));

            if (i % 3 == 0) {
                avgBubble += tests[i];
            }
            if (i % 3 == 1) {
                avgSelection += tests[i];
            }
            if (i % 3 == 2) {
                avgInsertion += tests[i];

                sbResults
                        .append("|")
                        .append(System.lineSeparator())
                        .append("+------------+------------+------------+").append(System.lineSeparator());
            }
        }

        sbResults
                .append("+------------+------------+------------+").append(System.lineSeparator())
                .append("                Average                 ").append(System.lineSeparator())
                .append("+------------+------------+------------+").append(System.lineSeparator())
                .append(String.format("| %10.3f | %10.3f | %10.3f |",
                        avgBubble / DEFAULT_NUMBER_OF_TESTS,
                        avgSelection / DEFAULT_NUMBER_OF_TESTS,
                        avgInsertion / DEFAULT_NUMBER_OF_TESTS))
                .append(System.lineSeparator()).append("+------------+------------+------------+");


        System.out.println(sbResults.toString());
    }

    private static double fillAndSortList(Integer[] array, Sort sort) {
        list = new BasicArrayList<>(array);

        long startTime = System.currentTimeMillis();

        switch (sort) {
            case BUBBLE:
                list.sortBubble();
                break;
            case SELECTION:
                list.sortSelect();
                break;
            case INSERTION:
                list.sortInsert();
                break;
        }

        return (System.currentTimeMillis() - startTime) / 1000d;
    }

    private static void fillArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (new Random().nextInt(DEFAULT_ARRAY_LENGTH) & 0x7fffffffL);
        }
    }
}
