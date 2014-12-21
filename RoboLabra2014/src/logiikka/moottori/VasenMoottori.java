package logiikka.moottori;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import logiikka.Moottori;

public class VasenMoottori implements Moottori{

	private NXTRegulatedMotor moottori;
	private NXTMotor m;
	
	public VasenMoottori() {
		moottori = Motor.B;
		m = new NXTMotor(MotorPort.B, MotorPort.STOP);
		m.setPower(100);
		m.stop();
	}
	
	@Override
	public void liikuEteen() {
		m.backward();
		
		
	}

	@Override
	public void liikuMonta(int maara) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public NXTRegulatedMotor getMotor() {
		return moottori;
	}

	@Override
	public void pysayta() {
		m.stop();
	}

	@Override
	public void asetaVoima(int maara) {
		m.setPower(maara);
		
		
	}

	@Override
	public int getTacho() {
		return m.getTachoCount();
	}

	@Override
	public void liikuTaakse() {
		m.forward();
		
	}

	@Override
	public void resetTacho() {
		m.resetTachoCount();
		
	}

}
