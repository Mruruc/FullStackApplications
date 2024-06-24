package com.bidAndWin.repository;

import com.bidAndWin.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByClient_ClientId(Long clientId);

    Optional<User> findByUsername(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.failedLoginAttempts = ?2 WHERE u.userId = ?1")
    void updateFailedLoginAttemptsByUserId(Long userId,Long failedLoginAttempts);
}
