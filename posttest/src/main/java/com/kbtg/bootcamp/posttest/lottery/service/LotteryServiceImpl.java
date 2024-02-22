package com.kbtg.bootcamp.posttest.lottery.service;

public class LotteryService {

    private final LotteryRepository lotteryRepository;

        public LotteryService(LotteryRepository lotteryRepository) {
            this.lotteryRepository = lotteryRepository;
        }


        //Create
        public LotteryResponseDto createLottery(LotteryRequestDto requestDto) {
            Optional<Lottery> optionalTicket = lotteryRepository.findByTicket(requestDto.getTicket());
            if (optionalTicket.isPresent()) {
                throw new LotteryExistException("Exist lottery In Database");
            } else {
                return new LotteryResponseDto(lotteryRepository.save(Lottery.builder()
                        .amount(requestDto.getAmount())
                        .price(requestDto.getPrice())
                        .ticket(requestDto.getTicket())
                        .build()
                ).getTicket());
            }
        }


        //ListAllLotteries
        public LotteryListResponseDto listAllLotteries() {
            return new LotteryListResponseDto(lotteryRepository.findAll()
                    .stream()
                    .map(Lottery::getTicket).
                    collect(Collectors.toList()));
        }

}
