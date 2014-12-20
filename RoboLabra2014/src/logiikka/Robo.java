package logiikka;

import lejos.nxt.*;
import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.navigation.DifferentialPilot;
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
		pilotti = new Pilotti(vasen, oikea,taka);
	}

	public void kaynnista() {
		
			haeMaxMinLukemat();
			
		
	}

	private void haeMaxMinLukemat() {
		pilotti.asetaVoimaOikea(25);
		pilotti.asetaVoimaVasen(25);
		
		pilotti.etene(135); // 135 astettta
	}
	
	
	
}
