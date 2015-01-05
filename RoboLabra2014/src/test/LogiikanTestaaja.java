package test;

import util.PID;

// yksikkötestit

public class LogiikanTestaaja {

	
	public boolean PIDAntaaNollanJosLuetaanKeskiarvo(){
		PID pid = new PID();
		return pid.laskeKaantoSuhde(50) == 0;
	}
	
	public boolean PIDAntaaNollanJosUseampiSama(){
		PID pid = new PID();
		pid.laskeKaantoSuhde(50);
		pid.laskeKaantoSuhde(49);
		pid.laskeKaantoSuhde(49);
		return pid.laskeKaantoSuhde(49) == 0;
	}
	
	
}
