package com.murdock.books.spring.statemachine.guide.example.persist;

/**
 * <pre>
 * PLACED -- #PROCESS --> PROCESSING --#SEND--> DELIVERED --#DELIVER--> SENT
 *
 * </pre>
 *
 * @author weipeng2k 2018年10月03日 下午22:16:35
 */
public class Order {
    /**
     * pk
     */
    int id;
    /**
     * status
     */
    String state;

    public Order(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
