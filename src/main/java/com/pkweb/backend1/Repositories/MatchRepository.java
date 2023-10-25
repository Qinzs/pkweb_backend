package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser1Id(Long user1Id);
    List<Match> findByUser2Id(Long user2Id);
    //根据user_ID查找,User1ID和User2ID都可以
    List<Match> findByUser1IdOrUser2Id(Long user1Id,Long user2Id);

}
