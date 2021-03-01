package com.dongbeen.algorithm.Programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Programmers_bestAlbum {
	public int[] solution(String[] genres, int[] plays) {
		int[] answer = {};

		HashMap<Integer, Integer> playMap = new HashMap<>();
		HashMap<Integer, String> genreMap = new HashMap<>();
		HashMap<Integer, String> playSum = new HashMap<>();
		HashSet<String> genreSet = new HashSet<>();

		for (int i = 0; i < genres.length; i++) {
			playMap.put(i, plays[i]);
			genreMap.put(i, genres[i]);
			genreSet.add(genres[i]);
		}

		for (String genre : genreSet) {
			int sum = 0;
			for (int i = 0; i < genres.length; i++) {
				if (genres[i].equals(genre))
					sum += playMap.get(i);
			}
			playSum.put(sum, genre);
		}

		// 플레이 횟수 별로 정렬(키 값을 기준으로 정렬, TreeMap)
		TreeMap sort = new TreeMap(playSum);
		String[] sortGenre = new String[genreSet.size()];
		int index = 0;
		for (Object o : sort.keySet()) {
			sortGenre[index] = sort.get(o).toString();
			index++;
		}

		// 장르별 많이 플레이 된 노래의 고유번호 찾기
		ArrayList<Integer> fIndex = new ArrayList<Integer>();
		for (int i = sortGenre.length - 1; i >= 0; i--) {
			int count = 0;
			for (int p1 : genreMap.keySet()) {
				if (sortGenre[i].equals(genreMap.get(p1))) {
					count++;
				}
			}

			int[] temp = new int[count];
			int k = 0;
			for (int p2 : genreMap.keySet()) {
				if (sortGenre[i].equals(genreMap.get(p2))) {
					temp[k] = playMap.get(p2);
					k++;
				}
			}

			if (temp.length != 1) {
				Arrays.sort(temp);
				for (int j = temp.length - 1; j >= temp.length - 2; j--) {
					for (int p : playMap.keySet()) {
						if (temp[j] == playMap.get(p)) {
							fIndex.add(p);
							playMap.put(p, 0);
							break;
						}
					}
				}
			} else {
				for (int p : playMap.keySet()) {
					if (temp[0] == playMap.get(p)) {
						fIndex.add(p);
						playMap.put(p, 0);
						break;
					}
				}
			}
		}

		answer = new int[fIndex.size()];

		for (int i = 0; i < fIndex.size(); i++) {
			answer[i] = fIndex.get(i);
		}

		return answer;
	}
}
