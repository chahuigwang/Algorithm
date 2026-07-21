import java.util.*;

class Solution {

    int[][] sortedSizes;
    int cardCount;
    
    public int solution(int[][] sizes) {
        cardCount = sizes.length;
        sortSizes(sizes);
        int answer = calculateMinWalletSize();
        return answer;
    }

    void sortSizes(int[][] sizes) {
        sortedSizes = new int[cardCount][2];
        
        int length1, length2;
        for(int cardIdx = 0; cardIdx < cardCount; cardIdx++) {
            length1 = sizes[cardIdx][0];
            length2 = sizes[cardIdx][1];
            
            sortedSizes[cardIdx][0] = Math.max(length1, length2);
            sortedSizes[cardIdx][1] = Math.min(length1, length2);
        }
        
        for(int[] a: sortedSizes) {
                System.out.println(Arrays.toString(a));
        }
    }
    
    int calculateMinWalletSize() {
        int width = 0;
        int height = 0;
        
        for(int cardIdx = 0; cardIdx < cardCount; cardIdx++) {
            width = Math.max(width, sortedSizes[cardIdx][0]);
        }

        for(int cardIdx = 0; cardIdx < cardCount; cardIdx++) {
            height = Math.max(height, sortedSizes[cardIdx][1]);
        }
        
        return width * height;
    }
}