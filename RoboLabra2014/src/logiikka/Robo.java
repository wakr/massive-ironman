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
		lukija = new ValoSensori(SensorPort.S1);
		pilotti = new Pilotti();
		pid = new PID();
		nakija = new UltraSensori(SensorPort.S2);
	}

	public void kaynnista() {

		haeMaxMinLukemat(); // kalibroidaan lukija vastaamaan nykyistä valoisuutta

		while (!Button.ENTER.isPressed()) {
			if (nakija.onkoLoydetty()) {
				vaistaEste();
			}
			seuraaViivaa();
		}
	}

	private void seuraaViivaa() {
		int kaannos = pid.laskeKaantoSuhde(lukija.getLuettu());
		int TP = PysyvaArvo.TargetPower.getArvo();
		int vasenPower = TP - kaannos, oikeaPower = TP + kaannos;

		paataToiminta(vasenPower, oikeaPower);
	}

	private void vaistaEste() {
		pysaytaRobootti();
		pilotti.resetoiTachot();

		etsiEsteenReunatJaKierra();

		alustaValmiusLukemiseen();
	}

    private void alustaValmiusLukemiseen(){
    		nakija.setLoydettyFalse();
		pilotti.asetaVoimatMolempiin(PysyvaArvo.TargetPower.getArvo());
		pilotti.vapautaRegulaatioMolemmista();
    }

	private void etsiEsteenReunatJaKierra() {
		kaannyJaKuljeEsteenOhiEdestä();
		kuljeEsteenOhiSivulta();
		kaannyJaKuljeKohtiViivaa();
	}

	private void kaannyJaKuljeKohtiViivaa() {
		pilotti.kaannyVasemmalle(45); 		 // kaannytaan kohti viivaa
		pilotti.kaannaNakijaaOikealle(90); // resetoidaan nakija osoittamaan suoraan
		kuljeEteenKunnesLukijanArvoAlle(40); // alle 40 tarkoittaa, että ollaan jo hieman mustalla viivalla
	}

	private void kuljeEsteenOhiSivulta() {
		kuljeEteenKunnesEtaisyysHaluttu(40);
		kuljeEteenKunnesTyhjaa(40); 						// kuljetaan esteen ohi
		pilotti.liikutaMolempiaEteenSynkronoidusti(15);
	}

	private void kaannyJaKuljeEsteenOhiEdestä() {
		kaannaRobottiJaLukijaSivuttainKohtiEstetta();	// kaannytaan sivuttain kohti estettä
		kuljeEteenKunnesTyhjaa(40);						// tyhjäksi luetaan yli 40 päässä olevat kohteet
		pilotti.liikutaMolempiaEteenSynkronoidusti(20); // liikutaan vielä robotin pituuden verran
		pilotti.kaannyVasemmalle(90);
	}

	private void kaannaRobottiJaLukijaSivuttainKohtiEstetta() {
		pilotti.kaannyOikealle(90);
		pilotti.kaannaNakijaaVasemmalle(90);
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

	private void paataToiminta(int vasenPower, int oikeaPower) {

		pilotti.asetaVoimaJaLiikutaEteenVasen(vasenPower);
		pilotti.asetaVoimaJaLiikutaEteenOikea(oikeaPower);

	}

	private void haeMaxMinLukemat() {

		pilotti.asetaVoimatMolempiin(30);	// 30 on tarpeeksi hidas, jotta saadaan vakaat tulokset
		pilotti.etsiAlkuArvot(135, lukija); 	// 135 astetta
		pilotti.pysaytaMolemmat();

	}

}
