import java.util.LinkedList;
import java.util.Queue;

public class PRO_42583_다리를지나는트럭 {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0; // 경과 시간
        int currentWeight = 0; // 현재 다리에 있는 트럭들의 총 무게

        Queue<Integer> bridge = new LinkedList<>(); // 다리를 건너는 트럭들을 관리할 큐

        // 초기 상태 : 다리가 올라가 있는 트럭이 없으므로 bridge_length 크기만큼 0을 채워넣음
        for (int i = 0; i < bridge_length; i++){
            bridge.add(0);
        }

        int index = 0; // 대기 트럭 배열의 인덱스

        // 모든 트럭이 다리를 건널 때까지 반복
        while (!bridge.isEmpty()){
            // 1초가 지남을 표현
            answer++;
            // 다리가 건넌 트럭을 큐에서 제거하고, 현재 다리의 총 무게에서 제외
            currentWeight -= bridge.poll();

            // 대기 트럭이 남아 있고, 현재 다리의 무게 제한을 넘지 않으면 트럭을 다리에 올림
            if (index < truck_weights.length){
                if (currentWeight + truck_weights[index] <= weight){
                    // 새로운 트럭을 다리에 올림
                    bridge.add(truck_weights[index]);
                    currentWeight += truck_weights[index];
                    index++;
                } else {
                    // 무게 제한을 넘으면 빈 자리를 유지 (0을 추가한다.)
                    bridge.add(0);
                }
            }
        }

        return answer; // 모든 트럭이 다리를 건너는데 걸리는 최소 시간 반환
    }

}
