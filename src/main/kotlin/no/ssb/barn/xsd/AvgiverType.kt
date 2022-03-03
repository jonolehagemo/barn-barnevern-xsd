package no.ssb.barn.xsd

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "AvgiverType",
    propOrder = ["organisasjonsnummer", "kommunenummer", "kommunenavn", "bydelsnummer", "bydelsnavn"]
)
data class AvgiverType(
    @field:XmlAttribute(name = "Organisasjonsnummer", required = true)
    var organisasjonsnummer: String = "",

    @field:XmlAttribute(name = "Kommunenummer", required = true)
    var kommunenummer: String = "",

    @field:XmlAttribute(name = "Kommunenavn", required = true)
    var kommunenavn: String = "",

    @field:XmlAttribute(name = "Bydelsnummer")
    var bydelsnummer: String? = null,

    @field:XmlAttribute(name = "Bydelsnavn")
    var bydelsnavn: String? = null

)
