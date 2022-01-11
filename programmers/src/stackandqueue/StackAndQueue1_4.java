package stackandqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueue1_4 {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println(queue.toString());
        System.out.println(queue.peek());
        int value = queue.poll();
        System.out.println(queue.toString() +  " " + value);
    }

}

