
/**
 * 위의 코드는 배열 weights를 오름차순으로 정렬한 후, 가장 작은 무게와 가장 큰 무게를 각각 선택하여 최대와 최소 점수를 계산합니다. 각 점수의 차이를 반환하여 문제를 해결합니다.
 *
 * 이 문제에서 사용된 자료구조는 배열과 정렬된 상태의 배열입니다. 입력된 무게 배열을 정렬하여 최소와 최대 점수를 쉽게 계산
 */

/**
 * 문제의 핵심은 주어진 구슬 배열을 k개의 비어있지 않은 가방으로 나누었을 때, 각 가방의 비용의 합을 최소화하고 최대화하는 방법을 찾고, 그 차이를 반환하는 것
 */
public class Main {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;

        // k가 n과 같으면 각 구슬이 하나씩 가방에 들어가므로 점수 차이는 0
        if (k == n) return 0;

        // 인접한 두 구슬의 가중치 합을 저장할 배열
        int[] costs = new int[n - 1];

        // 인접한 두 구슬의 가중치 합을 계산
        for (int i = 0; i < n - 1; i++) {
            costs[i] = weights[i] + weights[i + 1];
        }

        // costs 배열을 오름차순으로 정렬
        Arrays.sort(costs);

        long min = 0, max = 0;

        // 최소 점수를 구하기 위해 k-1개의 가장 작은 비용을 더함
        for (int i = 0; i < k - 1; i++) {
            min += costs[i];
        }

        // 최대 점수를 구하기 위해 k-1개의 가장 큰 비용을 더함
        for (int i = 0; i < k - 1; i++) {
            max += costs[n - i - 2];
        }

        // 최대 점수와 최소 점수의 차이를 반환
        return max - min;
    }

    public static void main(String[] args) {
        LEET_3737764_제출 solution = new LEET_3737764_제출();

        int[] weights1 = {1, 3, 5, 1};
        int k1 = 2;
        System.out.println(solution.putMarbles(weights1, k1)); // Output: 4

        int[] weights2 = {1, 3};
        int k2 = 2;
        System.out.println(solution.putMarbles(weights2, k2)); // Output: 0
    }
}
