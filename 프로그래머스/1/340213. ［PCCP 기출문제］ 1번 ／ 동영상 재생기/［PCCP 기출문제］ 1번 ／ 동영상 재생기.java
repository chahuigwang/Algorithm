class Solution {
	
	int curMin;
	int curSec;

	int endMin;
	int endSec;

	int opStartMin;
	int opStartSec;

	int opEndMin;
	int opEndSec;
	
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
    	String[] curMinSec = pos.split(":");
    	curMin = Integer.parseInt(curMinSec[0]);
        curSec = Integer.parseInt(curMinSec[1]);
        
        String[] endMinSec = video_len.split(":");
        endMin = Integer.parseInt(endMinSec[0]);
        endSec = Integer.parseInt(endMinSec[1]);
        
        String[] opStartMinSec = op_start.split(":");
        opStartMin = Integer.parseInt(opStartMinSec[0]);
        opStartSec = Integer.parseInt(opStartMinSec[1]);
        
        String[] opEndMinSec = op_end.split(":");
        opEndMin = Integer.parseInt(opEndMinSec[0]);
        opEndSec = Integer.parseInt(opEndMinSec[1]);
        
        for(String cmd : commands) {
        	skipOpening();
        	if(cmd.equals("prev")) backward();
        	else if(cmd.equals("next")) forward();
        }
        skipOpening();
        
        StringBuilder sb = new StringBuilder();
        if(curMin < 10) sb.append(0).append(curMin);
        else sb.append(curMin);
        
        sb.append(":");
        
        if(curSec < 10) sb.append(0).append(curSec);
        else sb.append(curSec);
        
    	String answer = sb.toString();
        return answer;
    }
    
    private void backward() {
    	// 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동
    	if(curMin == 0 && curSec < 10) curSec = 0;
    	else {
    		if(curSec < 10) {
    			curMin--;
    			curSec += 50;
    		}
    		else curSec -= 10;
    	}
    }
    
    private void forward() {
    	// 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동
    	if((curMin == endMin && endSec - curSec < 10) || (curMin == endMin - 1 && curSec - endSec > 50)) {
    		curMin = endMin;
    		curSec = endSec;
    	}
    	else {
    		if(curSec >= 50) {
    			curMin++;
    			curSec -= 50;
    		}
    		else curSec += 10;
    	}
    }
    
    private void skipOpening() {
    	// (op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동
    	if(((opStartMin < curMin) || (opStartMin == curMin && opStartSec <= curSec)) &&
    			((curMin < opEndMin) || (curMin == opEndMin && curSec <= opEndSec))) {
    		curMin = opEndMin;
    		curSec = opEndSec;
    	}
    }
}
