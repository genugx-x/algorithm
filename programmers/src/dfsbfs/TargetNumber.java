package dfsbfs;

import java.util.ArrayList;
import java.util.List;

public class TargetNumber {
    public static void main(String[] args) {
        int answer = solution(new int[]{1,1,1,1,1}, 3); // answer = 5
        System.out.println("1.answer : " + answer);

        answer = solution(new int[]{4, 1, 2, 1}, 4); // answer = 2
        System.out.println("2.answer : " + answer);

    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        answer = calc(numbers, target, 0);
        return answer;
    }

    public static int calc(int[] numbers, int target, int index) {
        int result = 0;
        int number = 0;
        if (index < numbers.length) {
            number = numbers[index];
            index++;
            result += calc(numbers, target + (number), index);
            result += calc(numbers, target + (number * -1), index);
        } else {
            if (target + number == 0) result = 1;
        }
        return result;
    }

}
