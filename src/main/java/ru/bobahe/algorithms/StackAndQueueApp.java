package ru.bobahe.algorithms;

import ru.bobahe.algorithms.ds.Queue;
import ru.bobahe.algorithms.ds.Stack;
import ru.bobahe.algorithms.ds.BasicLinkedQueue;
import ru.bobahe.algorithms.ds.BasicLinkedStack;

public class StackAndQueueApp {
    public static void main(String[] args) {
        Stack<Integer> stack = new BasicLinkedStack<>();
        Queue<Integer> queue = new BasicLinkedQueue<>();

        System.out.println("=== Testing stack based on linked list ===");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        while(!stack.isEmpty()) {
            System.out.println(stack.pop() + " (stack size = " + stack.size() + ")");
        }

        System.out.println();
        System.out.println("=== Testing queue based on linked list ===");
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove() + " (queue size = " + queue.size() + ")");
        }
    }
}
