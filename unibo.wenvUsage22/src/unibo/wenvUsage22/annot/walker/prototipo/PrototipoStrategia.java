package unibo.wenvUsage22.annot.walker.prototipo;

import it.unibo.kactor.IApplMessage;
import unibo.actor22.QakActor22FsmAnnot;
import unibo.actor22.annotations.State;
import unibo.actor22.annotations.Transition;
import unibo.actor22comm.SystemData;
import unibo.actor22comm.interfaces.Interaction2021;
import unibo.actor22comm.ws.WsConnection;

import unibo.wenvUsage22.annot.walker.WsConnWEnvObserver;
import unibo.wenvUsage22.common.VRobotMoves;

public class PrototipoStrategia extends QakActor22FsmAnnot  {
	private Interaction2021 conn;	
 	private int ncorner  = 0;

 	public PrototipoStrategia(String name) {
		super(name);
 	}
	
	@State( name = "robotStart", initial=true)
	@Transition( state = "robotMoving" ,  msgId = SystemData.endMoveOkId )
	@Transition( state = "wallDetected" , msgId = SystemData.endMoveKoId )
	protected void robotStart( IApplMessage msg ) {
		outInfo(""+msg + " connecting (blocking all the actors ) ... ");	
 		conn = WsConnection.create("localhost:8091" ); 	 
 		outInfo("connected "+conn);	
   		((WsConnection)conn).addObserver( new WsConnWEnvObserver(getName()) );
  		VRobotMoves.step(getName(),conn);
	}
	
 	@State( name = "robotMoving" )
 	@Transition( state = "robotMoving" ,  msgId = SystemData.endMoveOkId)
  	@Transition( state = "wallDetected" , msgId = SystemData.endMoveKoId )
	protected void robotMoving( IApplMessage msg ) {
		//outInfo(""+msg);	
		VRobotMoves.step(getName(),conn); 
 	}
 	
 	
 	
 	@State( name = "wallDetected" )
 	@Transition( state = "robotGoOne" , msgId = SystemData.endMoveOkId  )
	protected void wallDetected( IApplMessage msg ) {
		outInfo("ncorner="+ ncorner + " " + msg);	
		ncorner++;
		/*GuardContinueWork.setValue(ncorner);
		GuardEndOfWork.setValue(ncorner);*/
		GuardLeftPrototipo.setValue(ncorner);
		GuardRightPrototipo.setValue(ncorner);
		if(ncorner%2==0)
			 VRobotMoves.turnRight(getName(), conn);
		else VRobotMoves.turnLeft(getName(), conn);
		
	
 	} 
 	
 	
 	@State( name = "robotGoOne" )
 	@Transition( state = "robotTurnLeft" ,  msgId = SystemData.endMoveOkId,  guard=GuardLeftPrototipo.class)
 	@Transition( state = "robotTurnRight" ,  msgId = SystemData.endMoveOkId,  guard=GuardRightPrototipo.class)
 	@Transition( state = "endWork" ,  msgId = SystemData.endMoveKoId)
 	protected void robotGoUp( IApplMessage msg ) {	
 		VRobotMoves.step(getName(),conn); 
				
 	} 
 	
 	
 	

 	@State( name = "robotTurnLeft" )
 	@Transition( state = "robotMoving" ,  msgId = SystemData.endMoveOkId)
	protected void robotTurnLeft( IApplMessage msg ) {
		//outInfo(""+msg);	
 		outInfo("mi gioro");
 		
		VRobotMoves.turnLeft(getName(),conn);
 	}
 	
 	@State( name = "robotTurnRight" )
 	@Transition( state = "robotMoving" ,  msgId = SystemData.endMoveOkId)
	protected void robotTurnRight( IApplMessage msg ) {
		//outInfo(""+msg);	
 		outInfo("mi gioro");
		VRobotMoves.turnRight(getName(),conn);
 	}

 	/*
 	 * Transizioni condizionate (con guardie)
 	 */
// 	@State( name = "wallDetected" )
// 	protected void wallDetected( IApplMessage msg ) {
//		outInfo("ncorner="+ ncorner + " " + msg);	
//		ncorner++;
//		//Parte aggiunta al termine, per definire le transizioni
// 		if( ncorner == 4 ) {
// 			addTransition("endWork", null); //empty move
//  		}else {
//  			VRobotMoves.turnLeft(getName(), conn);
//  			addTransition("robotMoving",  SystemData.endMoveOkId);
//  		}
// 	}
 	
 	@State( name = "endWork" )
 	protected void endWork( IApplMessage msg ) {
 		//VRobotMoves.turnLeft(getName(), conn);
		outInfo("BYE" );	
		
 		System.exit(0);
 		
 	}
 	

}
