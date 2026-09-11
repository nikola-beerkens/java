package geometric;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class Assignment03Test {

	private Assignment03Tester tester;

	@Before
	public void resetTester() {
		tester = new Assignment03Tester();
	}

	@Test
	public void createCircle() {
		tester.createCircle(0, 0, 1);
		assertEquals(1.0, tester.topBorder(0), 0.0001);
		assertEquals(1.0, tester.rightBorder(0), 0.0001);
		assertEquals(-1.0, tester.bottomBorder(0), 0.0001);
		assertEquals(-1.0, tester.leftBorder(0), 0.0001);
		assertEquals(Math.PI, tester.area(0), 0.0001);
	}

	@Test
	public void createRectangle() {
		tester.createRectangle(50, 50, 10, 20);
		assertEquals(70, tester.topBorder(0), 0.0001);
		assertEquals(60, tester.rightBorder(0), 0.0001);
		assertEquals(50, tester.bottomBorder(0), 0.0001);
		assertEquals(50, tester.leftBorder(0), 0.0001);
		assertEquals(200, tester.area(0), 0.0001);
	}

	@Test
	public void multipleObjects() {
		tester.createCircle(0, 0, 1);
		tester.createRectangle(-10, -10, 20, 20);
		tester.createCircle(0, 0, 2);

		assertEquals(Math.PI, tester.area(0), 0.0001);
		assertEquals(400, tester.area(1), 0.0001);
		assertEquals(4 * Math.PI, tester.area(2), 0.0001);
	}

	@Test
	public void moveObject() {
		tester.createCircle(0, 0, 1);
		tester.createRectangle(-10, -10, 20, 20);
		tester.createCircle(0, 0, 2);

		assertEquals(1, tester.rightBorder(0), 0.0001);
		assertEquals(1, tester.topBorder(0), 0.0001);
		assertEquals(10, tester.rightBorder(1), 0.0001);
		assertEquals(10, tester.topBorder(1), 0.0001);
		assertEquals(2, tester.rightBorder(2), 0.0001);
		assertEquals(2, tester.topBorder(2), 0.0001);

		assertEquals(Math.PI, tester.area(0), 0.0001);
		assertEquals(400, tester.area(1), 0.0001);
		assertEquals(4 * Math.PI, tester.area(2), 0.0001);

		tester.move(0, 1.5, 2.7);
		tester.move(1, 1.5, 2.7);
		tester.move(2, 1.5, 2.7);

		// Area stays the same
		assertEquals(Math.PI, tester.area(0), 0.0001);
		assertEquals(400, tester.area(1), 0.0001);
		assertEquals(4 * Math.PI, tester.area(2), 0.0001);

		assertEquals(2.5, tester.rightBorder(0), 0.0001);
		assertEquals(3.7, tester.topBorder(0), 0.0001);
		assertEquals(11.5, tester.rightBorder(1), 0.0001);
		assertEquals(12.7, tester.topBorder(1), 0.0001);
		assertEquals(3.5, tester.rightBorder(2), 0.0001);
		assertEquals(4.7, tester.topBorder(2), 0.0001);
	}

	@Test
	public void sortByArea() {
		tester.createRectangle(-20, -20, 40, 40);
		tester.createCircle(0, 0, 19);
		tester.createRectangle(-10, -10, 20, 20);

		assertTrue(tester.area(0) > tester.area(1));
		assertTrue(tester.area(1) > tester.area(2));

		tester.sortByArea();

		assertTrue(tester.area(0) < tester.area(1));
		assertTrue(tester.area(1) < tester.area(2));
	}

	@Test
	public void sortByX() {
		tester.createRectangle(-10, -10, 20, 20);
		tester.createCircle(0, 0, 19);
		tester.createRectangle(-20, -20, 40, 40);

		assertTrue(tester.leftBorder(0) > tester.leftBorder(1));
		assertTrue(tester.leftBorder(1) > tester.leftBorder(2));

		tester.sortByX();

		assertTrue(tester.leftBorder(0) < tester.leftBorder(1));
		assertTrue(tester.leftBorder(1) < tester.leftBorder(2));
	}

	@Test
	public void sortByY() {
		tester.createRectangle(-10, -10, 20, 20);
		tester.createCircle(0, 0, 19);
		tester.createRectangle(-20, -20, 40, 40);

		assertTrue(tester.bottomBorder(0) > tester.bottomBorder(1));
		assertTrue(tester.bottomBorder(1) > tester.bottomBorder(2));

		tester.sortByY();

		assertTrue(tester.bottomBorder(0) < tester.bottomBorder(1));
		assertTrue(tester.bottomBorder(1) < tester.bottomBorder(2));
	}

}
