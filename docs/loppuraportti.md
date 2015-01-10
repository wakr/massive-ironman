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

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/robottiEdest%C3%A4.jpg?raw=true "Robotti edestä")

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/robottiSivusta.jpg?raw=true "Robotti sivusta")

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/robottiAlta.jpg?raw=true "Robotti alta")

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/robottiTakaa.jpg?raw=true "Robotti takaa")


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

* Viivanseuraaminen tapahtuu niin, että `ValoSensori.java`-luokan ilmentymä antaa ensin luetun valoisuuden väliltä 0 - 100, jossa arvo 0 on täysin musta ja 100 täysin vaalea. Tämä arvo annetaan PID-kontrollerin ilmentymälle, joka suorittaa [PID-operaation](http://en.wikipedia.org/wiki/PID_controller). `PID.java` laskee kuinka kaukana luettu arvo on halutusta 0-arvosta, jolloin lukija on viivan reunassa. PID-kontrolleri myös derivoi ja integroi arvoa `PysyvaArvo.java`:n arvojen avulla, jolloin saadaa tarkka ja säädettävä viivanseuraaja. PID palauttaa laskemansa kääntösuhteen, johon lisataan/vahennetaan moottorien keskiteho. Kun arvot on laskettu kutsutaan `paataToiminta(vasenPower, oikeaPower)`-metodia, joka taas kutsuu pilotin `asetaVoimaJaLiikutaEteen(teho)` kummallekkin puolelle eli oikealle sekä vasemmalle. Näin saadaan siirrettyä tarvittava teho suoraan pyörille. Jos teho on negatiivinen, osaa pilotin metodit muuttaa tehon positiiviseksi, mutta pyörimissuunnan taaksepäin.

* Esteen kierto alkaa, kun ultraäänisensori havaitsee kohteen radalla. Robotti ensin pysäyttää itsensä kutsumalla `pysaytaRobootti()`-metodia jonka jälkeen pilotti resetoi moottorien takometrit ettei `Pilotti.java`-luokan sisällä oleva `DifferentialPilot`-luokan ilmentymä menisi sekaisin. Metodi `etsiEsteenReunatJaKierra()` taas suorittaa itse väistöoperaation, jossa robotti aluksi kääntyy n. 20cm päässä esteestä oikealle ja kääntää ultraäänisensorin kohti estettä. Tämän jälkeen robotti etenee kääntyneenä esteen etureunaa pitkin pilottia käskyttäen, kunnes saapuu esteen kulmalle. Kulmalle saapuessaan robotti vielä liikuttaa itseään pituutensa verran eteenpäin ja kutsuu pilotin metodia `kaannyVasemmalle(90)`. Tämä metodikutsu (kuten edellä mainitut liikkumisetkin esteen väistössä) kohdistuvat pilotin sisällä olevalle `DifferentialPilot`-luokan ilmentymälle, joka saa aikaan synkronoidun liikkumisen moottorien välillä (mahdollistaa suoraan menemisen mahdollisimman tarkasti). Robotti suorittaa samanlaisen liikesarjan seuraavaksi metodin `kuljeEsteenOhiSivulta()`, kunnes saapuu viimeiselle reunalle esteellä. Tällöin robotti kääntyy 45 astetta vasemmalle pilotin avulla ja alkaa kulkemaan suoraan kunnes metodi `kuljeEteenKunnesLukijanArvoAlle(40)` havaitsee alle 40 lukeman valosensorilta (eli viivalukeman) ja pysäyttää robotin. Lopuksi vielä palautetaan `UltraSensori.java`:n boolean parametri `onkoLoydetty` takaisin falseksi, asetetaan pilotin avulla moottorien tehoiksi takaisin keskiteho ja vapautetaan pilotilla moottorit regulaatiosta. Näin päästään takaisin samaan tilanteeseen kuin metodin `haeMaxMinLukemat()` jälkeen alussa ja viivan seuraaminen jatkuu taas normaalisti.

Robotin tekoäly olettaa, että esteet ovat suunnilleen neliön tai suorakulman muotoisia ja hieman korkeampia kuin robotti johtuen ultraäänisensorin rajoitteista. Käännöksien asteet ovat 90 astetta ultraäänisensorille ja sivumoottoreille, kun estettä kierretään edestä ja sivulta. Viimeinen käännös esteen kierrossa on 45 astetta robotille ja 90 astetta ultraäänisensorille.  Seurattavan teipin olisi hyvä olla mustaa teippiä esimerkiksi sähköteippiä ja alustan mahdollisimman vaalea verrattuna seurattavaan teippiin, jotta robotin valosensori saa mahdollisimman tarkat arvot.

# Testaus

- **Yksikkötestit**
  * Robotin PID-säädin on testattu toimivaksi muutamalla yksikkötestillä, jotka sijaitsevat `LogiikanTestaaja.java`-luokassa, muuten testit ovat käytännössä olleet käytännöllisiä testejä eli testejä, jotka testaavat robotin toimintaa kokonaisuutena oikeilla ajoilla.
- **Käytännölliset testit:**
  * Alempana muutama tärkeä rajatapaus testattuna. Käytännössä koko testausprosessi kokonaisuudessaan kattoi paljon erilaisia testejä, kuten esimerkiksi kymmeniä erilaisia kulmia väliltä 0 - 180 astetta, monia erilaisia esteitä kuten kulmikkaita ja epätasaisia, erilaisia esteiden välejä, ratojen suorien osuuksien pituuksia sekä radan muotoja.

#####Testitapaus 1:

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/helppoRata.jpg?raw=true "Helppo rata")

Tein kuvassa olevan radan robotille, missä haastavin kulma oli yli 90 astetta. Testin tarkoitus oli testata, että robotti pääsee radan oikealta vasemmalle, kun PID oli säädetty ja moottorien teho oli suurin mahdollinen suhteessa PID-säätimeen.

Robottini onnistui testissä ja vielä erittäin nopeasti. Testi osoitti, että robotti osaa mennä rataa pitkin, jossa suurin kulma on yli 90 astetta.

#####Testitapaus 2:

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/vaikeaRata.jpg?raw=true)
Seuraava rata oli haastavampi. Robotti lähti radan oikeasti reunasta ja seurasi rataa nätisti, kunnes saapui viimeiseen käännökseen.

Tämä käännös oli robotilleni liian tiukka, joten päätin kokeilla uudestaan muutaman kerran ja päädyin tulokseen, että robotti pääsi noin 4/10 kerroista mutkan läpi. En vetäisi johtopäätöstä, että robotti epäonnistui testin, mutta voin varmasti sanoa sen, että viimeinen mutka oli robotille vaikea. Mutkan onnistumiseen vaikuttivat kulma, jossa robotti mutkaan tuli, robotin nopeus ja myös robotin rakenne.

#####Testitapaus 3:

![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/esimerkkiRata.jpg?raw=true)

Testasin robottia tällä kertaa radalla, jossa on kierros. Annoin robotin mennä rataa muutaman kerran ympäri ja kaikki toimi kuten pitikin. Lisäsin tämän jälkeen radan suoran osuuden keskelle esteen Fazerin konvehtirasiasta, joka osoitti radan suuntaan viistosti.

Testi onnistui täydellisesti. Robotti kiersi esteen tarpeeksi kaukaa, kuten pitikin ja palasi takaisin radalle ja jatkoi lukemista, kunnes taas palasi esteen eteen radalla. Väistämisen aikana etäisyys esteeseen kiertojen aikana muuttui hieman, mutta pysyi noin 10-20cm päästä esteestä jokaisella väistöllä.


#####Testitapaus 4 (laajennosta 3.):

Lisäsin samalle radalle nyt kaksi rasiaa peräkkäin. Suoran pituus oli noin 60cm ja rasioiden väli 15cm. Robotti toimi hyvin, kunnes se palasi ensimmäiseltä esteeltä. Sillä ei ollut tarpeeksi tilaa suoristaa itseään ja se törmäsi suoraan seuraavaan esteeseen yrittäessään palata ensimmäisen väistöstä.

Testi osoitti, että kahden peräkkäisen esteen väli on oltava tarpeeksi suuri, jotta robotti voi palata takaisin viivalle ja suoristaa itseään lukuvaiheessa.

#####Testitapaus 5

Tein testitapaus 3. radan uudestaan kahdella esteellä, mutta muokkasin hieman rataa tehden sen ylä- ja alaosasta symmetrisen. Laitoin robotin liikkeelle vasemmasta yläkulmasta ja robotti onnistui nyt väistämään kummatkin esteet. Annoin pyöriä robotin 5 kertaa rataa ympäri eikä virheitä esiintynyt.

Robotti siis toimi hyvin radalla, jossa oli annettu tarpeeksi tilaa kääntymisille ja väistöille. Robotti myös väisti useaa estettä ajon aikana, joten voin testin perusteella sanoa, että robottini seuraa syklisiä ratoja mallikkaasti väistäen sen eteen tulevia esteitä.

Toteutettujen testien perusteella voin sanoa, että robottini toimii varmasti radalla, jonka suurin kulma on yli 90 astetta. Sitä alemmissa voi robotti nopeutensa ja koonsa takia eksyä radalta. Radan suunnittelussa on otettava huomioon myös robotin koko, jolloin kääntymisille on annettava tarpeeksi tilaa yli 90 asteen kulmissakin. Esteiden olisi hyvä sijaita suorilla osuuksilla, joissa robotilla on tarpeeksi tilaa tulla takasin radalle. Kahden esteen välinen etäisyys ei saa olla liian pieni ja nyrkkisääntönä voidaan pitää noin 2 x robotin pituuden väliä. Muodoltaan esteiden olisi hyvä olla kohtisuorassa rataa kohti ja korkeudeltaan sopivia sekä radan ympäristön mahdollisimman esteetön, jotta ultraäänisensori toimii kuten pitääkin.



# Puutteet ja mahdolliset parannukset

Robotin suurimpana puutteena voidaan pitää sen hieman rajoitettua toimintaympäristöä, jossa radan esteiden sijoittelun ei voi olla vapaa. Tämä ongelma esiintyy, kun robotti on väistänyt esteen ja lähtee 45 asteen kulmassa suoraan löytämättä rataa uudelleen.

Robotin nopeus on sekä hyvä, että ongelmallinen asia. Suuri nopeus tarkoittaa epätarkkuutta tietyissä tilanteissa jolloin robotti saattaa eksyä radalta, mutta myös nopeaa radan selvittämistä. Toisaalta tasapainoilu nopeuden ja tarkkuuden välillä on vaikeaa.

Robotti olettaa kiertävänsä esteen aina oikealta puolelta, joten vaikka este olisi lyhyempi kiertää vasemmalta, niin robotti ei ota tätä huomioon. Esteen kierrossa myös paluu 45 asteen kulmassa on ongelmallinen, koska jos esteen leveys olisi esimerkiksi yhden metrin, niin robotti joutuisi kulkemaan pitkän matkan etsiäkseen viivaa ja tämä heijastuisi myös radan suunnittelussa.

Yksi ongelmista on myös robotin rakenteessa. Ultraäänisensorin korkeuden takia esteiden on oltava aina suhteellisen korkeita (n. 20cm), mutta rakenteessa eniten haittaavat etukelkat, jotka saattavat jäädä teippiin kiinni tai alustaan, jos se ei ole tarpeeksi tasainen.

Koodi ei lopeta robotin toimintaa, joten käyttäjän vastuulle jää Enter-painikkeen painaminen, kun hän haluaa, että robotti pysähtyy.

Robotti ei aina käänny tarvittavaa määrää, joten voi syntyä tilanteita väistämisessä, joissa robotti olettaa kääntyneensä tarpeeksi, mutta seurauksena onkin esteeseen törmääminen.

Vaikka ongelmia ja puutteita on jonkin verran, on nämä suhteellisen helppoja korjata esimerkisi muuttamalla robotin rakennetta paremmaksi, muokkaamalla väistämisen logiikkaa ja parantelemalla koodin tarkkuuksia. Tulevaisuudessa robottini ultraäänisensori voisi olla paremmassa paikassa ja väistön logiikkaa muutettu niin, että robotti ei tee 45 asteen käännöstä lopuksi vaan etenee lopussakin esteen pintoja pitkin kunnes saapuu viivalle esteen viereen ja kääntyy oikealle kohti rataa. Robotti voisi myös tarkistaa aina, että se on kääntynyt tarvittavaan kulmaan, etteivät kulmat jäisi liian pitkiksi/lyhyiksi.


# Käyttöohje

Kun rata on valmis ja robotti on koottuna mittojen mukaan etene näin:

1. Kiinnitä robotti USB-piuhalla tietokoneeseen ja aja build.xml projektista
2. Irroita USB-johto ja etsi main-tiedosto robotista
3. Laita robotti ratasi alkuun ja mieluiten suoralle osuudelle, jotta automaattinen kalibrointi onnistuu
4. Aja ohjelma robotin kautta
5. Katso kun robottisi kiitää tekemäsi radan ympäri

Kun haluat pysäyttää robotin:

1. Nosta robotti ilmaan
2. Paina Enter-painiketta

# Rakennusohje

1. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus1.jpg?raw=true)
2. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus2.jpg?raw=true)
3. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus3.jpg?raw=true)
4. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus4.jpg?raw=true)
5. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus5.jpg?raw=true)
6. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus6.jpg?raw=true)
7. ![](https://github.com/wakr/massive-ironman/blob/master/docs/pics/kasaus7.jpg?raw=true)
