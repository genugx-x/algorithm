package dfsbfs;


public class Network {

    public static void main(String[] args) {
//        System.out.println("1.answer = " + solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // answer = 2
        System.out.println("2.answer = " + solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}})); // answer = 1
//        System.out.println("3.answer = " + solution(4, new int[][] {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}})); // answer = 2
//        System.out.println("4.answer = " + solution(3, new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}})); // answer = 1
    }


    // (1) 1* 1
    // 1 (1) 1
    // 1 1 (1)
    static int solution(int n, int[][] computers) {
        int answer = n;
        for (int i = 0; i < n; i++) {
            for (int t = i+1; t < n; t++) {
                if (computers[i][t] == 1 && computers[t][i] == 1) { // 연결됨
                    boolean isAlreadyOneNetwork = isOneNetwork(computers, 0, i, t);
                    if (!isAlreadyOneNetwork)
                        answer--;
                }
            }
        }
        return answer;
    }

    // j -> t
    static boolean isOneNetwork(int[][] computers, int j, int i, int t) {
        boolean isOneNetWork = false;
        if (j < i) { // 0 < 1
            if (computers[j][i] == 1) {
                if (computers[i][j] == 1) {
                    return true;
                }
            }
            for (int k = 0; k < computers[j].length; k++) {
                if (k != i && k != t && k != j) {
                    if (computers[j][k] == 1 && computers[k][j] == 1) {
                        isOneNetWork = isOneNetwork(computers, k, i, t);
                    }
                }
            }
        }
        return isOneNetWork;
    }

//    static boolean isOneNetwork(int[][] computers, int j, int i) {
//        if (computers[j][i] == 1) {
//            return computers[i][j] == 1;
//        }
//        return false;
//    }
}
