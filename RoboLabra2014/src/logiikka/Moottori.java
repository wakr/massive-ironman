package logiikka;

import lejos.nxt.NXTRegulatedMotor;

public interface Moottori{
	public void liiku();
	public void liikuMonta(int maara);
	public void pysayta();
	public NXTRegulatedMotor getMotor();
}
