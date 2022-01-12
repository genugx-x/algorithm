package stackandqueue;

import java.util.*;

public class StackAndQueue1_5 {
    public static void main(String[] args) {
        System.out.println("result : " + Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30 ,5})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{99, 99, 99, 99, 99, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1, 2, 10, 100, 26, 100})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1, 2, 10, 100, 26, 100})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1, 2, 10, 100, 26, 100})));
        System.out.println();
        System.out.println("result : " + Arrays.toString(solution(new int[]{98, 99, 99, 98, 99, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> progressCompleteDays = getProgressCompleteDays(progresses, speeds);
        answer = getProgressCountsToDeploy(progressCompleteDays);
        return answer;
    }

    public static Queue<Integer> getProgressCompleteDays(int[] progresses, int[] speeds) {
        Queue<Integer> progressCompleteDays = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int progressCompleteDay = progresses[i];
            double speed = speeds[i];
            double calcDays = (100 - progressCompleteDay) / speed;
            progressCompleteDays.add((int) Math.ceil(calcDays));
        }
        System.out.println(progressCompleteDays.toString());
        return progressCompleteDays;
    }

    public static int[] getProgressCountsToDeploy(Queue<Integer> progressCompleteDays) {
        List<Integer> completedProgresses = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        while (!progressCompleteDays.isEmpty()) {
            if (progressCompleteDays.peek() > 0) { // 2, 1, 1, 2, 1, 1 -> 1, 0, 0, 1, 0, 0 -> 0, -1, -1, 0, -1, -1
                for (int i = 0; i < progressCompleteDays.size(); i++) {
                    progressCompleteDays.add(progressCompleteDays.poll() - 1);
                }
                if (completedProgresses.size() > 0) {
                    result.add(completedProgresses.size());
                    completedProgresses = new ArrayList<>();
                }
            } else {
                completedProgresses.add(progressCompleteDays.poll());
            }

            if (progressCompleteDays.size() == 0) {
                result.add(completedProgresses.size());
            }

        }
        return result
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}

