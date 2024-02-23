package com.kbtg.bootcamp.posttest.lottery.repo;

import com.kbtg.bootcamp.posttest.lottery.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryRepo extends JpaRepository<Lottery, String> {


}
