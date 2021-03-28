package com.dongbeen.algorithm.Programmers;

public class Programmers_level1_신규아이디추천 {
	public String solution(String new_id) {
		String id1 = step1(new_id);
		String id2 = step2(id1);
		String id3 = step3(id2);
		String id4 = step4(id3);
		String id5 = step5(id4);
		String id6 = step6(id5);
		String answer = step7(id6);

		return answer;
	}

	public String step1(String new_id) {
		return new_id.toLowerCase();
	}

	public String step2(String id) {
		StringBuilder sb = new StringBuilder();
		char[] id1 = id.toCharArray();

		for (int i = 0; i < id1.length; i++) {
			if (('a' <= id1[i] && id1[i] <= 'z') || (id1[i] == '-' || id1[i] == '_' || id1[i] == '.')
					|| ('0' <= id1[i] && id1[i] <= '9'))
				sb.append(id1[i]);
		}
		return sb.toString();
	}

	public String step3(String id) {
		while (id.contains(".."))
			id = id.replace("..", ".");
		return id;
	}

	public String step4(String id) {
		if (id.length() > 0)
			if (id.charAt(0) == '.')
				id = id.substring(1, id.length());
		if (id.length() > 0)
			if (id.charAt(id.length() - 1) == '.')
				id = id.substring(0, id.length() - 1);
		return id;
	}

	public String step5(String id) {
		if (id.equals(""))
			id = "a";
		return id;
	}

	public String step6(String id) {
		if (id.length() >= 16) {
			id = id.substring(0, 15);
			if (id.charAt(id.length() - 1) == '.')
				id = id.substring(0, id.length() - 1);
		}
		return id;
	}

	public String step7(String id) {
		if (id.length() >= 3)
			return id;

		StringBuilder sb = new StringBuilder();
		char[] id6 = id.toCharArray();
		for (int i = 0; i < id6.length; i++) {
			sb.append(id6[i]);
		}

		while (sb.length() < 3)
			sb.append(id6[id6.length - 1]);

		return sb.toString();
	}
}
