import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tests {
	
	static double[][] gain;
	static List<Integer>[] adjList;

	@Test
	public void test1() {
		int size = 6;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(2, 1, -1);
		takeInput(1, 2, 1);
		takeInput(1, 3, 1);
		takeInput(1, 4, 1);
		takeInput(2, 3, 1);
		takeInput(3, 2, -1);
		takeInput(3, 3, -1);
		takeInput(3, 4, 1);
		takeInput(4, 5, 1);

		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(1.25, ans, 0.0001);
	}
	
	@Test
	public void test2() {
		int size = 5;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(0, 2, 1);
		takeInput(1, 2, 1);
		takeInput(3, 1, -1);
		takeInput(3, 2, -1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);

		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.6667, ans, 0.0001);
	}

	@Test
	public void test3() {
		int size = 6;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 3, 1);
		takeInput(1, 2, 1);
		takeInput(2, 1, -1);
		takeInput(4, 1, -1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);
		takeInput(4, 3, -1);
		takeInput(4, 5, 1);


		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.3333, ans, 0.0001);
	}

	@Test
	public void test4() {
		int size = 7;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 2, 1);
		takeInput(2, 3, 1);
		takeInput(3, 5, 1);
		takeInput(4, 5, 1);
		takeInput(5, 6, 1);
		takeInput(1, 4, 1);
		takeInput(4, 4, -1);
		takeInput(3, 2, -1);
		takeInput(5, 3, -1);
		takeInput(5, 1, -1);

		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		System.out.println(ans);
		assertEquals(0.4, ans, 0.0001);
	}
	
	@Test
	public void test5() {
		int size = 9;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 2, 1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);
		takeInput(4, 5, 1);
		takeInput(5, 6, 1);
		takeInput(6, 7, 1);
		takeInput(7, 8, 1);
		takeInput(2, 7, 1);
		takeInput(5, 2, -1);
		takeInput(7, 4, -1);
		takeInput(7, 1, -1);


		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.5, ans, 0.0001);
	}
	
	@Test
	public void test6() {
		int size = 9;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 2, 1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);
		takeInput(4, 5, 1);
		takeInput(5, 6, 1);
		takeInput(6, 7, 1);
		takeInput(7, 8, 1);
		takeInput(3, 6, 1);
		takeInput(5, 7, 1);
		takeInput(5, 4, -1);
		takeInput(7, 5, -1);
		takeInput(6, 2, -1);
		takeInput(7, 1, -1);


		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.3333, ans, 0.0001);
	}

	@Test
	public void test7() {
		int size = 8;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 2, 1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);
		takeInput(4, 5, 1);
		takeInput(5, 6, 1);
		takeInput(6, 7, 1);
		takeInput(2, 1, -1);
		takeInput(4, 3, -1);
		takeInput(6, 5, -1);



		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.125, ans, 0.0001);
	}

	@Test
	public void test8() {
		int size = 10;
		gain = new double[size][size];
		adjList = new ArrayList[size];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		takeInput(0, 1, 1);
		takeInput(1, 2, 1);
		takeInput(2, 3, 1);
		takeInput(3, 4, 1);
		takeInput(4, 5, 1);
		takeInput(5, 6, 1);
		takeInput(6, 7, 1);
		takeInput(7, 8, 1);
		takeInput(8, 9, 1);
		takeInput(2, 1, -1);
		takeInput(4, 3, -1);
		takeInput(6, 5, -1);
		takeInput(8, 7, -1);


		Solver s = new Solver(gain, adjList);
		double ans  = s.solve();
		assertEquals(0.0625, ans, 0.00001);
	}

	
	private static void takeInput(int s, int d, double g) {
		adjList[s].add(d);
		gain[s][d] = g;
	}

}
