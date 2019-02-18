package ru.bobahe.algorithms.ds.testapps;

import ru.bobahe.algorithms.ds.BasicStack;
import ru.bobahe.algorithms.ds.Stack;

public class StackTestApp {
    public static void main(String[] args) {
        String s = "Леша на полке клопа нашел";
        Stack<Character> stack = new BasicStack<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
