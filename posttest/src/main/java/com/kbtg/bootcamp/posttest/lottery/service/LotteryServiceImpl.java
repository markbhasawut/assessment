package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import com.kbtg.bootcamp.posttest.lottery.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryServiceImpl implements LotteryService {

    private LotteryRepository lotteryRepository;

    @Autowired
    public LotteryServiceImpl(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }


    @Override
    public List<Lottery> listAllLotteries() {
        return lotteryRepository.findAll();
    }

    @Override
    public Optional<Lottery> createLottery(int id) {

        return Optional.empty();
    }
}
