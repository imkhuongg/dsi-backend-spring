package com.DSI_V1.dsi.repository;

import com.DSI_V1.dsi.models.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany,Integer> {

}
