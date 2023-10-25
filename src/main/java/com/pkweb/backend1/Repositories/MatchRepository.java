package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser1Id(Long user1Id);
    List<Match> findByUser2Id(Long user2Id);

}
