package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;

import java.util.List;
import java.util.Optional;

public interface LotteryService {

    List<Lottery> listAllLotteries();

    Optional<Lottery> createLottery(int id);
}
