import java.util.*;

class Solution {
    final int[] PATTERN1 = {1, 2, 3, 4, 5};
    final int[] PATTERN2 = {2, 1, 2, 3, 2, 4, 2, 5};
    final int[] PATTERN3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    int maxScore = 0;
    List<Integer> maxScoreList = new ArrayList<>();
    
    public int[] solution(int[] answers) {
        int score1 = calculateScore(answers, PATTERN1);
        int score2 = calculateScore(answers, PATTERN2);
        int score3 = calculateScore(answers, PATTERN3);
        
        maxScore = Math.max(score1, Math.max(score2, score3));
        
        if(score1 == maxScore) maxScoreList.add(1);
        if(score2 == maxScore) maxScoreList.add(2);
        if(score3 == maxScore) maxScoreList.add(3);
        
        int[] answer = maxScoreList.stream()
                                    .mapToInt(Integer::intValue)
                                    .toArray();
        return answer;
    }
    
    int calculateScore(int[] answers, int[] pattern) {
        int answerCount = answers.length;
        int patternLength = pattern.length;
        
        int score = 0;
        for(int probIdx = 0; probIdx < answerCount; probIdx++) {
            if(answers[probIdx] == pattern[probIdx % patternLength]) score++;
        }
        
        return score;
    }
}