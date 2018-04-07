package Tree.NArayTree;

import java.util.ArrayList;
import java.util.List;

public class LCA {

    public Employee LCA(Employee root, Employee a, Employee b){
        List<Employee> aPath = new ArrayList<>();
        List<Employee> bPath = new ArrayList<>();

        dfs(root, a, aPath);
        System.out.println(aPath);
        dfs(root, b, bPath);
        System.out.println(bPath);

        int i = 0;
        while (i < aPath.size() && i < bPath.size()){
            if (aPath.get(i) == bPath.get(i)){
                return aPath.get(i);
            }
            i ++;
        }

        return null;
    }

    private boolean dfs(Employee root, Employee a, List<Employee> path){
        if (root == null) return false;

        path.add(root);

        if (root == a) {
            return true;
        }

        if (root.team == null){
            return false;
        }

        for (Employee next : root.team){
            if (dfs(next, a, path)){
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }


    class Employee{
        public int id;
        public List<Employee> team;

        public Employee(int id){
            this.id = id;
            this.team = new ArrayList<>();
        }

        @Override
        public String toString(){
            return id + "";
        }
    }
}
