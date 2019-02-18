package ru.bobahe.algorithms;

import ru.bobahe.algorithms.ds.BasicStack;
import ru.bobahe.algorithms.ds.Stack;

import java.util.Scanner;

public class ReverseStringApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;

        while (true){
            System.out.println("Введите строку для преобразования (q для выхода)");
            s = scanner.nextLine();

            if (s.equals("q"))
                break;

            System.out.println(reverseString(s));
        }
    }

    private static String reverseString(String s) {
        // подсмотрел на StackOverflow, как привести char[] к Character[] через Stream API
        Stack<Character> stack = new BasicStack<>(s.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
        StringBuilder result = new StringBuilder(s.length());

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
