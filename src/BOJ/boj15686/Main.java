package BOJ.boj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Home {
        int row;
        int col;
        int chickenDist;

        Home(int row, int col) {
            this.row = row;
            this.col = col;
        }

        void setChickenDist() {
            int minDist = Integer.MAX_VALUE;
            for(ChickenStore chickenStore : selectedChickenStores) {
                int curDist = Math.abs(this.row - chickenStore.row) + Math.abs(this.col - chickenStore.col);
                minDist = Math.min(minDist, curDist);
            }
            this.chickenDist = minDist;
        }
    }

    static class ChickenStore {
        int row;
        int col;

        ChickenStore(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int mapSize;
    static int maxChickenStores;

    static final List<Home> homeList = new ArrayList<>();
    static final List<ChickenStore> chickenStoreList = new ArrayList<>();

    static int minCityChickenDist = Integer.MAX_VALUE;
    static final LinkedList<ChickenStore> selectedChickenStores = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        maxChickenStores = Integer.parseInt(st.nextToken());

        for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < mapSize; colIdx++) {
                int currentPoint = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 집, 2는 치킨집
                if(currentPoint == 1) {
                    homeList.add(new Home(rowIdx, colIdx));
                }
                else if(currentPoint == 2) {
                    chickenStoreList.add(new ChickenStore(rowIdx, colIdx));
                }
            }
        }
        combination(0, 0);
        System.out.println(minCityChickenDist);
    }

    static void combination(int selectedCount, int start) {
        if(selectedCount == maxChickenStores) {
            minCityChickenDist = Math.min(minCityChickenDist, calculateCityChickenDist());
            return;
        }

        for(int idx = start; idx < chickenStoreList.size(); idx++) {
            selectedChickenStores.add(chickenStoreList.get(idx));
            combination(selectedCount + 1, idx + 1);
            selectedChickenStores.removeLast();
        }
    }

    static int calculateCityChickenDist() {
        int totalDist = 0;
        for(Home home : homeList) {
            home.setChickenDist();
            totalDist += home.chickenDist;
        }
        return totalDist;
    }
}
