package logiikka;

import lejos.nxt.*;
import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.navigation.DifferentialPilot;
import logiikka.moottori.OikeaMoottori;
import logiikka.moottori.TakaMoottori;
import logiikka.moottori.VasenMoottori;

public class Robo {

	
	private Lukija lukija;
	private Pilotti pilotti;
	private Moottori vasen, oikea, taka;

	
	public Robo() {
		lukija = new Lukija();
		vasen = new VasenMoottori();
		oikea = new OikeaMoottori();
		taka = new TakaMoottori();
		pilotti = new Pilotti(vasen, oikea,taka);
	}

	public void kaynnista() {
			
			haeMaxMinLukemat();
			pilotti.asetaVoima(40);
			
			while(!Button.ENTER.isPressed()){
				paataToiminta(lukija.getLuettu());
			}
			
	}

	
	private void paataToiminta(int luettu) {
		int decimalFixer = 100;
		int error = (100) / 2;
		int luettuError = luettu - error;
		int kp = 50; // kaantonopeus / 100 	// 65 & 30 tarkka mutta heiluva
		int Tp = 30; // nopeus
		int Turn = kp * luettuError;
		Turn /= decimalFixer;
		int vasenPower = Tp - Turn;
		int oikeaPower = Tp + Turn;
		
		
		pilotti.asetaVoimaJaLiikutaEteenVasen(vasenPower, Tp);
		pilotti.asetaVoimaJaLiikutaEteenOikea(oikeaPower, Tp);
		
		
		
	}

	private void haeMaxMinLukemat() {
		pilotti.asetaVoimaOikea(30);
		pilotti.asetaVoimaVasen(30);
		pilotti.etsiAlkuArvot(135,lukija); // 135 astetta
		
		pilotti.pysaytaMolemmat();
	}
	
	
	
}
