import java.util.*;

public class Hash2 {

    public static void main(String[] args) {
        System.out.println(solution4(new String[]{"11", "3467568", "7958674", "87659897978", "119", "0089674223", "1195524421"}));
    }
    /*
        ["119", "97674223", "1195524421"]	false
        ["123","456","789"]	true
        ["12","123","1235","567","88"]	false

        [ ..., "119", ..., "119234234", ...]
     */
    // 효율성 테스트 3, 4번 테스트 실패 (시간 초과)
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
        Arrays.sort(phone_book);
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.addAll(Arrays.asList(phone_book));
        for (int i = 0; i < phone_book.length; i++) {
            int indexToRemove = -1;
            for (int j = 0; j < phoneNumbers.size(); j++) {
                if (phone_book[i].equals(phoneNumbers.get(j)))
                    indexToRemove = j;
                if (!phone_book[i].equals(phoneNumbers.get(j)) && phoneNumbers.get(j).startsWith(phone_book[i]))
                    return false;
            }
            phoneNumbers.remove(indexToRemove);
        }
        return true;
    }

    // 효율성 테스트4 실패 (시간 초과)
    private static boolean solution3(String[] phone_book) {

        Map<Integer, List<String>> phoneNumberMap = new HashMap<>();
        for (String phoneNumber: phone_book) {
            List<String> phoneNumbers = null;
            // 폰 번호의 길이에 따라 맵 셋팅 ( Integer(폰번호 길이), List<String>(Key값 길이가 동일한 번호 리스트) )
            if(null == phoneNumberMap.get(phoneNumber.length())) {
                phoneNumbers = new ArrayList<>();
                phoneNumbers.add(phoneNumber);
                phoneNumberMap.put(phoneNumber.length(), phoneNumbers);
            } else {
                phoneNumbers = phoneNumberMap.get(phoneNumber.length());
                phoneNumbers.add(phoneNumber);
            }
        }

        System.out.println(phoneNumberMap);

        for (int i = 1; i <= 20; i++) {
            List<String> phoneNumbers = phoneNumberMap.get(i);
            if (phoneNumbers != null) {
                for (int j = i+1; j <= 20; j++) {
                    List<String> phoneNumbersToCompare = phoneNumberMap.get(j);
                    if (phoneNumbersToCompare != null) {
                        for (String phoneNumber : phoneNumbers) {
                            for (String phoneNumberToCompare : phoneNumbersToCompare) {
                                System.out.print("Compare : " + phoneNumber + " " + phoneNumberToCompare);
                                if (!phoneNumberToCompare.equals(phoneNumber) && phoneNumberToCompare.startsWith(phoneNumber)) {
                                    System.out.println(" ★");
                                    return false;
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
        solution3번과는 다르게 번호의 길이가 아닌 번호의 시작 번호를 사용
     */
    private static boolean solution4(String[] phone_book) {
        HashMap<Integer, ? super HashMap<Integer, ?>> map = new HashMap<>();
        for (String phoneNumber: phone_book) {
            setNumber(map, phoneNumber);
        }
        //System.out.println(map);

        for(String phoneNumber: phone_book) {
            if(!getNumber(map, phoneNumber)) return false;
            //System.out.println();
        }
        return true;
    }

    public static void setNumber(HashMap<Integer, ? super HashMap<Integer, ?>> phoneBookMap, String phoneNumber) {
        HashMap<Integer, ? super HashMap<Integer, ?>> map = new HashMap<>();
        String startNumber = phoneNumber.substring(0, 1);
        String nextNumber = phoneNumber.substring(1);
        if (phoneBookMap.get(Integer.parseInt(startNumber)) == null) {
            phoneBookMap.put(Integer.parseInt(startNumber), map);
            if (nextNumber.length() == 0) {
                phoneBookMap.put(-1, map);
                return;
            }
        }
        setNumber(map, nextNumber);
    }

    // "1 1 9", -1
    // "1 1 9 5524421"
    // "97674223",
    public static boolean getNumber(HashMap<Integer, ? super HashMap<Integer, ?>> phoneBookMap, String phoneNumberToCompare) {
        String startNumber = phoneNumberToCompare.substring(0, 1);
        String nextNumber = phoneNumberToCompare.substring(1);
        // System.out.print(startNumber);
        HashMap map = (HashMap<Integer, ? super HashMap<Integer, ?>>) phoneBookMap.get(Integer.parseInt(startNumber));

        if (nextNumber.length() == 0) { // 다음 번호가 없는 경우
            return true;
        } else if (map == null) { // 현재 검색중인 번호인 경우?
            return true;
        } else if (phoneBookMap.get(-1) != null) { // 현재 검색중인 번호접두어인 경우. 번호 끝을 key값 -1로 구분
            return false;
        }

        return getNumber(map, nextNumber);
    }
}
