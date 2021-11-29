package no.ssb.barn.validation.rule

import no.ssb.barn.framework.AbstractRule
import no.ssb.barn.framework.ValidationContext
import no.ssb.barn.report.ReportEntry
import no.ssb.barn.report.WarningLevel
import no.ssb.barn.xsd.PlanType

class TodoPlanEndDateBeforeIndividEndDate : AbstractRule(
    WarningLevel.ERROR,
    "Plan Kontroll 2b: Sluttdato mot rapporteringsår",
    PlanType::class.java.simpleName
) {
    override fun validate(context: ValidationContext): List<ReportEntry>? {
        TODO("Not yet implemented")
    }
}