import java.util.Arrays;

public class Solution {
    public String orderlyQueue(String s, int k) {
        // k가 1인 경우
        if (k == 1) {
            String smallest = s; // 사전 순으로 가장 작은 문자열을 저장할 변수
            // 문자열의 각 위치에서 시작하여 모든 회전된 문자열을 확인
            for (int i = 0; i < s.length(); i++) {
                // 현재 회전된 문자열을 생성
                String rotated = s.substring(i) + s.substring(0, i);
                // 현재 회전된 문자열이 지금까지의 가장 작은 문자열보다 작으면 업데이트
                if (rotated.compareTo(smallest) < 0) {
                    smallest = rotated;
                }
            }
            return smallest; // 가장 작은 문자열 반환
        } else {
            // k가 2 이상인 경우
            // 문자열의 모든 문자들을 정렬하여 사전 순으로 가장 작은 문자열을 만듦
            char[] chars = s.toCharArray(); // 문자열을 문자 배열로 변환
            Arrays.sort(chars); // 문자 배열을 정렬
            return new String(chars); // 정렬된 문자 배열을 문자열로 변환하여 반환
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "cba";
        int k = 1;
        System.out.println(solution.orderlyQueue(s, k)); // Output: 4


        String s1 = "baaca";
        int k2 = 3;
        System.out.println(solution.orderlyQueue(s1, k2)); // Output: 0
    }
}
