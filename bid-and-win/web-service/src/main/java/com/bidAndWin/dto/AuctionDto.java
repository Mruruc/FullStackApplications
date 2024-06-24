package com.bidAndWin.dto;

import com.bidAndWin.model.auction.AuctionStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class AuctionDto {
    private Long auctionId;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auctionStartTime;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auctionEndTime;
    private AuctionStatus auctionStatus;

    private List<ItemDto> itemDtos;
    //private List<Bid>bids;

    public AuctionDto(){}

}
