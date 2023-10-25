package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Entity.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {
    UserPoints findByUser(User user);
    List<UserPoints> findAllByOrderByPointsDesc();

    UserPoints findByUser_UserId(Long userId);


    List<UserPoints> findTop15ByOrderByPointsDesc();

}