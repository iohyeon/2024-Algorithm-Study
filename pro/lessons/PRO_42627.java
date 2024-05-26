package hanghae.day5_Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 이 문제는 "디스크 스케줄링" 문제로, 주어진 작업들의 요청부터 종료까지의 시간을 최소화하는 방법을 찾아야 합니다.
 * 이를 위해 작업이 요청되는 시간과 소요 시간을 기준으로 작업을 처리하는 것이 중요합니다.
 * 작업을 효율적으로 처리하기 위해 최소 힙(우선순위 큐)을 사용할 수 있습니다.
 * 최소 힙을 사용하여 각 작업의 소요 시간이 짧은 작업을 먼저 처리하는 방식(SJF: Shortest Job First)을 구현할 수 있습니다.
 * 이렇게 하면 평균 대기 시간을 최소화할 수 있습니다.
 */
public class PRO_42627_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        // jobs 배열을 요청 시점을 기준으로 오름차순으로 정렬
        Arrays.sort(jobs, (a,  b) -> a[0] - b[0]);

        // 우선순위 큐를 사용하여 작업을 소요 시간 기준으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int currentTime = 0; // 현재 시간
        int totalWaitTime = 0; // 총 대기 시간
        int jobIndex = 0; // jobs 배열을 순회하기 위한 인덱스
        int jobCount = jobs.length; // 총 작업 수

        // 모든 작업이 처리될 때까지 반복
        while (jobIndex < jobCount || !pq.isEmpty()){
            // 현재 시점에서 처리 가능한 모든 작업을 우선순위 큐에 추가
            while (jobIndex < jobCount && jobs[jobIndex][0] <= currentTime){
                pq.add(jobs[jobIndex++]);
            }

            if (pq.isEmpty()){
                // 처리할 수 있는 작업이 없으면 현재 시간을 다음 작업의 요청 시간으로 업데이트
                currentTime = jobs[jobIndex][0];
            }else {
                // 우선순위 큐에서 가장 짧은 작업을 꺼내서 처리
                int[] currentJob = pq.poll();
                currentTime += currentJob[1]; // 현재 시간에 작업 소요 시간을 더해 업데이트
                totalWaitTime += currentTime - currentJob[0]; // 대기 시간 계산하여 총 대기 시간에 더함
            }
        }

        // 평균 대기 시간을 계산하여 반환 (소수점 이하 버림)
        return totalWaitTime / jobCount;
    }

    public static void main(String[] args) {
        PRO_42627_디스크컨트롤러 sol = new PRO_42627_디스크컨트롤러();
        // 테스트 케이스
        int[][] jobs1 = {{0, 3}, {1, 9}, {2, 6}};
        int result1 = sol.solution(jobs1);
        System.out.println(result1); // 출력: 9

        int[][] jobs2 = {{0, 10}, {2, 3}, {9, 3}};
        int result2 = sol.solution(jobs2);
        System.out.println(result2); // 출력: 9

    }
}
