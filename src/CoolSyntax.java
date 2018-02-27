import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CoolSyntax {

    public void method1(){
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();
        Collections.sort(a, Comparator.comparing(p -> p[0]));
        Collections.sort(b, Comparator.comparingInt(p -> p[0]));
        /* OR */
        a.sort(Comparator.comparingInt(p -> p[0]));
        b.sort(Comparator.comparingInt(p -> p[0]));

        PriorityQueue<String> q = new PriorityQueue<>((x, y) -> (y.length()-x.length()));
    }


}
