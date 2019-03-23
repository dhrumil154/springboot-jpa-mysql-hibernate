package com.kjit.Diekraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjit.Diekraft.entity.Authorities;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, String> {

}
