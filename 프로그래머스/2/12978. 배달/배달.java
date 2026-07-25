import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int townNo;
        int time;
        
        Node(int townNo, int time) {
            this.townNo = townNo;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }
    }
    
    List<Node>[] graph;
    
    int[] minTimes;
    final int INF = (int) 1e9;
    
    public int solution(int N, int[][] road, int K) {
        init(N, road);
        dijkstra(1, road);
        
        int answer = countCanDeliver(K);
        return answer;
    }
    
    void init(int N, int[][] road) {
        graph = new List[N+1];
        for(int townNo = 1; townNo <= N; townNo++) {
            graph[townNo] = new ArrayList<>();
        }
        
        int start, end, time;
        for(int roadIdx = 0; roadIdx < road.length; roadIdx++) {
            start = road[roadIdx][0];
            end = road[roadIdx][1];
            time = road[roadIdx][2];
            graph[start].add(new Node(end, time));
            graph[end].add(new Node(start, time));
        }
        
        minTimes = new int[N+1];
        Arrays.fill(minTimes, INF);
    }
    
    void dijkstra(int startTownNo, int[][] road) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startTownNo, 0));
        
        minTimes[startTownNo] = 0;
        
        Node curNode;
        int curTownNo;
        int curTime;
        int nextTownNo;
        int nextTime;
        while(!pq.isEmpty()) {
            curNode = pq.poll();
            curTownNo = curNode.townNo;
            curTime = curNode.time;
            
            if(minTimes[curTownNo] < curTime) continue;
            
            for(Node nextNode : graph[curTownNo]) {
                nextTownNo = nextNode.townNo;
                nextTime = minTimes[curTownNo] + nextNode.time;
                if(nextTime < minTimes[nextTownNo]) {
                    minTimes[nextTownNo] = nextTime;
                    pq.offer(new Node(nextTownNo, nextTime));
                }
            }
        }
    }
    
    int countCanDeliver(int K) {
        int count = 0;
        for(int minTime : minTimes) {
            if(minTime <= K) count++;
        }
        
        return count;
    }
}