import java.util.ArrayList;
import java.util.List;

public class Solver {
    private int[][] adjMatrix;
    private List[] adjList;
    private List<List<Integer>> forwardPathList;
	private List<List<Integer>> loopsList;
    public List<List<Integer>> getForwardPathList() {
		return forwardPathList;
	}

	public List<List<Integer>> getLoopsList() {
		return loopsList;
	}

    public Solver(int[][] m, List[] list) {
        adjMatrix = m;
        adjList = list;
        forwardPathList = new ArrayList<>();
        loopsList = new ArrayList<>();
    }

    public void solve() {
    	getForwardPathsAndLoops(0, adjList.length - 1);
    }

    private void getForwardPathsAndLoops(int s, int d) {
        boolean[] isVisited = new boolean[adjList.length];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        forwardPathsAndLoopsUtil(s, d, isVisited, pathList);
    }

    private void forwardPathsAndLoopsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {
        isVisited[u] = true;
        if (u.equals(d)) {
            forwardPathList.add((List<Integer>) ((ArrayList<Integer>) localPathList).clone());
            isVisited[u] = false;
            return;
        }

        for (Integer i : (List<Integer>) adjList[u]) {
            if (!isVisited[i]) {
                localPathList.add(i);
                forwardPathsAndLoopsUtil(i, d, isVisited, localPathList);
                localPathList.remove(i);
            }else {// loop found
            	localPathList.add(i);
				cutAndAddLoop(localPathList);
				localPathList.remove(localPathList.size() - 1);
			}
        }
        isVisited[u] = false;
    }
    
	private void cutAndAddLoop(List<Integer> path) {
		Integer loopNode = path.get(path.size()-1);
		Integer index = path.size()-2;
		while(!path.get(index).equals(loopNode)) {
			index--;
		}
		List<Integer> loop = path.subList(index, path.size());
		if (!loopAlreadyExist(loop)) {
			loop = new ArrayList<Integer>(path.subList(index, path.size()));
			loopsList.add((List<Integer>) ((ArrayList<Integer>)loop).clone());
		}
	}
	
	/**
	 * Three condition to determine that 2 loops are not equivalent   
	 * 1 - not same size
	 * 2 - the old loop doesn't have the loop node of the new loop
	 * 3 - not the same path
	 * @param newLoop
	 * @return
	 */
	private boolean loopAlreadyExist(List<Integer> newLoop) {
		int startIndex;
		for (List<Integer> loop : loopsList) {
			if(loop.size() != newLoop.size()) {
				continue;
			}
			startIndex = -1;
			//find start index
			for (int i = 0; i < loop.size(); i++) {
				if (loop.get(i).equals(newLoop.get(0))) {
					startIndex = i;
					break;
				}
			}
			if (startIndex == -1) {
				continue;
			}
			int j;
			for (j = 0; j < newLoop.size() - 1; j++) {
				if (!newLoop.get(j).equals(loop.get(startIndex))) {
					break;
				}		
				++startIndex;
				startIndex %= (newLoop.size() - 1); 
			}
			//2 loops are identical
			if (j == newLoop.size() - 1) {
				return true;
			}
		}		
		return false;
	}

}
