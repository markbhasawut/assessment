package com.kbtg.bootcamp.posttest.userticket.rest.dto;

import java.util.List;

public class UserTicketResponseDto {

    private List<String> tickets;
    private Integer count;
    private Integer totalPrice;

    public UserTicketResponseDto() {

    }

    public UserTicketResponseDto(List<String> tickets, Integer count, Integer totalPrice) {
        this.tickets = tickets;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
