package com.filippau.tc.repository;

import com.filippau.tc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
    User findByActivationCode(String code);
    User findByEmail(String email);
    User findById (long id);

}
