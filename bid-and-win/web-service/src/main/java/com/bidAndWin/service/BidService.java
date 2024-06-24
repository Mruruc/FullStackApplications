package com.bidAndWin.service;

import com.bidAndWin.dto.BidDto;
import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.model.bid.Bid;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BidService {
    private final BidRepository bidRepository;
    private final AuctionService auctionService;
    private final ItemService itemService;

    public Long saveBid(Long auctionId, BidDto bidDto) {
        Auction auction =auctionService.getAuctionById(auctionId);

        // construct bid
        Bid bid = bidDto.toBid();
        // set bid to auction
        bid.setAuction(auction);
        // set client to bid
        Client client = new Client();
        client.setClientId(bidDto.getClientId());
        bid.setClient(client);
        // set item to bid
        Item item = itemService.getItemById(bidDto.getItemId());
        item.setCurrentPrice(bidDto.getBidAmount());
        bid.setItem(item);

       return bidRepository.save(bid).getAuction().getAuctionId();
    }

    public List<BidDto> getBidsByClientId(Long clientId) {
        return bidRepository.findByClient_ClientId(clientId)
                .stream()
                .map(BidDto::toBidDto)
                .toList();
    }

    public List<BidDto> getBidsByItemId(Long itemId) {
        return bidRepository.findByItem_ItemId(itemId)
                .stream()
                .map(BidDto::toBidDto)
                .toList();
    }
}