package geometric;

import java.util.Comparator;

public class CompareX implements Comparator<Geometric> {

    public CompareX(){

    }

    @Override
    public int compare(Geometric o1, Geometric o2) {
        if(o1.leftBorder()<o2.leftBorder())
        return -1;
    else if(o1.leftBorder()>o2.leftBorder())
        return 1;
    else
        return 0;

    }
    
    
}
