package logiikka;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.Move.MoveType;

public class Pilotti {

	private Moottori vasen,oikea, taka;
	
	public Pilotti(Moottori vasen, Moottori oikea, Moottori taka) {
		this.vasen = vasen;
		this.oikea = oikea;
		this.taka = taka;
	}
	
	public void liikutaMolempiaEteen(){
			vasen.liikuEteen();
			oikea.liikuEteen();
	}
	
	public void liikutaMolempiaTaakse(){
			vasen.liikuTaakse();
			oikea.liikuTaakse();
	}
	
	public void pysaytaMolemmat(){
		vasen.pysayta();
		oikea.pysayta();
	}
	
	public void asetaVoima(int maara){
		vasen.asetaVoima(maara);
		oikea.asetaVoima(maara);
	}
	
	public void asetaVoimaVasen(int maara){
		vasen.asetaVoima(maara);
	}
	
	public void asetaVoimaOikea(int maara){
		oikea.asetaVoima(maara);
	}
	
	public void liikutaVasenEteen(){
		vasen.liikuEteen();
	}
	
	public void liikutaOikeaEteen(){
		oikea.liikuEteen();
	}
	
	public void liikutaOikeaTaakse(){
		oikea.liikuTaakse();
	}
	
	public void liikutaVasenTaakse(){
		vasen.liikuTaakse();
	}
	
	public void pysaytaVasen(){
		vasen.pysayta();
	}
	
	public void pysaytaOikea(){
		oikea.pysayta();
	}
	
	public int[] etsiAlkuArvot(int aste, Lukija lukija){
		//turhaa
		int arvot[] = new int[]{0, Integer.MAX_VALUE}; //kirkkain & tummin
		
		while(vasen.getTacho() < aste){
			vasen.liikuTaakse();
			arvot[0] = Math.max(arvot[0], lukija.getLuettuNormalized());
		}
		
		lukija.asetaMax();
		
		while(vasen.getTacho() > 0){	
			vasen.liikuEteen();
			arvot[1] = Math.min(arvot[1], lukija.getLuettuNormalized());
		}
		
		lukija.asetaMin();
		
		return arvot;
	}

	public void etsiParasKulma(Lukija lukija, int keskiarvo) {
		
		
		
		Button.waitForPress();
		
	}
	
}
