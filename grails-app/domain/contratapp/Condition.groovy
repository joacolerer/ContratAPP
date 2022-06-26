package contratapp

class Condition {

    static constraints = {
    }

    Condition(Integer quantity, String task, BigDecimal price) {
        this.quantity = quantity
        this.price = price
        this.task = task
        this.state = State.PENDING
    }

    enum State{
        COMPLETED,
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Integer quantity
    public BigDecimal price
    public String task
    public State state

    void accept(){
        this.state = State.ACCEPTED
    }

    void reject(){
        this.state = State.REJECTED
    }
    Boolean isAccepted(){
        return this.state == State.ACCEPTED
    }

    BigDecimal complete(){
        this.state = State.COMPLETED
        return quantity * price
    }
    Boolean isCompleted(){
        return this.state == State.COMPLETED
    }
    Boolean isPending(){
        return this.state == State.PENDING
    }

}
