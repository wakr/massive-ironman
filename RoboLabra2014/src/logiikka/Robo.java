package logiikka;

import util.PID;
import util.PysyvaArvo;
import lejos.nxt.*;
import logiikka.sensori.ValoSensori;
import logiikka.sensori.UltraSensori;

public class Robo {

	private ValoSensori lukija;
	private UltraSensori nakija;
	private Pilotti pilotti;
	private PID pid; // PID-kontrolleri

	public Robo() {
		lukija = new ValoSensori();
		pilotti = new Pilotti();
		pid = new PID();
		nakija = new UltraSensori();
	}

	public void kaynnista() {

		haeMaxMinLukemat(); // kalibroidaan lukija vastaamaan nykyistä valoisuutta

		while (!Button.ENTER.isPressed()) {
			if (nakija.onkoLoydetty()) {
				pysaytaRobootti();
				pilotti.resetoiTachot();
				
				etsiEsteenReunatJaKierra();
				
				nakija.setLoydettyFalse();
				pilotti.asetaVoimatMolempiin(PysyvaArvo.TargetPower.getArvo());
				pilotti.getVasen().getMotor().suspendRegulation();
				pilotti.getOikea().getMotor().suspendRegulation();
			}

			int luettu = lukija.getLuettu();
			int kaannos = pid.laskeKaantoSuhde(luettu);
			int TP = PysyvaArvo.TargetPower.getArvo();
			int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;
			
			paataToiminta(vasenPower, oikeaPower, TP);
		}

	}

	private void etsiEsteenReunatJaKierra() {
		pilotti.kaannyOikealle(90);		// kaannytaan sivuttain kohti estettä
		pilotti.kaannaNakijaaVasemmalle(90);
		
		kuljeEteenKunnesTyhjaa(40);

		pilotti.liikutaMolempiaEteenSynkronoidusti(20); // liikutaan vielä robotin pituuden verran
		pilotti.kaannyVasemmalle(90);

		kuljeEteenKunnesEtaisyysHaluttu(40);
		kuljeEteenKunnesTyhjaa(40); 						// kuljetaan esteen ohi

		pilotti.liikutaMolempiaEteenSynkronoidusti(15);

		pilotti.kaannyVasemmalle(45); 		 // kaannytaan kohti viivaa
		pilotti.kaannaNakijaaOikealle(90); // resetoidaan nakija osoittamaan suoraan

		kuljeEteenKunnesLukijanArvoAlle(40);
		
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

	private void kuljeEteenKunnesTyhjaa(int raja) {
		while (true) {
			if (nakija.getEtaisyys() >= raja) {
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

		pilotti.asetaVoimatMolempiin(30);
		pilotti.etsiAlkuArvot(135, lukija); // 135 astetta
		pilotti.pysaytaMolemmat();

	}

}
