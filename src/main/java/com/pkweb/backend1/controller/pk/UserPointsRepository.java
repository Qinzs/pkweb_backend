package com.pkweb.backend1.controller.pk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {
    UserPoints findByUser(User user);
    List<UserPoints> findAllByOrderByPointsDesc();

    UserPoints findByUserId(Long userId);

    List<UserPoints> findTop15ByOrderByPointsDesc();
}
