package dfsbfs;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    public static void main(String[] args) {
        System.out.println("1.answer = " + solution2(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // answer = 2
        System.out.println();
        System.out.println("2.answer = " + solution2(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}})); // answer = 1
        System.out.println();
        System.out.println("3.answer = " + solution2(4, new int[][] {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}})); // answer = 2
        System.out.println();
        System.out.println("4.answer = " + solution2(3, new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}})); // answer = 1
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
}
