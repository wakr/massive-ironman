package logiikka;

import utils.Arvo;
import utils.PID;
import lejos.nxt.*;
import logiikka.moottori.OikeaMoottori;
import logiikka.moottori.TakaMoottori;
import logiikka.moottori.VasenMoottori;

public class Robo {

	
	private Lukija lukija;
	private Pilotti pilotti;
	private Moottori vasen, oikea, taka;
	private PID pid;

	
	public Robo() {
		lukija = new Lukija();
		vasen = new VasenMoottori();
		oikea = new OikeaMoottori();
		taka = new TakaMoottori();
		pilotti = new Pilotti(vasen, oikea,taka);
		pid = new PID();
	}

	public void kaynnista() {
			
			haeMaxMinLukemat();
			//pilotti.asetaVoima(40);

			while(!Button.ENTER.isPressed()){
				int luettu = lukija.getLuettu();
				int kaannos = pid.laskeKaantoSuhde(luettu);
				int TP = Arvo.TargetPower.getArvo();
				int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;
				paataToiminta(vasenPower, oikeaPower, TP);	
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
