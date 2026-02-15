package BOJ.boj10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numMyCard;
    static int[] myCards;

    static int numTargetCard;
    static int[] targetCards;

    static int[] targetCardCount;

    public static void main(String[] args) throws IOException {
        init();
        getTargetCount();
        printTargetCardCount();
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        numMyCard = Integer.parseInt(br.readLine().trim());
        myCards = new int[numMyCard];
        st = new StringTokenizer(br.readLine().trim());
        for(int myCardIdx = 0; myCardIdx < numMyCard; myCardIdx++) {
            myCards[myCardIdx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(myCards);

        numTargetCard = Integer.parseInt(br.readLine().trim());
        targetCards = new int[numTargetCard];
        st = new StringTokenizer(br.readLine().trim());
        for(int targetCardIdx = 0; targetCardIdx < numTargetCard; targetCardIdx++) {
            targetCards[targetCardIdx] = Integer.parseInt(st.nextToken());
        }

        targetCardCount = new int[numTargetCard];
    }

    static int getLowerBound(int target) {
        int start = 0, end = numMyCard;

        int mid;
        while(start < end) {
            mid = (start + end) / 2;

            if(myCards[mid] < target) start = mid + 1;
            else end = mid;
        }

        return start;
    }

    static int getUpperBound(int target) {
        int start = 0, end = numMyCard;

        int mid;
        while(start < end) {
            mid = (start + end) / 2;

            if(myCards[mid] <= target) start = mid + 1;
            else end = mid;
        }

        return start;
    }

    static void getTargetCount() {
        int target, lowerBound, upperBound;
        for(int targetCardIdx = 0; targetCardIdx < numTargetCard; targetCardIdx++) {
            target = targetCards[targetCardIdx];
            lowerBound = getLowerBound(target);
            upperBound = getUpperBound(target);

            targetCardCount[targetCardIdx] = upperBound - lowerBound;
        }
    }

    static void printTargetCardCount() {
        sb = new StringBuilder();
        for(int count : targetCardCount) {
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }
}

