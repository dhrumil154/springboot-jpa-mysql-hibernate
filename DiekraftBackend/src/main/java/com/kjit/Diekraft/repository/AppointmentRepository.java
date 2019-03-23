package com.kjit.Diekraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kjit.Diekraft.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String>  {

}
