package slidingGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class SlidingGameTest {

	@Test
	public void testIsSolution() {
		int[] oneStep = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		SlidingGame g = new SlidingGame(oneStep);
		assertTrue(g.isSolution());
	}

	@Test
	public void testIsNotSolution() {
		int[] oneStep = { 1, 2, 3, 4, 5, 6, 7, 9, 8 };
		SlidingGame g = new SlidingGame(oneStep);
		assertFalse(g.isSolution());
	}

	@Test
	public void testToString() {
		int[] oneStep = { 1, 2, 3, 4, 5, 6, 7, 9, 8 };
		SlidingGame g = new SlidingGame(oneStep);
		assertEquals("1 2 3 \n4 5 6 \n7   8 \n", g.toString());
	}

	@Test
	public void testEquals() {
		int[] oneStep = { 1, 2, 3, 4, 5, 6, 7, 9, 8 };
		int[] twoStep = { 1, 2, 3, 4, 9, 6, 7, 5, 8 };
		SlidingGame gA = new SlidingGame(oneStep);
		SlidingGame gB = new SlidingGame(oneStep);
		SlidingGame gC = new SlidingGame(twoStep);
		assertEquals(gA, gB);
		assertNotEquals(gA, gC);
	}

	// If you implement hashCode as specified in the assignment text
	@Test
	public void testHashCode() {
		int[] oneStep = { 1, 2, 3, 4, 5, 6, 7, 9, 8 };
		int[] twoStep = { 1, 2, 3, 4, 9, 6, 7, 5, 8 };
		SlidingGame gA = new SlidingGame(oneStep);
		SlidingGame gB = new SlidingGame(twoStep);
		assertEquals(-1077916253, gA.hashCode());
		assertEquals(-1188738773, gB.hashCode());
	}

	@Test
	public void testSolving() {
		int[] twoSteps = { 1, 2, 3, 4, 5, 6, 9, 7, 8 };
		SlidingGame g = new SlidingGame(twoSteps);
		Solver s = new Solver(g);
		String result = s.solve();
		assertEquals("1 2 3 \n4 5 6 \n  7 8 \n\n" + "1 2 3 \n4 5 6 \n7   8 \n\n" + "1 2 3 \n4 5 6 \n7 8   \n\n",
				result);
	}

	// The solution has distance 0
	@Test
	public void testManhattanDistance0() {
		int[] solution = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		SlidingGame g = new SlidingGame(solution);
		assertEquals(0, g.getManhattanDistance());
	}

	// Only 1 is in the wrong place, 4 steps away from it's place
	@Test
	public void testManhattanDistance4() {
		int[] game = { 9, 2, 3, 4, 5, 6, 7, 8, 1 };
		SlidingGame g = new SlidingGame(game);
		assertEquals(4, g.getManhattanDistance());
	}

	// 1, 3 and 8 are out of place, for a total distance of 10
	@Test
	public void testManhattanDistance10() {
		int[] game = { 9, 2, 8, 4, 5, 6, 7, 3, 1 };
		SlidingGame g = new SlidingGame(game);
		assertEquals(10, g.getManhattanDistance());
	}

	// A configuration that is closer to the solution should compare less to
	// one further away
	@Test
	public void testCompareTo() {
		int[] dist1 = { 1, 2, 3, 4, 5, 6, 7, 9, 8 };
		int[] distAlso1 = { 1, 2, 3, 4, 5, 9, 7, 8, 6 };
		int[] dist10 = { 9, 2, 8, 4, 5, 6, 7, 3, 1 };
		SlidingGame gA = new SlidingGame(dist1);
		SlidingGame gB = new SlidingGame(dist10);
		SlidingGame gC = new SlidingGame(distAlso1);
		assertTrue(gA.compareTo(gB) < 0);
		assertTrue(gB.compareTo(gA) > 0);
		assertEquals(0, gA.compareTo(gA));

		// Two different configurations, but with the same manhattan distance
		assertEquals(0, gA.compareTo(gC));
	}
}
