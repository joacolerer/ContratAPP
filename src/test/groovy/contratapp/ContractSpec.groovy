package contratapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDate

class ContractSpec extends Specification implements DomainUnitTest<Contract> {

    User joaco
    User nike

    def setup() {
        joaco = new User("joaco", "CABA", User.SocialNetwork.INSTAGRAM)
        nike = new User("nike", "CABA", User.SocialNetwork.INSTAGRAM)
    }

    def cleanup() {
    }


    void "un contrato recien creado sin condiciones aceptadas no es valido"() {
        given:
        List<Condition> conditions = Arrays.asList(new Condition(3, "IG Stories", 1000 as BigDecimal))
        when:
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        then:
        !contract.isValid()
    }

    void "un contrato creado el mismo dia es valido el mismo dia"() {
        given:
        Condition cond = new Condition(3, "IG Stories", 1000 as BigDecimal)
        List<Condition> conditions = Arrays.asList(cond)
        when:
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        cond.accept()
        then:
        contract.isValid()
    }

    void "un contrato creado pero con una condicion no aceptada no es valido"() {
        given:
        Condition cond = new Condition(3, "IG Stories", 1000 as BigDecimal)
        List<Condition> conditions = Arrays.asList(cond)
        when:
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        cond.reject()
        then:
        !contract.isValid()
    }


    void "Completar contrato valido con una condicion deposita cantidad valida en usuario"() {
        given:
        Condition firsCond = new Condition(3, "IG Stories", 1000 as BigDecimal)
        Condition secondCond = new Condition(1, "IG Stories", 7000 as BigDecimal)
        List<Condition> conditions = Arrays.asList(firsCond, secondCond)
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        firsCond.accept()
        secondCond.accept()
        when:
        contract.complete()
        then:
        joaco.balance == 10000
    }

    void "No se puede completar un contrato invalido"() {
        given:
        Condition firsCond = new Condition(3, "IG Stories", 1000 as BigDecimal)
        List<Condition> conditions = Arrays.asList(firsCond)
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        when:
        contract.complete()
        then:
        thrown Exception
    }

    void "Contrato cno condicion extra no aceptada igualmente es valido"() {
        given:
        Condition firstCond = new Condition(3, "IG Stories", 1000 as BigDecimal)
        Condition secondCond = new ExtraCondition(3, "IG Stories", 1000 as BigDecimal)
        List<Condition> conditions = Arrays.asList(firstCond, secondCond)
        firstCond.accept()
        Contract contract = new Contract(nike, joaco, LocalDate.of(2022, 10, 28), conditions)
        expect:
        contract.isValid()
    }
}
