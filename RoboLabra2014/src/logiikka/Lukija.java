package logiikka;

import lejos.nxt.ADSensorPort;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class Lukija {
	private LightSensor sensori;
	
	public Lukija() {
		sensori = new LightSensor(SensorPort.S1);
		
	}
	
	
	public void lueRuudulle(){
		LCD.drawString("Valon arvo: " + sensori.getNormalizedLightValue(), 0, 1);
	}
	
	public int getLuettu(){
		return sensori.getNormalizedLightValue();
	
	}
}
