package util;

/*
 * http://en.wikipedia.org/wiki/PID_controller käyttämät staattiset arvot
 * 
 */

public enum PysyvaArvo {

	DesiKorjaaja(100), // mahdollistaa tarkan desimaalilaskennan leJOS:ssa
	OffSet(100 / 2), // valolukijan virhemarginaalin tasoitus välille -50, 0 , 50

	// kaikki arvot 100 kertaa suurempina
	KonstatIntegraali(1),    // integraalin säätämiseen 12 
	KonstantDerivaatta(10000),  // ennustaa korjattavaa virhemarginaalia 500 
	KonstantProportional(190), // virheestä kääntymisen kulma 84
	TargetPower(90); 		  // Robotin käyttämän moottorin tehokkuus 35

	private int arvo;

	private PysyvaArvo(int arvo) {
		this.arvo = arvo;
	}
	
	public int getArvo() {
		return this.arvo;
	}

}
