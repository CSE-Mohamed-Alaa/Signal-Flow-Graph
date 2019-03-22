import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer>[] x = new ArrayList[7];
		for (int i = 0; i < x.length; i++) {
			x[i] = new ArrayList<Integer>();
		}
		x[0].add(1);
		x[0].add(2);
		x[0].add(3);
		x[1].add(0);
		x[1].add(3);
		x[2].add(3);
		x[3].add(0);
		x[3].add(4);
		x[3].add(5);
		x[4].add(4);
		x[4].add(5);
		x[5].add(4);
		x[5].add(6);
		Solver s = new Solver(null, x);
		s.solve();
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

}
