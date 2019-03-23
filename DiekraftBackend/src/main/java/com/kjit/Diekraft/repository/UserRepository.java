package com.kjit.Diekraft.repository;

import com.kjit.Diekraft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByEmail(String email);

	Optional<User> findOneWithAuthoritiesByEmail(String login);

}
