# Kuvaus

Viivanseuraaja-robotti on nimensä mukaisesti tarkoitettu seuraamaan viivaa, mutta siihen on lisätty ominaisuus väistää esteitä, jotka sijaitsevat sen reitillä. Esteitä voi olla useampi, kuin yksi reitillä. Käynnistyessään robotti ensin kalibroi lukijansa ja sitten kulkee pitkin sille annettua polkua mahdollisimman tarkasti, kunnes havaitsee tiellään esteen, jonka se tunnistaa robotin päällä olevan ultraäänen avulla. Kun robotti on havainnut esteen, se lähtee kiertämään sitä oikean kautta käyttäen hyödykseen esineen äärirajoja sekä ultraääntä. Saapuessaan esteen viimeiselle reunalle, kääntää se itsensä kohti viivaa ja kulkee sitä kohti kunnes lukija pääsee lukemaan taas viivaa.

Parhaiten robotti seuraa mustaa viivaa mahdollisimman vaalealla alustalla, jolloin robotti on kaikista tarkimmillaan ja pystyy kääntymään tiukkojakin mutkia sen koon sallimissa rajoissa. Esteiden koon on oltava pidempiä kuin robotti, mutta syvyydellä eikä leveydellä ole rajaa kunhan ne mahtuvat radalle. Radan tekemisessä on otettava huomioon robotin koko, radan kulmien "tiukkuus" ja annettava tarpeeksi tilaa esteille sekä robotille.

# Rakenne

Rakenteeltaan robotti koostuu seuraavista kontrolloiduista osista:
* Ultraäänisensori
* Valosensori
* 3 moottoria
  * 2 liikkumista varten
  * 1 ultraäänisensorin liikuttamiseen

Robotti on kelkkamainen auto, jonka päällä on vaakatasossa liikkuva ultraäänisensori ja edessä staattinen valosensori. Robotin liikkuminen tapahtuu sen kahden pyörän avulla, joita voidaan liikuttaa eri tahtiin paikallaan pyörimisen saavuttamiseksi tai samaan aikaan synkronoidusti, jolloin saavutettaan tasainen ja suora liike eteenpäin. Edessä sivuilla olevat kelkat pitävät robotin lukijan mahdollisimman lähellä maata tarkkuuden saamiseksi, mutta ne myös mahdollistavat robotin kääntymisen tasapainoisesti. Päällä olevaa ultraäänisensoria on myös mahdollista liikuttaa esteen kiertämisen helpoittamiseksi sen alapuolella olevan moottorin avulla.

Robotin koodille tärkeät mitat ovat renkaiden halkaisija (n. 5.5cm) sekä renkaiden etäisyys toisistaan (n. 12.5cm). Ultraäänisensori on noin 18cm korkeudessa, mutta käytännössä sen korkeudella ei ole mitään väliä, kunhan sen etäisyys pyörittävistä renkaista on 8-10cm ja se sijaitsee suunnilleen samalla tasolla, kuin valosensorikin.

Robotin rakenne mahdollistaa vakaan, mutta nopean liikkumisen. Vakaa rakenne parantaa myös ultraäänisensorin tarkkuutta. Rakenne sallii käytännössä kaikki liikkeet, kuten pyörimisen paikallaan ja suoraan menemisen tarkasti.


### Kuvat

![Robotti edestä](https://lh6.googleusercontent.com/dqeAUgI8XFqTLG00TBykzOhrfqEopdX2JgnqEcWNVtJ3fND3puI0sLVqMFIUKONcB16so4GoAcg=w1350-h523)

![Robotti sivusta](https://lh6.googleusercontent.com/gis7PV-TqqqF5m6o9BCGXtCwZm--5on86NXIFD-gwbXJM493LliIoOnJMkF0C9mRRZmx4vkJktA=w1350-h523)

![Robotti alta](https://lh3.googleusercontent.com/NiV8ioAuP3ziJ2y2eY8NNGtCp2Fa5U4eCTZKnnjUULjwoXy4bJUIfPSZ2e4CDxWjrmTZjpZa7ng=w1350-h523)

![Robotti takaa](https://lh3.googleusercontent.com/0R7PSvCfmrGax1ue9WN4gHY0dL7bdcuFwCfDhRwZw-KR_Ew40zIX9EnFW1rBBq5tbIbHObFvSrI=w1350-h523)


# Koodin rakenne

Koodi on jaettu neljään eri pääpakkaukseen:
* **logiikka**
  * ***moottori***
    * `Moottori.java`
    * `NakijaMoottori.java`
    * `SivuMoottori.java`
  * ***sensori***
    * `UltraSensori.java`
    * `ValoSensori.java`
  * `Pilotti.java`
  * `Robo.java`
* **main**
  * `Main.java`
* **test**
  * `LogiikanTestaaja.java`
* **util**
  * `PID.java`
  * `PysyvaArvo.java`

Logiikka sisältää kaikki robotin toimintaan liittyvät luokat kuten moottorit, sensorit, pilotin ja itse robotin tekoälyn. Main-pakkaus sisältää main-metodin josta robotti käynnistetään, test-pakkaus taas yksikkötestit PID-kontrollerille ja util-pakkaus sisältää sekä PID-kontrollerin sekä sen käyttämät ajon aikana muuttumattomat arvot.

Vastuut luokkien välillä on jaettu niin, että `Robo.java` tuntee PID-kontrollerin, pilotin ja kummatkin sensorit, mutta muut eivät tunne toisiaan. `PID.java` käyttää `PysyvaArvo.java`-enumin sekä parametrina saatua lukijan luettua arvoa hyödykseen laskiessaan kääntymisen astetta. Näiden yhteistoiminnallisuuden avulla `Robo.java` laskee ja välittää viivanseuraamiseen sekä esteiden väistelyyn tarvittavia arvoja, jotka se välittää `Pilotti.java`-luokalle, joka taas tuntee `Moottori.java`-intefacen toteuttavat luokat `NakijaMooottori.java` sekä `SivuMoottori.java`. Näitä ilmentymiä pilotti hallinnoi kaikkia moottoreita `Robo.java`:lta saamiensa arvojen perusteella mahdollistaen viivanseuraamisen ja esteiden väistelyn radalla.

Kun ohjelma käynnistetään mainista luoden `Robo.java`:n ilmentymä ja kutsuen `Robo.java`:n `kaynnista()`-metodia ohjelma ensin alustaa sensorit, pilotin sekä PID-kontrollerin. Tämän jälkee se kutsuu omaa metodiaan `haeMaxMinLukemat()`, joka kalibroi valosensorin vastaamaan nykyisen tilan valoisuutta pilotin avulla suoritettavan liikesarjan lopuksi. Suoritus etenee looppiin, jossa tapahtuu viivanseuraaminen sekä esteiden väistely kunnes Enter-painiketta painetaan. `Robo.java`-luokan ilmentymän sisällä olevassa loopissa robotti joko seuraa viivaa tai väistää esteen  riippuen siitä onko ultraäänisensori havainnut kohteen:

* Viivanseuraaminen tapahtuu niin, että `ValoSensori.java`-luokan ilmentymä antaa ensin luetun valoisuuden väliltä 0 - 100, jossa arvo 0 on täysin musta ja 100 täysin vaalea. Tämä arvo annetaan PID-kontrollerin ilmentymälle, joka suorittaa [PID-operaation](http://en.wikipedia.org/wiki/PID_controller). `PID.java` laskee kuinka kaukana luettu arvo on halutusta 0-arvosta, jolloin lukija on viivan reunassa. PID-kontrolleri myös derivoi ja integroi arvoa `PysyvaArvo.java`:n arvojen avulla, jolloin saadaa tarkka ja säädettävä viivanseuraaja. PID palauttaa laskemansa kääntösuhteen, johon lisataan/vahennetaan moottorien keskiteho. Kun arvot on laskettu kutsutaan `paataToiminta(vasenPower, oikeaPower)`-metodia, joka taas kutsuu pilotin `asetaVoimaJaLiikutaEteen(teho)` kummallekkin puolelle eli oikealle sekä vasemmalle. Näin saadaan siirrettyä tarvittava teho suoraan pyörille ja jos teho on negatiivinen, osaa pilotin metodit muuttaa tehon positiiviseksi, mutta pyörimissuunnan taaksepäin.

* Esteen kierto alkaa, kun ultraäänisensori havaitsee kohteen radalla. Robotti ensin pysäyttää itsensä kutsumalla `pysaytaRobootti()`-metodia jonka jälkeen pilotti resetoi moottorien takometrit ettei `Pilotti.java`-luokan sisällä oleva `DifferentialPilot`-olion ilmentymä menisi sekaisin. Metodi `etsiEsteenReunatJaKierra()` taas suorittaa itse väistö-operaation, jossa robotti aluksi kääntyy n. 20cm päässä esteestä oikealle ja kääntää ultraäänisensorin kohti estettä. Tämän jälkeen robotti etenee viistosti esteen etureunaa pitkin pilottia käskyttäen, kunnes saapuu esteen kulmalle. Kulmalle saapuessaan robotti vielä liikuttaa itseään pituutensa verran eteenpäin ja kutsuu pilotin metodia `kaannyVasemmalle(90)`. Tämä metodikutsu (kuten edellä mainitut liikkumisetkin esteen väistössä) kohdistuvat pilotin sisällä olevalle `DifferentialPilot`-olion ilmentymälle, joka saa aikaan synkronoidun liikkumisen moottorien välillä (mahdollistaa suoraan menemisen mahdollisimman tarkasti). Robotti suorittaa samanlaisen liikesarjan seuraavaksi metodin `kuljeEsteenOhiSivulta()`, kunnes saapuu viimeiselle reunalle esteellä. Tällöin robotti kääntyy 45 astetta vasemmalle pilotin avulla ja alkaa kulkemaan suoraan kunnes metodi `kuljeEteenKunnesLukijanArvoAlle(40)` havaitsee alle 40 lukeman valosensorilta (eli viivalukeman) ja pysäyttää robotin. Lopuksi vielä palautetaan nakijan boolean parametri `onkoLoydetty` takaisin falseksi, asetetaan pilotin avulla moottorien tehoiksi takaisin keskiteho ja vapautetaan pilotilla moottorit regulaatiosta. Näin päästään takaisin samaan tilanteeseen kuin metodin `haeMaxMinLukemat()` jälkeen alussa.

Robotin tekoäly olettaa, että esteet ovat n. neliön muotoisia ja hieman korkeampia, kuin robotti. Käännöksien asteet ovat 90 astetta ultraäänisensorille ja sivumoottoreille, kun estettä kierretään edestä ja sivulta. Viimeinen käännös esteen kierrossa on 45 astetta robotille ja 90 astetta ultraäänisensorille.

# Testaus

- Omat metodit
- omat luokat
- omat testit käytännössä

# Puutteet

# Käyttöohje
