package com.bidAndWin.repository;

import com.bidAndWin.model.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item>  findAllByClient_ClientId(Long clientId);

}
