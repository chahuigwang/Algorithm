class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int minLevel = Integer.MAX_VALUE;
        int start = 1;
        int end = 100_000;
        int mid;
        while(start <= end) {
        	mid = (start + end) / 2;
        	if(canSolve(diffs, times, limit, mid)) {
        		minLevel = Math.min(minLevel, mid);
        		end = mid - 1;
        	}
        	else {
        		start = mid + 1;
        	}
        }
        return minLevel;
    }
    
    private boolean canSolve(int[] diffs, int[] times, long limit, int level) {
    	int diff, timeCur, timePrev;
    	int wrongCount;
    	long totalTime = 0;
    	for(int puzzleIdx = 0; puzzleIdx < diffs.length; puzzleIdx++) {
    		diff = diffs[puzzleIdx];
    		timeCur = times[puzzleIdx];
    		if(diff <= level) {
    			totalTime += timeCur;
    		}
    		else {
    			timePrev = (puzzleIdx >= 1) ? times[puzzleIdx - 1] : 0;
    			wrongCount = diff - level;
    			totalTime += (timeCur + timePrev) * wrongCount + timeCur;
    		}
    	}
    	return totalTime <= limit;
    }
}
