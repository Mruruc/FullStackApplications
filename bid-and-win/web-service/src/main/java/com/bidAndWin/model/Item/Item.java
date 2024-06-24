package com.bidAndWin.model.Item;


import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.model.bid.Bid;
import com.bidAndWin.model.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString

@Entity
@Table(name = "items")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "itemId_sequence")
    @SequenceGenerator(name = "itemId_sequence",allocationSize = 1)
    private Long itemId;
    @Column(nullable = false)
    private String itemTitle;
    @Column(length = 500,nullable = false)
    private String itemDescription;
    @Column(nullable = false,precision = 2)
    private Double startingPrice;
    @Column(nullable = false,precision = 2)
    private Double currentPrice;
    /**
     * Many items can be belonged to one auction
     */
    @JsonBackReference("auction-items")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id",nullable = false)
    private Auction auction;

    /**
     * One item can be belonged to one client
     */
    @JsonBackReference("client-items")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
    /**
     * One item can have many bids
     */
    @JsonManagedReference("item-bids")
    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
    private List<Bid> bids;


    public Item(){}
}
