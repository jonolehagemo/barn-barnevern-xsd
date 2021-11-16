# kostra-barnevern-xsd
Filbeskrivelse basert på xml schema definition brukt til å rapportere barnevernsdata fra kommuner til Nasjonalt barnevernregister. Erstatter tidligere KOSTRA barnevern-rapportering til Statistisk sentralbyrå og Kommunal halvårsrapportering til Bufdir.

# Versjonshistorikk
---
# v.0.13.0
## Struktur
- Nytt element *Virksomhet* inn som rotelement i *Sak* - for å håndtere når sak overføres mellom ulike bydeler og samme sak med samme id rapporteres fra ulike bydeler
    - Attributt *AvgiverType/@Distriktsnummer*, *AvgiverType/@Bydelsnummer* og *AvgiverType/@Bydelsnavn* er flyttet til *VirksomhetType*
- *Ettervern* har fått ny struktur: 
    - Attributt *Konklusjon* er blitt til et element med attributt *Kode*
		- Element *Konklusjon* har fått attributt *SluttDato*
- *OversendelseBarneverntjeneste* lagt til under *Sak* (var definert som type, men ikke lagt til som element)
- Fjernet *AvvistMelding*, meldinger som karakteriseres som grunnløse skal ikke rapporteres inn
- *Melding/Konklusjon* er blitt et eget element som inneholder obligatoriske attributter *Kode* og *SluttDato* for å unngå egen valideringsregel
- *Plan/@EvaluertDato* flyttet i struktur til *Plan/Evaluering/@UtfortDato*
- *Plan/@SluttDato* flyttet i struktur til *Plan/Konklusjon/@SluttDato*
- *Undersokelse/@UtvidetFrist* og *Undersokelse/@UtvidetFristDato* erstattet med nytt element *Undersokelse/UtvidetFrist* med attributter *StartDato* og *Innvilget* (ja/nei)
- *Tiltak/@SluttDato* flyttet i struktur til *Tiltak/Konklusjon/SluttDato*
- *Vedtak/@StatusKode* flyttet til *Vedtak/Status/@Kode* 
- Nytt attributt: *Vedtak/Status/@EndretDato*
- Nytt element: *Virksomhet/Flytting*

## Nye elementtyper
- *MigrertId* representerer original id for elementer (*Virksomhet*, *Plan*, *Melding*, *Tiltak*, *Undersøkelse*) som er migrert fra gammel løsning. Disse kan potensielt være rapportert tidligere for saker som har historie tilbake til periode som er rapportert på gammel løsning. MigreringId brukes til å identifisere disse slik at man unngår duplikater.   
- *FlyttingType* representerer flytting av barn under omsorg (innbefatter ikke kun adresseendring i samme tiltak)

## Navneendringer
- Type *OversendelseBarneverntjeneste* endret til *OversendelseBarneverntjenesteType*
- Element *OversendelseBarneverntjeneste* endret til *OversendelseFylkesnemnd*

## Andre endringer
- Dokumentasjon lagt inn i flere element/typer
- Enum-typer har fått tilbake opplisting som Annotation for enklere oversikt i verktøy / generert dokumentasjon (men er fortsatt i tillegg maskinlesbare via AppInfo)
- Endret tekst *MelderType/@kode* 8=Politi
- *Lov*, *Kapittel* og *Paragraf* kan nå bare ha én forekomst (ikke lenger *maxOccurs=unbounded*),  
- Maks. lengde for *Presisering* økt fra 100 til 300
- Verdier 1.1, 1.2, 1.3, 1.4 fjernet fra kodeliste i *Melding/Konklusjon/@Kode*. 
  Ny liste:
	1 = Henlagt 
  2 = Ikke henlagt – konklusjonsdato melding (eventuelt 7 dager etter mottatt melding) er startdato undersøkelse
  3 = Henlagt pga. aktive tiltak
  4 = Melding i pågående undersøkelse

- Attributt *Sak/@Saksbehandler* utgår
- *Plan/@EvaluertDato* flyttet i struktur til *Plan/Evaluering/@UtfortDato*
- *Tiltak/@Lovhjemmel* og *Tiltak/@JmfrLovhjemmel* er lagt tilbake i *Tiltak* - praksis viser at et *Vedtak* kan inneholde flere tiltak med hver sin hjemmel
- *Tiltak/@SluttDato* flyttet i struktur til *Tiltak/Konklusjon/@SluttDato*
- *TiltakType/@Ettervern* fjernet (var en feil som hang igjen fra tidligere endring)
- Elementtype *AnsvarType* fjernet (var ikke i bruk)
- Attributt *Vedtak/Krav/@SluttDato* flyttet til nytt element *Vedtak/Krav/Konklusjon/@SluttDato*
- Nye enumverdier i *BegrepsType*: "OversendelseFylkesnemnd" og "Flytting"

---
# v.0.10.1
XSD er nå strukturert for
- Innrapportering av én sak eller én avvist melding av gangen
- Rapporteringer med korte intervaller

Presisering av bruk av Id'er for innrapportering:
- Id'er forutsettes å være unike pr. elementtype pr. sak.	De trenger altså ikke være unike på tvers av elementtyper (eks.: et vedtak og en melding kan ha samme id).

## Struktur
- Alt er nå samlet i én fil for enklere redigering og oversikt
- Tidligere rotelement *Presisering* er lagt om til en type. XSD støtter ikke flere rotelement, så denne "feilen" har ikke vært synlig i XSD-verktøy eller ved lasting.

## Nye elementtyper
- *AvvistMelding* : Meldinger som ikke er realitetsbehandlet på grunn av innholdets karakter (sjikanøst, åpenbart grunnløst, o.l.) 
Kun Id skal innrapporteres (for å kunne telle opp unike id'er)
- *Referanse* : Representerer kobling mellom elementer
Alle koblinger som eksisterer i fagsystemet mellom elementer i xsd, som *Melding* <> *Undersøkelse*, *Undersøkelse* <> *Vedtak*, *Vedtak* <> *Vedtak*.
Erstatter tidligere *BasertPaaId*.
- *OversendelseBarneverntjeneste* representerer oversendelse fra barneverntjeneste til fylkesnemnd. Disse inngikk i kommunal halvårsrapportering, men lå ikke inne i XSD i versjon 0.9.0

## Navneendringer
- *Individ* er endret til *Sak*
- *Krav* er endret til *OversendelsePrivatKravType*

## Andre endringer
- *Tiltak/@Lovhjemmel* og *Tiltak/@JmfrLovhjemmel* er tatt ut av *Tiltak* - forutsettes innrapportert via *Vedtak*
- *Presisering* er nå definert som en attributt-type (se i avsnitt "Grunnstruktur")
- *SaksinnholdType/@Kode* kodeliste er endret
- *KategoriType/@Kode* kodeliste er endret
- *VedtakType/@StatusEndretDato* utgår, dette fanges opp tilstrekkelig nøye ved daglige innrapporteringer
- *Sak/@Avsluttet* erstatter tidligere attributt *Avsluttet3112*

---
# v.0.9.1 (presentert i arbeidsgruppe med leverandører 21.06.2021 - ikke publisert)
Det er foretatt en omstrukturering av XSD’ene (definisjonene er splittet opp til 3 nye filer) samt noen endringer i innhold. Dette i et forsøk på å gjøre spesifikasjonen mer konsistent, forståelig og brukbar. I den nærmeste tiden vil vi søke å få avklart enkelte gjenstående spørsmål med leverandører og barnevernfaglige.

## Nye filer:
