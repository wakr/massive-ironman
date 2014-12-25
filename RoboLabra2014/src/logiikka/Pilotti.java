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
	
	public void asetaVoimatJaLiikuta(int teho){
		if(teho > 0){
			asetaVoima(teho);
			liikutaMolempiaEteen();
		}
		else{
			teho *= -1;
			liikutaMolempiaTaakse();
		}
	}
	
	public void asetaVoimaJaLiikutaEteenOikea(int teho, int tp){
		if(teho > 0){
			asetaVoimaOikea(teho);
			liikutaOikeaEteen();
		}
		else{
			asetaVoimaOikea((teho * -1) + tp);
			liikutaOikeaTaakse();
		}
	}
	
	public void asetaVoimaJaLiikutaEteenVasen(int teho, int tp){
		if(teho < 0){
			asetaVoimaVasen(teho);
			liikutaVasenEteen();
		}
		else{
			asetaVoimaVasen((teho * -1) + tp);
			liikutaVasenTaakse();
		}
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
	
	public void etsiAlkuArvot(int aste, Lukija lukija){
		
		oikea.resetTacho();
		
		while(Math.abs(oikea.getTacho()) < aste){
			oikea.liikuTaakse();
		}
		
		lukija.asetaMax();
		
		while(Math.abs(oikea.getTacho()) > 0){	
			oikea.liikuEteen();
		}
		
		lukija.asetaMin();
	}

	
}
