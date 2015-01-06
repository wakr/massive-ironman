# Kuvaus

Viivanseuraaja-robotti on nimensä mukaisesti tarkoitettu seuraamaan viivaa, mutta siihen on lisätty ominaisuus väistää esteitä, jotka sijaitsevat sen reitillä. Esteitä voi olla useampi, kuin yksi reitillä. Käynnistyessään robotti ensin kalibroi lukijansa ja sitten kulkee pitkin sille annettua polkua mahdollisimman tarkasti, kunnes havaitsee tiellään esteen, jonka se tunnistaa robotin päällä olevan ultraäänen avulla. Kun robotti on havainnut esteen, se lähtee kiertämään sitä vasemman kautta käyttäen hyödykseen esineen äärirajoja sekä ultraääntä. Saapuessaan esteen viimeiselle reunalle, kääntää se itsensä kohti viivaa ja kulkee sitä kohti kunnes lukija pääsee lukemaan taas viivaa.

Parhaiten robotti seuraa mustaa viivaa mahdollisimman vaalealla alustalla, jolloin robotti on kaikista tarkimmillaan ja pystyy kääntymään tiukkojakin mutkia sen koon sallimissa rajoissa. Esteiden koon on oltava pidempiä kuin robotti, mutta syvyydellä eikä leveydellä ole rajaa. Radan tekemisessä on otettava huomioon robotin koko ja annettava tarpeeksi tilaa esteille sekä robotille.

# Rakenne

Rakenteeltaan robotti koostuu seuraavista kontrolloiduista osista:
* Ultraäänisensori
* Valosensori
* 3 moottoria
  * 2 liikkumista varten
  * 1 ultraäänisensorin liikuttamiseen

Robotti on kelkkamainen auto, jonka päällä on vaakatasossa liikkuva ultraäänisensori ja edessä staattinen valosensori. Robotin liikkuminen tapahtuu sen kahden pyörän avulla, joita voidaan liikuttaa eri tahtiin paikallaan pyörimisen saavuttamiseksi tai samaan aikaan synkronoidusti, jolloin saavutettaan tasainen ja suora liike eteenpäin. Edessä sivuilla olevat kelkat pitävät robotin lukijan mahdollisimman lähellä maata tarkkuuden saamiseksi, mutta ne myös mahdollistavat robotin kääntymisen tasapainoisesti. Päällä olevaa ultraäänisensoria on myös mahdollista liikuttaa esteen kiertämisen helpoittamiseksi sen alapuolella olevan moottorin avulla.

Mitat:



### Kuvat & Videot

# Koodin rakenne

# Testaus

# Puutteet

# Käyttöohje
