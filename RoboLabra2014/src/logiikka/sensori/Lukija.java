package logiikka.sensori;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;


public class Lukija {
	private LightSensor valoSensori;

	public Lukija() {
		valoSensori = new LightSensor(SensorPort.S1);
		valoSensori.setFloodlight(true); // otetaan lukema keinoteikoisesta valosta
	}

	public int getLuettuNormalized() {
		return valoSensori.getNormalizedLightValue(); // raaka-arvo 
	}

	public int getLuettu() {
		return valoSensori.readValue(); // kalibroitu lukema väliltä 0% - 100%
	}

	public void asetaMax() {
		valoSensori.calibrateHigh();
	}

	public void asetaMin() {
		valoSensori.calibrateLow();
	}

	public int getMin() {
		return valoSensori.getLow();
	}

	public int getHigh() {
		return valoSensori.getHigh();
	}
}
