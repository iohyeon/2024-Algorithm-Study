package hanghae.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2179_비슷한단어 {
    // 두 단어의 공통 접두사의 길이를 계산하는 함수
    private static int wordLength(String word1, String word2){
        int minLength = Math.min(word1.length(), word2.length());
        // 두 단어의 최소 길이만큼 문자를 비교합니다.
        // 문자가 다르면 현재 인덱스를 반환합니다.
        // 모든 문자가 동일하다면 최소 길이를 반환합니다.
        for (int i=0; i<minLength; i++){
            if (word1.charAt(i) != word2.charAt(i)){
                return i;
            }
        }
        return minLength;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에서 단어의 개수 'n' 입력 받기
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        // 단어 입력 받기
        // 다음 n개의 줄에서 각 단어를 배열에 저장
        for (int i=0; i<n; i++){
            words[i] = br.readLine();
        }

        int maxPrefixLength = -1;
        String resultWord1 = "";
        String resultWord2 = "";

        // 모든 단어 쌍에 대해 접두사 길이를 계산
        // 이중 루프를 사용하여 모든 단어 쌍을 비교하고, 접두사 길이가 최대인 경우를 추적
        for(int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                int prefixLength = wordLength(words[i], words[j]);
                if (prefixLength > maxPrefixLength){
                    maxPrefixLength = prefixLength;
                    resultWord1 = words[i];
                    resultWord2 = words[j];
                }
            }
        }

        // 결과출력
        System.out.println(resultWord1);
        System.out.println(resultWord2);
    }
}
