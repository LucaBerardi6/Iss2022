/*
ClientUsingHttp.java
*/
package unibo.wenvUsage22.annot.walker.prototipo;
import unibo.actor22comm.SystemData;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.actor22.Qak22Context;
import unibo.actor22.annotations.Actor22;
import unibo.actor22.annotations.Context22;
import unibo.wenvUsage22.common.ApplData;

@Context22(name = "pcCtx", host = "localhost", port = "8083")
@Actor22(name = ApplData.robotName,contextName="pcCtx",implement = PrototipoStrategia.class)

//@Actor22(name = "sentinel",contextName="pcCtx",implement = Sentinel.class)
public class MainPrototipoStrategia { 
 	

	protected void configure() throws Exception {
 		Qak22Context.configureTheSystem(this);
		CommUtils.delay(1000);  //Give time to start ...
		Qak22Context.showActorNames();
		CommSystemConfig.tracing = false;
 	}
 
 /*
MAIN
 */
	public static void main(String[] args) throws Exception   {
		CommUtils.aboutThreads("Before start - ");
 		new MainPrototipoStrategia().configure();
  		CommUtils.aboutThreads("At end - ");
	}
	
 
	
 }
