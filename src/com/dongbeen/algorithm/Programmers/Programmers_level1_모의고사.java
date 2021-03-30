package com.dongbeen.algorithm.Programmers;

import java.util.PriorityQueue;

public class Programmers_level1_모의고사 {

	class Student implements Comparable<Student> {
		int StudentNo;
		int CorrectAnswerCnt;

		public Student(int StudentNo, int CorrectAnswerCnt) {
			this.StudentNo = StudentNo;
			this.CorrectAnswerCnt = CorrectAnswerCnt;
		}

		@Override
		public int compareTo(Student o) {
			return this.CorrectAnswerCnt <= o.CorrectAnswerCnt ? 1 : -1;
		}
	}

	PriorityQueue<Student> highScoreStudent = new PriorityQueue<>();

	public int[] solution(int[] answers) {
		studentOne(answers);
		studentTwo(answers);
		studentThree(answers);

		Student first = highScoreStudent.poll();
		Student second = highScoreStudent.poll();
		Student third = highScoreStudent.poll();

		int[] answer;
		if (first.CorrectAnswerCnt == second.CorrectAnswerCnt && second.CorrectAnswerCnt == third.CorrectAnswerCnt) {
			answer = new int[] { first.StudentNo, second.StudentNo, third.StudentNo };
		} else if (first.CorrectAnswerCnt == second.CorrectAnswerCnt) {
			answer = new int[] { first.StudentNo, second.StudentNo };
		} else {
			answer = new int[] { first.StudentNo };
		}
		return answer;
	}

	public void studentOne(int[] answers) {
		int corretAnswersCnt = 0;
		for (int i = 1; i <= answers.length; i++) {
			if (i % 5 == 1) {
				if (answers[i - 1] == 1)
					corretAnswersCnt++;
			} else if (i % 5 == 2) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 5 == 3) {
				if (answers[i - 1] == 3)
					corretAnswersCnt++;
			} else if (i % 5 == 4) {
				if (answers[i - 1] == 4)
					corretAnswersCnt++;
			} else {
				if (answers[i - 1] == 5)
					corretAnswersCnt++;
			}
		}
		highScoreStudent.add(new Student(1, corretAnswersCnt));
	}

	public void studentTwo(int[] answers) {
		int corretAnswersCnt = 0;
		for (int i = 1; i <= answers.length; i++) {
			if (i % 8 == 1) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 8 == 2) {
				if (answers[i - 1] == 1)
					corretAnswersCnt++;
			} else if (i % 8 == 3) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 8 == 4) {
				if (answers[i - 1] == 3)
					corretAnswersCnt++;
			} else if (i % 8 == 5) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 8 == 6) {
				if (answers[i - 1] == 4)
					corretAnswersCnt++;
			} else if (i % 8 == 7) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else {
				if (answers[i - 1] == 5)
					corretAnswersCnt++;
			}
		}
		highScoreStudent.add(new Student(2, corretAnswersCnt));
	}

	public void studentThree(int[] answers) {
		int corretAnswersCnt = 0;
		for (int i = 1; i <= answers.length; i++) {
			if (i % 10 == 1) {
				if (answers[i - 1] == 3)
					corretAnswersCnt++;
			} else if (i % 10 == 2) {
				if (answers[i - 1] == 3)
					corretAnswersCnt++;
			} else if (i % 10 == 3) {
				if (answers[i - 1] == 1)
					corretAnswersCnt++;
			} else if (i % 10 == 4) {
				if (answers[i - 1] == 1)
					corretAnswersCnt++;
			} else if (i % 10 == 5) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 10 == 6) {
				if (answers[i - 1] == 2)
					corretAnswersCnt++;
			} else if (i % 10 == 7) {
				if (answers[i - 1] == 4)
					corretAnswersCnt++;
			} else if (i % 10 == 8) {
				if (answers[i - 1] == 4)
					corretAnswersCnt++;
			} else if (i % 10 == 9) {
				if (answers[i - 1] == 5)
					corretAnswersCnt++;
			} else {
				if (answers[i - 1] == 5)
					corretAnswersCnt++;
			}
		}
		highScoreStudent.add(new Student(3, corretAnswersCnt));
	}
}
