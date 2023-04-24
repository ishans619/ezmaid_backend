package com.ezmaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmaid.entity.Maid;

@Repository
public interface MaidDao extends JpaRepository<Maid, String> {

}
