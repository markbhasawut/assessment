package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotteryServiceImpl implements LotteryService {

    private final LotteryRepository lotteryRepository;

    @Autowired
    public LotteryServiceImpl(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }


    @Override
    public LotteryListResponseDto listAllLotteries() {

        return new LotteryListResponseDto(lotteryRepository.findAll().stream().map(Lottery::getTicket).collect(Collectors.toList()));
    }

    @Override
    public LotteryResponseDto createLottery(LotteryRequestDto lotteryRequestDto) {
        Optional<Lottery> ticket = lotteryRepository.findById(lotteryRequestDto.getTicket());
        if (ticket.isPresent()) {
            throw new RuntimeException("Lottery exists in the database.");
        }

        return new LotteryResponseDto(lotteryRepository.save(new Lottery(
                lotteryRequestDto.getTicket(),
                lotteryRequestDto.getPrice(),
                lotteryRequestDto.getAmount()))
                .getTicket());
    }
}
