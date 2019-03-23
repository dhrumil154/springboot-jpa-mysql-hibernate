package com.kjit.Diekraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kjit.Diekraft.entity.Labour;
import org.springframework.stereotype.Repository;

@Repository
public interface LabourRepository extends JpaRepository<Labour, Long>{

}
