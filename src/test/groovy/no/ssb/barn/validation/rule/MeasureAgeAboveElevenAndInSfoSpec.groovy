package no.ssb.barn.validation.rule

import no.ssb.barn.validation.ValidationContext
import no.ssb.barn.report.WarningLevel
import no.ssb.barn.xsd.KategoriType
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static no.ssb.barn.testutil.TestDataProvider.getMockSocialSecurityNumber
import static no.ssb.barn.testutil.TestDataProvider.getTestContext

@Narrative("""
Gitt at det er en sak med tiltak og fødselnummer (slik at man kan utlede alder)
dersom barnets alder er større enn 11 år og tiltakets kategori er '4.2' SFO/AKS
så gi feilmelding "Klienten er over 11 år og i SFO"

Alvorlighetsgrad: Warning
""")
class MeasureAgeAboveElevenAndInSfoSpec extends Specification {

    @Subject
    MeasureAgeAboveElevenAndInSfo sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new MeasureAgeAboveElevenAndInSfo()
        context = getTestContext()
    }

    @Unroll
    def "test alle scenarier"() {
        given: "det er en sak "
        def sak = context.rootObject.sak
        and: "som har tiltak med kategorikode"
        sak.virksomhet[0].tiltak[0].kategori = (createKategori) ? new KategoriType(code, "~presisering~") : null
        and: "som har et fødselnummer (her generert basert på en gitt alder)"
        sak.fodselsnummer = getMockSocialSecurityNumber(age)

        when: "barnets alder er større enn 11 skal det sjekkes at kategori er '4.2' SFO/Aktivitetsskole"
        def reportEntries = sut.validate(context)

        then: "at ingen unntak er kastet"
        noExceptionThrown()
        and: "at antall feil er som forventet"
        (reportEntries != null) == errorExpected
        and: "hvis det forventes feil"
        if (errorExpected) {
            and: "at antall feil er 1"
            assert 1 == reportEntries.size()
            and: "at riktig alvorlighetsgrad er satt"
            assert WarningLevel.WARNING == reportEntries[0].warningLevel
            and: "at feilmeldingsteksten inneholder en gitt tekst"
            assert reportEntries[0].errorText.contains("Klienten er over 11 år og i SFO")
        }

        where:
        age  | createKategori | code     || errorExpected
        11   | false          | ""       || false
        12   | false          | ""       || false
        11   | true           | "~kode~" || false
        12   | true           | "~kode~" || false
        11   | true           | "4.2"    || false
        11   | true           | "4.2"    || false
        12   | true           | "4.2"    || true
        12   | true           | "4.3"    || false
    }
}
