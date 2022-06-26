package contratapp

class ExtraCondition extends Condition {

    static constraints = {
    }

    ExtraCondition(Integer quanitity, String task, BigDecimal price) {
        super(quanitity, task, price)
    }

    Boolean isAccepted(){
        return true
    }

    BigDecimal complete(){
        if(this.state == State.ACCEPTED) return quantity * price
        this.state = State.REJECTED
        return 0
    }
}
