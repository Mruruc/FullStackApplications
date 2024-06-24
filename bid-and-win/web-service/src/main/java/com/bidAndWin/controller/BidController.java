package com.bidAndWin.controller;

import com.bidAndWin.dto.BidDto;
import com.bidAndWin.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor

public class BidController {
    private final BidService bidService;

    @GetMapping(value = "/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BidDto> getBidsByClient(@PathVariable("clientId") Long clientId){
        return bidService.getBidsByClientId(clientId);
    }

    @GetMapping(value = "/item/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BidDto> getBidsByItem(@PathVariable("itemId") Long itemId){
        return bidService.getBidsByItemId(itemId);
    }


    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> saveBid(@PathVariable("id")Long Id, @RequestBody BidDto bidDto){
        return new ResponseEntity<>(URI.create("/api/auctions/" + bidService.saveBid(Id, bidDto)), HttpStatus.CREATED);
    }
}


