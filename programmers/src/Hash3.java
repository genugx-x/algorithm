import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        solution(new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}});
    /*
        System.out.println(solution(new String[][]{
                {"a", "watch"}, {"b", "watch"}, {"c", "watch"}, {"d", "watch"},
                {"a","shoes"}, {"b","shoes"}, {"c","shoes"}, {"d","shoes"}, {"e","shoes"}, {"f","shoes"}, {"g","shoes"},
                {"yellowhat", "headgear"},
                {"bluesunglasses", "eyewear"}, {"a", "eyewear"}, {"b", "eyewear"}, {"c", "eyewear"}, {"d", "eyewear"}, {"e", "eyewear"},
                {"green_turban", "headgear"}}));

     */

    }

    public static int solution(String[][] clothes) {
        List<String> typesOfCostume = new ArrayList<>();
        List<String> costumeNames = null;
        HashMap<String, List<String>> clothesMap = new HashMap<>();
        for (String[] wear: clothes) {
            String costumeName = wear[0]; // 의상의 이름
            String typeOfCostume = wear[1]; // 의상의 종류
            boolean isExistType = false;
            for (String typeOfCostumeInnerList: typesOfCostume) {
                if(typeOfCostume.equals(typeOfCostumeInnerList)) {
                    isExistType = true;
                }
            }
            if (!isExistType) {
               typesOfCostume.add(typeOfCostume);
            }
            costumeNames = clothesMap.get(typeOfCostume);
            if (costumeNames == null) {
                costumeNames = new ArrayList<>();
                clothesMap.put(typeOfCostume, costumeNames);
            }
            costumeNames.add(costumeName);
        }
        System.out.println(clothesMap);
        System.out.println(typesOfCostume);

        //위장 의상 입히기
        // clothesMap : 모든 의상 분류한 맵
        // typesOfCostume : 의상 종류 리스트

        // 1. 의상의 종류 리스트에서 한 종류를 기준으로 핏팅 조합 수를 구하고 기준이 된 종류는 리스트 및 맵에서 제거 - 이유: 한번 기준이 된 의상으로 구할 수 있는 경우의 수를 이미 다 구한 상태기 때문에 중복 핏팅 수 제거
        // 2. 1번과 같은 방식으로 나머지 타입의 경우의 수를 핏팅 수에 + 한다.
//        List<String> usedTypesOfCostume = new ArrayList<>(); // 기준으로 사용했던 의상종류 리스트
//        boolean isUsed = false;
//        for (String typeOfCostume: typesOfCostume) { // 기준이 되는 의상종류
//            usedTypesOfCostume.add(typeOfCostume);
//            for (String typeOfCostumeForFitting: typesOfCostume) {
//                if(usedTypesOfCostume.contains(typeOfCostumeForFitting)) { // 핏팅에 사용할 타입(typeOfCostumeForFitting)이 기준으로 사용한 의상 종류 리스트에 포함된 경우 제외
//                    continue;
//                }
//                // 핏팅 조합 수를 구하기
//                // typeOfCostume 기준
//                // typeOfCostumeForFitting 조합용
//
//            }
//        }

        List<String> usedTypesOfCostume = new ArrayList<>(); // 기준으로 사용했던 의상종류 리스트
        List<String> typesOfCostumeForFitting = null;
        int fittingCount = 0;
        for (String typeOfCostume: typesOfCostume) { // 기준이 되는 의상종류
            System.out.println("typesOfCostume -> " + typesOfCostume);
            String standardType = typeOfCostume;
            usedTypesOfCostume.add(standardType);
            typesOfCostumeForFitting = new ArrayList<>();
            for (String typeOfCostumeForFitting: typesOfCostume) {
                if(standardType.equals(typeOfCostumeForFitting)) { // 핏팅에 사용할 타입(typeOfCostumeForFitting)이 기준으로 사용한 의상 종류 리스트에 포함된 경우 제외
                    continue;
                }
                typesOfCostumeForFitting.add(typeOfCostumeForFitting);

            }
            System.out.println();
            fittingCount = getFittingCount(fittingCount, standardType, clothesMap, typesOfCostumeForFitting);
                // 핏팅 조합 수를 구하기
                // typeOfCostume 기준
                // typeOfCostumeForFitting 조합용
            clothesMap.remove(standardType);
            typesOfCostume.remove(standardType);
            System.out.println("clothesMap -> " + clothesMap);
            System.out.println("typesOfCostume -> " + typesOfCostume);
        }
        // [watch, shoes, headgear, eyewear]
        // w : a, b, c, d
        // w s : a, b, c, d
        // w h
        // w e
        // w s e
        // w h e
        // w s h e
        // watch 삭제
        return fittingCount;
    }

    public static int getFittingCount(int fittingCount, String standardType, HashMap<String, List<String>> clothesMap, List<String> typesOfCostumeForFitting) {
        List<String> standardClothesNames = clothesMap.get(standardType);
        for (String standardClothesName: standardClothesNames) {
            fittingCount++;
            System.out.print(fittingCount + ". ");
            System.out.print(standardClothesName);
            // System.out.println(standardType + "* [" + standardClothesName + "] ");
            for (int i = 1; i <= typesOfCostumeForFitting.size(); i++) {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < typesOfCostumeForFitting.size(); k++) {
                        String typeOfCostumeForFitting = typesOfCostumeForFitting.get(k);
                        List<String> clothesNamesForFitting = clothesMap.get(typeOfCostumeForFitting);
                        System.out.println();
                        for (String clothesNameForFitting: clothesNamesForFitting) {
                            fittingCount++;
                            System.out.print(fittingCount + ". " + standardClothesName + " + " + clothesNameForFitting);
                        }
                    }
                }
            }
            System.out.println();
        }
        return fittingCount;
    }

}
