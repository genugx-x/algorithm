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

        answer = calc(queue, target, count);


        return answer;
    }

    public static int calc(Queue<Integer> q, int t, int c) {
        int result = 0;
        Integer number = null;
        if (!q.isEmpty().size() > 0) {
            number = q.poll();
            if (c > 0) {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        // +
                        number *= 1;
                    } else {
                        // -
                        number *= -1;
                    }
                    result += calc(q, t + number, --c);
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        // +
                        number *= 1;
                    } else {
                        // -
                        number *= -1;
                    }
                    if (t + number == 0) {
                        result = 1;
                    }
                }
            }
        }
        return result;
    }
}
