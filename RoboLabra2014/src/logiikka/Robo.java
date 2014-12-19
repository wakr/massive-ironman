package logiikka;

import lejos.nxt.*;
import lejos.robotics.RegulatedMotorListener;
import logiikka.moottori.OikeaMoottori;
import logiikka.moottori.TakaMoottori;
import logiikka.moottori.VasenMoottori;

public class Robo {

	
	Lukija lukija;
	Pilotti pilotti;
	Moottori vasen;
	Moottori oikea;
	Moottori taka;
	
	public Robo() {
		lukija = new Lukija();
		vasen = new VasenMoottori();
		oikea = new OikeaMoottori();
		taka = new TakaMoottori();
		pilotti = new Pilotti(vasen, oikea);
	}

	public void kaynnista() {
		
			while(!Button.ENTER.isPressed()){
				
				pilotti.liikuta();
				
				if(lukija.getLuettu() < 300){
					pilotti.pysaytaMolemmat();
					break;
				}

				
			}
			
		
	}
	
	
	
}
