package logiikka.moottori;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import logiikka.Moottori;

public class TakaMoottori implements Moottori{

	private NXTRegulatedMotor moottori;
	private NXTMotor m;
	
	public TakaMoottori() {
		moottori = Motor.C;
		m = new NXTMotor(MotorPort.C, MotorPort.STOP);
		m.setPower(100);
		m.stop();
	}
	
	@Override
	public void liikuEteen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liikuMonta(int maara) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NXTRegulatedMotor getMotor() {
		return Motor.C;
	}

	@Override
	public void pysayta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asetaVoima(int maara) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTacho() {
		// TODO Auto-generated method stub
		return 0;
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
