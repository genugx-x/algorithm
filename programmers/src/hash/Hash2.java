package hash;

import java.util.*;

public class Hash2 {

    public static void main(String[] args) {
        System.out.println(solution4(new String[]{"12", "13", "14"}));
        System.out.println(solution4(new String[]{"11", "3467568", "7958674", "87659897978", "123", "456", "789", "119", "0089674223", "1195524421"}));
        System.out.println(solution4(new String[]{"123", "456", "789"}));
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
        // Map<Integer, HashMap<Integer, ? super HashMap<Integer, ?>>> map = null;
        /*
        for (String phoneNumber: phone_book) {
            List<String> phoneNumbers = null;
            // 폰 번호의 길이에 따라 맵 셋팅 ( Integer(폰번호 길이), List<String>(Key값 길이가 동일한 번호 리스트) )
            if (null == phoneNumberMap.get(phoneNumber.length())) {
                map = new HashMap<>();
                phoneNumberMap.put(phoneNumber.length(), map);
            } else {
                phoneNumbers.add(phoneNumber);
            }
        }

         */

        // System.out.println(phoneNumberMap);

        HashMap<Integer, ? super HashMap<Integer, ?>> map = new HashMap<>();
//        for (int i = 1; i <= 20; i++) {
//            List<String> phoneNumbers = phoneNumberMap.get(i);
//            if (phoneNumbers != null) {
//                for (String phoneNumber : phoneNumbers) {
//                    setNumber(map, phoneNumber);
//                }
//            }
//        }

        for (String phoneNumber: phone_book) {
            setNumber(map, phoneNumber);
        }
        System.out.println(map);
        return getNumber(map);


        // System.out.println(map);
        
//        for (int i = 1; i <= 20; i++) {
//            List<String> phoneNumbers = phoneNumberMap.get(i);
//            if (phoneNumbers != null) {
//                for (String phoneNumber : phoneNumbers) {
//                    if(!getNumber(map, phoneNumber)) return false;
//                }
//            }
//        }

        // System.out.println(map);
        // return true;
    }

    public static void setNumber(HashMap<Integer, ? super HashMap<Integer, ?>> phoneBookMap, String phoneNumber) {
        HashMap<Integer, ? super HashMap<Integer, ?>> map = new HashMap<>();
        String startNumber = phoneNumber.substring(0, 1);
        String nextNumber = phoneNumber.substring(1);

        HashMap innerPhoneBookMap = (HashMap) phoneBookMap.get(Integer.parseInt(startNumber));
        if (innerPhoneBookMap == null) {
            phoneBookMap.put(Integer.parseInt(startNumber), map);
            if (nextNumber.length() == 0) {
                innerPhoneBookMap = (HashMap) phoneBookMap.get(Integer.parseInt(startNumber));
                innerPhoneBookMap.put(-1, map);
                return;
            }
            setNumber(map, nextNumber);
        } else {
            setNumber(innerPhoneBookMap, nextNumber);
        }
    }

    // "1 1 9 34535235524421"
    // "1 1 8 6 7235235235" ,
    // "1 1 9 "
    // "1 1 8 34537658
    // "1 2 2 412532463547"
    // "97674223";
    public static boolean getNumber(HashMap<Integer, ? super HashMap<Integer, ?>> phoneBookMap, String phoneNumberToCompare) {
        String startNumber = (phoneNumberToCompare.length() == 1) ? phoneNumberToCompare : phoneNumberToCompare.substring(0, 1);
        String nextNumber = phoneNumberToCompare.substring(1);
        //System.out.print(startNumber);
        HashMap map = (HashMap<Integer, ? super HashMap<Integer, ?>>) phoneBookMap.get(Integer.parseInt(startNumber));

        if (nextNumber.length() == 0) { // 자신의 숫자 비교시
            return true;
        } else if (map.get(-1) != null) { // 현재 검색중인 번호접두어인 경우. 번호 끝을 key값 -1로 구분
            return false;
        } else if (map == null) { // 현재 startNumber로 검색한 Map의 다음 번호가 없는경우 이미 접두어에 해당 안됨
            return true;
        }
        return getNumber(map, nextNumber);
    }

    // 1자리 ↓
    // 2자리
    // 3자리

    // "1 1 9 "

    // "1 1 8 3 4 5 37658
    // "1 1 8 6 7 2 3 5 2 3 5235" ,
    // "1 2 3 4"
    // "1 1 9 34535235524421"
    // "97674223";
    // 여기까지 정확성 테스트 14번 실패
    public static boolean getNumber(HashMap<Integer, ? super HashMap<Integer, ?>> phoneBookMap) {
        boolean result = true;
        HashMap map = null;
        for (int i = 0; i < 10; i++) {
            map = (HashMap<Integer, ? super HashMap<Integer, ?>>) phoneBookMap.get(i);
            if (map != null) {
                if (map.get(-1) != null && map.size() == 1)
                    result = true;
                else if (map.get(-1) != null) {
                    result = false;
                    break;
                }
                if (!getNumber(map)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
