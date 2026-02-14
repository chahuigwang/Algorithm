package BOJ.boj1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int a;
    static int b;

    static int onesPlace;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int numTestCase = Integer.parseInt(br.readLine().trim());
        for(int testCaseIdx = 0; testCaseIdx < numTestCase; testCaseIdx++) {
            init();
            getOnesPlace(a, b);
            if (onesPlace == 0) {
                System.out.println(10);
            } else {
                System.out.println(onesPlace);
            }
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        onesPlace = 1;
    }

    static void getOnesPlace(int a, int b) {
        for(int i = 0; i < b; i++) {
            onesPlace = (onesPlace * a) % 10;
        }
    }
}
