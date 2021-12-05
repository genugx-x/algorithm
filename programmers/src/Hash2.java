import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hash2 {

    public static void main(String[] args) {
        System.out.println(solution2(new String[]{"119", "97674223", "1195524421"}));
    }
    /*
    ["119", "97674223", "1195524421"]	false
    ["123","456","789"]	true
    ["12","123","1235","567","88"]	false

    [ ..., "119", ..., "119234234", ...]

    1부터 9까지 시작하는 전화번호를 나누고
    0 시작
    1 시작
    2 시작
    ...
    9 시작

     */
    // 효율성 3, 4번 테스트 실패 (시간 초과)
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (String phoneNumber: phone_book) {
            for (String phoneNumberToCompare: phone_book) {
                if (!phoneNumber.equals(phoneNumberToCompare) && phoneNumber.startsWith(phoneNumberToCompare)) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean solution2(String[] phone_book) {
        Map<Integer, String> map = new HashMap<>();
        for (String phoneNumber: phone_book) {
            String startNumber = phoneNumber.substring(0, 1);
            switch (Integer.parseInt(startNumber)) {
                case 0: map.put(0, phoneNumber); break;
                case 1: map.put(1, phoneNumber); break;
                case 2: map.put(2, phoneNumber); break;
                case 3: map.put(3, phoneNumber); break;
                case 4: map.put(4, phoneNumber); break;
                case 5: map.put(5, phoneNumber); break;
                case 6: map.put(6, phoneNumber); break;
                case 7: map.put(7, phoneNumber); break;
                case 8: map.put(8, phoneNumber); break;
                case 9: map.put(9, phoneNumber); break;
            }
        }
        for (String phoneNumber: phone_book) {
            String startNumber = phoneNumber.substring(0, 1);
            String phoneNumberToCompare = map.get(Integer.parseInt(startNumber));
            if (!phoneNumber.equals(phoneNumberToCompare) && phoneNumber.startsWith(phoneNumberToCompare)) {
                return false;
            }
        }
        return true;
    }
}
