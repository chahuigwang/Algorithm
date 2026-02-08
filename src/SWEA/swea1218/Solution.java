package SWEA.swea1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    static BufferedReader br;
    static StringBuilder sb;

    static final char OPEN_SMALL = '(';
    static final char CLOSE_SMALL = ')';

    static final char OPEN_MEDIUM = '[';
    static final char CLOSE_MEDIUM = ']';

    static final char OPEN_LARGE = '{';
    static final char CLOSE_LARGE = '}';

    static final char OPEN_GL = '<';
    static final char CLOSE_GL = '>';

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for(int testCaseNo = 1; testCaseNo <= 10; testCaseNo++) {
            int length = Integer.parseInt(br.readLine().trim());

            String str = br.readLine().trim();

            int smallCount = 0; // 소괄호
            int mediumCount = 0; // 중괄호
            int largeCount = 0; // 대괄호
            int glCount = 0; // 대소 비교 괄호

            int valid = 1;

            for(int idx = 0; idx < length; idx++) {
                char cur = str.charAt(idx);
                switch(cur) {
                    case OPEN_SMALL:
                        smallCount++;
                        break;
                    case CLOSE_SMALL:
                        smallCount--;
                        break;
                    case OPEN_MEDIUM:
                        mediumCount++;
                        break;
                    case CLOSE_MEDIUM:
                        mediumCount--;
                        break;
                    case OPEN_LARGE:
                        largeCount++;
                        break;
                    case CLOSE_LARGE:
                        largeCount--;
                        break;
                    case OPEN_GL:
                        glCount++;
                        break;
                    case CLOSE_GL:
                        glCount--;
                        break;
                    default:
                        break;
                }
            }
            if(!(smallCount == 0 && mediumCount == 0 && largeCount == 0 && glCount == 0)) valid = 0;
            sb.append("#").append(testCaseNo).append(" ").append(valid).append("\n");
        }
        System.out.println(sb);
    }
}
