class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
    	int endSec = attacks[attacks.length - 1][0];
    	
    	int finalHeath = calcFinalHealth(bandage, health, endSec, attacks);
    	
        int answer = (finalHeath <= 0) ? -1 : finalHeath;
        return answer;
    }
    
    private int calcFinalHealth(int[] bandage, int startHealth, int endSec, int[][] attacks) {
    	int health = startHealth;
    	int healStreak = 0;
    	int attackIdx = 0;
    	int attackCount = attacks.length;
    	for(int sec = 1; sec <= endSec; sec++) {
    		// 모든 공격이 끝났다면 종료
    		if(attackIdx == attackCount) break;
    		// 현재 시간에 공격 당하는 경우
    		if(sec == attacks[attackIdx][0]) {
    			health -= attacks[attackIdx][1];
    			// 죽으면 종료
    			if(health <= 0) return health;
    			healStreak = 0;
    			attackIdx++;
    		}
    		// 현재 시간에 공격 x
    		else {
    			healStreak++;
    			if(healStreak == bandage[0]) {
    				health += bandage[2];
    				healStreak = 0;
    			}
    			health += bandage[1];
    			health = Math.min(health, startHealth);
    		}
    	}
    	return health;
    }
}
