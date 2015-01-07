package logiikka.moottori;

import util.PysyvaArvo;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

public class SivuMoottori implements Moottori{

	private NXTRegulatedMotor reguloituMoottori;
	private NXTMotor saadeltavaMoottori;

	public SivuMoottori(NXTRegulatedMotor reguloituMoottori, MotorPort moottorinPortti) {
		this.reguloituMoottori = reguloituMoottori;
		saadeltavaMoottori = new NXTMotor(moottorinPortti, MotorPort.STOP); // aloitetaan pysähtyneenä
		saadeltavaMoottori.setPower(PysyvaArvo.TargetPower.getArvo());
		saadeltavaMoottori.stop();
	}

	@Override
	public void liikuEteen() {
		saadeltavaMoottori.forward();
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
		saadeltavaMoottori.backward();
	}

	@Override
	public void resetTacho() {
		saadeltavaMoottori.resetTachoCount();
	}
}
