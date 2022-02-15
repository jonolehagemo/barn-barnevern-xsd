# Kravspesifikasjon for validering

## Innhold
- [Filbeskrivelse](#Filbeskrivelse)
- [Sak](#Sak)
- [Virksomhet](#Virksomhet)
- [Melding](#Melding)
  - [Melder](#Melder)
  - [Saksinnhold](#Saksinnhold)
- [Undersøkelse](#Undersokelse)
- [Vedtak](#Vedtak)
- [Tiltak](#Tiltak)
  - [Lovhjemmel](#TiltakLovhjemmel)
- [Plan](#Plan)
- [Ettervern](#Ettervern)
- [Flytting](#Flytting)
- [Oversendelse til fylkesnemnd](#OversendelseTilFylkesnemnd)




<a name="Filbeskrivelse"></a>
## Filbeskrivelse

### Validéring av innhold mot filbeskrivelse

Gitt at man har en fil med innhold som man skal validere mot filbeskrivelsen<br/>
når validering av filen mot filbeskrivelsen feiler<br/>
så gi feilmeldingen "Innholdet er feil i forhold til filbeskrivelsen / XSD"

Alvorlighetsgrad: FATAL

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/XsdRuleSpec.groovy) 

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/XsdRule.kt)

<a name="Sak"></a>
## Sak

### Sak Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Sak der StartDato og SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Sakens startdato {StartDato} er etter sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseEndDateAfterStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseEndDateAfterStartDate.kt)



### Sak Kontroll 3: Fødselsnummer og DUFnummer

Gitt at man har en Sak<br/>
når fødselsnummer mangler <br/>
så gi feilmeldingen "Feil i fødselsnummer. Kan ikke identifisere klienten."

Gitt at man har en Sak<br/>
når fødselsnummer mangler og DUF-nummer mangler <br/>
så gi feilmeldingen "DUFnummer mangler. Kan ikke identifisere klienten."

Gitt at man har en Sak<br/>
når fødselsnummer og DUF-nummer mangler <br/>
så gi feilmeldingen "Fødselsnummer og DUFnummer mangler. Kan ikke identifisere klienten."

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseSocialSecurityIdAndDufSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseSocialSecurityIdAndDuf.kt)



### Sak Kontroll 6: Klienten skal ha melding, plan eller tiltak

Gitt at man har en Sak <br/>
når saken mangler melding, plan og tiltak<br/>
så gi feilmeldingen "Klienten har ingen meldinger, planer eller tiltak"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseHasContentSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseHasContent.kt)



### Sak Kontroll 7: Klient over 25 år og skal avsluttes i barnevernet

Gitt at man har en Sak og utleder alder ved hjelp av fødselsnummer<br/>
når alder er 25 eller større<br/>
så gi feilmeldingen "Klienten er over 25 år og skal avsluttes som klient"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseAgeAboveTwentyFiveSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseAgeAboveTwentyFive.kt)




### Sak Kontroll 8: Klient over 18 år skal ha tiltak

Gitt at man har en Sak og utleder alder ved hjelp av fødselsnummer<br/>
når alder er 18 eller større og tiltak mangler <br/>
så gi feilmeldingen "Klienten er over 18 år og skal dermed ha tiltak"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseAgeAboveEighteenAndMeasuresSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseAgeAboveEighteenAndMeasures.kt)



### Sak Kontroll 11: Fødselsnummer

Gitt at man har en Sak<br/>
når fødselsnummer mangler <br/>
så gi feilmeldingen "Klienten har ufullstendig fødselsnummer. Korriger fødselsnummer."

Alvorlighetsgrad: Warning

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/CaseSocialSecurityIdSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/CaseSocialSecurityId.kt)





<a name="Virksomhet"></a>

## Virksomhet

### Virksomhet Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Virksomhet der StartDato og SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Virksomhetens startdato {StartDato} er etter sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/BusinessEndDateAfterStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/BusinessEndDateAfterStartDate.kt)



### Virksomhet Kontroll 2c: SluttDato er etter sakens SluttDato

Gitt at man har en Sak der SluttDato finnes og virksomhet der SluttDato finnes<br/>
når virksomhetens SluttDato er etter sakens SluttDato<br/>
så gi feilmeldingen "Virksomhetens startdato {SluttDato} er etter sakens sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/BusinessEndDateAfterCaseEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/BusinessEndDateAfterCaseEndDate.kt)



### Virksomhet Kontroll 2e: StartDato er før sakens StartDato

Gitt at man har en Sak der StartDato finnes og virksomhet der StartDato finnes<br/>
når virksomhetens StartDato er før sakens StartDato <br/>
så gi feilmeldingen "Virksomhetens startdato {StartDato } er før sakens startdato {StartDato }"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/BusinessStartDateBeforeCaseStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/BusinessStartDateBeforeCaseStartDate.kt)



### Virksomhet Kontroll 3: Bydelsnummer og bydelsnavn

Gitt at man har en Virksomhet der Organisasjonsnummer er en av 958935420 (Oslo), 964338531 (Bergen), 942110464 (Trondheim) eller 964965226 (Stavanger)<br/>
når Bydelsnummer eller Bydelsnavn mangler utfylling<br/>
så gi feilmeldingen "Virksomhetens Bydelsnummer og Bydelsnavn skal være utfylt"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/BusinessUrbanDistrictNumberAndNameSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/BusinessUrbanDistrictNumberAndName.kt)



<a name="Melding"></a>

## Melding

### Melding Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Melding der StartDato og Konklusjon/SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Meldingens startdato {StartDato} er etter sluttdato {Konklusjon/SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageStartDateAfterEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageStartDateAfterEndDate.kt)



### Melding Kontroll 2c: SluttDato er etter virksomhetens SluttDato

Gitt at man har en Melding der Konklusjon/SluttDato finnes og i virksomhet der SluttDato finnes<br/>
når meldingens SluttDato er etter virksomhetens SluttDato<br/>
så gi feilmeldingen "Meldingens sluttdato {Konklusjon/SluttDato} er etter Virksomhetens sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageEndDateAfterBusinessEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageEndDateAfterBusinessEndDate.kt)



### Melding Kontroll 2e: StartDato er før virksomhetens StartDato

Gitt at man har en Melding med StartDato og i virksomhet med StartDato<br/>
når meldingens StartDato er før virksomhetens StartDato<br/>
så gi feilmeldingen "Meldingens startdato {StartDato} er før virksomhetens startdato {StartDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageStartDateBeforeBusinessStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageStartDateBeforeBusinessStartDate.kt)



### Melding Kontroll 3: Fristoverskridelse på behandlingstid

Gitt at man har en Melding der StartDato og Konklusjon/SluttDato finnes<br/>
når Konklusjon/SluttDato er mer enn 7 dager etter StartDato<br/>
så gi feilmeldingen "Fristoverskridelse på behandlingstid for melding, ({StartDato} -> {Konklusjon/SluttDato})"

Alvorlighetsgrad: Warning

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageProcessingTimeOverdueSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageProcessingTimeOverdue.kt)



### Melding Kontroll 4: Konkludert melding mangler melder

Gitt at man har en Melding<br/>
når Konklusjon finnes og 1 eller flere Melder(e) mangler<br/>
så gi feilmeldingen "Konkludert melding mangler melder(e)"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageMissingReportersSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageMissingReporters.kt)



### Melding Kontroll 5: Konkludert melding mangler saksinnhold

Gitt at man har en Melding<br/>
når Konklusjon finnes og 1 eller flere Saksinnhold mangler<br/>
så gi feilmeldingen "Konkludert melding mangler saksinnhold"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageMissingCaseContentSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageMissingCaseContent.kt)


<a name="Melder"></a>

### Melder

#### Melder Kontroll 2: Mangler Presisering

Gitt at man har en Melder der Kode er 22 (= Andre offentlige instanser)<br/>
når Melder mangler Presisering<br/>
så gi feilmeldingen "Melder med kode ({Kode}) mangler presisering"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageReporterMissingClarificationSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageReporterMissingClarification.kt)


<a name="Saksinnhold"></a>

### Saksinnhold

#### Saksinnhold Kontroll 2: Mangler Presisering

Gitt at man har et Saksinnhold der Kode er 18 (= Andre forhold ved foreldre/familien) eller 19 (= Andre forhold ved barnets situasjon)<br/>
når Saksinnhold mangler Presisering<br/>
så gi feilmeldingen "Saksinnhold med kode ({Kode}) mangler presisering"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/MessageReporterMissingClarificationSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/MessageReporterMissingClarification.kt)



<a name="Undersokelse"></a>
## Undersøkelse

### Undersøkelse Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Undersøkelse der StartDato og Konklusjon/SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Undersøkelsens startdato {StartDato} er etter sluttdato {Konklusjon/SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationStartDateAfterEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationStartDateAfterEndDate.kt)



### Undersøkelse Kontroll 2c: SluttDato er etter virksomhetens SluttDato

Gitt at man har en Undersøkelse der Konklusjon/SluttDato finnes og i virksomhet der SluttDato finnes<br/>
når undersøkelsens SluttDato er etter virksomhetens SluttDato<br/>
så gi feilmeldingen "Undersøkelsens sluttdato {Konklusjon/SluttDato} er etter virksomhetens sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationEndDateAfterBusinessEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationEndDateAfterBusinessEndDate.kt)



### Undersøkelse Kontroll 2e: StartDato er før virksomhetens StartDato

Gitt at man har en Undersøkelse der StartDato finnes og virksomhet der StartDato finnes<br/>
når undersøkelsens StartDato er før virksomhetens StartDato <br/>
så gi feilmeldingen "Undersøkelsens startdato {StartDato } er før virksomhetens startdato {StartDato }"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationStartDateBeforeBusinessStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationStartDateBeforeBusinessStartDate.kt)



### Undersøkelse Kontroll 3: Vedtaksgrunnlag mangler presisering

Gitt at man har et Vedtaksgrunnlag der Kode er 18 (= Andre forhold ved foreldre/familien) eller 19 (= Andre forhold ved barnets situasjon)<br/>
når Vedtaksgrunnlag mangler Presisering<br/>
så gi feilmeldingen "Vedtaksgrunnlag med kode ({Kode}) mangler presisering"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationDecisionMissingClarificationSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationDecisionMissingClarification.kt)



### Undersøkelse Kontroll 7: Konkludert undersøkelse mangler vedtaksgrunnlag

Gitt at man har en Undersøkelse der Konklusjon finnes og Konklusjon sin Kode er 1 eller 2<br/>
når Vedtaksgrunnlag mangler<br/>
så gi feilmeldingen "Undersøkelse konkludert med kode {Konklusjon/Kode} mangler vedtaksgrunnlag"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationConcludedMissingDecisionSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationConcludedMissingDecision.kt)



### Undersøkelse Kontroll 10: Undersøkelse skal ha relasjon til melding

Gitt at man har en Undersøkelse, en Relasjon og en Melding<br/>
når en relasjon som inneholder melding/Id i sin FraId, "Melding" i sin FraType, undersøkelse/Id i sin TilId og "Undersokelse" i sin TilType mangler<br/>
så gi feilmeldingen "Undersøkelse mangler en relasjon til melding"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationRelatedFromMessageSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationRelatedFromMessage.kt)



### Undersøkelse Kontroll 11: Fristoverskridelse på behandlingstid i forhold til melding sin startdato

Gitt at man har en ukonkludert Undersøkelse med en Relasjon til en Melding<br/> 
der relasjon som inneholder melding/Id i sin FraId, "Melding" i sin FraType, undersøkelse/Id i sin TilId og "Undersokelse" i sin TilType<br/>
og undersøkelse sin Konklusjon/SluttDato mangler <br/>

når Datouttrekk er mer enn 7 + 90 dager etter Melding sin StartDato <br/> 
og UtvidetFrist sin Invilget enten mangler eller er lik 2 (= "Nei"), <br/> 
så gi feilmeldingen "Undersøkelse skal konkluderes innen 7 + 90 dager etter melding sin startdato"

når Datouttrekk er mer enn 7 + 180 dager etter Melding sin StartDato <br/>
så gi feilmeldingen "Undersøkelse skal konkluderes innen 7 + 180 dager etter melding sin startdato"

Alvorlighetsgrad: Warning

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/InvestigationProcessingTimePassedDueDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/InvestigationProcessingTimePassedDueDate.kt)






<a name="Vedtak"></a>
## Vedtak

### X Vedtak Kontroll 2a: StartDato er etter SluttDato

### X Vedtak Kontroll 2c: SluttDato mot virksomhetens SluttDato

### X Vedtak Kontroll 2e: StartDato mot virksomhetens StartDato

<a name="Tiltak"></a>

## Tiltak

### X Tiltak Kontroll 2a: StartDato er etter SluttDato

### X Tiltak Kontroll 2c: SluttDato mot virksomhetens SluttDato

### X Tiltak Kontroll 2e: StartDato mot virksomhetens StartDato

### X Tiltak Kontroll  4: Omsorgstiltak med sluttdato krever årsak til opphevelse

### X Tiltak Kontroll 5: Kontroll om barnet er over 7 år og er i barnehage

### [Tiltak Kontroll 6: Klienten er 11 år eller eldre og i SFO](src/test/groovy/no/ssb/barn/validation/rule/MeasureAgeAboveElevenAndInSfoSpec.groovy)

Gitt at det er en sak med tiltak og fødselnummer (slik at man kan utlede alder)<br/>
når barnets alder er større enn 11 år og tiltakets kategori er '4.2' SFO/AKS<br/>
så gi feilmelding "Klienten er over 11 år og i SFO"

Alvorlighetsgrad: Warning

### X Tiltak Kontroll 7: Kontroll om presisering av tiltakskategori



### X Tiltak Kontroll  8: Kontroll av kode og presisering av opphevelse



### X Tiltak Kontroll 9: Kontroll om flere plasseringstiltak er oppgitt i samme tidsperiode

<a name="TiltakLovhjemmel"></a>

### Lovhjemmel

#### X Tiltak Kontroll 12: Kontroll av omsorgstiltak med sluttdato, krever årsak til opphevelse

#### X Tiltak Kontroll 13: Kontroll om individ er over 18 år og har omsorgtiltak

#### X Tiltak Kontroll 14: Kontroll om lovhjemmel er fylt ut med tallet 0



<a name="Plan"></a>

## Plan

### Plan Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Plan der StartDato og Konklusjon/SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Planens startdato {StartDato} er etter sluttdato {Konklusjon/SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/PlanStartDateAfterEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/PlanStartDateAfterEndDate.kt)



### Plan Kontroll 2c: SluttDato er etter virksomhetens SluttDato

Gitt at man har en Plan der Konklusjon/SluttDato finnes og i virksomhet der SluttDato finnes<br/>
når planens SluttDato er etter virksomhetens SluttDato<br/>
så gi feilmeldingen "Planens sluttdato {Konklusjon/SluttDato} er etter virksomhetens sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/PlanEndDateAfterBusinessEndDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/PlanEndDateAfterBusinessEndDate.kt)



### Plan Kontroll 2e: StartDato er før virksomhetens StartDato

Gitt at man har en Plan der StartDato finnes og virksomhet der StartDato finnes<br/>
når planens StartDato er før virksomhetens StartDato <br/>
så gi feilmeldingen "Planens startdato {StartDato } er før virksomhetens startdato {StartDato }"

Alvorlighetsgrad: ERROR

[Akseptanse kriterie](src/test/groovy/no/ssb/barn/validation/rule/PlanStartDateBeforeBusinessStartDateSpec.groovy)

[Kildekode](src/main/kotlin/no/ssb/barn/validation/rule/PlanStartDateBeforeBusinessStartDate.kt)



<a name="Ettervern"></a>
## Ettervern

### X Ettervern Kontroll 2a: TilbudSendtDato er etter SluttDato

### X Ettervern Kontroll 2c: SluttDato mot virksomhetens SluttDato

### X Ettervern Kontroll 2e: StartDato mot virksomhetens StartDato

<a name="Flytting"></a>
## Flytting

### X Plan Kontroll 2c: SluttDato mot virksomhetens SluttDato

### X Plan Kontroll 2f: SluttDato mot virksomhetens StartDato

<a name="OversendelseTilFylkesnemnd"></a>

## Oversendelse til fylkesnemnd

### X Plan Kontroll 2c: StartDato mot virksomhetens SluttDato

### X Plan Kontroll 2e: StartDato mot virksomhetens StartDato