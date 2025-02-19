package com.kbtg.bootcamp.posttest.lottery.service;


import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryListResDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;

public interface LotteryService {
    LotteryListResDto listAllLotteries();

    LotteryResponseDto createLottery(LotteryRequestDto lotteryRequestDto);
}
