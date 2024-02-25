package com.kbtg.bootcamp.posttest.userticket.service;


import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.repo.LotteryRepo;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.userticket.entity.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.userticket.exception.InvalidUserTicketExceptionHandling;
import com.kbtg.bootcamp.posttest.userticket.exception.LotteryNotFoundException;
import com.kbtg.bootcamp.posttest.userticket.repo.UserTicketRepo;
import com.kbtg.bootcamp.posttest.userticket.rest.dto.UserTicketReqDto;
import com.kbtg.bootcamp.posttest.userticket.rest.dto.UserTicketResDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTicketServiceImp implements  UserTicketService {
    private final UserTicketRepo userTicketRepo;
    private final LotteryRepo lotteryRepo;

    @Autowired
    public UserTicketServiceImp(UserTicketRepo userTicketRepo, LotteryRepo lotteryRepo) {
        this.userTicketRepo = userTicketRepo;
        this.lotteryRepo = lotteryRepo;
    }

    @Override
    @Transactional
    public UserTicketReqDto buyLottery(String userId, String ticketId) throws BadRequestException {
        Lottery lottery = lotteryRepo.findById(ticketId)
                .orElseThrow(() -> new LotteryNotFoundException("Lottery not found"));

        if (lottery.getAmount() <= 0) {
            throw new BadRequestException("Lottery is sold out");
        }

        // Critical Section - Within the transaction
        lottery.setAmount(lottery.getAmount() - 1); // Decrement amount
        lotteryRepo.save(lottery); // Update immediately to the database

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(userId);
        userTicket.setLottery(lottery);

        return new UserTicketReqDto(userTicketRepo.save(userTicket).getId());
    }

    @Override
    public UserTicketResDto getLotteryByUserId(java.lang.String userId) {
        UserTicketResDto userTicketResDto = new UserTicketResDto();
        List<UserTicket> fromUser = userTicketRepo.findByUserId(userId);
        List<String> tickets = fromUser.stream().map(eachUserId -> eachUserId.getLottery().getTicket()).toList();

        userTicketResDto.setTickets(tickets);
        userTicketResDto.setCount(tickets.size());
        userTicketResDto.setTotalPrice(sumPrice(fromUser));

        return userTicketResDto;
    }


    @Override
    @Transactional
    public LotteryResponseDto sellLottery(String userId, String ticketId) {

        List<UserTicket> userTicket = userTicketRepo.findByUserIdAndTicketId(userId, ticketId);

        if (userTicket.isEmpty()) {
            throw new InvalidUserTicketExceptionHandling("Either userId or ticketId is invalid. Please check.");
        }

        // Delete UserTicket
        userTicketRepo.delete(userTicket.get(0));

        // Update and return Lottery
        Lottery lottery = lotteryRepo.findById(ticketId)
                .orElseThrow(() -> new InvalidUserTicketExceptionHandling("Either userId or ticketId is invalid. Please check.")); // Reuse exception

        lottery.setAmount(lottery.getAmount() + 1);
        lotteryRepo.save(lottery);
        return new LotteryResponseDto(ticketId);
    }


    private Integer sumPrice(List<UserTicket> fromUser) {
        Integer sum = 0;
        for (UserTicket ticket : fromUser) {
            sum += ticket.getLottery().getPrice();
        }
        return sum;
    }
}




