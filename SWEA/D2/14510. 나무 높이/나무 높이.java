import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author chahuigwang
 * SWEA 14510. 나무 높이
 * 1. 1성장이 필요한 횟수와 2성장이 필요한 횟수를 저장한다.
 * 2. (2성장이 필요한 횟수 > 1성장이 필요한 횟수 + 1) 이라면
 * 		쉬는 날이 발생하므로 2성장이 필요한 횟수 1개를 1성장이 필요한 횟수 2개로 변환한다.
 * 3. (1성장이 필요한 횟수 > 2성장이 필요한 횟수) 라면 최소 날짜 수 = 1성장이 필요한 횟수 * 2 - 1(마지막 날은 안쉬어도 되기 때문)
 * 		그렇지 않다면 최소 날짜 수 = 2성장이 필요한 횟수 * 2
 *
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
	
    static int treeCount;
    static int[] heightList;
    static int maxHeight;
    
	public static void main(String[] args) throws IOException {
		int testCaseCount = Integer.parseInt(br.readLine().trim());
		for(int testCaseNo = 1; testCaseNo <= testCaseCount; testCaseNo++) {
			init();
			sb.append("#").append(testCaseNo).append(" ").append(calcMinDayCount()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException {
		treeCount = Integer.parseInt(br.readLine().trim());
		
		heightList = new int[treeCount];
		maxHeight = 0;
		st = new StringTokenizer(br.readLine().trim());
		int height;
		for(int treeIdx = 0; treeIdx < treeCount; treeIdx++) {
			height = Integer.parseInt(st.nextToken());
			heightList[treeIdx] = height;
			maxHeight = Math.max(maxHeight, height);
		}
	}
	
	static int calcMinDayCount() {
		int diff;
		int needOneCount = 0, needTwoCount = 0;
		for(int height : heightList) {
			diff = maxHeight - height;
			needOneCount += diff % 2;
			needTwoCount += diff / 2;
		}
		
		while(needTwoCount > needOneCount + 1) {
			needTwoCount--;
			needOneCount += 2;
		}
		
		return (needOneCount > needTwoCount) ? needOneCount * 2 - 1 : needTwoCount * 2;
	}
}
