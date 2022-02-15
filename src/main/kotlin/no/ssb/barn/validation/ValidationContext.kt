package no.ssb.barn.validation

import no.ssb.barn.xsd.BarnevernType

data class ValidationContext(
    val messageId: String,
    val xml: String,
    val rootObject: BarnevernType
) {
    constructor(messageId: String, xml: String) : this(
        messageId,
        xml,
        BarnevernType()
    )
}