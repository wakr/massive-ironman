package utils;

/*
 * http://en.wikipedia.org/wiki/PID_controller käyttämät staattiset arvot
 */

public enum Arvo {

	DesiKorjaaja(100), // mahdollistaa tarkan desimaalilaskennan leJOS:ssa
	OffSet(100 / 2), // valolukijan virhemarginaalin tasoitus välille -50, 0 , 50

	KonstatIntegraali(12), // integraalin säätämiseen 7 | 12
	KonstantDerivaatta(500), // ennustaa korjattavaa virhemarginaalia 950 | 500
	KonstantProportional(84), // 140 // virheestä kääntymisen kulma 90 | 84
	// 0.4s Pc 0.017 dT
	TargetPower(35); // Robotin käyttämän moottorin tehokkuus

	private int arvo;

	private Arvo(int arvo) {
		this.arvo = arvo;
	}

	public int getArvo() {
		return this.arvo;
	}

}
