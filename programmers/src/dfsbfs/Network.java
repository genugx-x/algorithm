package dfsbfs;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    public static void main(String[] args) {
        System.out.println("1.answer = " + solution3(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // answer = 2
        System.out.println();
        System.out.println("2.answer = " + solution3(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}})); // answer = 1
        System.out.println();
        System.out.println("3.answer = " + solution3(4, new int[][] {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}})); // answer = 2
        System.out.println();
        System.out.println("4.answer = " + solution3(3, new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}})); // answer = 1
    }


    // (1) 1* 1
    // 1 (1) 1
    // 1 1 (1)
    static int solution(int n, int[][] computers) {
        int answer = n;
        for (int i = 0; i < n; i++) {
            for (int t = i+1; t < n; t++) {
                if (computers[i][t] == 1 && computers[t][i] == 1) { // 연결됨
                    boolean isAlreadyOneNetwork = isOneNetwork(computers, t, i, t);
                    if (!isAlreadyOneNetwork)
                        answer--;
                }
            }
        }
        return answer;
    }

    static int solution2(int n, int[][] computers) {
        List<List<Integer>> networks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int computer = i;
            List<Integer> network = networks.stream()
                    .filter(net -> net.parallelStream()
                            .anyMatch(com -> com == computer))
                    .findFirst()
                    .orElse(null);
            if (network == null) {
                network = new ArrayList<>();
                networks.add(network);
            }
            for (int t = 0; t < n; t++) {
                int targetComputer = t;
                boolean isExist = network.parallelStream().anyMatch(net -> net == computer);
                if (!isExist) network.add(i);
                if (computer != targetComputer) {
                    if (computers[computer][targetComputer] == 1 && computers[targetComputer][computer] == 1) { // 한 네트워크
                        isExist = network.parallelStream().anyMatch(net -> net == targetComputer);
                        if (!isExist) network.add(t);
                    }
                }
            }
        }
        for (int i = 0; i < networks.size(); i++) {
            System.out.print(i + ". network : ");
            for (Integer com : networks.get(i)) {
                System.out.print(com + ", ");
            }
            System.out.println();
        }
        return networks.size();
    }

    // j -> t
    static boolean isOneNetwork(int[][] computers, int p, int i, int t) {
        boolean flag = false;
        for (int j = 0; j < computers[p].length; j++) {
            if ( j != i && j < t) {
                if (computers[p][j] == 1 && computers[j][p] == 1) {
                    if (computers[j][i] == 1 && computers[i][j] == 1) {
                        flag = true;
                    } else {
                        flag = isOneNetwork(computers, j, i, t);
                    }
                }
            }
        }
        return flag;
    }

//    static boolean isOneNetwork(int[][] computers, int j, int i) {
//        if (computers[j][i] == 1) {
//            return computers[i][j] == 1;
//        }
//        return false;
//    }
    
/*****************************************************************************************************/

    public static int solution3(int n, int[][] computers) {
        List<List<Integer>> networks = new ArrayList<>(); // networks.size()가 답이 된다.
        for (int current = 0; current < n; current++) {
            List<Integer> network = new ArrayList<>();
            network.add(current);
            for (int target = current; target < n; target++) {
                if (target != current) {
                    if (computers[current][target] == 1 && computers[target][current] == 1) { // 연결된 상태 확인
                        network.add(target);
                        // updateNetwork(networks, i, t);
                    }
                }
            }
            networks.add(network);
        }
        print(networks);
        updateNetwork(networks);
        System.out.println();
        System.out.println("-----After update-----");
        print(networks);
        return networks.size();
    }

    public static void print(List<List<Integer>> networks) {
        int count = 1;
        for (List<Integer> network : networks) {
            System.out.print(count++ + ". network list: ");
            for (Integer computer : network) {
                System.out.print(computer + ", ");
            }
            System.out.println();
        }
    }

    // i와 t는 하나의 네트워크
    // networks 목록에서 i와 t의 네트워크 존재 여부를 확인
    public static void updateNetwork(List<List<Integer>> networks, int i, int t) {
        // i, t 번호가 각기 다른 network 리스트에 있는 경우 하나의 네트워크로 묶어주기
        Integer networkIndexOfCurrent = null;
        Integer networkIndexOfTarget = null;
        boolean flag = false;
        for (int n = 0; n < networks.size(); n++) {
            List<Integer> network = networks.get(n);
            if (network.contains(i) && network.contains(t)) {
                flag = true;
                break;
            } // networks에 추가 작업 하지 않아도 됨.
            if (network.contains(i))
                networkIndexOfCurrent = n;
            if (network.contains(t))
                networkIndexOfTarget = n;
        }

        // i와 t가 네트워크 목록에 모두 없으면 새 네트워크 목록 생성
        // i와 t가 같은 네트워크 상에 존재하면 아무 작업 없음
        // i와 t가 다른 네트워크 상에 존재하면 동일 네트워크 목록으로 추가 후 한쪽 네트워크는 제거
        // i와 t 중 하나가 네트워크 목록에 없다면 네트워크에 존재하는 곳에 없는 인덱스를 추가
        if (!flag) { // 동일 네트워크에 있지 않은 상태.
            if (networkIndexOfCurrent == null) {
                if (networkIndexOfTarget == null) { // 네트워크에 모두 없는 경우
                    List<Integer> network = new ArrayList<>();
                    network.add(i);
                    network.add(t);
                    networks.add(network);
                } else {
                    networks.get(networkIndexOfTarget).add(t);
                }
            } else {
                if (networkIndexOfTarget == null) {
                    networks.get(networkIndexOfCurrent).add(i);
                } else {
                        for (Integer target : networks.get(networkIndexOfTarget)) {
                            networks.get(networkIndexOfCurrent).add(target);
                        }
                        networks.remove(networkIndexOfTarget);
                }
            }
        }
    }

    public static void updateNetwork(List<List<Integer>> networks) {
        List<List<Integer>> updatedNetworks = new ArrayList<>();

        while(true) {
            for (int i = 0; i < networks.size(); i++) {
                updatedNetworks.add(networks.get(i));
                for (List<Integer> updatedNetwork : updatedNetworks) {

                }
            }
        }
    }
    
}
