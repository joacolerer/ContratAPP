package contratapp


import java.time.LocalDate

class Contract {

    static constraints = {
    }
    public LocalDate creationDate
    public LocalDate finishDate
    public User promisor
    public User promisee
    public List<Condition> conditions

    Contract(User promisor, User promisee, LocalDate finishDate, List<Condition> conditions) {
        this.finishDate = finishDate
        this.promisor = promisor
        this.promisee = promisee
        this.conditions = conditions
        this.creationDate = LocalDate.now()
    }

    Boolean isAccepted(){
        for(Condition condition : conditions){
            if(!condition.isAccepted()){
                return false
            }
        }
        return true
    }

    Boolean isValid(){
        return this.isAccepted() & isBetweenValidDates()
    }
    private Boolean isBetweenValidDates() {
        LocalDate today = LocalDate.now()
        return (today == creationDate | today == finishDate) | (today.isAfter(creationDate) & today.isBefore(finishDate))
    }

    void complete(){
        if(this.isAccepted()){
            BigDecimal total = 0
            for(Condition condition : conditions){
                total += condition.complete()
            }
            promisee.addBalance(total)
        }  else { throw new Exception("Cant complete an invalid contract")}
    }
}
