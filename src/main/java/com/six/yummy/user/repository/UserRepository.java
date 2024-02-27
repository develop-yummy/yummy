package com.six.yummy.user.repository;

import com.six.yummy.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

//    @Query("select u from User u where u.email = ?1") //jpql 만들어줘
//    Optional<User> findByEmailAndPhoneNumber(String email, String phoneNumber);

}
