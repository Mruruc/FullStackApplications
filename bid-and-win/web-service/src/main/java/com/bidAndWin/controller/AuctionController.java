package com.bidAndWin.controller;

import com.bidAndWin.dto.AuctionDto;
import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        return new ResponseEntity<>(auctionService.getAuctionById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuctionDto> createAuction(@RequestBody AuctionDto auctionDto) {
        return new ResponseEntity<>(auctionService.saveAuction(auctionDto), HttpStatus.CREATED);
    }
}
