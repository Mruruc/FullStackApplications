package com.bidAndWin.model.auction;

import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.bid.Bid;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "auctionId_sequence")
    @SequenceGenerator(name = "auctionId_sequence",allocationSize = 1)
    private Long auctionId;
    @Column(nullable = false)
    private LocalDateTime auctionStartTime;
    @Column(nullable = false)
    private LocalDateTime auctionEndTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuctionStatus auctionStatus;
    /**
     * one auction can have many items
     */
    @JsonManagedReference("auction-items")
    @OneToMany(mappedBy = "auction",cascade ={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    private List<Item> items;
    /**
     * one auction can have many bids
     */
    @JsonManagedReference("auction-bids")
    @OneToMany(mappedBy = "auction",fetch = FetchType.LAZY)
    private List<Bid> bids;


    public Auction(){}
}
