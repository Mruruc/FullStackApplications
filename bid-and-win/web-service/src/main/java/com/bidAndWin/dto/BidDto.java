package com.bidAndWin.dto;

import com.bidAndWin.model.bid.Bid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BidDto {
    private Long bidId;
    private Double bidAmount;
    private LocalDateTime bidTime;
    private Long itemId;
    private Long auctionId;
    private Long clientId;
    private String paymentId;
    private String payerId;

    public BidDto(){}

    public Bid toBid(){
        Bid bid = new Bid();
        bid.setBidAmount(this.bidAmount);
        bid.setBidTime(this.bidTime);
        return bid;
    }

    public static BidDto toBidDto(Bid bid){
        BidDto bidDto = new BidDto();
        bidDto.setBidId(bid.getBidId());
        bidDto.setBidAmount(bid.getBidAmount());
        bidDto.setBidTime(bid.getBidTime());
        bidDto.setItemId(bid.getItem().getItemId());
        bidDto.setAuctionId(bid.getAuction().getAuctionId());
        bidDto.setClientId(bid.getClient().getClientId());
        return bidDto;
    }
}
