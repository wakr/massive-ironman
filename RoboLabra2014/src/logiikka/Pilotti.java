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
	
	public void etene(int aste){
		
		
		
		while(vasen.getTacho() < aste){
			vasen.liikuTaakse();
		}
		while(vasen.getTacho() > 0){	
			vasen.liikuEteen();
		}
		
	}
	
}
