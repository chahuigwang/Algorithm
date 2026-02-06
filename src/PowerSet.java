public class PowerSet {

    static int[] arr = {1, 2, 3};
    static boolean[] selected = new boolean[arr.length];

    public static void main(String[] args) {
        powerSet(0);
    }

    static void powerSet(int visitedIdx) {
        if(visitedIdx == arr.length) {
            for(int idx = 0; idx < arr.length; idx++) {
                if (selected[idx]) {
                    System.out.print(arr[idx] + " ");
                }
            }
            System.out.println();
            return;
        }

        selected[visitedIdx] = true;
        powerSet(visitedIdx + 1);

        selected[visitedIdx] = false;
        powerSet(visitedIdx + 1);
    }
}
