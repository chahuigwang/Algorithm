import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int leftCount;
    static int rightCount;

    static int[][] combination;

    public static void main(String[] args) throws IOException {
        calcCombination();
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        for(int testCaseIdx = 0; testCaseIdx < testCaseCount; testCaseIdx++) {
            initTestCase();
            sb.append(combination[rightCount][leftCount]).append("\n");
        }
        System.out.println(sb);
    }

    static void initTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        leftCount = Integer.parseInt(st.nextToken());
        rightCount = Integer.parseInt(st.nextToken());
    }

    static void calcCombination() {
        combination = new int[30][30];
        for (int n = 0; n < 30; n++) {
            combination[n][0] = 1;
            for (int r = 1; r <= n; r++) {
                combination[n][r] = combination[n - 1][r - 1] + combination[n - 1][r];
            }
        }
    }
}
