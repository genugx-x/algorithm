package stackandqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackAndQueue1_2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30 ,5})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{99, 99, 99, 99, 99, 99}, new int[]{1, 1, 1, 1, 1, 1})));
        System.out.println();
        System.out.println(Arrays.toString(solution(new int[]{99, 98, 87, 15, 75, 12}, new int[]{1,2, 10, 100, 26, 100})));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> progressCompleteDays = getProgressCompleteDays(progresses, speeds);
        answer = getProgressCountsToDeploy(progressCompleteDays);
        return answer;
    }

    public static List<Integer> getProgressCompleteDays(int[] progresses, int[] speeds) {
        List<Integer> progressCompleteDays = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            double speed = speeds[i];
            double tempDays = (100 - progress) / speed;
            System.out.println(tempDays);
            progressCompleteDays.add((int) Math.ceil(tempDays));
        }
        // System.out.println(progressCompleteDays.toString());
        return progressCompleteDays;
    }

    public static int[] getProgressCountsToDeploy(List<Integer> progressCompleteDays) {
        List<Integer> deployCounts = new ArrayList<>();
        List<Integer> tempList = null;
        for (int i = 0; i < progressCompleteDays.size(); i++) {
            int days = progressCompleteDays.get(i);
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(days);
                if (i == progressCompleteDays.size()-1) {
                    deployCounts.add(tempList.size());
                }
                continue;
            }

            if (tempList.get(tempList.size()-1) >= days) {
                tempList.add(days);
                if (i == progressCompleteDays.size()-1) {
                    deployCounts.add(tempList.size());
                }
            } else {
                deployCounts.add(tempList.size());
                tempList = null;
                i--;
            }
        }
        return deployCounts
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }


}
