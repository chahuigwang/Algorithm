import java.util.*;

class Solution {
    
    Set<Integer> primeSet = new HashSet<>();
    
    int numberLength;
    int selectCount;
    boolean[] selected;
    
    public int solution(String numbers) {
        numberLength = numbers.length();
        selected = new boolean[numberLength];
        for(selectCount = 1; selectCount <= numberLength; selectCount++) {
            findPrime(0, numbers, "");
            Arrays.fill(selected, false);
        }
        
        int answer = primeSet.size();
        return answer;
    }
    
    void findPrime(int selectedCount, String numbers, String numStr) {
        if(selectedCount == selectCount) {
            int num = Integer.parseInt(numStr);
            if(isPrime(num)) primeSet.add(num);
            return;
        }
        
        for(int numIdx = 0; numIdx < numberLength; numIdx++) {
            if(selected[numIdx]) continue;
            
            selected[numIdx] = true;
            findPrime(selectedCount + 1, numbers, numStr + numbers.substring(numIdx, numIdx + 1));
            
            selected[numIdx] = false;
        }
    }
    
    boolean isPrime(int num) {
        if(num < 2) return false;
        if(num == 2) return true;
        if(num % 2 == 0) return false;
        
        for(int divisor = 3; (long) divisor * divisor <= num; divisor += 2) {
            if(num % divisor == 0) return false;
        }
        
        return true;
    }
}