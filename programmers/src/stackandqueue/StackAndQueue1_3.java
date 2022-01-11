package stackandqueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueue1_3 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        System.out.println(stack.toString());
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.toString());
        System.out.println(stack.peek());

    }

}

