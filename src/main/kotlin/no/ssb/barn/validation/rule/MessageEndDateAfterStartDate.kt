package no.ssb.barn.validation.rule

import no.ssb.barn.framework.AbstractRule
import no.ssb.barn.framework.ValidationContext
import no.ssb.barn.report.ReportEntry
import no.ssb.barn.report.WarningLevel

class MessageEndDateAfterStartDate : AbstractRule(
    WarningLevel.ERROR,
    "Melding Kontroll 2a: Startdato etter sluttdato"
) {
    override fun validate(context: ValidationContext): List<ReportEntry>? =
        context.rootObject.sak.virksomhet.asSequence()
            .mapNotNull { virksomhet -> virksomhet.melding }
            .flatten()
            .filter { melding ->
                melding.konklusjon != null
                        && melding.startDato > melding.konklusjon!!.sluttDato
            }
            .map {
                createReportEntry(
                    """Meldingens startdato (${it.startDato}) er etter meldingens 
                        |sluttdato (${it.konklusjon?.sluttDato})""".trimMargin()
                )
            }
            .toList()
            .ifEmpty { null }
}