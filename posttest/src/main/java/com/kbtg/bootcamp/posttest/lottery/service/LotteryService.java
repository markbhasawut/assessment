package com.kbtg.bootcamp.posttest.lottery.service;

import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;

public interface LotteryService {

    LotteryListResponseDto listAllLotteries();

    LotteryResponseDto createLottery(LotteryRequestDto lotteryRequestDto);
}
