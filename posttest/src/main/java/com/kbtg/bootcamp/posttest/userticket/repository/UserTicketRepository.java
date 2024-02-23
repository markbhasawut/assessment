package com.kbtg.bootcamp.posttest.userticket.repository;

import com.kbtg.bootcamp.posttest.userticket.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserIdAndTicketId(String userId, String ticketId);
}
