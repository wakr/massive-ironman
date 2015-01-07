package logiikka.sensori;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;


public class ValoSensori {
	private LightSensor valoSensori;

	public ValoSensori(SensorPort sensoriPortti) {
		valoSensori = new LightSensor(sensoriPortti);
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
