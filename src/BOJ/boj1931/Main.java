package BOJ.boj1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numMeeting = Integer.parseInt(br.readLine().trim());
        Meeting[] meetings = new Meeting[numMeeting];
        for(int meetingIdx = 0; meetingIdx < numMeeting; meetingIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            meetings[meetingIdx] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings);

        int meetingCount = 0;
        int availableAfter = 0;

        for(Meeting meeting : meetings) {
            if(meeting.start >= availableAfter) {
                availableAfter = meeting.end;
                meetingCount++;
            }
        }

        System.out.println(meetingCount);
    }
}
