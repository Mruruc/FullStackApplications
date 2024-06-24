package com.bidAndWin.model.bid;

import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.model.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bidId_sequence")
    @SequenceGenerator(name = "bidId_sequence",allocationSize = 1)
    private Long bidId;
    @Column(nullable = false,precision = 2)
    private Double bidAmount;
    @Column(nullable = false)
    private LocalDateTime bidTime;
    /**
     * many bids can be made for one item
     */
    @JsonBackReference("item-bids")
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "item_id",nullable = false)
    private Item item;
    /**
     * many bids can be made for one auction
     */
    @JsonBackReference("auction-bids")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id",nullable = false)
    private Auction auction;
    /**
     * many bids can be made by one client
     */
    @JsonBackReference("client-bids")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    public Bid(){}
}
