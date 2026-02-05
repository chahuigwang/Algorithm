package SWEA.swea2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize; // 벌통의 가로, 세로 칸 수
    static int numSelectBucket; // 선택할 수 있는 벌통의 개수
    static int maxHoneyAmount; // 꿀을 채취할 수 있는 최대 양

    static int[][] honeyMap; // 꿀의 양을 저장하는 2차원 배열

    static class SelectArea {
        int row;
        int startCol;
        int profit;

        SelectArea(int row, int startCol) {
            this.row = row;
            this.startCol = startCol;
            profit = calculateProfit(row, startCol);
        }

        boolean isOverlapped(SelectArea other) {
            if (this.row != other.row) return false;

            int thisEnd = this.startCol + numSelectBucket - 1;
            int otherEnd = other.startCol + numSelectBucket - 1;

            return !(thisEnd < other.startCol || otherEnd < this.startCol);
        }
    }

    static int maxAreaProfit; // 선택한 구간에서의 최대 수익

    static List<SelectArea> selectAreaList;

    static int maxTotalProfit;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numTestCase = Integer.parseInt(br.readLine().trim());

        for(int testCaseNo = 1; testCaseNo <= numTestCase; testCaseNo++) {
            st = new StringTokenizer(br.readLine().trim());

            mapSize = Integer.parseInt(st.nextToken());
            numSelectBucket = Integer.parseInt(st.nextToken());
            maxHoneyAmount = Integer.parseInt(st.nextToken());

            honeyMap = new int[mapSize][mapSize];
            for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                    honeyMap[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
            }

            selectAreaList = new ArrayList<>();
            for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
                for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                    if((colIdx + numSelectBucket - 1) < mapSize) {
                        selectAreaList.add(new SelectArea(rowIdx, colIdx));
                    }
                }
            }

            maxTotalProfit = 0;
            for(int i = 0; i < selectAreaList.size(); i++) {
                for(int j = i+1; j < selectAreaList.size(); j++) {
                    if(!selectAreaList.get(i).isOverlapped(selectAreaList.get(j))) {
                        int currentTotalProfit = selectAreaList.get(i).profit + selectAreaList.get(j).profit;
                        maxTotalProfit = Math.max(maxTotalProfit, currentTotalProfit);
                    }
                }
            }
            sb.append("#").append(testCaseNo).append(" ").append(maxTotalProfit).append("\n");
        }
        System.out.println(sb);
    }

    static int calculateProfit(int row, int startCol) {
        int[] arr = new int[numSelectBucket];
        for(int idx = 0; idx < numSelectBucket; idx++) {
            arr[idx] = honeyMap[row][startCol + idx];
        }

        maxAreaProfit = 0;
        subset(arr, 0, 0, 0);
        return maxAreaProfit;
    }

    static void subset(int[] arr, int idx, int sum, int profit) {
        if(sum > maxHoneyAmount) return;

        if(idx == numSelectBucket) {
            maxAreaProfit = Math.max(maxAreaProfit, profit);
            return;
        }

        int honey = arr[idx];
        subset(arr, idx + 1, sum + honey, profit + honey*honey); // 현재 idx를 선택한 경우
        subset(arr, idx + 1, sum, profit); // 현재 idx를 선택하지 않은 경우
    }
}
