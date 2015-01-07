package logiikka.moottori;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class NakijaMoottori implements Moottori {

	private NXTRegulatedMotor reguloituMoottori;
	private NXTMotor saadeltavaMoottori;

	public NakijaMoottori(NXTRegulatedMotor reguloituMoottori, MotorPort moottorinPortti)  {
		this.reguloituMoottori = reguloituMoottori;
		saadeltavaMoottori = new NXTMotor(moottorinPortti, MotorPort.STOP); // aloitetaan pysähtyneenä
		saadeltavaMoottori.setPower(25);
		saadeltavaMoottori.stop();
	}

	@Override
	public void liikuEteen() {
		saadeltavaMoottori.backward();
	}

	@Override
	public NXTRegulatedMotor getMotor() {
		return reguloituMoottori;
	}

	@Override
	public void pysayta() {
		saadeltavaMoottori.stop();
	}

	@Override
	public void asetaVoima(int maara) {
		saadeltavaMoottori.setPower(maara);
	}

	@Override
	public int getTacho() {
		return saadeltavaMoottori.getTachoCount();
	}

	@Override
	public void liikuTaakse() {
		saadeltavaMoottori.forward();
	}

	@Override
	public void resetTacho() {
		saadeltavaMoottori.resetTachoCount();
	}

}
