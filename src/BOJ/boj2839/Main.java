package BOJ.boj2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sugarKg = Integer.parseInt(br.readLine().trim());

        int[] numSugarBags = new int[sugarKg + 1];
        Arrays.fill(numSugarBags, -1);
        numSugarBags[0] = 0;

        int[] sugarBagKgs = {3, 5};
        for(int sugarBagKg : sugarBagKgs) {
            for(int kg = sugarBagKg; kg < sugarKg + 1; kg++) {
                if(numSugarBags[kg - sugarBagKg] != -1) numSugarBags[kg] = numSugarBags[kg - sugarBagKg] + 1;
            }
        }

        System.out.println(numSugarBags[sugarKg]);
    }
}
