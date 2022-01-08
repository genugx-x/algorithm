package Hash;

import java.util.Arrays;

public class Hash1 {
    public static void main(String[] args) {

    }
    // 완주하지 못한 자
    class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            Arrays.sort(participant);
            Arrays.sort(completion);
            for (int i = 0; i < participant.length; i++) {
                if (participant[i].equals(completion[i])) {
                    if (i == completion.length-1) {
                        answer = participant[i + 1];
                        break;
                    }
                } else {
                    answer = participant[i];
                    break;
                }
            }
            return answer;
        }
    }
}
