import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int studentCount;
	static int compareCount;
	
	static List<Integer>[] studentGraph;
	
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		init();
		topologicalSort();
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		studentCount = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());
		
		studentGraph = new List[studentCount + 1];
		for(int studentNo = 0; studentNo <= studentCount; studentNo++) {
		    studentGraph[studentNo] = new ArrayList<Integer>();
		}
		
		indegree = new int[studentCount + 1];
		
		int shorter, taller;
		for(int compareIdx = 0; compareIdx < compareCount; compareIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			shorter = Integer.parseInt(st.nextToken());
			taller = Integer.parseInt(st.nextToken());
			
			studentGraph[shorter].add(taller);
			indegree[taller]++;
		}
	}
	
	static void topologicalSort() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int studentNo = 1; studentNo <= studentCount; studentNo++) {
			if(indegree[studentNo] == 0) queue.offer(studentNo);
		}
		
		int curStudentNo;
		while(!queue.isEmpty()) {
			curStudentNo = queue.poll();
			sb.append(curStudentNo).append(" ");
			
			for(int taller : studentGraph[curStudentNo]) {
				indegree[taller]--;
				
				if(indegree[taller] == 0) {
					queue.offer(taller);
				}
			}
		}
		
		System.out.println(sb);
	}
}
