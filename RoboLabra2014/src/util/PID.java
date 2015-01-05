package util;

public class PID {

	private int integraali, viimeVirhe, derivaatta, virhe;

	public PID() {
		this.integraali = 0;
		this.viimeVirhe = 0;
		this.derivaatta = 0;
		this.virhe = 0;
	}

	public int laskeKaantoSuhde(int luettu) {
		virhe = luettu - PysyvaArvo.OffSet.getArvo(); // tasoitetaan virhelukema
		integraali = (2 / 3) * integraali + virhe;    // virheensäätö, jossa "2/3" parantaa tarkkuutta
		derivaatta = virhe - viimeVirhe; 			  // ennustaminen
		int Kaanto = PysyvaArvo.KonstantProportional.getArvo() * virhe
				+ PysyvaArvo.KonstatIntegraali.getArvo() * integraali
				+ PysyvaArvo.KonstantDerivaatta.getArvo() * derivaatta; // PID-logiikka
		Kaanto /= PysyvaArvo.DesiKorjaaja.getArvo();                    // palautetaan pois desimaalista
		viimeVirhe = virhe; 
		return Kaanto;
	}

}
