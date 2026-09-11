package geometric;

import org.junit.Ignore;

@Ignore("Test helper; covered by Assignment03Test")
public class Assignment03Tester {
    private final Functions functions = new Functions();

	public Assignment03Tester() {
	}

	public void createCircle(double x, double y, double r) {
		functions.createCircle(x, y, r, functions.showNumber() + 1);
	}

	public void createRectangle(double x, double y, double width, double height) {
		functions.createRectangle(x, y, height, width, functions.showNumber() + 1);
	}

	public double topBorder(int index) {
		return functions.getObject(index).topBorder();
	}

	public double rightBorder(int index) {
		return functions.getObject(index).rightBorder();
	}

	public double bottomBorder(int index) {
		return functions.getObject(index).bottomBorder();
	}

	public double leftBorder(int index) {
		return functions.getObject(index).leftBorder();
	}

	public double area(int index) {
		return functions.getObject(index).Area();
	}

	public void move(int index, double dx, double dy) {
		functions.moveObject(index, dx, dy);
	}

	public void sortByArea() {
		functions.sortByArea();
	}

	public void sortByX() {
		functions.sortByX();
	}

	public void sortByY() {
		functions.sortByY();
	}
}
