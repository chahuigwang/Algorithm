public class Permutation {

    static int[] arr = {1, 2, 3};
    static boolean[] selected = new boolean[arr.length];
    static int[] perm = new int[2];
    static final int SELECT_COUNT = 2;

    public static void main(String[] args) {
        permutation(0);
    }

    static void permutation(int selectedCount) {
        if(selectedCount == SELECT_COUNT) {
            // 완성된 순열 처리
            for(int x : perm) {
                System.out.print(x + " ");
            }
            System.out.println();
            return;
        }

        for(int idx = 0; idx < arr.length; idx++) {
            if(selected[idx]) continue;

            selected[idx] = true;
            perm[selectedCount] = arr[idx];
            permutation(selectedCount + 1);
            selected[idx] = false; // 백트래킹
        }
    }
}
