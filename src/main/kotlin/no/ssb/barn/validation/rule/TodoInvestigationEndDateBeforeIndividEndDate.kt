package no.ssb.barn.validation.rule

import no.ssb.barn.framework.AbstractRule
import no.ssb.barn.framework.ValidationContext
import no.ssb.barn.report.ReportEntry
import no.ssb.barn.report.WarningLevel
import no.ssb.barn.xsd.UndersokelseType

class TodoInvestigationEndDateBeforeIndividEndDate : AbstractRule(
    WarningLevel.ERROR,
    "Undersøkelse Kontroll 2c: Sluttdato mot individets sluttdato",
    UndersokelseType::class.java.simpleName
) {
    override fun validate(context: ValidationContext): List<ReportEntry>? {
        TODO("Not yet implemented")
    }
}