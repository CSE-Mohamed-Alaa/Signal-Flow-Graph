import java.util.ArrayList;
import java.util.List;

public class Solver {
    int[][] adjMatrix;
    List[] adjList;
    List<List<Integer>> forwardPathList;

    public Solver(int[][] m, List[] list) {
        adjMatrix = m;
        adjList = list;
        forwardPathList = new ArrayList<>();
    }

    public void solve() {
        getForwardPaths(0, adjList.length - 1);
    }

    public void getForwardPaths(int s, int d) {
        boolean[] isVisited = new boolean[adjList.length];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        forwardPathsUtil(s, d, isVisited, pathList);
    }

    private void forwardPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {
        isVisited[u] = true;
        if (u.equals(d)) {
            forwardPathList.add((List<Integer>) ((ArrayList<Integer>) localPathList).clone());
            isVisited[u] = false;
            return;
        }

        for (Integer i : (List<Integer>) adjList[u]) {
            if (!isVisited[i]) {
                localPathList.add(i);
                forwardPathsUtil(i, d, isVisited, localPathList);
                localPathList.remove(i);
            }
        }
        isVisited[u] = false;
    }

}
