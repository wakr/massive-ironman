package logiikka.sensori;

import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class UltraSensori implements FeatureListener {
	private UltrasonicSensor sensori;
	private RangeFeatureDetector etaisyysKuuntelija; // automaattinen listeneri törmäyksiä varten
	private boolean loydetty;

	public UltraSensori() {
		this.sensori = new UltrasonicSensor(SensorPort.S2);
		this.etaisyysKuuntelija = new RangeFeatureDetector(sensori, 20, 350); // 20cm etäisyys 350ms viiveellä
		this.etaisyysKuuntelija.addListener(this);
		loydetty = false;
	}

	public void setLoydettyFalse() {
		this.loydetty = false;
	}

	public float getEtaisyys() {
		return sensori.getRange();
	}
	
	public int getUltraEtaisyys(){
		return sensori.getDistance();
	}

	public boolean onkoLoydetty() {
		return loydetty;
	}

	@Override
	public void featureDetected(Feature feature, FeatureDetector detector) {
		if (!loydetty) { 
			int range = (int) feature.getRangeReading().getRange();
			Sound.playTone(1200 - (range * 10), 100);
			loydetty = true;
		}
	}
}
