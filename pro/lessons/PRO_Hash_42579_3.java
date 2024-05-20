package study2.day1;

import java.util.*;
// 제출 코드

/*
Comparable을 이용한 정렬:
Collections.sort(songs)를 사용하여 각 장르별 노래 리스트를 정렬합니다.
keySet 리스트를 정렬하여 재생 횟수가 많은 순서대로 장르를 처리합니다.
 */
public class PRO_Hash_42579_3 {
    // Song 클래스 정의, Comparable 인터페이스를 구현하여 정렬 기준을 설정
    public static class Song implements Comparable<Song> {
        /*
        Song 클래스:
        Song 클래스는 각 노래의 인덱스와 재생 횟수를 저장하며, Comparable<Song>을 구현하여 정렬 기준을 정의합니다.
        compareTo 메서드는 재생 횟수를 기준으로 내림차순 정렬하고, 재생 횟수가 같으면 인덱스를 기준으로 오름차순 정렬합니다.
         */
        int index; // 노래의 인덱스
        int plays; // 노래의 재생 횟수

        public Song(int index, int plays) {
            this.index = index; // 인덱스 초기화
            this.plays = plays; // 재생 횟수 초기화
        }

        @Override
        public int compareTo(Song other) {
            // 재생 횟수가 같으면 인덱스 오름차순 정렬
            if (this.plays == other.plays) {
                return this.index - other.index;
            }
            // 재생 횟수가 다르면 재생 횟수 내림차순 정렬
            return other.plays - this.plays;
        }
    }

    public static int[] getBestAlbum(String[] genres, int[] plays) {
        /*
        자료 구조 업데이트:
        music 맵은 HashMap<String, List<Song>> 형태로 변경되어 장르별 노래 리스트를 저장합니다.
         */
        ArrayList<Integer> answer = new ArrayList<>(); // 최종 답을 저장할 리스트

        HashMap<String, Integer> num = new HashMap<>(); // 장르별 총 재생 횟수를 저장할 맵
        HashMap<String, List<Song>> music = new HashMap<>(); // 장르별 노래 리스트를 저장할 맵

        for (int i = 0; i < plays.length; i++) {
            // 장르별 총 재생 횟수를 갱신
            num.put(genres[i], num.getOrDefault(genres[i], 0) + plays[i]);

            // 장르별 노래 리스트에 현재 노래 추가
            if (!music.containsKey(genres[i])) {
                music.put(genres[i], new ArrayList<>()); // 해당 장르가 처음 등장하면 리스트 생성
            }
            music.get(genres[i]).add(new Song(i, plays[i])); // 노래 추가
        }

        // 장르별 총 재생 횟수를 기준으로 장르를 내림차순 정렬
        List<String> keySet = new ArrayList<>(num.keySet());
        Collections.sort(keySet, (s1, s2) -> num.get(s2) - num.get(s1));

        for (String key : keySet) {
            List<Song> songs = music.get(key); // 현재 장르의 노래 리스트 가져오기
            Collections.sort(songs); // 노래 리스트를 재생 횟수 기준으로 정렬

            // 정렬된 리스트에서 상위 두 개의 노래 인덱스 추가
            answer.add(songs.get(0).index);
            if (songs.size() > 1) {
                answer.add(songs.get(1).index);
            }
        }

        // ArrayList를 int 배열로 변환하여 반환
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
