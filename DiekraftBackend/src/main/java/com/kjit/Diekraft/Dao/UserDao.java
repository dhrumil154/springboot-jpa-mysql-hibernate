package com.kjit.Diekraft.Dao;


import com.kjit.Diekraft.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}