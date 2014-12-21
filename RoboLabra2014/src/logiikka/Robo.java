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

	private int tummin, kirkkain, keskiarvo;
	
	public Robo() {
		lukija = new Lukija();
		vasen = new VasenMoottori();
		oikea = new OikeaMoottori();
		taka = new TakaMoottori();
		pilotti = new Pilotti(vasen, oikea,taka);
		tummin = Integer.MIN_VALUE;
		kirkkain = 0;
		keskiarvo = 0;
	}

	public void kaynnista() {
			
			haeMaxMinLukemat();
			
			LCD.drawString("Kalibroitu!", 0, 2);
			LCD.drawString("Kun olet valmis\n, paina Enter.", 0, 3);
			Button.waitForPress();
			LCD.clear();
			
			
			while(!Button.ENTER.isPressed()){
				paataToiminta(lukija.getLuettu());
			}
			
	}

	

	private void paataToiminta(int luettu) {
		
		int alaraja = 45;
		
		final int forward = 1;
		final int stop = 3;
		final int flt = 4;
		final int power = 80;
		
		
		if(luettu < alaraja){ // ollaan mustalla --> oikealle
			MotorPort.A.controlMotor(0,stop);
			MotorPort.B.controlMotor(power, forward);
			
		}
		else{
			MotorPort.B.controlMotor(0,stop);
			MotorPort.A.controlMotor(power, forward);
		}
		
		
		
		
	}

	private void haeMaxMinLukemat() {
		pilotti.asetaVoimaOikea(30);
		pilotti.asetaVoimaVasen(30);
		int[] arvot = pilotti.etsiAlkuArvot(135,lukija); // 135 astettta
		
		kirkkain = arvot[0];
		tummin = arvot[1];
		keskiarvo = (kirkkain + tummin) / 2;
		pilotti.pysaytaMolemmat();
	}
	
	
	
}
