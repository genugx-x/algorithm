package stackandqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class StackAndQueue3_2 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> bridgeQ = new ArrayBlockingQueue<>(2);


        bridgeQ.add(5);
        bridgeQ.add(3);
//        System.out.println(bridgeQ.stream()
//                .mapToInt(weight -> weight)
//                .sum());
        System.out.println(bridgeQ.toString());

        bridgeQ.forEach( sec -> {
            bridgeQ.poll();
            bridgeQ.add(--sec);
        });
        System.out.println(bridgeQ.toString());
    }
}
