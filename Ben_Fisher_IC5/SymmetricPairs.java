package Ben_Fisher_IC5;
import java.util.ArrayList;
import java.util.HashMap;
public class SymmetricPairs {
    public static ArrayList<int[]> FindPairs(int[][] pairs){
        HashMap<Integer, Integer> pairMap = new HashMap<>();
        ArrayList<int[]> res = new ArrayList<>();
        for(int[]pair : pairs){
            if(pairMap.containsKey(pair[1]) && pairMap.get(pair[1]) == pair[0]){
                res.add(new int[]{pair[1], pair[0]});
            }
            else{
                pairMap.put(pair[0], pair[1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] pairs = {{11, 20}, {30, 40}, {5, 10}, {40, 30}, {10,5}};
        ArrayList<int[]> symmetricPairs = FindPairs(pairs);
        System.out.println("Symmetric pairs:");
        for (int[] pair : symmetricPairs) {
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        }
    }
}
