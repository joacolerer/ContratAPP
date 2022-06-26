package contratapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }
    void "usuario recien creado tiene balance 0"() {
        given:
        User joaco = new User("joaco", "CABA", User.SocialNetwork.INSTAGRAM)
        expect:
        joaco.balance == 0
    }

    void "usuario con preferencia contacta exitosamente"() {
        given:
        User joaco = new User("joaco", "CABA", User.SocialNetwork.INSTAGRAM)
        User CocaCola = new User("CocaCola", "CABA", User.SocialNetwork.INSTAGRAM)
        when:
        joaco.addPreference(new Preference("location", "CABA"))
        then:
        joaco.contact(CocaCola)
    }
    void "usuario con preferencias dsitintas no da contacto exitoso"() {
        given:
        User joaco = new User("joaco", "CABA", User.SocialNetwork.INSTAGRAM)
        User CocaCola = new User("CocaCola", "CABA", User.SocialNetwork.INSTAGRAM)
        when:
        joaco.addPreference(new Preference("location", "Capital Federal"))
        then:
        !joaco.contact(CocaCola)
    }
}
