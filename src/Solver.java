import java.util.ArrayList;
import java.util.List;

public class Solver {
	private double[][] adjMatrix;
	private List[] adjList;
	private List<List<Integer>> forwardPathList;
	private List<List<Integer>> loopsList;
	

	public List<List<Integer>> getForwardPathList() {
		return forwardPathList;
	}

	public List<List<Integer>> getLoopsList() {
		return loopsList;
	}

	public Solver(double[][] m, List[] list) {
		adjMatrix = m;
		adjList = list;
		forwardPathList = new ArrayList<>();
		loopsList = new ArrayList<>();
	}

	public double solve() {
		getForwardPathsAndLoops(0, adjList.length - 1);
		double deltaD = delta(loopsList);
		double[] deltaN = new double[forwardPathList.size()];
		List<List<Integer>> tempLoops = new ArrayList<List<Integer>>();
		for (int i = 0; i < forwardPathList.size(); i++) {
			for (List<Integer> loop : loopsList) {
				if(!isTouchingEachOther(forwardPathList.get(i), loop)) {
					tempLoops.add(loop);
				}
			}
			deltaN[i] = delta(tempLoops);
		}
		
		double overAllGain = 0;
		//Summation of the numerator 
		for (int i = 0; i < deltaN.length; i++) {
			overAllGain += (deltaN[i]*pathGain(forwardPathList.get(i)));
		}
		overAllGain /= deltaD;
		return overAllGain;
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
			} else {// loop found
				localPathList.add(i);
				cutAndAddLoop(localPathList);
				localPathList.remove(localPathList.size() - 1);
			}
		}
		isVisited[u] = false;
	}

	private void cutAndAddLoop(List<Integer> path) {
		Integer loopNode = path.get(path.size() - 1);
		Integer index = path.size() - 2;
		while (!path.get(index).equals(loopNode)) {
			index--;
		}
		List<Integer> loop = path.subList(index, path.size());
		if (!loopAlreadyExist(loop)) {
			loop = new ArrayList<Integer>(path.subList(index, path.size()));
			loopsList.add((List<Integer>) ((ArrayList<Integer>) loop).clone());
		}
	}

	/**
	 * Three condition to determine that 2 loops are not equivalent 1 - not same
	 * size 2 - the old loop doesn't have the loop node of the new loop 3 - not the
	 * same path
	 * 
	 * @param newLoop
	 * @return
	 */
	private boolean loopAlreadyExist(List<Integer> newLoop) {
		int startIndex;
		for (List<Integer> loop : loopsList) {
			if (loop.size() != newLoop.size()) {
				continue;
			}
			startIndex = -1;
			// find start index
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
			// 2 loops are identical
			if (j == newLoop.size() - 1) {
				return true;
			}
		}
		return false;
	}

	private double delta(List<List<Integer>> loops) {
		double totalGain = 0;
		// max 32 loop because max value of int is 2^32-1
		int numOFCombinations = (int) Math.pow(2, loops.size());
		// if 2 loops touched in any combination skip it
		boolean twoTouched;
		// generate all combinations
		for (int i = 0; i < numOFCombinations; i++) {
			twoTouched = false;
			String loopCombination = getLoopCombination(i,loops.size());
			for (int j = 0; j < loops.size() - 1; j++) {
				if (loopCombination.charAt(j) == '0')
					continue;
				for (int j2 = j + 1; j2 < loops.size(); j2++) {
					if (loopCombination.charAt(j2) == '0')
						continue;
					if (isTouchingEachOther(loops.get(j), loops.get(j2))) {
						twoTouched = true;
						break;
					}
				}
				if (twoTouched)
					break;
			}
			if (!twoTouched) {
				totalGain += calculateCombinationGain(i, loops);
			}
		}
		return totalGain;
	}

	private boolean isTouchingEachOther(List<Integer> list1, List<Integer> list2) {
		for (Integer integer : list2) {
			if (list1.contains(integer)) return true;
		}
		return false;
	}
	
	private double calculateCombinationGain(int combination, List<List<Integer>> loops) {
		String loopCombination = getLoopCombination(combination,loops.size());
		//calculate sign
		double gain = (Integer.bitCount(combination) % 2 == 0) ? 1 : -1;
		for (int i = 0; i < loops.size(); i++) {
			if (loopCombination.charAt(i) == '1') {
				gain *= pathGain(loops.get(i));
			}
		}
		return gain;
	}
	
	private double pathGain(List<Integer> path) {
		double gain = 1;
		for (int i = 0; i < path.size() - 1; i++) {
			gain *= adjMatrix[path.get(i)][path.get(i+1)];
		}
		return gain;
	}
	
	private String getLoopCombination(int combination, int numOfLoops) {
		String loopCombination = Integer.toBinaryString(combination);
		if (loopCombination.length() < numOfLoops) {
			StringBuilder zeros = new StringBuilder("0");
			while((zeros.length() + loopCombination.length()) < numOfLoops) {
				zeros.append('0');
			}
			loopCombination = zeros.append(loopCombination).toString();
		}

		return loopCombination;
	}

}
