package com.bidAndWin.service;

import com.bidAndWin.dto.AuctionDto;
import com.bidAndWin.dto.ItemDto;
import com.bidAndWin.exceptions.EntityNotFoundException;
import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.model.auction.AuctionStatus;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository auctionRepository;


    public AuctionDto saveAuction(AuctionDto auctionDto) {
        Auction auction = new Auction();
        auction.setAuctionStartTime(auctionDto.getAuctionStartTime());
        auction.setAuctionEndTime(auctionDto.getAuctionEndTime());
        auction.setAuctionStatus(
                auction.getAuctionStartTime().isBefore(LocalDateTime.now()) ?
                        AuctionStatus.ACTIVE : AuctionStatus.PENDING
        );
        // construct item
        Item item = new Item();
        ItemDto itemDto = auctionDto.getItemDtos().getFirst();
        item.setItemTitle(itemDto.getItemTitle());
        item.setItemDescription(itemDto.getItemDescription());
        item.setStartingPrice(itemDto.getStartingPrice());
        item.setCurrentPrice(itemDto.getCurrentPrice());
        item.setAuction(auction);
        Client client = new Client();
        client.setClientId(itemDto.getClientId());
        item.setClient(client);
        // set item to auction
        auction.setItems(List.of(item));
        // save auction
        Auction savedAuction = auctionRepository.save(auction);
        // set auction id
        auctionDto.setAuctionId(savedAuction.getAuctionId());
        return auctionDto;
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction with id: " + id + " does not exist"));
    }
}
