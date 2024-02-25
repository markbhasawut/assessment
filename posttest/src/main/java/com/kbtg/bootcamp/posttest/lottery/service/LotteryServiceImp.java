package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.exception.DubLotteryExceptionHandling;
import com.kbtg.bootcamp.posttest.lottery.repo.LotteryRepo;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryListResDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotteryServiceImp implements LotteryService {

    private final LotteryRepo lotteryRepo;

    @Autowired
    public LotteryServiceImp(LotteryRepo lotteryRepo) {
        this.lotteryRepo = lotteryRepo;
    }

    @Override
    public LotteryListResDto listAllLotteries() {
        List<String> tickets = lotteryRepo.findAll()
                .stream()
                .map(Lottery::getTicket)
                .collect(Collectors.toList());

        return new LotteryListResDto(tickets); // Construct the response directly
    }

    @Override
    public LotteryResponseDto createLottery(LotteryRequestDto lotteryRequestDto) {
        Optional<Lottery> ticket = lotteryRepo.findById(lotteryRequestDto.getTicket());

        if (ticket.isPresent()) {
            throw new DubLotteryExceptionHandling("This lottery number has already existed in database.");
            //throw new RuntimeException("Lottery exists in the database.");
            //for Simple
        }

        return new LotteryResponseDto(lotteryRepo.save(new Lottery(lotteryRequestDto.getTicket(),
                        lotteryRequestDto.getAmount(),
                        lotteryRequestDto.getPrice()))
                .getTicket()
        );
    }
}
