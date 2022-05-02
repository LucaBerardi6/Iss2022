package unibo.wenvUsage22.annot.walker.prototipo;

 
import unibo.actor22comm.utils.ColorsOut;

public class GuardRightPrototipo {
	protected static int vn ; 
  	
	public static void setValue( int n ) {
		vn = n;
	}
	public static boolean checkValue(   ) {
		
 		if(vn%2==0)
 			return true;
 		else
 			return false;
	}
 	public boolean eval( ) {
 		boolean b = checkValue();
 		ColorsOut.outappl("GuardRightPrototipo eval="+b , ColorsOut.CYAN);
 		return b;
	}

}
