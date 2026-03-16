import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5607. [Professional] 조합
 * [문제]
 * - N combination R의 값을 1234567891로 나눈 나머지를 출력한다.
 * - 1 ≤ N ≤ 1000000, 0 ≤ R ≤ N
 * 
 * [전략]
 * - nCr = n! / (r! * (n-r)!)
 * - 모듈러 연산에서는 나눗셈을 그대로 할 수 없으므로 모듈러 역원을 사용한다.
 * - MOD가 소수이므로 페르마의 소정리를 이용한다.
 * - a^(MOD - 2) % MOD = a의 모듈러 역원
 * - 0 ~ 1000000 까지의 팩토리얼을 미리 구해둔다.
 * 
 * @author chahuigwang
 * 
 *  @see #main(String[])
 * 
 *  @see #makeFactorial()
 *  1. 0 ~ 1000000 까지의 팩토리얼 배열을 완성한다.
 * 
 *  2. 테스트 케이스 개수를 입력받는다.
 *  3. 각 테스트 케이스마다,
 *  
 *      @see #initTestCase()
 *      3-1. n과 r을 입력받는다.
 *      
 *      @see #combination(int, int)
 *      3-2. nCr 값을 계산한다.
 *
 */
class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int n, r;
	
	static final int MAX_N = 1_000_000;
	static final long MOD = 1_234_567_891L;
	static final long[] factorial = new long[MAX_N + 1];
	
	public static void main(String[] args) throws IOException {
		// 1. 0 ~ 1000000 까지의 팩토리얼 배열을 완성한다.
		makeFactorial();
		
		// 2. 테스트 케이스 개수를 입력받는다.
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		
		// 3. 각 테스트 케이스마다,
		for (int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			initTestCase();
			sb.append("#").append(testCaseNo).append(" ").append(combination(n, r)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void initTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
	}
	
	static void makeFactorial() {
		factorial[0] = 1;
		
		for (int i = 1; i <= MAX_N; i++) {
			factorial[i] = (factorial[i - 1] * i) % MOD;
		}
	}
	
	static long combination(int n, int r) {
		long denominator = (factorial[r] * factorial[n - r]) % MOD;
		long inverse = power(denominator, MOD - 2);
		
		return (factorial[n] * inverse) % MOD;
	}
	
	static long power(long number, long exponent) {
		long result = 1;
		
		while (exponent > 0) {
			if ((exponent & 1) == 1) {
				result = (result * number) % MOD;
			}
			
			number = (number * number) % MOD;
			exponent >>= 1;
		}
		
		return result;
	}
}