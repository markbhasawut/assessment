package com.kbtg.bootcamp.posttest.lottery.rest.controller;

import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryListResponseDto;
import com.kbtg.bootcamp.posttest.lottery.rest.dto.LotteryResponseDto;
import com.kbtg.bootcamp.posttest.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotteryController {

    private final LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/lotteries")
    public ResponseEntity<LotteryResponseDto> createLottery(@RequestBody LotteryRequestDto lotteryRequestDto) {

        return new ResponseEntity<>(new LotteryResponseDto(lotteryService.createLottery(lotteryRequestDto).getTicket()), HttpStatus.OK);
    }

    @GetMapping("/lotteries")
    public ResponseEntity<LotteryListResponseDto> listAllLotteries() {
        return new ResponseEntity<>(lotteryService.listAllLotteries(),HttpStatus.OK);
    }
}
