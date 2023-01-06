package com.example.ourwedding.REPOSITORY;

import com.example.ourwedding.DTO.InvitedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@Repository
@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<InvitedPerson, Integer>{

}
