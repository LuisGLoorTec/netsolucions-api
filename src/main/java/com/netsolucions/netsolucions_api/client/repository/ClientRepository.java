package com.netsolucions.netsolucions_api.client.repository;

import com.netsolucions.netsolucions_api.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}