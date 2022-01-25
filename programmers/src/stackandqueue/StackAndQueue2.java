package stackandqueue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class StackAndQueue2 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int[] priorities3 = {2, 1, 9, 2, 3, 4, 6, 3, 5, 2, 6, 1, 7, 8, 6, 5};
        solution(priorities, 2); // result 1
        solution(priorities2, 0); // result 5
        solution(priorities3, 4); // result 5

    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int priority : priorities) {
            queue.add(priority);
        }
        while (true) {
            int priority = queue.poll();

        }

        return answer;
    }
}
