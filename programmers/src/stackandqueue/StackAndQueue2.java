package stackandqueue;

import java.util.*;

public class StackAndQueue2 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int[] priorities3 = {2, 1, 9, 2, 3, 4, 6, 3, 5, 2, 6, 1, 7, 8, 6, 5};
        System.out.println("result: " + solution(priorities, 2)); // result 1
        System.out.println();
        System.out.println("result: " + solution(priorities2, 0)); // result 5
        System.out.println();
        System.out.println("result: " + solution(priorities3, 4)); // result ?

    }

    static class PrintOrder {
        private final Integer priority;
        private final Boolean flag;

        PrintOrder(int priority, boolean flag) {
            this.priority = priority;
            this.flag = flag;
        }

        public Integer getPriority() {
            return priority;
        }

        public Boolean getFlag() {
            return flag;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<PrintOrder> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new PrintOrder(priorities[i], i == location));
        }
        queue.forEach(order -> {
            System.out.println(order.getPriority() + " " + order.getFlag());
        });


        int completeCount = 0;
        while (true) {
            boolean flag = true;
            PrintOrder currentOrder = queue.poll();
            for (PrintOrder p : queue) {
                assert currentOrder != null;
                if (p.getPriority() > currentOrder.getPriority()) {
                    queue.add(currentOrder);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                completeCount++;
                if (currentOrder.getFlag()) {
                    answer = completeCount;
                    break;
                }
            }
        }
        return answer;
    }
}
