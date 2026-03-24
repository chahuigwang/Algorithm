import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Wire implements Comparable<Wire> {
		int start;
		int end;
		int cost;
		
		Wire(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Wire o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int computerCount;
	static int wireCount;
	
	static Wire[] wires;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(calcMinCost());
	}
	
	static void init() throws IOException {
		computerCount = Integer.parseInt(br.readLine().trim());
		wireCount = Integer.parseInt(br.readLine().trim());
		
		wires = new Wire[wireCount];
		int start, end, cost;
		for(int wireIdx = 0; wireIdx < wireCount; wireIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			wires[wireIdx] = new Wire(start, end, cost);
		}
		
		Arrays.sort(wires);
		
		parents = new int[computerCount + 1];
		makeSets();
	}
	
	static void makeSets() {
		for(int computer = 1; computer <= computerCount; computer++) {
			parents[computer] = computer;
		}
	}
	
	static int find(int computer) {
		if(parents[computer] == computer) return computer;
		return parents[computer] = find(parents[computer]);
	}
	
	static boolean union(int computer1, int computer2) {
		int rootComputer1 = find(computer1);
		int rootComputer2 = find(computer2);
		
		if(rootComputer1 == rootComputer2) return false;
		
		parents[rootComputer2] = rootComputer1;
		return true;
	}
	
	static int calcMinCost() {
		int totalCost = 0;
		int connectedWireCount = 0;
		for(Wire wire : wires) {
			if(union(wire.start, wire.end)) {
				totalCost += wire.cost;
				if(++connectedWireCount == computerCount - 1) break;
			}
		}
		return totalCost;
	}
}
