package main;

import test.LogiikanTestaaja;
import logiikka.Robo;

/**
 * @author Kristian Wahlroos
 * 
 * Viivanseuraaja PID-kontrollerilla, mikä osaa väistää 
 * eteen tulleet esteet, jos tarpeeksi tilaa radalla
 * 
 * 15.12.2014 - 12.1.2015 
 * 
 */

public class Main {

	public static void main(String[] args) {
	
		Robo r = new Robo();
		r.kaynnista();
		
	}
	

}
