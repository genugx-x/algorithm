package Hash;

import java.util.*;

// 문제: 위장
public class Hash3 {
    /*
        clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
        스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
        같은 이름을 가진 의상은 존재하지 않습니다.
        clothes의 모든 원소는 문자열로 이루어져 있습니다.
        모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
        스파이는 하루에 최소 한 개의 의상은 입습니다.
     */
    public static void main(String[] args) {
        // [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]     5
        // [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]                 3

        // solution(new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}});


        System.out.println(solution(new String[][]{
                {"w-a", "watch"}, {"w-b", "watch"}, {"w-c", "watch"}, {"w-d", "watch"},
                {"s-a","shoes"}, {"s-b","shoes"}, {"s-c","shoes"}, {"s-d","shoes"}, {"s-e","shoes"}, {"s-f","shoes"}, {"s-g","shoes"},
                {"h-a", "headgear"},
                {"e-a", "eyewear"}, {"e-b", "eyewear"}, {"e-c", "eyewear"}, {"e-d", "eyewear"}, {"e-e", "eyewear"},
                {"h-b", "headgear"},
                {"1", "number"}, {"3", "number"}, {"4", "number"}, {"5", "number"}, {"6", "number"}, {"7", "number"}, {"8", "number"}, {"9", "number"},
                {"a", "alphabet"}, {"b", "alphabet"}, {"c", "alphabet"}, {"d", "alphabet"}, {"e", "alphabet"}, {"f", "alphabet"}, {"g", "alphabet"}, {"h", "alphabet"},
                {"ⅰ", "roma"},
                {"ⅱ", "roma"},
                {"ㄱ", "korean"},
                {"ㄴ", "korean"}}));


        System.out.println(solution(new String[][]{
                {"1", "number"},
                {"2", "number"},
                {"a", "alphabet"},
                {"b", "alphabet"},
                {"ⅰ", "roma"},
                {"ⅱ", "roma"},
                {"ㄱ", "korean"},
                {"ㄴ", "korean"}}
        ));

        /*
        System.out.println(solution(new String[][]{
                {"w-a", "A"},
                {"s-a", "B"}}
                // {"h-a", "C"}}
                // {"e-a", "D"}}
        ));
         */

/*        System.out.println(solution(new String[][]{
                {"1", "number"},
                {"2", "number"},
                {"a", "alphabet"},
                {"b", "alphabet"},
                {"ⅰ", "roma"},
                {"ⅱ", "roma"}}
        ));*/

    }

    public static int solution(String[][] clothes) {
        Map<String, ArrayList<String>> clothesMap = new HashMap<>();
        ArrayList<String> cTypes = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (String[] clothesInner: clothes) {
            String cname = clothesInner[0];
            String ctype = clothesInner[1];
            ArrayList<String> cnames = (ArrayList<String>) clothesMap.get(ctype);
            if (cnames == null) {
                ArrayList<String> cnamesToAdd = new ArrayList<>();
                cnamesToAdd.add(cname);
                clothesMap.put(ctype, cnamesToAdd);
                cTypes.add(ctype);
            } else {
                cnames.add(cname);
            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("map setting time : " + (endTime - startTime) + "(ms)");
        System.out.println(clothesMap);

        startTime = System.currentTimeMillis();
        // int fittingCount = countFitting(null, cTypes, clothesMap);
        int fittingCount = countFitting(cTypes,clothesMap);
        endTime = System.currentTimeMillis();
        System.out.println("counting time : " + (endTime - startTime) + "(ms)");
        return fittingCount;
    }

    public static int countFitting(ArrayList<String> types, Map<String, ArrayList<String>> clothesMap) {
        int fittingCount = 0;
        for (int i = 0;i < types.size(); i++) {
            int cNamesSize = clothesMap.get(types.get(i)).size();
            if (i > 0) {
                fittingCount += (cNamesSize * fittingCount);
            }
            fittingCount += cNamesSize;
        }
        return fittingCount;
    }

}
