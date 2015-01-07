package logiikka;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;
import logiikka.moottori.Moottori;
import logiikka.moottori.NakijaMoottori;
import logiikka.moottori.SivuMoottori;
import logiikka.sensori.ValoSensori;

// sisältää kaiken tarvittavan ohjauksen liikkeitä varten

public class Pilotti {

	private Moottori vasen, oikea, nakija;
	private DifferentialPilot synkrOhjaaja; // synkronoitu suoraan menemistä varten

	public Pilotti() {
		this.vasen = new SivuMoottori(Motor.B, MotorPort.B);
		this.oikea = new SivuMoottori(Motor.A, MotorPort.A);
		this.nakija = new NakijaMoottori(Motor.C, MotorPort.C);
		this.synkrOhjaaja = new DifferentialPilot(5.5f, 12.5f, // 5.5cm renkaiden halkaisija, 12.5cm leveys renkaiden välillä
				vasen.getMotor(), oikea.getMotor(), false);
		this.synkrOhjaaja.setTravelSpeed(10);
		this.synkrOhjaaja.setRotateSpeed(40);
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

	public void resetoiTachot() {
		synkrOhjaaja.reset();
		vasen.resetTacho();
		oikea.resetTacho();
		vasen.getMotor().resetTachoCount();
		oikea.getMotor().resetTachoCount();
	}

	public void vapautaRegulaatioMolemmista() {
		vasen.getMotor().suspendRegulation();
		oikea.getMotor().suspendRegulation();
	}

	public void liikutaMolempiaEteenSynkronoidusti() {
		synkrOhjaaja.forward();
	}

	public void liikutaMolempiaEteenSynkronoidusti(int matkaCm) {
		synkrOhjaaja.travel(matkaCm);
	}

	public void pysaytaMolemmatSynkronoidusti() {
		synkrOhjaaja.stop();
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

	public void pysaytaVasen() {
		vasen.pysayta();
	}

	public void pysaytaOikea() {
		oikea.pysayta();
	}

	public void pysaytaNakija() {
		nakija.pysayta();
	}

	public void asetaVoimatMolempiin(int maara) {
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
			asetaVoimatMolempiin(teho);
			liikutaMolempiaEteen();
		} else {
			teho *= -1;
			liikutaMolempiaTaakse();
		}
	}

	public void asetaVoimaJaLiikutaEteenOikea(int teho) {
		if (teho > 0) {
			asetaVoimaOikea(teho);
			liikutaOikeaEteen();
		} else {
			asetaVoimaOikea(teho * -1);
			liikutaOikeaTaakse();
		}
	}

	public void asetaVoimaJaLiikutaEteenVasen(int teho) {
		if (teho < 0) {
			asetaVoimaVasen(teho);
			liikutaVasenEteen();
		} else {
			asetaVoimaVasen(teho * -1);
			liikutaVasenTaakse();
		}
	}

	public void liikutaVasenEteen() {
		vasen.liikuEteen();
	}

	public void liikutaVasenTaakse() {
		vasen.liikuTaakse();
	}

	public void liikutaOikeaEteen() {
		oikea.liikuEteen();
	}

	public void liikutaOikeaTaakse() {
		oikea.liikuTaakse();
	}

	public void kaannyOikealle() {
		synkrOhjaaja.rotateRight();
	}

	public void kaannyOikealle(int aste) {
		synkrOhjaaja.rotate(-aste);
	}

	public void kaannyVasemmalle() {
		synkrOhjaaja.rotateLeft();
	}

	public void kaannyVasemmalle(int aste) {
		synkrOhjaaja.rotate(aste);
	}

	public void kaannaNakijaaOikealle() {
		nakija.liikuEteen();
	}

	public void kaannaNakijaaVasemmalle() {
		nakija.liikuTaakse();
	}

	public void liikutaVasenTaakse(int aste) {
		vasen.resetTacho();
		while (Math.abs(vasen.getTacho()) < aste) {
			vasen.liikuTaakse();
		}
		vasen.pysayta();
	}

	public void liikutaVasenEteen(int aste) {
		vasen.resetTacho();
		while (Math.abs(vasen.getTacho()) < aste) {
			vasen.liikuEteen();
		}
		vasen.pysayta();
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

	public void etsiAlkuArvot(int aste, ValoSensori lukija) {

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

}
