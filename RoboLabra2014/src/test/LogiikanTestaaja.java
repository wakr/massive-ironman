package test;

import util.PID;

// yksikkötestit, jotka palauttavat true onnistuessaan

public class LogiikanTestaaja {

	
	public boolean PIDAntaaNollanJosLuetaanKeskiarvo(){
		PID pid = new PID();
		return pid.laskeKaantoSuhde(50) == 0;
	}
	
	public boolean PIDAntaaNollanJosUseampiKeskiarvoLuettu(){
		PID pid = new PID();
		pid.laskeKaantoSuhde(50);
		pid.laskeKaantoSuhde(50);
		return pid.laskeKaantoSuhde(50) == 0;
	}
	
	public boolean korjaaAlleKeskiarvoNegatiivisella(){
		PID pid = new PID();
		return pid.laskeKaantoSuhde(49) < 0;
	}
	
	public boolean korjaaYliKeskiarvoPositiivisella(){
		PID pid = new PID();
		return pid.laskeKaantoSuhde(51) > 0;
	}
	
}