package geometric;

import java.util.Arrays;

public class Functions {
    private static final int MAX_OBJECTS = 10;
    private final Geometric[] array = new Geometric[MAX_OBJECTS];
    private final Xcomp xcomp = new Xcomp();
    private final Ycomp ycomp = new Ycomp();
    private final Areacomp areacomp = new Areacomp();
    private int objectCount;

    public void index() {
    }

    public String show() {
        if (objectCount == 0) {
            return "Empty.";
        }
        return Arrays.toString(Arrays.copyOf(array, objectCount));
    }

    public void createCircle(double x, double y, double radius, int count) {
        if (objectCount < MAX_OBJECTS) {
            array[objectCount++] = new Circle(x, y, radius, count);
        }
    }

    public void createRectangle(double x, double y, double height, double length, int count) {
        if (objectCount < MAX_OBJECTS) {
            array[objectCount++] = new Rectangle(x, y, height, length, count);
        }
    }

    public void moveObject(int pointer, double dx, double dy) {
        if (pointer >= 0 && pointer < objectCount) {
            array[pointer].Move(dx, dy);
        }
    }

    public void remove(int pointer) {
        if (pointer >= 0 && pointer < objectCount) {
            System.arraycopy(array, pointer + 1, array, pointer, objectCount - pointer - 1);
            array[--objectCount] = null;
        }
    }

    public void sortByArea() {
        Arrays.sort(array, 0, objectCount, areacomp);
    }

    public void sortByX() {
        Arrays.sort(array, 0, objectCount, xcomp);
    }

    public void sortByY() {
        Arrays.sort(array, 0, objectCount, ycomp);
    }

    public int showNumber() {
        return objectCount;
    }

    Geometric getObject(int pointer) {
        if (pointer < 0 || pointer >= objectCount) {
            throw new IndexOutOfBoundsException("object index is out of bounds.");
        }
        return array[pointer];
    }
}
