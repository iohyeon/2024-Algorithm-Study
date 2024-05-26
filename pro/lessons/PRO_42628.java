
/**
 * 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
 *
 * 명령어	수신 탑(높이)
 * I 숫자	큐에 주어진 숫자를 삽입합니다.
 * D 1	큐에서 최댓값을 삭제합니다.
 * D -1	큐에서 최솟값을 삭제합니다.
 * 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
 */
/**
 * 하나는 최소 힙(min-heap)으로 최솟값을 추적하고, 다른 하나는 최대 힙(max-heap)으로 최댓값을 추적합니다.
 * 이 두 개의 힙을 사용하면 각 연산을 효율적으로 수행할 수 있습니다. 각 연산 후 두 힙이 동일한 요소를 유지
 */

public class Main {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 오름차순(최솟값)으로 정렬
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순(최댓값)으로 정렬

        for (String operation : operations) {
            String[] parts = operation.split(" "); // 명령어와 값을 분리
            String command = parts[0]; // 명령어 ("I" 또는 "D")
            int value = Integer.parseInt(parts[1]); // 숫자

            if (command.equals("I")) { // 삽입 연산
                minHeap.add(value); // 최소 힙에 삽입
                maxHeap.add(value); // 최대 힙에 삽입
            } else if (command.equals("D")) { // 삭제 연산
                if (value == 1 && !maxHeap.isEmpty()) { // 최댓값 삭제
                    int maxValue = maxHeap.poll(); // 최댓값 추출
                    minHeap.remove(maxValue); // 최소 힙에서 삭제
                } else if (value == -1 && !minHeap.isEmpty()) { // 최솟값 삭제
                    int minValue = minHeap.poll(); // 최솟값 추출
                    maxHeap.remove(minValue); // 최대 힙에서 삭제
                }
            }
        }

        if (minHeap.isEmpty() || maxHeap.isEmpty()) {
            return new int[]{0, 0}; // 큐가 비어있는 경우 [0, 0] 반환
        } else {
            return new int[]{maxHeap.peek(), minHeap.peek()}; // 비어있지 않은 경우 [최댓값, 최솟값] 반환
        }
    }

    public static void main(String[] args) {
        PRO_42628_이중우선순위큐_main sol = new PRO_42628_이중우선순위큐_main();

        // 테스트 케이스 1
        String[] operations1 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        System.out.println(Arrays.toString(sol.solution(operations1))); // 출력: [0, 0]

        // 테스트 케이스 2
        String[] operations2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(sol.solution(operations2))); // 출력: [333, -45]
    }
}
