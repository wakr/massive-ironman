package logiikka;

import util.PID;
import util.PysyvaArvo;
import lejos.nxt.*;
import logiikka.sensori.Lukija;
import logiikka.sensori.Nakija;

public class Robo {

	private Lukija lukija;
	private Nakija nakija;
	private Pilotti pilotti;
	private PID pid; // PID-kontrolleri

	public Robo() {
		lukija = new Lukija();
		pilotti = new Pilotti();
		pid = new PID();
		nakija = new Nakija();
	}

	public void kaynnista() {

		haeMaxMinLukemat(); // kalibroidaan lukija vastaamaan nykyistä valoisuutta

		while (!Button.ENTER.isPressed()) {
			if (nakija.onkoLoydetty()) {
				pysaytaRobootti();
				etsiEsteenReunatJaKierra();
				nakija.setLoydettyFalse();
				pilotti.asetaVoimatMolempiin(PysyvaArvo.TargetPower.getArvo());
			}

			int luettu = lukija.getLuettu();
			int kaannos = pid.laskeKaantoSuhde(luettu);
			int TP = PysyvaArvo.TargetPower.getArvo();
			int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;
			
			paataToiminta(vasenPower, oikeaPower, TP);
		}

	}

	private void etsiEsteenReunatJaKierra() {
		pilotti.kaannyVasemmalle(90);		// kaannytaan sivuttain kohti estettä
		pilotti.kaannaNakijaaOikealle(90);

		kuljeEteenKunnesTyhjaa();

		pilotti.liikutaMolempiaEteenSynkronoidusti(15); // liikutaan vielä robotin pituuden verran
		pilotti.kaannyOikealle(90);

		kuljeEteenKunnesEtaisyysHaluttu(40);
		kuljeEteenKunnesTyhjaa(); 						// kuljetaan esteen ohi

		pilotti.liikutaMolempiaEteenSynkronoidusti(10);

		pilotti.kaannyOikealle(45); 		 // kaannytaan kohti viivaa
		pilotti.kaannaNakijaaVasemmalle(90); // resetoidaan nakija osoittamaan suoraan

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
