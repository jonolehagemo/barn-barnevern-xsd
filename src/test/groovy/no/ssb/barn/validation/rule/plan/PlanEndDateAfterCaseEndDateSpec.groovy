package no.ssb.barn.validation.rule.plan

import no.ssb.barn.report.WarningLevel
import no.ssb.barn.validation.ValidationContext
import no.ssb.barn.xsd.PlanKonklusjonType
import spock.lang.*

import java.time.ZonedDateTime

import static no.ssb.barn.testutil.TestDataProvider.getTestContext

@Narrative("""
Plan Kontroll 2c: SluttDato er etter sakens SluttDato

Gitt at man har en Plan der Konklusjon/SluttDato finnes og i sak der SluttDato finnes<br/>
når planens SluttDato er etter sakens SluttDato<br/>
så gi feilmeldingen "Planens sluttdato {Konklusjon/SluttDato} er etter sakens sluttdato {SluttDato}"

Alvorlighetsgrad: ERROR
""")
class PlanEndDateAfterCaseEndDateSpec extends Specification {

    @Subject
    PlanEndDateAfterCaseEndDate sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new PlanEndDateAfterCaseEndDate()
        context = getTestContext()
    }

    @Unroll
    def "Test av alle scenarier"() {
        given:
        context.rootObject.sak.sluttDato = caseEndDate
        and:
        def plan = context.rootObject.sak.plan.first()
        and:
        if (resetConclusion) {
            plan.konklusjon = null
        } else {
            plan.konklusjon = new PlanKonklusjonType(planEndDate)
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
            assert WarningLevel.ERROR == reportEntries.first().warningLevel
            assert reportEntries[0].errorText.contains("er etter sakens")
        }

        where:
        caseEndDate                        | planEndDate                     | resetConclusion || errorExpected
        ZonedDateTime.now().plusSeconds(1) | ZonedDateTime.now()             | false           || false
        ZonedDateTime.now()                | ZonedDateTime.now().plusDays(1) | false           || true
        ZonedDateTime.now()                | ZonedDateTime.now().plusDays(1) | true            || false
    }
}