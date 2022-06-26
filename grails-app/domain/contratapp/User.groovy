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
    public BigDecimal balance
    private List<Preference> preferences
    private Boolean blocked

    User(String name, String location, SocialNetwork socialNetwork) {
        this.name = name
        this.location = location
        this.socialNetwork = socialNetwork
        this.balance = 0
        this.preferences = new ArrayList<Preference>()
        this.blocked = false
    }

    void block() {
        this.blocked = true
    }
    void unblock() {
        this.blocked = false
    }
    Boolean isBlocked() {
        return this.blocked
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
