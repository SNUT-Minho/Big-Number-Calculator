package Calculator;
import java.util.Scanner;

import javax.xml.stream.events.Characters;

public class Calculator {
	static char[] flags = { '+', '-', '%', '*' };
	static int[] det;
	static int flag;
	static int Dflag;
	static int Fflag;
	static String ans = "";

	public static void main(String[] args) {

		System.out.println("Program is running");

	}

	// 초기화
	void clear() {
		ans = "";
	}

	// 소수점 가지고있는지 확인
	static boolean checkPoint(String s) {
		char[] sk = s.toCharArray();
		for (int i = 0; i < sk.length; i++) {
			if (sk[i] == '.') {
				return true;
			}
		}
		return false;
	}

	// 최종 결과값 전달
	static String finalAns() {

		char[] print = ans.toCharArray();
		int min = 1000;
		ans = "";
		for (int i = 0; i < print.length; i++) {
			if (print[i] != '-' && (Character.getNumericValue(print[i]) > 0) && (i < min)) {
				min = i;
			}
		}

		if (flag == -1) {
			ans += '-';
		}

		for (int k = min; k < print.length; k++) {
			ans += print[k];
		}

		flag = 0;

		if (min == 1000) {
			return "0";
		} else {
			return ans;
		}

	}

	// s1과 s2 크기 비교
	static int checkValue(String s1, String s2) {
		if (s1.length() < s2.length()) {
			return 1;
		} else if ((s1.length() == s2.length()) && s1.compareTo(s2) < 0) {
			return 1;
		} else {
			return 0;
		}
	}

	static Boolean checkNaN(String s1, String s2) {
		if (s1.equals("NaN") || s2.equals("NaN")) {
			return true;
		}
		return false;
	}

	// check_zero
	static int checkZero(String s) {
		if (Integer.parseInt(s) == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	// 덧셈
	static String plus(String s1, String s2) {
		if (checkNaN(s1, s2)) {
			return "NaN";
		}

		char[] ss1 = s1.toCharArray();

		if (ss1[0] == '-') {

			String c1 = "";
			for (int i = 1; i < ss1.length; i++) {
				c1 += ss1[i];
			}

			return minus(s2, c1);
		}

		if (checkValue(s1, s2) == 1) {
			det = new int[s2.length() + 2];
		} else {
			det = new int[s1.length() + 2];
		}

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		for (int i = (c1.length - 1), q = (det.length - 1); i >= 0; i--, q--) {
			det[q] += Character.getNumericValue(c1[i]);
		}

		for (int i = (c2.length - 1), q = (det.length - 1); i >= 0; i--, q--) {
			det[q] += Character.getNumericValue(c2[i]);
		}

		for (int i = (det.length - 1); i >= 2; i--) {
			int sig = det[i] / 10;
			det[i] = det[i] % 10;
			det[i - 1] += sig;
		}

		for (int i = 0; i < det.length; i++) {
			if (i == 0) {
				if (flag == -1) {
					ans += '-';
				}
				continue;
			}
			if (i == 1 && det[i] == 0) {
				continue;
			}
			ans += det[i];
		}

		return finalAns();
	}

	// 뺄셈
	static String minus(String s1, String s2) {
		if (checkNaN(s1, s2)) {
			return "NaN";
		}

		String k1;
		String k2;

		char[] ss1 = s1.toCharArray();
		char[] ss2 = s2.toCharArray();

		if (ss1[0] == '-' && ss2[0] == '-') {
			String c1 = "";
			for (int i = 1; i < ss1.length; i++) {
				c1 += ss1[i];
			}

			String c2 = "";
			for (int i = 1; i < ss2.length; i++) {
				c2 += ss2[i];
			}

			System.out.println("c1 : " + c1);
			System.out.println("c2 : " + c2);

			return minus(c2, c1);
		}

		if (ss1[0] == '-' && ss2[0] != '-') {
			flag = -1;

			String c1 = "";
			for (int i = 1; i < ss1.length; i++) {
				c1 += ss1[i];
			}

			return plus(c1, s2);
		}

		if (checkValue(s1, s2) == 1) {
			k1 = s1;
			k2 = s2;

			det = new int[s2.length() + 2];
			flag = -1;
		} else {
			k1 = s2;
			k2 = s1;
			det = new int[s1.length() + 2];
		}

		// c2가 긴거 c1이 작은거
		char[] c1 = k1.toCharArray();
		char[] c2 = k2.toCharArray();

		for (int i = (c2.length - 1), q = (det.length - 1); i >= 0; i--, q--) {
			if (c1.length == 1 && c2.length == 1) {

				ans += (Character.getNumericValue(c2[i]) - Character.getNumericValue(c1[i]));
				return finalAns();
			}

			if (i == c2.length - 1) {
				det[q] += (Character.getNumericValue(c2[i]) + 10);
			} else if (i == 0) {
				det[q] += (Character.getNumericValue(c2[i]) - 1);
			} else {
				det[q] += (Character.getNumericValue(c2[i]) + 10 - 1);
			}

		}

		for (int i = (c1.length - 1), q = (det.length - 1); i >= 0; i--, q--) {
			det[q] -= Character.getNumericValue(c1[i]);
		}

		for (int i = (det.length - 1); i >= 2; i--) {
			int sig = det[i] / 10;
			det[i] = det[i] % 10;
			det[i - 1] += sig;
		}

		for (int i = 0; i < det.length; i++) {

			if (i == 0) {
				if (flag == -1) {
					ans += '-';
				}
				continue;
			}

			if (i == 1 && det[i] == 0) {
				continue;
			}

			ans += det[i];
		}

		return finalAns();
	}

	// 곱셈
	static String multiply(String s1, String s2) {
		if (checkNaN(s1, s2)) {
			return "NaN";
		}

		System.out.println("S1: " + s1 + " S2: " + s2);

		String k1;
		String k2;
		String convert = "";

		char[] ss1 = s1.toCharArray();
		char[] ss2 = s2.toCharArray();

		if (checkPoint(s2)) {
			Fflag = ss2.length - 1;
			for (int i = 0; i < ss2.length; i++) {
				if (ss2[i] != '0' && ss2[i] != '.') {
					Dflag = i;
					break;
				}
			}

			for (int i = Dflag; i < ss2.length; i++) {
				convert += ss2[i];
			}

			return multiply(s1, convert);
		}

		if ((ss1[0] == '-' && ss2[0] != '-')) {
			flag = -1;
			String c1 = "";
			for (int i = 1; i < ss1.length; i++) {
				c1 += ss1[i];
			}
			return multiply(c1, s2);
		}

		if ((ss1[0] != '-' && ss2[0] == '-')) {
			flag = -1;
			String c2 = "";
			for (int i = 1; i < ss2.length; i++) {
				c2 += ss2[i];
			}
			return multiply(s1, c2);
		}

		if ((ss1[0] == '-' && ss2[0] == '-')) {

			String c1 = "";
			for (int i = 1; i < ss1.length; i++) {
				c1 += ss1[i];
			}

			String c2 = "";
			for (int i = 1; i < ss2.length; i++) {
				c2 += ss2[i];
			}

			return multiply(c1, c2);
		}

		if ((ss1[0] == '-' && ss2[0] != '-')) {
			flag = -1;

		}

		if (checkValue(s1, s2) == 1) {
			k1 = s1;
			k2 = s2;
			det = new int[s2.length() * 2];
		} else {
			k1 = s2;
			k2 = s1;
			det = new int[s1.length() * 2];
		}

		// c2가 긴거 c1이 작은거
		char[] c1 = k1.toCharArray();
		char[] c2 = k2.toCharArray();

		for (int j = c1.length - 1; j >= 0; j--) {
			for (int i = c2.length - 1; i >= 0; i--) {
				if (c1.length == 1 && c2.length == 1) {

					if (Dflag != 0) {
						double tempAns = (Character.getNumericValue(c2[i]) * Character.getNumericValue(c1[i]));
						tempAns = Math.round((tempAns / Math.pow(10, Fflag - 1)));
						ans += (int) tempAns;
						return finalAns();
					}

					else {
						ans += (Character.getNumericValue(c2[i]) * Character.getNumericValue(c1[i]));
						return finalAns();
					}
				}

				det[i + j] += Character.getNumericValue(c1[j]) * Character.getNumericValue(c2[i]);
			}
		}

		for (int i = (c1.length + c2.length - 2); i >= 1; i--) {
			int sig = det[i] / 10;
			det[i] = det[i] % 10;
			det[i - 1] += sig;
		}

		if (Dflag != 0) {
			String tempAns = "";
			int[] tempArr = new int[det.length];
			
			
			for (int e = 0; e <= c1.length + c2.length - 2; e++) {
				tempAns += det[e];
			}
			char[] temp = tempAns.toCharArray();
			int special = temp.length-1;
			
			for (int e = 0; e <= c1.length + c2.length - 2; e++) {
				det[e] = Character.getNumericValue(temp[e]);
			}
			
		
			System.out.println("Special : " + special);
			for (int i = 0; i <= special - (Fflag - 1); i++) {
				System.out.println("22222222222222");
				if (i == (special - (Fflag - 1))) {
					if (det[i + 1] >= 5) {
						ans += (det[i] + 1);
					} else {
						ans += det[i];
					}
				} else {
					System.out.println("Final : " + det[i]);
					ans += det[i];
				}
			}
		} else {
			for (int i = 0; i <= (c1.length + c2.length - 2); i++) {
				ans += det[i];
			}
		}

		System.out.println("최종 : " + ans);
		return finalAns();
	}

	// 무한소수나오느지 체크
	static boolean checkInfinity(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			int status = arr[i];
			for (int j = 1; j < arr.length; j++) {
				if (arr[j - 1] == status && arr[j] == status) {
					count++;
				}
			}
			if (count >= 3) {
				return true;
			}
		}
		return false;
	}

	// 나눗셈
	static String divide(String s1, String s2) {
		if (checkNaN(s1, s2)) {
			return "NaN";
		}

		if (s2.equals("0")) {
			return "NaN";
		}

		if (s1.equals(s2)) {
			return "1";
		}

		Dflag = 0;
		int[] detD = new int[s1.length() * 2];

		if (checkValue(s1, s2) == 1) {
			int x1 = Integer.parseInt(s1) * 10;
			int x2 = Integer.parseInt(s2) * 5;

			if (x1 >= x2) {
				return "1";
			} else {
				return "0";
			}
		}

		String num = "1";
		int i = 0;

		while (true) {
			if (checkValue(num, s2) == 1) {
				System.out.println("i : " + i);
				System.out.println("detD[" + i + "]: " + detD[i]);
				num = multiply(num, "10");
				i++;
				ans = "";

				System.out.println("Num1 : " + num);
			} else {
				num = minus(num, s2);
				detD[i]++;
				ans = "";
				System.out.println("detD[" + i + "]: " + detD[i]);
				System.out.println("Num2 : " + num);
			}

			if (i >= s1.length() + 1 && checkInfinity(detD) == true) {
				i--;
				break;
			}

			if (num == "0") {
				break;
			}
		}

		String reverse = "";
		for (int q = 0; q <= i; q++) {
			if (q == 1) {
				reverse += ".";
			}
			reverse += detD[q];
		}

		return multiply(s1, reverse);
	}
}
