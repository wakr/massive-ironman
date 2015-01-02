# 3. viikko

Viikko on kulunut pääasiassa väistämis-logiikan toteuttamisessa, mutta myös PID-kontrolleri tuli valmiiksi ainakin siltä osin, että sen toiminnallisuus on suhteellisen hyvää. Heilumista kuitenkin vielä esiintyy, mutta se ei käytännössä haittaa robotin toiminnallisuutta, vaan pikemminkin PID-kontrollerista johtuva heiluminen auttaa robottia tekemään tiukkojakin käännöksiä ja selviytyminen tiukoista käännöksistä oli koko projektini idea. Jos aikaa jää ylimääräistä, niin pystyn lopuksi vielä yrittämään saada kaiken tehon irti PID:sta. 

Robotin rakenne koki alkuviikosta muutoksia, kun lisäsin uuden sensorin väistämis-logiikan toteutusta varten. Ongelmia syntyi pääasiassa liittämisen kanssa, kun en keksinyt aluksi järkevää tapaa tehdä sivuuttaissuunnassa kääntyvää sensoria moottorien rakenteen vuoksi. Keksin kuitenkin tavan lopulta, mutta sensori on aika korkealla, joten esteiden on oltava aina korkeammalla kun robottini sensori. Rakenteen puolelta yritin myös korvata kelkkoja jollain paremmalla tavalla, mutta valitettavasti kaikki pyörät ovat liian isoja kumien kanssa ja ilman kumeja niissä ei ole tarpeeksi pitoa. Päätin jättää lopulta edessä olevat kelkat paikoilleen, koska ne tuovat tarpeeksi nostetta valosensorille ja ne luistavat hyvin erilaisilla pinnoilla. 

Viikon koodaukset keskittyivät pääasiassa jo aiemmin mainittuun väistämisen logiikkaan. Se on aika yksinkertaista toteuttaa teoriassa: jos sensori havaitsee esteen reitillä, niin pysäytetään viivan lukeminen ja käytetään ultraäänisensoria etsimään esteen reunat joita käytetään hyödyksi estettä kiertäessä. Käytännössä kohtasin kuitenkin monia ongelmia toteutuksen kanssa: ultraäänisensorin hitautta, samanaikaisuuksien hallintaa robotissa, robotin koko ja logiikka. Sain ongelmat suurimmaksi osin kuitenkin ratkaistua, mutta kiertäminen ei ole kovin sulavaa vielä, joten viimeisellä viikolla testaukset jatkuvat ja hienosäätö. Olen havainnut, että paras este on tällä hetkellä dvd-boksi sen muodon ollessa suorakulmio. Esteen kiertämiseen liittyy myös rajoite kentässä; käytännössä kentän on jatkuttava samanlaisena esteen toisella puolelta kuin edestäkin, tai muuten robotti ei enää osaa takaisin reitille. Tähän en ole keksinyt mitään ratkaisua, joten jatkan projektia sillä olettamuksella, ettei ongelmaan ole ratkaisua kahden sensorin avulla.  

Viimeisen viikon työksi ennen demoja jää siis yleinen/koodin testaus ja sen dokumentointi, koodin siistiminen, parametrien hienosäädöt ja robotin rakenteen siistiminen. Myös väistämisen logiikan lopullinen toteutus tapahtuu tänään/huomenna jolloin projektini pääaiheet ovat käytännössä valmiit ja vain hienosäädöt puuttuvat. 








