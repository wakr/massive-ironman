package util;

/*
 * http://en.wikipedia.org/wiki/PID_controller käyttämät staattiset arvot
 */

public enum PysyvaArvo {

	DesiKorjaaja(100), // mahdollistaa tarkan desimaalilaskennan leJOS:ssa
	OffSet(100 / 2), // valolukijan virhemarginaalin tasoitus välille -50, 0 , 50

	KonstatIntegraali(12),    // integraalin säätämiseen 
	KonstantDerivaatta(500),  // ennustaa korjattavaa virhemarginaalia 
	KonstantProportional(84), // virheestä kääntymisen kulma 
	TargetPower(35); 		  // Robotin käyttämän moottorin tehokkuus

	private int arvo;

	private PysyvaArvo(int arvo) {
		this.arvo = arvo;
	}

	public int getArvo() {
		return this.arvo;
	}

}
