package geometric;

import java.util.Comparator;

public class CompareY implements Comparator<Geometric>{

    public CompareY(){
        
    }
    @Override
    public int compare(Geometric o1, Geometric o2) {
        if(o1.bottomBorder()<o2.bottomBorder())
        return -1;
    else if(o1.bottomBorder()>o2.bottomBorder())
        return 1;
    else
        return 0;

    }
    
}
