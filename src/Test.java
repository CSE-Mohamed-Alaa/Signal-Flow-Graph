import java.util.ArrayList;
import java.util.List;

public class Test {
	static double[][] gain;
	static List<Integer>[] x;

	public static void main(String[] args) {
		gain = new double[10][10];
		x = new ArrayList[10];
		for (int i = 0; i < x.length; i++) {
			x[i] = new ArrayList<Integer>();
		}
		takeInput(1, 2, 1);
		takeInput(0, 1, 1);
		takeInput(2,3,10);
		takeInput(3,4,1);
		takeInput(4,5,20);
		takeInput(5,6,1);
		takeInput(5,2,-5);
		takeInput(6,7,30);
		takeInput(7,8,40);
		takeInput(7,4,-1);
		takeInput(8,6,-1);
		takeInput(8,9,1);

//		x[0].add(1);
//		x[0].add(2);
//		x[0].add(3);
//		x[1].add(0);
//		x[1].add(3);
//		x[2].add(3);
//		x[3].add(0);
//		x[3].add(4);
//		x[3].add(5);
//		x[4].add(4);
//		x[4].add(5);
//		x[5].add(4);
//		x[5].add(6);
		Solver s = new Solver(gain, x);
		System.out.println(s.solve());
		List<List<Integer>> f = s.getForwardPathList();
		List<List<Integer>> l = s.getLoopsList();

		for (int i = 0; i < f.size(); i++) {
			System.out.println(f.get(i));
		}
		System.out.println("------------------------------");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}

	}

	private static void takeInput(int s, int d, double g) {
		x[s].add(d);

		gain[s][d] = g;
	}

}
