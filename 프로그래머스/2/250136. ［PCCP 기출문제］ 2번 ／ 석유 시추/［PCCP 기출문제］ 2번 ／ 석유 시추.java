class Solution {

    int height;
    int width;

    final int OIL = 1;

    boolean[][] visited;

    final int[] DR = {-1, 1, 0, 0};
    final int[] DC = {0, 0, -1, 1};
    final int DIRECTION_COUNT = 4;

    int[][] oilGroupIds;
    int[] oilAmountList;
    int oilGroupId = 0;

    public int solution(int[][] land) {
        height = land.length;
        width = land[0].length;

        visited = new boolean[height][width];

        oilGroupIds = new int[height][width];
        oilAmountList = new int[height * width];
        
        makeOilGroups(land);

        int answer = getMaxOil(land);
        return answer;
    }

    private void makeOilGroups(int[][] land) {
        int oilAmount;
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                if(land[row][col] == OIL && !visited[row][col]) {
                    oilAmount = calcOilAmount(land, row, col, oilGroupId);
                    oilAmountList[oilGroupId] = oilAmount;
                    oilGroupId++;
                }
            }
        }
    }

    private int calcOilAmount(int[][] land, int row, int col, int id) {
        int count = 1;
        visited[row][col] = true;
        oilGroupIds[row][col] = id;

        int nextRow, nextCol;
        for(int direction = 0; direction < DIRECTION_COUNT; direction++) {
            nextRow = row + DR[direction];
            nextCol = col + DC[direction];
            if(isInRange(nextRow, nextCol) && land[nextRow][nextCol] == OIL && !visited[nextRow][nextCol]) {
                count += calcOilAmount(land, nextRow, nextCol, id);
            }
        }
        return count;
    }

    private boolean isInRange(int row, int col) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }

    private int getMaxOil(int[][] land) {
        boolean[] counted;
        int oilAmount;
        int maxOilAmount = Integer.MIN_VALUE;
        int id;
        for(int col = 0; col < width; col++) {
            counted = new boolean[oilGroupId];
            oilAmount = 0;
            for(int row = 0; row < height; row++) {
                if(land[row][col] == OIL) {
                    id = oilGroupIds[row][col];
                    if(!counted[id]) {
                        counted[id] = true;
                        oilAmount += oilAmountList[id];
                    }
                }
            }
            maxOilAmount = Math.max(maxOilAmount, oilAmount);
        }
        return maxOilAmount;
    }
}
