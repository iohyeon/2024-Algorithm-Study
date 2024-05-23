import java.util.Stack;

/**
 * 주식가격이 주어질 때, 각 가격이 떨어지지 않은 기간을 계산하는 문제
 */
public class PRO_42584_주식가격 {
    public int[] solutions(int[] prices) {
        // 결과를 저장할 배열
        int[] answer = new int[prices.length];

        // 가격과 인덱스를 저장할 스택을 초기화
        Stack<Integer> stack = new Stack<>();

        // 모든 가격에 대해 반복 (순차적으로 확인)
        for (int i = 0; i < prices.length; i++) {
            // 스택이 버이있지 않고, 현재 가격이 스택의 마지막 가격보다 작으면
            // 즉, 가격이 떨어졌다면
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // 스택에서 인덱스를 꺼내고 해당 인덱스의 기간을 계산한다.
                int j = stack.pop();
                answer[j] = i - j;
            }
            // 현재 인덱스를 스택에 추가
            stack.push(i);
        }

        // 스택에 남아있는 인덱스들에 대해 남은 기간을 계산
        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = prices.length - 1 - j;
        }

        return answer;
    }
}
