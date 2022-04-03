package it.unibo.radarSystem22_4.appl.observer;

import java.util.Observable;
import java.util.Observer;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22_4.appl.RadarSystemConfig;
import it.unibo.radarSystem22_4.appl.main.UseSonarFromPc;
import it.unibo.radarSystem22_4.appl.proxy.SonarProxy;
import it.unibo.radarSystem22_4.comm.ProtocolType;
import it.unibo.radarSystem22_4.comm.interfaces.IApplication;
import it.unibo.radarSystem22_4.comm.utils.BasicUtils;
import it.unibo.radarSystem22_4.comm.utils.ColorsOut;
import it.unibo.radarSystem22_4.comm.utils.CommSystemConfig;

public class Osservatore implements Observer, IApplication{
	 	//private ISonar  sonar ;
	 	private Distance distance;
	 	
		@Override
		public String getName() {
			return this.getClass().getName() ; 
		}
		@Override
		public void doJob(String domainConfig, String systemConfig ) {
		//	setup(domainConfig,systemConfig);
		//	configure();
			//execute();		
			//terminate();
		}
		
		

		@Override
		public void update(Observable obs, Object arg) {
			if (arg instanceof Distance) {
				 distance = (Distance)arg;
					ColorsOut.out("************************* OSSERVATORE:**************************\n!!! update distance= "+distance.getVal(),ColorsOut.RED);
				 } else {
					 ColorsOut.out("!!! update distance=Unkonwn change!",ColorsOut.RED);
				 }


		}
		
		
		
	}




