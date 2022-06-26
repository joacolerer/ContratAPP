package contratapp

class Preference {

    static constraints = {
    }

    public String selector
    public String description

    Preference(String selector, String description) {
        this.selector = selector
        this.description = description
    }


     Boolean appliesTo(User user) {
         user.(this.selector) == description
     }
}
