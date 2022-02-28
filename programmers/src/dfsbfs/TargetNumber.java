package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class TargetNumber {
    public static void main(String[] args) {
        int answer = solution(new int[]{1,1,1,1,1}, 3); // answer = 5
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        int count = numbers.length-1;
        Queue<Integer> queue = new LinkedList<>();
        for (int number : numbers) {
            queue.add(number);
        }
        answer = calc(queue, target);
        return answer;
    }

    public static int calc(Queue<Integer> q, int t) {
        int result = 0;
        Integer number = null;
        if (!q.isEmpty()) {
            Queue<Integer> plusQ = new LinkedList<Integer>();
            Queue<Integer> minusQ = new LinkedList<Integer>();
            plusQ.addAll(q);
            minusQ.addAll(q);
            number = q.poll();
            result += calc(plusQ, t + (number));
            result += calc(minusQ, t + (number * -1));
        } else {
            if (t + number == 0) result = 1;
        }
        return result;
    }
}
