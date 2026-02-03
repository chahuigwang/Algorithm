package SWEA.swea1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW Expert Academy
 * @author chahuigwang
 *
 * @see #main(String[])
 * 1. 10개의 테스트 케이스마다,
 *      1-1. 트리가 갖는 정점의 총 수 N을 입력 받는다.
 *          1-1-1. N이 짝수라면 항상 계산 불가능하므로 N줄의 입력을 비운 다음 불가능 출력 후,
 *                  continue로 다음 테스트 케이스로 넘어간다.
 *
 *      1-2. 계산이 불가능한 경우는 다음 두 가지이다.
 *              1) leaf 노드인데 연산자인 경우
 *              2) internal 노드인데 숫자인 경우
 *
 *      1-3. N개의 입력을 보며,
 *          1-3-1. 입력 문자열의 토큰 수를 보며 leaf 노드인지 검사한다.
 *          1-3-2. 입력 문자열의 두번째 요소를 보며 isOperator 메서드를 통해 연산자인지 검사한다.
 *      1-4. 계산이 불가능한 경우 flushInput 메서드를 통해 남은 입력을 처리하고 valid를 0으로 설정한 후 반복문을 탈출한다.
 *      1-5. 테스트 케이스 번호와 함께 계산 가능 여부를 출력한다.
 *
 */
class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final String OPERATORS = "+-*/";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for(int testCase = 1; testCase <= 10; testCase++) {
            st = new StringTokenizer(br.readLine().trim());
            sb = new StringBuilder();
            sb.append("#").append(testCase).append(" ");

            int N = Integer.parseInt(st.nextToken());
            if(N%2 == 0) {
                flushInput(N);
                sb.append(0);
                System.out.println(sb.toString());
                continue;
            }

            int valid = 1;
            for(int iter = 0; iter < N; iter++) {
                st = new StringTokenizer(br.readLine().trim());

                int tokenCnt = st.countTokens();

                st.nextToken(); // 노드 번호
                String data = st.nextToken();

                if(tokenCnt == 2) { // leaf 노드
                    if(isOperator(data)) { // 연산자
                        flushInput(N - iter - 1);
                        valid = 0;
                        break;
                    }
                }
                else { // tokenCnt == 4 -> internal 노드
                    if(!isOperator(data)) { // 숫자
                        flushInput(N - iter - 1);
                        valid = 0;
                        break;
                    }
                }
            }

            sb.append(valid);
            System.out.println(sb.toString());
        }
    }

    static void flushInput(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            br.readLine();
        }
    }

    static boolean isOperator(String substr) {
        int idx = OPERATORS.indexOf(substr);
        return idx >= 0;
    }
}
