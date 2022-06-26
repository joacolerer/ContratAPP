package contratapp

class User {

    static constraints = {
    }

    enum SocialNetwork {
        INSTAGRAM,
        TIKTOK,
        FACEBOOK,
        TWITTER
    }

    public String name
    public String location
    public SocialNetwork socialNetwork
    public String category
    private BigDecimal balance
    private List<Preference> preferences

    User(String name, String location, SocialNetwork socialNetwork, String category) {
        this.name = name
        this.location = location
        this.socialNetwork = socialNetwork
        this.balance = 0
        this.preferences = new ArrayList<Preference>()
        this.category = category
    }

    void addBalance(BigDecimal amount){
        this.balance += amount
    }

    BigDecimal getBalance() {
        return balance
    }

    void addPreference(Preference preference) {
        this.preferences.add(preference)
    }

    Boolean contact(User user) {
        for(Preference pref : preferences){
            if(!pref.appliesTo(user)) return false
        }
        return true
    }

}
