package quadtrees;

import java.io.IOException;
import java.io.Writer;

public class WhiteLeaf implements QuadTreeNode {

    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
       bitmap.fillArea(x, y, width, true);
    }

    @Override
    public void writeNode(Writer out) {
        try {
            out.append("01");
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }
}
