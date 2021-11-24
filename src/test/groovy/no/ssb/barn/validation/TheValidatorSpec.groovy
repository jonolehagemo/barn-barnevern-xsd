package no.ssb.barn.validation

import no.ssb.barn.testutil.TestDataProvider
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class TheValidatorSpec extends Specification {

    @Shared
    TheValidator sut

    @SuppressWarnings('unused')
    def setupSpec() {
        sut = TheValidator.create()
    }

    def "when create, receive validator instance"() {
        expect:
        TheValidator.create() instanceof TheValidator
    }

    def "when validate with invalid params, receive JSON result"() {
        when:
        def result = sut.validate(1, "~xmlBody~")

        then:
        result.startsWith("{\"journalId\"")
        and:
        result.contains("warningLevel\":\"FATAL\"")
    }

    def "when validate with valid params, receive JSON result"() {
        when:
        def result = sut.validate(
                1,
                TestDataProvider.getResourceAsString("test01_fil01.xml"))

        then:
        result.startsWith("{\"journalId\"")
        and:
        !result.contains("warningLevel")
    }

    @Unroll("test repeated #i time")
    def "when validate multiple times with invalid params, receive JSON result"() {
        when:
        def result = sut.validate(
                1,
                TestDataProvider.getResourceAsString("barnevern_with_errors.xml"))

        then:
        noExceptionThrown()
        and:
        null != result
        and:
        result.startsWith("{\"journalId\"")
        and:
        result.contains("warningLevel\":\"FATAL\"")

        where:
        i << (1..10)
    }
}