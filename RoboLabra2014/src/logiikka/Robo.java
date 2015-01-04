package logiikka;

import utils.Arvo;
import utils.PID;
import lejos.nxt.*;

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

		while (!Button.ENTER.isPressed()) {
			if (nakija.onkoLoydetty()) {
				pysaytaRobootti();
				etsiEsteenReunatJaKierra();
				nakija.setLoydettyFalse();
				pilotti.asetaVoimatMolempiin(Arvo.TargetPower.getArvo());
			}

			int luettu = lukija.getLuettu();
			int kaannos = pid.laskeKaantoSuhde(luettu);
			int TP = Arvo.TargetPower.getArvo();
			int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;
			paataToiminta(vasenPower, oikeaPower, TP);

		}

	}

	private void etsiEsteenReunatJaKierra() {
		// int etaisyysEsteeseen = nakija.getUltraEtaisyys();
		// int nakijanAlku = pilotti.getNakijanMoottori().getTacho();
		pilotti.kaannyVasemmalle(90);
		pilotti.kaannaNakijaaOikealle(90);

		kuljeEteenKunnesTyhjaa();

		pilotti.liikutaMolempiaEteenSynkronoidusti(15); // liikutaan vielä
		// robotin pituuden
		// verran
		pilotti.kaannyOikealle(90);

		kuljeEteenKunnesEtaisyysHaluttu(40);
		kuljeEteenKunnesTyhjaa(); // kuljetaan esteen ohi

		pilotti.liikutaMolempiaEteenSynkronoidusti(10);

		pilotti.kaannyOikealle(45); // kulma viivaa kohti
		pilotti.kaannaNakijaaVasemmalle(90); // resetoidaan nakija osoittamaan
												// suoraan

		kuljeEteenKunnesLukijanArvoAlle(50);

	}

	private void kuljeEteenKunnesLukijanArvoAlle(int minLuettu) {
		while (true) {
			if (lukija.getLuettu() <= minLuettu) {
				pilotti.pysaytaMolemmatSynkronoidusti();
				break;
			}
			pilotti.liikutaMolempiaEteenSynkronoidusti();
		}

	}

	private void kuljeEteenKunnesEtaisyysHaluttu(int minEtaisyys) {
		while (true) {
			if (nakija.getEtaisyys() <= minEtaisyys) {
				pilotti.pysaytaMolemmatSynkronoidusti();
				break;
			}
			pilotti.liikutaMolempiaEteenSynkronoidusti();
		}

	}

	private void kuljeEteenKunnesTyhjaa() {
		while (true) {
			if (nakija.getEtaisyys() == 255) {
				pilotti.pysaytaMolemmatSynkronoidusti();
				break;
			}
			pilotti.liikutaMolempiaEteenSynkronoidusti();
		}
	}

	private void pysaytaRobootti() {
		pilotti.asetaVoimatMolempiin(0);
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
