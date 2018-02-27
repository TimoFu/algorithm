package MultiThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class ExecuteThreadInParticularOrder {

    public static void main(String[] args){
        List<int[]> a = new ArrayList<>();
        a.add(new int[]{1,3});
        a.add(new int[]{2,4});
        a.add(new int[]{8,9});

        List<int[]> b = new ArrayList<>();
        b.add(new int[]{0,3});
        b.add(new int[]{2,8});
        b.add(new int[]{11,19});

        List<int[]> res = merge(a, b);
        System.out.println(Arrays.toString(res.toArray()));
    }

    public static List<int[]> merge(List<int[]> a, List<int[]> b){
        // validation
        if (a == null || a.size() == 0){
            return b;
        }
        if (b == null || b.size() == 0){
            return a;
        }

        a.sort(Comparator.comparingInt(p -> p[0]));
        b.sort(Comparator.comparingInt(p -> p[0]));

        /**
         *L1 [1,3] [4,5] [7,10] ...
         *L2 [2,4] [6,9] [12,15] ...
         */

        int i = 0, j = 0;
        int[] cur = a.get(i)[0] <= b.get(j)[0] ? a.get(i++) : b.get(j++);

        List<int[]> res = new ArrayList<>();

        while (i < a.size() || j < b.size()){
            int[] x = i < a.size() ? a.get(i) : null;
            int[] y = j < b.size() ? b.get(j) : null;
            if (x != null && x[0] < cur[1]){
                // x is overlapped;
                cur[1] = Math.max(cur[1], a.get(i++)[1]);
            }else if(y != null && y[0] < cur[1]){
                // y is overlapped;
                cur[1] = Math.max(cur[1], b.get(j++)[1]);
            }else{
                res.add(new int[]{cur[0], cur[1]});
                if (x != null && y != null){
                    cur = x[0] <= y[0] ? a.get(i++) : b.get(j++);
                }else if (x != null){
                    cur = a.get(i++);
                }else{
                    cur = b.get(j++);
                }
            }
        }
        res.add(new int[]{cur[0], cur[1]});

        return res;

    }


}
