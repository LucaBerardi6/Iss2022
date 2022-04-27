package unibo.wenvUsage22.wshttp;

import java.util.Observable;

import unibo.actor22comm.http.HttpConnection;
import unibo.actor22comm.interfaces.IObserver;
import unibo.actor22comm.interfaces.Interaction2021;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;
import unibo.actor22comm.ws.WsConnection;
import unibo.wenvUsage22.common.ApplData;

public class BoundaryWalkerWS implements IObserver{

	/*private  final String localHostName    = "localhost"; //"localhost"; 192.168.1.7
	private  final int port                = 8090;
	private  final String HttpURL          = "http://"+localHostName+":"+port+"/api/move";*/
	boolean obstacle = false;

	private Interaction2021 conn;
	

	
	protected void doJob() throws Exception {
  		
  		conn = WsConnection.create("localhost:8091" );
  		((WsConnection)conn).addObserver(this);

  	
  		for( int i=1; i<=4; i++ ) {
	  		while( ! obstacle ) {
	  			conn.forward( ApplData.moveForward(500) ); 
	  			CommUtils.delay(500);
	  		}
	  		obstacle = false;
	  		conn.forward( ApplData.turnLeft(300) );
  		}
 
	
 	}
	
	
	

	@Override
	public void update(Observable source, Object data) {
		ColorsOut.out("ClientUsingWs update/2 receives:" + data);
		obstacle = data.toString().contains("collision");
//		JSONObject d = new JSONObject(""+data);
//		ColorsOut.outappl("ClientUsingWs update/2 collision=" + d.has("collision"), ColorsOut.MAGENTA);
		
	}
	@Override
	public void update(String data) {
		ColorsOut.out("ClientUsingWs update receives:" + data);
		obstacle = data.toString().contains("collision");
	}	
	
	
	/*
	MAIN
	 */
		public static void main(String[] args) throws Exception   {
			CommUtils.aboutThreads("[BoundaryWalkerWS] Before start - ");
	 		new BoundaryWalkerWS().doJob();
			CommUtils.aboutThreads("[BoundaryWalkerWS] At end - ");
		}
		
}
