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
			
			
			while(!Button.ENTER.isPressed()){
				paataToiminta(lukija.getLuettu());
			}
			
	}

	


	private void paataToiminta(int luettu) {
		int alaraja = 45, ylaraja = 55, error = 50;
		int luettuError = luettu - error;
		int kp = 1;
		int Tp = 20;
		int Turn = kp * luettuError;
		
		int vasenPower = Tp + Turn;
		int oikeaPower = Tp - Turn;
		
		if(oikeaPower > 0){
			pilotti.asetaVoimaJaLiikutaEteenOikea(oikeaPower);
		}else{
			oikeaPower *= -1;
			MotorPort.A.controlMotor(oikeaPower, 2);
		}
		if(vasenPower > 0){
			pilotti.asetaVoimaJaLiikutaEteenVasen(vasenPower);
		}
		else{
			vasenPower *= -1;
			MotorPort.B.controlMotor(vasenPower, 2);
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void haeMaxMinLukemat() {
		pilotti.asetaVoimaOikea(30);
		pilotti.asetaVoimaVasen(30);
		pilotti.etsiAlkuArvot(135,lukija); // 135 astetta
		
		pilotti.pysaytaMolemmat();
	}
	
	
	
}
