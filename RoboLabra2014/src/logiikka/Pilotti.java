package logiikka;

import lejos.nxt.Button;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.Move.MoveType;

public class Pilotti {

	private Moottori vasen,oikea;
	
	public Pilotti(Moottori vasen, Moottori oikea) {
		this.vasen = vasen;
		this.oikea = oikea;
	}
	
	public void liikuta(){
		
		
			vasen.liiku();
			oikea.liiku();
		
	}
	
	public void pysaytaMolemmat(){
		vasen.pysayta();
		oikea.pysayta();
	}
	
	
}
