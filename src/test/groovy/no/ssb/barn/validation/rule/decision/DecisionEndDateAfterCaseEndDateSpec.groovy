package no.ssb.barn.validation.rule.decision

import no.ssb.barn.report.WarningLevel
import no.ssb.barn.validation.ValidationContext
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.ZonedDateTime

import static no.ssb.barn.testutil.TestDataProvider.getTestContext

@Narrative("""
Vedtak Kontroll 2c: SluttDato mot sakens SluttDato

Gitt at man har et Vedtak der Konklusjon/SluttDato finnes og i sak der SluttDato finnes<br/>
når vedtakets SluttDato er etter sakens SluttDato<br/>
så gi feilmeldingen "Vedtakets sluttdato {Konklusjon/SluttDato} er etter sakens sluttdato {SluttDato}

Alvorlighetsgrad: ERROR
""")
class DecisionEndDateAfterCaseEndDateSpec extends Specification {

    @Subject
    DecisionEndDateAfterCaseEndDate sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new DecisionEndDateAfterCaseEndDate()
        context = getTestContext()
    }

    @Unroll
    def "Test av alle scenarier"() {
        given:
        context.rootObject.sak.sluttDato = caseEndDate
        and:
        def decision = context.rootObject.sak.vedtak.first()
        and:
        decision.konklusjon.sluttDato = decisionEndDate
        and:
        context.rootObject.sak.vedtak = [decision]
        and:
        if (resetConclusion) {
            decision.konklusjon = null
        }

        when:
        def reportEntries = sut.validate(context)

        then:
        (reportEntries != null) == errorExpected
        and:
        if (errorExpected) {
            assert 1 == reportEntries.size()
            assert WarningLevel.ERROR == reportEntries[0].warningLevel
            assert reportEntries[0].errorText.contains("er etter sakens sluttdato")
        }

        where:
        resetConclusion | caseEndDate                       | decisionEndDate                   || errorExpected
        false           | null                              | null                              || false
        false           | ZonedDateTime.now().minusYears(1) | ZonedDateTime.now()               || true
        true            | ZonedDateTime.now().minusYears(1) | ZonedDateTime.now()               || false
        false           | ZonedDateTime.now()               | ZonedDateTime.now().minusHours(1) || false
        true            | ZonedDateTime.now()               | ZonedDateTime.now()               || false
        false           | ZonedDateTime.now()               | ZonedDateTime.now().minusYears(1) || false
        true            | ZonedDateTime.now()               | ZonedDateTime.now().minusYears(1) || false
    }
}