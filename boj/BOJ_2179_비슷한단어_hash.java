package hanghae.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


// 해당 로직은 처리속도가 오래걸림
//  클래스에서 해시맵을 사용하여 단어의 입력 순서를 저장
// 두 클래스 모두 이중 루프를 사용하여 모든 단어 쌍을 비교하므로 시간 복잡도는 O(n^2)입니다. 단어의 최대 길이가 100이므로, 실제로는 단어의 길이와 상관없이 모든 쌍을 비교하는 것이 주요 성능 병목점입니다.

public class BOJ_2179_비슷한단어_hash {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 단어의 개수 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 단어와 인덱스를 저장할 해시맵 (단어를 키로, 인덱스를 값으로 저장)
        Map<String, Integer> wordIndex = new HashMap<>();
        String[] words = new String[n]; // 단어를 저장할 배열

        // 단어를 배열에 저장하여 각 단어 쌍에 대해 접두사 길이를 계산
        for (int i = 0; i < n; i++){
            // 단어 입력 받기
            words[i] = br.readLine();
            // 단어와 해당 단어의 인덱스 저장
            wordIndex.put(words[i], i);
        }

        // 가장 비슷한 두 단어를 저장할 변수
        String firstWord = ""; // 첫 번째 단어
        String secondWord = ""; // 두 번째 단어
        int maxPrefixLength = 0; // 최대 접두사의 길이

        // 모든 단어 쌍에 대해 최대 접두사 길이 계산
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                // 현재 단어와 그 다음 단어들에 대해 접두사 길이 계산
                int getPrefixLength = getWordLength(words[i], words[j]);

                // 현재 최대 길이보다 크면 갱신
                if (getPrefixLength > maxPrefixLength){
                    maxPrefixLength = getPrefixLength;
                    firstWord = words[i];
                    secondWord = words[j];
                }
            }
        }
        // 결과 출력
        System.out.println(firstWord); // 첫 번째 단어 출력
        System.out.println(secondWord); // 두 번째 단어 출력
    }

    // 두 단어의 최대 접두사 길이를 계산하는 함수
    private static int getWordLength(String word1, String word2){
        // 두 단어 중 짧은 길이
        int minLength = Math.min(word1.length(), word2.length());
        // 최대 접두사 길이
        int maxLength = 0;

        // 단어의 각 문자를 비교하여 최대 접두사 길이를 계산
        for (int i = 0; i < minLength; i++){
            if (word1.charAt(i) == word2.charAt(i)){
                maxLength++; // 단어가 같으면 접두사 길이 증가
            }else{
                break; // 단어가 다르면 비교 종료
            }
        }

        return maxLength;
    }

}
