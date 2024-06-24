package com.bidAndWin.repository;

import com.bidAndWin.model.bid.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

    List<Bid> findByClient_ClientId(Long clientId);

    List<Bid> findByItem_ItemId(Long itemId);
}
