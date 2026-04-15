class Solution {
	
	int curSecTime;

	int endSecTime;

	int opStartSecTime;

	int opEndSecTime;
	
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
    	curSecTime = timeStringToSecTime(pos);
    	
    	endSecTime = timeStringToSecTime(video_len);
    	
    	opStartSecTime = timeStringToSecTime(op_start);
    	
    	opEndSecTime = timeStringToSecTime(op_end);
    	
    	for(String command : commands) {
    		skipOpening();
    		if(command.equals("prev")) backward();
    		else if(command.equals("next")) forward();
    	}
    	skipOpening();
    	
    	String answer = secTimeToTimeString(curSecTime);
        return answer;
    }
    
    private int timeStringToSecTime(String timeString) {
    	String[] minSec = timeString.split(":");
    	int min = Integer.parseInt(minSec[0]);
    	int sec = Integer.parseInt(minSec[1]);
    	
    	return min*60 + sec;
    }
    
    private String secTimeToTimeString(int secTime) {
    	int min = secTime / 60;
    	int sec = secTime % 60;
    	
    	StringBuilder sb = new StringBuilder();
    	if(min < 10) sb.append(0).append(min);
    	else sb.append(min);
    	
    	sb.append(":");
    	
    	
    	if(sec < 10) sb.append(0).append(sec);
    	else sb.append(sec);
    	
    	return sb.toString();
    }
    
    private void backward() {
    	// 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동
    	if(curSecTime < 10) curSecTime = 0;
    	else curSecTime -= 10;
    }
    
    private void forward() {
    	// 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동
    	if(endSecTime - curSecTime < 10) curSecTime = endSecTime;
    	else curSecTime += 10;
    }
    
    private void skipOpening() {
    	if(opStartSecTime <= curSecTime && curSecTime <= opEndSecTime)
    		curSecTime = opEndSecTime;
    }
}
