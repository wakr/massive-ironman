package logiikka;

import lejos.nxt.NXTRegulatedMotor;

public interface Moottori{
	public void liikuEteen();
	public void liikuTaakse();
	public void liikuMonta(int maara);
	public void pysayta();
	public void asetaVoima(int maara);
	public int getTacho();
	public void resetTacho();
	public NXTRegulatedMotor getMotor();
}
