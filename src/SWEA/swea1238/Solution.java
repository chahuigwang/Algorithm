package SWEA.swea1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1238
 * @author chahuigwang
 *
 *    @see #main(String[])
 *    1. 10개의 각 테스트 케이스 마다,
 *
 *        @see #initTestCase()
 *        1-1. 연락망 데이터의 개수를 입력받는다.
 *        1-2. 시작하는 당번의 번호를 입력받는다.
 *        1-3. 연락망 정보를 저장할 리스트를 생성하고 초기화한다.
 *
 *        @see #bfs()
 *        1-4. 시작하는 당번의 번호를 시작으로 너비우선탐색을 진행한다.
 *        1-5. depth가 하나 늘어날 때마다 해당 depth에서의 번호가 가장 큰 사람을 구한다.
 *
 *        1-6. 테스트 케이스 번호와 함께 최대 depth에서의 번호가 가장 큰 사람의 번호를 출력한다.
 */

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numContact;
    static int start;
    static final int MAX_NUM_PEOPLE = 100;

    static List<List<Integer>> contacts;
    static boolean[] visited;

    static int maxPersonNo;

    public static void main(String[] args) throws IOException {
        for(int testCaseNo = 1; testCaseNo <= 10; testCaseNo++) {
            initTestCase();
            bfs();
            sb.append("#").append(testCaseNo).append(" ").append(maxPersonNo).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numContact = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        contacts = new ArrayList<List<Integer>>();
        for(int peopleIdx = 0; peopleIdx < MAX_NUM_PEOPLE + 1; peopleIdx++) {
            contacts.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine().trim());
        for(int contactIdx = 0; contactIdx < numContact / 2; contactIdx++) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            contacts.get(from).add(to);
        }

        visited = new boolean[MAX_NUM_PEOPLE + 1];
        maxPersonNo = 0;
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(start);
        visited[start] = true;

        int maxDepth = 0;
        int curDepth = 0;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            curDepth++;

            while(queueSize-- > 0) {
                int curPersonNo = queue.poll();

                for(int neighbor : contacts.get(curPersonNo)) {
                    if(!visited[neighbor]) {
                        if(curDepth > maxDepth) {
                            maxDepth = curDepth;
                            maxPersonNo = neighbor;
                        }
                        else maxPersonNo = Math.max(maxPersonNo, neighbor);

                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
        }
    }
}
