import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int studentCount; // 학생들의 수
	static int compareCount; // 키 비교 횟수
	
	static boolean[][] knowTaller; // 누가 키가 큰지 알고있는지 여부 ex) knowTaller[i][j] : j가 i보다 키가 크다.
	static int knowHeightRankStudentCount; // 자신의 키가 몇 번째인지 알 수 있는 학생들의 수
	
	public static void main(String[] args) throws IOException {
		init();
		compareHeight();
		getKnowHeightStudentCount();
		System.out.println(knowHeightRankStudentCount);
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		studentCount = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());
		
		knowTaller = new boolean[studentCount + 1][studentCount + 1];
		
		int shorter, taller;
		for(int compareIdx = 0; compareIdx < compareCount; compareIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			shorter = Integer.parseInt(st.nextToken());
			taller = Integer.parseInt(st.nextToken());
			knowTaller[shorter][taller] = true;
		}
		
		knowHeightRankStudentCount = 0;
	}

	static void compareHeight() {
		for(int mid = 1; mid <= studentCount; mid++) {
			for(int shorter = 1; shorter <= studentCount; shorter++) {
				for(int taller = 1; taller <= studentCount; taller++) {
					// mid가  shorter보다 크고, taller가 mid보다 큰 걸 알고 있다면 taller가 shorter보다 크다는 것을 알 수 있다.
					if(knowTaller[shorter][mid] && knowTaller[mid][taller]) knowTaller[shorter][taller] = true;
				}
			}
		}
	}
	
	static void getKnowHeightStudentCount() {
		int canCompareCount;
		for(int studentIdx = 1; studentIdx <= studentCount; studentIdx++) {
			canCompareCount = 0;
			for(int compStudentIdx = 1; compStudentIdx <= studentCount; compStudentIdx++) {
				if(studentIdx == compStudentIdx) continue;
				if(knowTaller[studentIdx][compStudentIdx] || knowTaller[compStudentIdx][studentIdx]) canCompareCount++;
			}
			if(canCompareCount == studentCount - 1) knowHeightRankStudentCount++;
		}
	}
}
