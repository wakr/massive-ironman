package logiikka;

import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class Nakija implements FeatureListener {
	private UltrasonicSensor sensori;
	private RangeFeatureDetector detector;
	private boolean loydetty;

	public Nakija() {
		this.sensori = new UltrasonicSensor(SensorPort.S2);
		this.detector = new RangeFeatureDetector(sensori, 20, 350);
		this.detector.addListener(this);
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
			System.out.println("Range:" + range);
			loydetty = true;
		}
	}
}
