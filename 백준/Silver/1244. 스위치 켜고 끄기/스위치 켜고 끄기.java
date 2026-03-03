import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Student {
		int sex;
		int num;
		
		Student(int sex, int num) {
			this.sex = sex;
			this.num = num;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int ledCount;
	static int[] ledStatus;
	static int studentCount;
	static List<Student> students = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		changeLedStatus();
		printAnswer();
	}

	static void init() throws IOException {
		ledCount = Integer.parseInt(br.readLine().trim());
		
		ledStatus = new int[ledCount];
		st = new StringTokenizer(br.readLine().trim());
		for(int ledIdx = 0; ledIdx < ledCount; ledIdx++) {
			ledStatus[ledIdx] = Integer.parseInt(st.nextToken());
		}
		
		studentCount = Integer.parseInt(br.readLine().trim());
		for(int studentIdx = 0; studentIdx < studentCount; studentIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			int curStudentSex = Integer.parseInt(st.nextToken());
			int curStudentNum = Integer.parseInt(st.nextToken());
			students.add(new Student(curStudentSex, curStudentNum));
		}
	}
	
	static void printAnswer() {
		for(int ledIdx = 0; ledIdx < ledCount; ledIdx++) {
			sb.append(ledStatus[ledIdx]);
			if((ledIdx + 1) % 20 == 0) sb.append("\n");
			else sb.append(" ");
		}
		System.out.println(sb);
	}
	
	static void changeLedStatus() {
		int curStudentSex, curStudentNum;
		for(Student curStudent : students) {
			curStudentSex = curStudent.sex;
			curStudentNum = curStudent.num;
			if(curStudentSex == 1) maleChangeLedStatus(curStudentNum);
			else if(curStudentSex == 2) femaleChangeLedStatus(curStudentNum - 1);
		}
	}
	
	static void reverseLed(int idx) {
		ledStatus[idx] = (ledStatus[idx] + 1) % 2;
	}
	
	static void maleChangeLedStatus(int num) {
		int changeCount = 0;
		int targetIdx;
		while(true) {
			changeCount++;
			targetIdx = num * changeCount - 1;
			if(targetIdx > ledCount - 1) break;
			reverseLed(targetIdx);
		}
	}
	
	static void femaleChangeLedStatus(int idx) {
		int left, right;
		int changeCount = 0;
		reverseLed(idx);
		while(true) {
			changeCount++;
			left = idx - changeCount;
			right = idx + changeCount;
			if(left < 0 || right > ledCount - 1) break;
			if(ledStatus[left] != ledStatus[right]) break;
			reverseLed(left);
			reverseLed(right);
		}
	}
}
