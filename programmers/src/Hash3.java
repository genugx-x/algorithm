import java.sql.Types;
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
        /*
        System.out.println(solution(new String[][]{
                {"w-a", "watch"}, {"w-b", "watch"}, {"w-c", "watch"}, {"w-d", "watch"},
                {"s-a","shoes"}, {"s-b","shoes"}, {"s-c","shoes"}, {"s-d","shoes"}, {"s-e","shoes"}, {"s-f","shoes"}, {"s-g","shoes"},
                {"h-a", "headgear"},
                {"e-a", "eyewear"}, {"e-b", "eyewear"}, {"e-c", "eyewear"}, {"e-d", "eyewear"}, {"e-e", "eyewear"},
                {"h-b", "headgear"}}));

         */

        /*
        System.out.println(solution(new String[][]{
                {"w-a", "watch"},
                {"s-a", "shoes"},
                {"h-a", "headgear"},
                {"e-a", "eyewear"}}
        ));
         */

        /*
        System.out.println(solution(new String[][]{
                {"w-a", "A"},
                {"s-a", "B"}}
                // {"h-a", "C"}}
                // {"e-a", "D"}}
        ));
         */


        System.out.println(solution2(new String[][]{
                {"1", "number"},
                {"2", "number"},
                {"a", "alphabet"},
                {"b", "alphabet"}}
        ));

        // System.out.println(getFittingCount3(null));

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

        System.out.println("=========================");
        System.out.println("피팅 수 구하기");
        int fittingCount = 0;
        fittingCount = getFittingCount2(fittingCount, clothesMap, typesOfCostume);


        return fittingCount;
    }

    public static int getFittingCount(int fittingCount, String standardType, HashMap<String, List<String>> clothesMap, List<String> typesOfCostumeForFitting) {
        List<String> standardClothesNames = clothesMap.get(standardType);
        clothesMap.remove(standardType);
        System.out.println("standardType = [" + standardType + "]");
        for (String standardClothesName: standardClothesNames) {
            fittingCount++;
            System.out.print(fittingCount + ". ");
            System.out.print(standardClothesName);
            // System.out.println(standardType + "* [" + standardClothesName + "] ");
            System.out.println();
            for (int i = 1; i <= typesOfCostumeForFitting.size(); i++) {
                // i가 1이면 기준의상타입 + A or B or C ...
                // i가 2면 기준의상타입 + A + B or A + C or B + C or ....
                // 1가 3면 기준의상타입 + A + B + C ...
                fittingCount++;
                // System.out.print(fittingCount + ". " + standardClothesName + " + " + clothesNameForFitting);
                System.out.println();


                for (int j = 0; j < i; j++) {
                    if (i == 1) {
                        for (int k = 0; k < typesOfCostumeForFitting.size(); k++) {
                            String typeOfCostumeForFitting = typesOfCostumeForFitting.get(k);
                            List<String> clothesNamesForFitting = clothesMap.get(typeOfCostumeForFitting);
                            for (String clothesNameForFitting : clothesNamesForFitting) {
                                fittingCount++;
                                System.out.print(fittingCount + ". " + standardClothesName + " + " + clothesNameForFitting);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        return fittingCount;
    }

    // A*
    // B*
    // C*
    // D*

    // A*
    // A* + B
    // A* + B + C
    // A* + B + C + D
    // A* + C
    // A* + C + D
    // A* + D

    // A*
    // A* + B
    // A* + B + C
    // A* + C
    // B*
    // B* + C
    // C*

    // A*
    // A* + B
    // B*
    public static int getFittingCount2(int fittingCount, HashMap<String, List<String>> clothesMap, List<String> types) {
        String type = null;
        if(types.size() > 0) {
            type = types.get(0);
            List<String> fixedType = null;
            for (int i = 0; i < types.size(); ) {
                List<String> nextTypes = null;
                if (type.equals(types.get(i))) {
                    fixedType = clothesMap.get(type);
                    i++;
                    continue;
                }
                for (int j = 2; j <= types.size(); j++) {
                    nextTypes = new ArrayList<>();
                    nextTypes.addAll(types.subList(1, j));
                }
                List<List<String>> typesToFitting = new ArrayList<>();
                typesToFitting.add(fixedType);
                for(String nextType: nextTypes)
                    typesToFitting.add(clothesMap.get(nextType));
                // fittingCount += getFittingCount3(fittingCount, typesToFitting);
                types.remove(types.get(0));
                System.out.println();
            }

            System.out.println();
        }

        return fittingCount;
    }

    public static int getFittingCount3(List<List<String>> typesToFitting) {
        List<List<String>> typesToFittingtemp = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("a");
        temp.add("b");
        typesToFittingtemp.add(temp);
        temp = new ArrayList<>();
        temp.add("1");
        temp.add("2");
        typesToFittingtemp.add(temp);
        int fittingCount = 0;
        for (int i = 0; i < typesToFittingtemp.size(); ) {
            if (typesToFittingtemp.size() > 0) {
                for (int j = 1; j < typesToFittingtemp.size(); j++) {
                    for (int k = 0; k < typesToFittingtemp.get(j).size(); k++) {
                    }
                }
            }
            System.out.println();
            typesToFittingtemp.remove(i);
        }
        return fittingCount;
    }

    // A: a, b
    // B: 1, 2
    // a
    // a, 1
    // a, 2
    // b,
    // b, 1
    // b, 2
    public static int getFittingCount5(List<List<String>> typesToFitting) {
        for (int i = 0; i < typesToFitting.size(); i++) {
            // typesToFitting.
        }
        return 0;
    }


    // A: a, b, c
    // B: 1, 2, 3
    // C: !, @, #

    // A: a, b, c

    // a, 1
    // a, 1, !
    // a, 1, @
    // a, 1, #
    // a, 2
    // a, 2, !
    // a, 2, @
    // a, 2, #
    // a, 3
    // a, 3, !
    // a, 3, @
    // a, 3, #
    // b
    // b, 1
    // b, 1, !
    // b, 1, @
    // b, 1, #
    // b, 2
    // b, 2, !
    // b, 2, @
    // b, 2, #
    // b, 3
    // b, 3, !
    // b, 3, @
    // b, 3, #
    // b
    // b, 1
    // b, 1, !
    // b, 1, @
    // b, 1, #
    // b, 2
    // b, 2, !
    // b, 2, @
    // b, 2, #
    // b, 3
    // b, 3, !
    // b, 3, @
    // b, 3, #

    // ...
    // c
    // c, 3
    // c, 3, !


    // A*
    // A* + B
    // B*
    public static int getFittingCount4(int fittingCount, HashMap<String, List<String>> clothesMap, List<String> types) {
        String type = null;
        List<List<String>> typesToFitting = null;
        for (int i = 0; i < types.size(); ) {
            typesToFitting = new ArrayList<>();
            for (int j = 0; j < types.size(); j++) {
                type = types.get(j);
                typesToFitting.add(clothesMap.get(type));
                fittingCount += getFittingCount3(typesToFitting);
            }
            types.remove(types.get(0));
        }
        System.out.println();

        return fittingCount;
    }


    // a, b
    // 1, 2
    // !, @
    public static int solution2(String[][] clothes) {
        Map<String, List<String>> clothesMap = new HashMap<>();
        List<String> cTypes = new ArrayList<>();
        for (String[] clothesInner: clothes) {
            String cname = clothesInner[0];
            String ctype = clothesInner[1];
            List<String> cnames = clothesMap.get(ctype);
            if (cnames == null) {
                List<String> cnamesToAdd = new ArrayList<>();
                cnamesToAdd.add(cname);
                clothesMap.put(ctype, cnamesToAdd);
                cTypes.add(ctype);
            } else {
                cnames.add(cname);
            }

        }
        System.out.println(clothesMap);
        System.out.println();

        // 구조
        HashMap<String, ? super HashMap<String, ?>> costumesMap = solution2nextStep(cTypes, clothesMap, null);
        System.out.println(costumesMap);

        return 0;
    }

    public static HashMap<String, ? super HashMap<String, ?>> solution2nextStep(List<String> types, Map<String, List<String>> clothesMap, HashMap<String, ? super HashMap<String, ?>> costumesMap) {
        // types : List<String>
        List<String> subTypes = new ArrayList<>();
        if (types.size() > 1) {
            for (int i = 1; i < types.size(); i++) {
                subTypes.add(types.get(i));
            }
        }
        // System.out.println(subTypes);
        if (costumesMap == null) {
            costumesMap = new HashMap<>();
        }
        for (int i = 0; i < types.size();) {
            String type = types.get(0);
            List<String> cNames = clothesMap.get(type);
            HashMap<String, ? super HashMap<String, ?>> innerCostumesMap = null;
            for (String cName: cNames) {
                System.out.print(cName + " " + subTypes);
                innerCostumesMap = new HashMap<>();
                costumesMap.put(cName, innerCostumesMap);
                solution2nextStep(subTypes, clothesMap, innerCostumesMap);
            }
            types.remove(type);
        }

        return costumesMap;
    }

}
