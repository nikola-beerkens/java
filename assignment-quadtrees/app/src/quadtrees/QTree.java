package quadtrees;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class QTree {
	private QuadTreeNode root;

	public QTree(Reader input) {
		root = readQTree(input);
	}

	public QTree(Bitmap bitmap) {
		root = bitmap2QTree(0, 0, bitmap.getWidth(), bitmap);
	}

	public void fillBitmap(Bitmap bitmap) {
		root.fillBitmap(0, 0, bitmap.getWidth(), bitmap);
	}

	public void writeQTree(Writer sb) {
		root.writeNode(sb);
	}

	private static QuadTreeNode readQTree(Reader input) {
		try {
			if(input.read()=='1'){
				QuadTreeNode[] children = new QuadTreeNode[4];
				for (int i = 0; i < children.length; i++) {
					children[i] = readQTree(input);
				}
				return new GreyNode(children);
			} else if(input.read()=='0'){
				return new BlackLeaf();
			} else{
				return new WhiteLeaf();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new BlackLeaf();
		}
	}

	public static QuadTreeNode bitmap2QTree(int x, int y, int width, Bitmap bitmap) {
		
		QuadTreeNode[] children = new QuadTreeNode[4];
		if(width>1)
			width= width/2;
		else
			return returnNode(x, y, 1, bitmap, children);

		//square 1
		children[0] = bitmap2QTree(x, y, width, bitmap);
		//square 2
		children[1] = bitmap2QTree(x+width, y, width, bitmap);
		//square 3
		children[2] =bitmap2QTree(x+width, y+width, width, bitmap);
		//square 4
		children[3] =bitmap2QTree(x, y+width, width, bitmap);

		return returnNode(x, y, width, bitmap, children);
	}

	private static QuadTreeNode returnNode(int x, int y, int width, Bitmap bitmap,QuadTreeNode[] children){
		
		if(checkSquare(x,y,width,bitmap)){
			
			if(bitmap.getBit(x, y))
				return new WhiteLeaf();
			else
			 	return new BlackLeaf();
		}
		else{
			return new GreyNode(children);
		}
	}

	private static boolean checkSquare(int x, int y, int width, Bitmap bitmap){
		//true = white
		ArrayList<Boolean> arr = new ArrayList<Boolean>();
		for(int i = x; i<width; i++){
			for(int j=y; j<width;j++){
				arr.add(bitmap.getBit(i, j)); 
			}
		} 
		
		if (arr == null || arr.size() <= 1) {
			// If array is null or has only 1 element, it is considered to have the same elements.
			return true;
		}
		
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i) != arr.get(0)) {
				//System.out.println(arr[0]);
				return false;
			}
		}

		return true;
	}
}
