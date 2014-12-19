# Aihe

Työstettäväksi aiheeksi olen valinnut niin sanotun "viivaseuraajan", jonka tarkoitus on seurata mustaa viivaa. Musta viiva tehdään mustan teipin avulla ja se toimii robotin "ratana", jota pitkin robottini kulkee mahdollisimman nopeasti esimerkiksi pöydällä. Työni tavoitteeksi tuleekin minun luoda tiukkoja mutkia ja mahdollisesti jopa sykliä pitkin menevä robotti, joka olisi kuitenkin mahdollisimman nopea. (Jos/kun aihe on yleinen muitten keskuudessa, olisi tästä kiva järjestää jotain kisoja *vink*)


Toteutus

Toteutan robottini pääsosin hyödyntämällä Virtualboxiin luotua valmista kehitysympäristöä, jossa ohjelmointi tapahtuu Eclipsen avulla. Liikkumisen toteuttaminen on todennäköisesti suhteellisen yksinkertaista verrattuna valosensoriin ja sen lukemasta aiheutuvaan liikkumiseen, joten jälkimmäinen tulee olemaan projektini painopiste, jos haluan nopean ja toimivan robotin. Järjestyksen toteutukselle olen hahmoittanut näin: 

	1. Liikkuminen
	2. Lukeminen
	3. Liikkuminen ja lukeminen yhdessä
	4. Korjausliikkeet (korjaus-algoritmi)
	5. Extraa


Aikataulu (suuntaa antava)

1. viikko tutustuminen Lego-robottiin, kehitysympäristöön ja ongelmaan
2. viikko ideat konkreettisiksi luokiksi ja toiminnallisuudeksi
3. viikko työstäminen jatkuu
4. viikko testaus/viimeistely


Robottini

- 2 etupyörää ohjaajina
- etupyörin välissä valosensori
- takapyörä (mallia kaupan kärryjen pyörät) tai vaihtoehtoisesti "laahaava kelkka"


Työkalut

 - lejOS
 - Lego Mindstorms -robotti
 - Eclipse
 - git
 - paperi tai vastaava visuaalinen (luokkien hahmoittelua varten)


Haasteet

Haasteena tulee olemaan ainakin se, että miten robotti tunnistaa liikkumisen väärään suuntaan ja miten väärään suuntaan liikkuminen korjataan. Myös tiukat käännökset tulevat olemaan ongelma, koska näissä robotin on saatava paljon dataa siitä, minne suuntaan pitäisi liikkua. Tämä kaikki pitäisi myös tehdä mahdollisimman tehokkaasti, joten oikeanlaisen algoritmin suunnittelu on tärkeää. 





