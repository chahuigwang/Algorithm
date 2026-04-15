import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA 4038. 단어가 등장하는 횟수
 * @author chahuigwang
 *
 * 	@see #main(String[])
 * 	1. 테스트 케이스 개수를 입력받는다.
 * 	2. 각 테스트 케이스마다,
 *
 * 		2-1. 책의 내용 B를 입력받는다.
 * 		2-2. 찾고자 하는 단어 S를 입력받는다.
 *
 * 		@see #kmp(String, String)
 * 		2-3. KMP 알고리즘으로 S가 B에 등장하는 횟수를 구한다.
 * 			 문자가 불일치할 경우 실패 함수를 이용해 패턴 인덱스를 이동한다.
 * 			 문자가 일치하고 패턴을 완전히 찾았을 경우 카운트를 증가시키고,
 * 			 중첩 매칭을 위해 실패 함수를 이용해 패턴 인덱스를 이동한다.
 *
 * 		@see #getFailureFunction(String)
 * 		2-3-1. 실패 함수를 계산한다.
 * 			   각 인덱스에서 접두사와 접미사가 일치하는 최대 길이를 저장한다.
 *
 * 		2-4. 테스트 케이스 번호와 함께 정답을 출력한다.
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 1. 테스트 케이스 개수를 입력받는다.
        int T = Integer.parseInt(br.readLine().trim());

        // 2. 각 테스트 케이스마다,
        for (int t = 1; t <= T; t++) {
            // 2-1. 책의 내용 B를 입력받는다.
            String B = br.readLine();
            // 2-2. 찾고자 하는 단어 S를 입력받는다.
            String S = br.readLine();

            // 2-3. KMP 알고리즘으로 S가 B에 등장하는 횟수를 구한다.
            // 2-4. 테스트 케이스 번호와 함께 정답을 출력한다.
            sb.append("#").append(t).append(" ").append(kmp(B, S)).append("\n");
        }
        System.out.print(sb);
    }

    static int kmp(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        // 2-3-1. 실패 함수를 계산한다.
        int[] fail = getFailureFunction(pattern);
        int matchCount = 0;
        int j = 0;

        for (int i = 0; i < textLength; i++) {
            // 문자가 불일치할 경우 실패 함수를 이용해 패턴 인덱스를 이동한다.
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = fail[j - 1];
            }
            // 문자가 일치할 경우
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == patternLength - 1) { // 패턴을 완전히 찾았을 때
                    matchCount++;
                    j = fail[j]; // 중첩 매칭을 위해 이동
                } else {
                    j++;
                }
            }
        }
        return matchCount;
    }

    static int[] getFailureFunction(String pattern) {
        int patternLength = pattern.length();
        int[] fail = new int[patternLength];
        int k = 0;

        for (int i = 1; i < patternLength; i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = fail[k - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(k)) {
                fail[i] = ++k;
            }
        }
        return fail;
    }
}