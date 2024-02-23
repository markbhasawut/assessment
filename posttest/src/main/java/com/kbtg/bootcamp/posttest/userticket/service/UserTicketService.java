package com.kbtg.bootcamp.posttest.userticket.service;

import com.kbtg.bootcamp.posttest.userticket.rest.dto.UserTicketResponseDto;

public interface UserTicketService {

    String buyLottery(String userId, String ticketId);

    UserTicketResponseDto getLotteries(String userId);

    String sellLottery(String userId, String ticketId);
}
