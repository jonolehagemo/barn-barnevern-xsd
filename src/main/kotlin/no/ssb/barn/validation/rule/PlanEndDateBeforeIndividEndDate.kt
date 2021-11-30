package no.ssb.barn.validation.rule

import no.ssb.barn.framework.AbstractRule
import no.ssb.barn.framework.ValidationContext
import no.ssb.barn.report.ReportEntry
import no.ssb.barn.report.WarningLevel
import no.ssb.barn.xsd.PlanType

class PlanEndDateBeforeIndividEndDate : AbstractRule(
    WarningLevel.ERROR,
    "Plan Kontroll 2c: Sluttdato mot individets sluttdato",
    PlanType::class.java.simpleName
) {
    override fun validate(context: ValidationContext): List<ReportEntry>? {
        val sak = context.rootObject.sak
        val individEndDate = sak.sluttDato ?: return null

        return sak.virksomhet.asSequence()
            .mapNotNull { virksomhet -> virksomhet.plan }
            .flatten()
            .filter { plan ->
                plan.konklusjon?.sluttDato?.isAfter(individEndDate) == true
            }
            .map {
                createReportEntry(
                    "Plan (${it.id}). Planens sluttdato"
                            + " (${it.konklusjon?.sluttDato}) er etter individets"
                            + " sluttdato ($individEndDate)",
                    it.id
                )
            }
            .toList()
            .ifEmpty { null }
    }
}