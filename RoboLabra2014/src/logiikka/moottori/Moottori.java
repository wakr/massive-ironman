package logiikka.moottori;

import lejos.nxt.NXTRegulatedMotor;

// robotin moottorien tarvitsemat toiminnallisuudet
// joita myös pilotti osaa käyttää

public interface Moottori {
	public void liikuEteen();

	public void liikuTaakse();

	public void pysayta();

	public void asetaVoima(int maara);

	public int getTacho();

	public void resetTacho();

	public NXTRegulatedMotor getMotor();
}
