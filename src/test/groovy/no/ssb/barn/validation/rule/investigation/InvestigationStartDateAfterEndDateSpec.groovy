package no.ssb.barn.validation.rule.investigation

import no.ssb.barn.report.WarningLevel
import no.ssb.barn.validation.ValidationContext
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.ZonedDateTime

import static no.ssb.barn.testutil.TestDataProvider.getTestContext

@Narrative("""
Undersøkelse Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Undersøkelse der StartDato og Konklusjon/SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Undersøkelsens startdato {StartDato} er etter sluttdato {Konklusjon/SluttDato}"

Alvorlighetsgrad: ERROR
""")
class InvestigationStartDateAfterEndDateSpec extends Specification {

    @Subject
    InvestigationStartDateAfterEndDate sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new InvestigationStartDateAfterEndDate()
        context = getTestContext()
    }

    @Unroll
    def "Test av alle scenarier"() {
        given:
        def undersokelse = context.rootObject.sak.undersokelse[0]
        and:
        undersokelse.startDato = startDate
        and:
        if (resetConclusion) {
            undersokelse.konklusjon = null
        } else {
            undersokelse.konklusjon.sluttDato = endDate
        }

        when:
        def reportEntries = sut.validate(context)

        then:
        noExceptionThrown()
        and:
        (reportEntries != null) == errorExpected
        and:
        if (errorExpected) {
            assert 1 == reportEntries.size()
            assert WarningLevel.ERROR == reportEntries[0].warningLevel
            assert reportEntries[0].errorText.contains("er etter sluttdato")
        }

        where:
        resetConclusion | startDate                       | endDate                         || errorExpected
        false           | ZonedDateTime.now()             | ZonedDateTime.now()             || false
        false           | ZonedDateTime.now()             | ZonedDateTime.now().plusDays(1) || false
        true            | ZonedDateTime.now()             | ZonedDateTime.now()             || false
        false           | ZonedDateTime.now().plusDays(1) | ZonedDateTime.now()             || true
    }
}