package com.bidAndWin.repository;

import com.bidAndWin.model.client.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByClient_ClientId(Long clientId);

    void deleteByClient_ClientId(Long clientId);
}
