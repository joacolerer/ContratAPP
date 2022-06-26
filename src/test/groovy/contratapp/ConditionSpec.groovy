package contratapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ConditionSpec extends Specification implements DomainUnitTest<Condition> {
    Condition cond

    def setup() {
        cond = new Condition(3, "IG Stories", 1000 as BigDecimal)
    }

    def cleanup() {
    }

    void "Condition is pending whe created"() {
        expect:
        cond.isPending()
    }

    void "Condition is accepted"() {
        when:
        cond.accept()
        then:
        cond.isAccepted()
    }

    void "Complete condition"(){
        expect:
        cond.complete() == 3000
    }
    void "Completed condition updates state"(){
        when:
        cond.complete()
        then:
        cond.isCompleted()
    }
}
