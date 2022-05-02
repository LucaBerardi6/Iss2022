package unibo.wenvUsage22.annot.walker.prototipo;

 
import unibo.actor22comm.utils.ColorsOut;

public class GuardLeftPrototipo {
	protected static int vn ; 
  	
	public static void setValue( int n ) {
		vn = n;
	}
	public static boolean checkValue(   ) {
		
 		if(vn%2==0)
 			return false;
 		else
 			return true;
	}
 	public boolean eval( ) {
 		boolean b = checkValue();
 		ColorsOut.outappl("GuardLeftPrototipo eval="+b , ColorsOut.CYAN);
 		return b;
	}

}
