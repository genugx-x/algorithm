package stackandqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

// 다리를 지나는 트럭
public class StackAndQueue3 {
    public static void main(String[] args) {
        // 2	10	[7,4,5,6]
        int answer = solution(2, 10, new int[]{7,4,5,6}); // answer = 8;
        System.out.println(answer);
        int answer2 = solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}); // answer = 8;
        System.out.println(answer2);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        // 트럭 한대가 다리를 오른다
        // 트럭 한대를 다리를 지나갔을떼 (+1초), 동시에 트럭 1대가 다리를 오르면 추가 시간 없음.
        // 현재 지나가는 트럭의 다음 트럭이 다리를 함께 오를 수 있으면 다리에 올린다. 이때 먼저 앞의 트럭은 그대로 지나가는중.
        // 트럭은 다리에 오른 후부터 다리의 길이(+n초)만큼 지나가고 내려가기까지의 시간(+1초)을 측정해야한다.
        // 트럭 한대가 지나가는 소요시간 = 다리를 오르는 시간 (0초) + 다리를 지나가는 시간 (다리길이) + 다리에서 내려간 시간(1초)
        // 즉 n(다리길이) + 1초가 고정적이다.

        // 다리의 길이에 따라 이동하는 거리에 따라 소요되는 시간도 고려해야한다.
        // 다리의 길이 : 2
        // 다리의 무게 : 1
        // 트럭의 무게 : 1
        // 트럭을 다리를 오른다. -> 다리를 건넌다.(길이 만큼 소요[2초]) -> 트럭이 다리를 내린다. [1초 추가]
        // 총 3초

        // 위의 예시에서 만약 무게가 1인 트럭이 추가적으로 있다면,
        //      > 총 4초 (즉 바로 이어서 탄 차량은 앞차량 소요시간에 +1초만 소요됨)
        // 무게가 2인 트럭이 추가적으로 있다면,
        //      > 총 5초 (함께 건너지 못하는 차량은 다리의 길이만큼 추가 소요됨 (+n [다리의 길이])
        // 다리에 차량을 올릴 수 있는 상황에 따라 더해지는 소요시간의 규칙을 발견할 수 있을거 같다.

        // 이 문제에서 주요한 조건
        //      - 다리를 지나가려면 다리의 길이 + 1 만큼 소요 시간이 걸린다. (고정)
        //      - 현재 올라간 트럭 뒤의 트럭을 올릴 수 있다면, 앞의 다리 +1초 뒤에 도착할것이다.
        //      - 뒤의 트럭을 올릴 수 없다면, 현재 다리를 건너는 트럭의 초만큼 대기해야한다.
        //          > 현재 트럭이 내리는 초(다리의 길이 + 1초)에 다음 트럭이 다리에 동시에 오른다.

        // 해결 방법
        //      > 트럭의 큐를 생성하여 꺼낸 트럭의 무게를 비교
        //          >> 함께 건너가게 할 수 있으면 +1
        //          >> 대기해야 한다면 +n [다리의 길이]
        //      > 위 *조건대로 시작 트럭의 고정 소요시간* + 다음 트럭의 이동 조건에 따라 소요되는 누적 초

        // 다리의 길이 : 2, 다리가 버틸 수 있는 최대 무게: 10, 다리를 건널 트럭: [7,4,5,6]
        Queue<Truck> truckQ = new LinkedList<>();
        for (int truck_weight : truck_weights) {
            truckQ.add(new Truck(truck_weight, bridge_length)); // Truck 생성자 (무게, 다리를 통과하기 까지의 소요시간)
        }
        ArrayBlockingQueue<Truck> bridgeQ = new ArrayBlockingQueue<>(bridge_length);
        boolean startFlag = false;
        int trucksWeightOnBridge = 0;
        while (true) {
            // 다리를 통과한 트럭을 체크한 후 제외
            bridgeQ.forEach( t -> {
                bridgeQ.poll();
                if (--t.second > 0)
                    bridgeQ.add(t);
            });
            if (!truckQ.isEmpty()) {
                // 다리 건너고 있는 트럭의 총 무게 계산
                trucksWeightOnBridge = bridgeQ.stream()
                        .mapToInt(t -> t.weight)
                        .sum();
                // (다리 하중 - 다리위 트럭의 총 무게) >= 대기중인 트럭 무게
                if ((weight - trucksWeightOnBridge) >= truckQ.peek().weight) {
                    bridgeQ.add(truckQ.poll());
                }
                startFlag = true; // 측정 시작 flag
            }
            // 시간 관련 측정
            if (startFlag) {
                answer++;
                if (bridgeQ.isEmpty()) // 다리 위 트럭이 더이상 없다면 while 벗어나기
                    break;
            }
        }
        return answer;
    }

    static class Truck {
        Integer weight;
        Integer second;

        Truck(int weight, Integer second) {
            this.weight = weight;
            this.second = second;
        }
    }
}
