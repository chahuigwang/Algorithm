import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int cityCount;
	static int roadCount;
	static int getherCity;
	static int[][] minTime;
	
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		init();
		calcMinTime();
		System.out.println(calcMaxRoundTime());
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		cityCount = Integer.parseInt(st.nextToken());
		roadCount = Integer.parseInt(st.nextToken());
		getherCity = Integer.parseInt(st.nextToken());
		minTime = new int[cityCount + 1][cityCount + 1];
		for(int start = 1; start <= cityCount; start++) {
			for(int end = 1; end <= cityCount; end++) {
				if(start == end) minTime[start][end] = 0;
				else minTime[start][end] = INF;
			}
		}
		int start, end, time;
		for(int roadIdx = 0; roadIdx < roadCount; roadIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			minTime[start][end] = time;
		}
	}
	
	// 도시 간 최소 이동 시간
	static void calcMinTime() {
		for(int via = 1; via <= cityCount; via++) {
			for(int start = 1; start <= cityCount; start++) {
				for(int end = 1; end <= cityCount; end++) {
					if(minTime[start][end] > minTime[start][via] + minTime[via][end])
						minTime[start][end] = minTime[start][via] + minTime[via][end];
				}
			}
		}
	}
	
	// 최대 왕복 시간
	static int calcMaxRoundTime() {
		int maxRoundTime = -1;
		int roundTime;
		for(int cityIdx = 1; cityIdx <= cityCount; cityIdx++) {
			roundTime = minTime[cityIdx][getherCity] + minTime[getherCity][cityIdx];
			maxRoundTime = Math.max(maxRoundTime, roundTime);
		}
		return maxRoundTime;
	}
}