package no.ssb.barn.validation.rule

import no.ssb.barn.framework.ValidationContext
import no.ssb.barn.testutil.TestDataProvider
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class RegionCityPartSpec extends Specification {

    @Subject
    RegionCityPart sut

    ValidationContext context

    @SuppressWarnings('unused')
    def setup() {
        sut = new RegionCityPart()
        context = TestDataProvider.getTestContext()
    }

    @Unroll
    def "Tester alle scenarier bydelsnr og -navn"() {
        given:
        context.rootObject.avgiver.kommunenummer = kommuneNr
        and:
        context.rootObject.avgiver.bydelsnummer = bydelsnr
        and:
        context.rootObject.avgiver.bydelsnavn = bydelsnavn

        when:
        def reportEntries = sut.validate(context)

        then:
        if (numberOfErrorsExpected == 0) {
            assert null == reportEntries
        } else {
            assert numberOfErrorsExpected == reportEntries.size()
            assert reportEntries[0].errorText.startsWith(firstError)
        }

        where:
        kommuneNr | bydelsnr | bydelsnavn   || numberOfErrorsExpected | firstError
        "0301"    | "11"     | "Nordstrand" || 0                      | null
        "0301"    | ""       | "Nordstrand" || 1                      | "Filen mangler bydelsnummer."
        "0301"    | "11"     | ""           || 1                      | "Filen mangler bydelsnavn."
        "0301"    | ""       | ""           || 2                      | "Filen mangler bydelsnummer."
        "0301"    | null     | null         || 2                      | "Filen mangler bydelsnummer."
        "0219"    | null     | null         || 0                      | null
    }
}
