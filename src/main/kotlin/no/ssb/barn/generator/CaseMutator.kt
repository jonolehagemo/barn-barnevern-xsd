package no.ssb.barn.generator

import no.ssb.barn.xsd.*
import java.time.LocalDate

object CaseMutator {

    @JvmStatic
    fun mutate(caseToMutate: CaseEntry) =
        newStateFuncMap.entries.asSequence()
            .filter { it.key.first == caseToMutate.state }
            .toList()
            .ifEmpty { return } // if state == Plan, there is no transition
            .random()
            .run {
                value(caseToMutate) // call transformation
                caseToMutate.state = key.second
            }

    // START Melding

    @JvmStatic
    fun fromMessageToInvestigation(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].undersokelse.add(UndersokelseType())
    }

    @JvmStatic
    fun fromMessageToDecision(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].vedtak.add(
            createVedtakType()
        )
    }

    // START Undersokelse

    @JvmStatic
    fun fromInvestigationToMeasure(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].tiltak.add(
            createTiltakType(caseEntry.updated)
        )
    }

    @JvmStatic
    fun fromInvestigationToDecision(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].vedtak.add(
            createVedtakType()
        )
    }

    // START Plan
    // N/A

    // START Tiltak

    @JvmStatic
    fun fromMeasureToPlan(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].plan.add(PlanType())
    }

    @JvmStatic
    fun fromMeasureToDecision(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].vedtak.add(
            createVedtakType()
        )
    }

    @JvmStatic
    fun fromMeasureToAfterCare(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].ettervern.add(EttervernType())
    }

    // START Vedtak

    @JvmStatic
    fun fromDecisionToMeasure(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].tiltak.add(
            createTiltakType(caseEntry.updated)
        )
    }

    @JvmStatic
    fun fromDecisionToAnotherDecision(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].vedtak.add(
            createVedtakType()
        )
    }

    @JvmStatic
    fun fromDecisionToAfterCare(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].ettervern.add(EttervernType())
    }

    // START Ettervern

    @JvmStatic
    fun fromAfterCareToMeasure(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].tiltak.add(
            createTiltakType(caseEntry.updated)
        )
    }

    @JvmStatic
    fun fromAfterCareToDecision(caseEntry: CaseEntry) {
        caseEntry.barnevern.sak.virksomhet[0].vedtak.add(
            createVedtakType()
        )
    }

    private val newStateFuncMap = mapOf(
        Pair(
            Pair(BarnevernState.MESSAGE, BarnevernState.INVESTIGATION),
            ::fromMessageToInvestigation
        ),
        Pair(
            Pair(BarnevernState.MESSAGE, BarnevernState.DECISION),
            ::fromMessageToDecision
        ),

        Pair(
            Pair(BarnevernState.INVESTIGATION, BarnevernState.MEASURE),
            ::fromInvestigationToMeasure
        ),
        Pair(
            Pair(BarnevernState.INVESTIGATION, BarnevernState.DECISION),
            ::fromInvestigationToDecision
        ),

        Pair(
            Pair(BarnevernState.MEASURE, BarnevernState.PLAN),
            ::fromMeasureToPlan
        ),
        Pair(
            Pair(BarnevernState.MEASURE, BarnevernState.DECISION),
            ::fromMeasureToDecision
        ),
        Pair(
            Pair(BarnevernState.MEASURE, BarnevernState.AFTERCARE),
            ::fromMeasureToAfterCare
        ),

        Pair(
            Pair(BarnevernState.DECISION, BarnevernState.MEASURE),
            ::fromDecisionToMeasure
        ),
        Pair(
            Pair(BarnevernState.DECISION, BarnevernState.DECISION),
            ::fromDecisionToAnotherDecision
        ),
        Pair(
            Pair(BarnevernState.DECISION, BarnevernState.AFTERCARE),
            ::fromDecisionToAfterCare
        ),

        Pair(
            Pair(BarnevernState.AFTERCARE, BarnevernState.MEASURE),
            ::fromAfterCareToMeasure
        ),
        Pair(
            Pair(BarnevernState.AFTERCARE, BarnevernState.DECISION),
            ::fromAfterCareToDecision
        )
    )

    private fun createVedtakType(): VedtakType =
        VedtakType(
            lovhjemmel = LovhjemmelType(
                kapittel = "1",
                paragraf = "2",
                ledd = mutableListOf("~ledd~")
            )
        )

    private fun createTiltakType(startDate: LocalDate): TiltakType =
        TiltakType(
            startDato = startDate,
            lovhjemmel = LovhjemmelType(
                kapittel = "1",
                paragraf = "2",
                ledd = mutableListOf("~ledd~")
            ),
            kategori = KategoriType(presisering = "~Presisering~")
        )
}