package com.kbtg.bootcamp.posttest.userticket.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.userticket.entity.UserTicket;
import com.kbtg.bootcamp.posttest.userticket.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.userticket.rest.dto.UserTicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTicketServiceImpl implements UserTicketService {

    private final UserTicketRepository userTicketRepository;

    private final LotteryRepository lotteryRepository;

    @Autowired
    public UserTicketServiceImpl(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    @Override
    public String buyLottery(String userId, String ticketId) {

        Optional<Lottery> lottery = lotteryRepository.findById(ticketId);
        UserTicket userTicket = new UserTicket();

        if (lottery.isEmpty()) {
            throw new RuntimeException("Lottery number not found.");
        }

        lottery.get().setTicket(ticketId);
        userTicket.setUserId(userId);
        userTicket.setLottery(lottery.get());

        return userTicketRepository.save(userTicket).getId().toString();
    }

    @Override
    public UserTicketResponseDto getLotteries(String userId) {

        //TODO implement getLotteries logic
        return null;
    }

    @Override
    public String sellLottery(String userId, String ticketId) {

        //TODO implement sellLottery logic
        return null;
    }
}
