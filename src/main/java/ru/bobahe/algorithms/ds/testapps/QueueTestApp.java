package ru.bobahe.algorithms.ds.testapps;

import ru.bobahe.algorithms.ds.BasicQueue;
import ru.bobahe.algorithms.ds.Queue;

public class QueueTestApp {
    public static void main(String[] args) {
        Queue<Integer> queue = new BasicQueue<>(3);
        queue.push(1);
        queue.push(2);
        queue.push(3);

        queue.remove();
        queue.remove();

        queue.push(4);
        queue.remove();
        System.out.println(queue.peek());
        queue.remove();
        System.out.println(queue.peek());
    }
}
