import java.util.*;

public class AdventurerGuild {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arrayList.add(sc.nextInt());
        }
        Collections.sort(arrayList);

        int groupCount = 0;
        int memberCount = 0;
        for(int fear : arrayList) {
            memberCount++;
            if(memberCount >= fear) {
                groupCount++;
                memberCount = 0;
            }
        }
        System.out.println(groupCount);
    }
}
