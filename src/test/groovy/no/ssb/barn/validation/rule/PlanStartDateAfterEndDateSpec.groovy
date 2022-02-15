package no.ssb.barn.validation.rule

import no.ssb.barn.validation.ValidationContext
import no.ssb.barn.report.WarningLevel
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate

import static no.ssb.barn.testutil.TestDataProvider.getTestContext

@Narrative("""
Plan Kontroll 2a: StartDato er etter SluttDato

Gitt at man har en Plan der StartDato og Konklusjon/SluttDato finnes<br/>
når StartDato er etter SluttDato<br/>
så gi feilmeldingen "Planens startdato {StartDato} er etter sluttdato {Konklusjon/SluttDato}"

Alvorlighetsgrad: ERROR
""")
class PlanStartDateAfterEndDateSpec extends Specification {

    @Subject
    PlanStartDateAfterEndDate sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new PlanStartDateAfterEndDate()
        context = getTestContext()
    }

    @Unroll
    def "Test av alle scenarier"() {
        given:
        def plan = context.rootObject.sak.virksomhet[0].plan[0]
        and:
        plan.startDato = planStartDate
        and:
        plan.konklusjon.sluttDato = planEndDate
        and:
        if (resetConclusion) {
            plan.konklusjon = null
        }

        when:
        def reportEntries = sut.validate(context)

        then:
        (reportEntries != null) == errorExpected
        and:
        if (errorExpected) {
            assert 1 == reportEntries.size()
            assert WarningLevel.ERROR == reportEntries[0].warningLevel
            assert reportEntries[0].errorText.contains("er etter planens sluttdato ")
        }

        where:
        resetConclusion | planStartDate                 | planEndDate                   || errorExpected
        false           | LocalDate.now().minusYears(1) | LocalDate.now()               || false
        false           | LocalDate.now()               | LocalDate.now()               || false
        false           | LocalDate.now()               | LocalDate.now().minusYears(1) || true
        true            | LocalDate.now()               | LocalDate.now().minusYears(1) || false
    }
}