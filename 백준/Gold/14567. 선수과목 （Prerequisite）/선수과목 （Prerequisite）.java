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
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int subjectCount;
    static int prerequisiteCount;

    static List<Integer>[] subjectGraph;
    static int[] indegree; // 진입차수
    static int[] semesterCount;

    public static void main(String[] args) throws IOException {
        init();
        topologicalSort();
        printSemesterCount();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        subjectCount = Integer.parseInt(st.nextToken());
        prerequisiteCount = Integer.parseInt(st.nextToken());

        subjectGraph = new ArrayList[subjectCount + 1];
        for(int subjectIdx = 1; subjectIdx <= subjectCount; subjectIdx++) {
            subjectGraph[subjectIdx] = new ArrayList<>();
        }

        indegree = new int[subjectCount + 1];

        for(int prerequisiteIdx = 0; prerequisiteIdx < prerequisiteCount; prerequisiteIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            subjectGraph[pre].add(post);
            indegree[post]++;
        }

        semesterCount = new int[subjectCount + 1];
    }

    static void topologicalSort() {
        Queue<Integer> queue = new ArrayDeque<>();

        for(int subjectIdx = 1; subjectIdx <= subjectCount; subjectIdx++) {
            if(indegree[subjectIdx] == 0) queue.offer(subjectIdx);
        }

        int curSemesterCount = 0;
        int queueSize;
        int curSubject;
        while(!queue.isEmpty()) {
            queueSize = queue.size();
            curSemesterCount++;

            while(queueSize-- > 0) {
                curSubject = queue.poll();
                semesterCount[curSubject] = curSemesterCount;

                for(int nextSubject : subjectGraph[curSubject]) {
                    indegree[nextSubject]--;

                    if(indegree[nextSubject] == 0) {
                        queue.offer(nextSubject);
                    }
                }
            }
        }
    }

    static void printSemesterCount() {
        for(int subjectIdx = 1; subjectIdx <= subjectCount; subjectIdx++) {
            sb.append(semesterCount[subjectIdx]).append(" ");
        }
        System.out.println(sb);
    }
}
