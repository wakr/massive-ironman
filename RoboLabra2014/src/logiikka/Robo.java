package logiikka;

import utils.Arvo;
import utils.PID;
import lejos.nxt.*;
import logiikka.moottori.OikeaMoottori;
import logiikka.moottori.NakijaMoottori;
import logiikka.moottori.VasenMoottori;

public class Robo {

	private Lukija lukija;
	private Nakija nakija;
	private Pilotti pilotti;
	private PID pid;

	public Robo() {
		lukija = new Lukija();
		pilotti = new Pilotti();
		pid = new PID();
		nakija = new Nakija();
	}

	public void kaynnista() {

		haeMaxMinLukemat();
		// pilotti.asetaVoima(40);

		while (!Button.ENTER.isPressed()) {
			if (nakija.onkoLoydetty()) {
				pysaytaRobootti();
				etsiEsteenReunat();
			}

			int luettu = lukija.getLuettu();
			int kaannos = pid.laskeKaantoSuhde(luettu);
			int TP = Arvo.TargetPower.getArvo();
			int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;
			paataToiminta(vasenPower, oikeaPower, TP);

		}

	}

	private void etsiEsteenReunat() {
		int etaisyysEsteeseen = nakija.getUltraEtaisyys();
		int nakijanAlku = pilotti.getNakijanMoottori().getTacho();
		while(true){
			if(etaisyysEsteeseen < nakija.getEtaisyys() - 10){
				pilotti.pysaytaNakija();
				break;
			}
			pilotti.kaannaNakijaaVasemmalle();
		}
		nakijanAlku += pilotti.getNakijanMoottori().getTacho();
		pilotti.kaannaNakijaaVasemmalle(15);
		nakijanAlku += 15;
		System.out.println(nakijanAlku);
		pilotti.asetaVoima(30);
		pilotti.liikutaVasenTaakse(nakijanAlku*4 + 15);
		pilotti.kaannaNakijaaOikealle(nakijanAlku);
		pilotti.kaannaNakijaaOikealle(90);
		pilotti.liikutaMolempiaEteen();
		
		
		Button.waitForPress();
	}

	private void pysaytaRobootti() {
		pilotti.asetaVoima(0);
		pilotti.pysaytaMolemmat();

	}

	private void paataToiminta(int vasenPower, int oikeaPower, int Tp) {

		pilotti.asetaVoimaJaLiikutaEteenVasen(vasenPower, Tp);
		pilotti.asetaVoimaJaLiikutaEteenOikea(oikeaPower, Tp);

	}

	private void haeMaxMinLukemat() {

		pilotti.asetaVoimaOikea(30);
		pilotti.asetaVoimaVasen(30);
		pilotti.etsiAlkuArvot(135, lukija); // 135 astetta
		pilotti.pysaytaMolemmat();

	}

}
