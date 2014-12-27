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
			
			final int decimalFixer = 100;
			final int offset = (100) / 2;
			final int ki = 2; // 15 6
			final int kd = 1128286; // 100000
			final int kp = 100; // kaantonopeus / 100 	// 65 & 30 tarkka mutta heiluva // 55
			final int Tp = 30; // nopeus 35
			int integral = 0;
			int lastError = 0;
			int derivate = 0;
			
			while(!Button.ENTER.isPressed()){
				int luettu = lukija.getLuettu();
				int luettuError = luettu - offset;
				integral = (2/3)*integral + luettuError;
				derivate = luettuError - lastError;
				int Turn = kp * luettuError + ki * integral + kd * derivate;
				Turn /= decimalFixer;
				int vasenPower = Tp - Turn;
				int oikeaPower = Tp + Turn;
				
				paataToiminta(vasenPower, oikeaPower, Tp);
				lastError = luettuError;
			}
			
	}

	
	private void paataToiminta(int vasenPower, int oikeaPower, int Tp) {
		
		
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
