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

# Testaus

# Puutteet

# Käyttöohje
