package stackandqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackAndQueue1_5 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30 ,5})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{99, 99, 99, 99, 99, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1, 2, 10, 100, 26, 100})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1, 2, 10, 100, 26, 100})));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Stack<Integer> progressCompleteDays = getProgressCompleteDays(progresses, speeds);
        answer = getProgressCountsToDeploy(progressCompleteDays);
        return answer;
    }

    public static Stack<Integer> getProgressCompleteDays(int[] progresses, int[] speeds) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            double speed = speeds[i];
            double tempDays = (100 - progress) / speed;
            stack.push((int) Math.ceil(tempDays));
        }
        System.out.println(stack.toString());
        return stack;
    }

    public static int[] getProgressCountsToDeploy(Stack<Integer> progressCompleteDays) {
        int day = 0;
        while (!progressCompleteDays.empty()) {
            day = progressCompleteDays.peek();
            progressCompleteDays.pop();
        }
        return null;
    }

}

