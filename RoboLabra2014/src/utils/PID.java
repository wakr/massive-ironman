package utils;

public class PID {

	private int integraali, viimeVirhe, derivaatta, virhe;

	public PID() {
		this.integraali = 0;
		this.viimeVirhe = 0;
		this.derivaatta = 0;
		this.virhe = 0;
	}

	public int laskeKaantoSuhde(int luettu) {
		virhe = luettu - Arvo.OffSet.getArvo();
		integraali = (2 / 3) * integraali + virhe;
		derivaatta = virhe - viimeVirhe;
		int Kaanto = Arvo.KonstantProportional.getArvo() * virhe
				+ Arvo.KonstatIntegraali.getArvo() * integraali
				+ Arvo.KonstantDerivaatta.getArvo() * derivaatta;
		Kaanto /= Arvo.DesiKorjaaja.getArvo();
		viimeVirhe = virhe;
		return Kaanto;
	}

}
