package logiikka;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.Move.MoveType;
import logiikka.moottori.NakijaMoottori;
import logiikka.moottori.OikeaMoottori;
import logiikka.moottori.VasenMoottori;

public class Pilotti {

	private Moottori vasen, oikea, nakija;

	public Pilotti() {
		this.vasen = new VasenMoottori();
		this.oikea = new OikeaMoottori();
		this.nakija = new NakijaMoottori();
	}

	public Moottori getVasen() {
		return vasen;
	}

	public Moottori getOikea() {
		return oikea;
	}

	public Moottori getNakijanMoottori() {
		return nakija;
	}

	public void liikutaMolempiaEteen() {
		vasen.liikuEteen();
		oikea.liikuEteen();
	}

	public void liikutaMolempiaTaakse() {
		vasen.liikuTaakse();
		oikea.liikuTaakse();
	}

	public void pysaytaMolemmat() {
		vasen.pysayta();
		oikea.pysayta();
	}

	public void asetaVoima(int maara) {
		vasen.asetaVoima(maara);
		oikea.asetaVoima(maara);
	}

	public void asetaVoimaVasen(int maara) {
		vasen.asetaVoima(maara);
	}

	public void asetaVoimaOikea(int maara) {
		oikea.asetaVoima(maara);
	}

	public void asetaVoimatJaLiikuta(int teho) {
		if (teho > 0) {
			asetaVoima(teho);
			liikutaMolempiaEteen();
		} else {
			teho *= -1;
			liikutaMolempiaTaakse();
		}
	}

	public void asetaVoimaJaLiikutaEteenOikea(int teho, int tp) {
		if (teho > 0) {
			asetaVoimaOikea(teho);
			liikutaOikeaEteen();
		} else {
			asetaVoimaOikea((teho * -1) + tp);
			liikutaOikeaTaakse();
		}
	}

	public void asetaVoimaJaLiikutaEteenVasen(int teho, int tp) {
		if (teho < 0) {
			asetaVoimaVasen(teho);
			liikutaVasenEteen();
		} else {
			asetaVoimaVasen((teho * -1) + tp);
			liikutaVasenTaakse();
		}
	}

	public void liikutaVasenEteen() {
		vasen.liikuEteen();
	}

	public void liikutaOikeaEteen() {
		oikea.liikuEteen();
	}

	public void liikutaOikeaTaakse() {
		oikea.liikuTaakse();
	}

	public void liikutaVasenTaakse() {
		vasen.liikuTaakse();
	}
	
	public void liikutaVasenTaakse(int aste){
		vasen.resetTacho();
		while (Math.abs(vasen.getTacho()) < aste) {
			vasen.liikuTaakse();
		}
		vasen.pysayta();
	}

	public void kaannaNakijaaOikealle() {
		nakija.liikuEteen();
	}

	public void kaannaNakijaaVasemmalle() {
		nakija.liikuTaakse();
	}

	public void pysaytaVasen() {
		vasen.pysayta();
	}

	public void pysaytaOikea() {
		oikea.pysayta();
	}

	public void etsiAlkuArvot(int aste, Lukija lukija) {

		oikea.resetTacho();

		while (Math.abs(oikea.getTacho()) < aste) {
			oikea.liikuTaakse();
		}

		lukija.asetaMax();

		while (Math.abs(oikea.getTacho()) > 0) {
			oikea.liikuEteen();
		}

		lukija.asetaMin();
	}

	public void pysaytaNakija() {
		nakija.pysayta();

	}

	public void kaannaNakijaaOikealle(int aste) {
		nakija.resetTacho();

		while (Math.abs(nakija.getTacho()) < aste) {
			nakija.liikuEteen();
		}
		nakija.pysayta();

	}

	public void kaannaNakijaaVasemmalle(int aste) {
		nakija.resetTacho();

		while (Math.abs(nakija.getTacho()) < aste) {
			nakija.liikuTaakse();
		}
		nakija.pysayta();
	}
	
	

}
