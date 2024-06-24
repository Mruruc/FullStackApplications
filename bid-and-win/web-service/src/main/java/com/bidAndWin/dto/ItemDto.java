package com.bidAndWin.dto;

import com.bidAndWin.model.Item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemDto {
    private Long itemId;
    private String itemTitle;
    private String itemDescription;
    private Double startingPrice;
    private Double currentPrice;
    private Long auctionId;
    private Long clientId;
    private List<BidDto> bids;

    public ItemDto() {}

    public static ItemDto toItemDto(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemId(item.getItemId());
        itemDto.setItemTitle(item.getItemTitle());
        itemDto.setItemDescription(item.getItemDescription());
        itemDto.setStartingPrice(item.getStartingPrice());
        itemDto.setCurrentPrice(item.getCurrentPrice());
        itemDto.setBids(item.getBids().stream().map(BidDto::toBidDto).toList());
        itemDto.setClientId(item.getClient().getClientId());
        itemDto.setAuctionId(item.getAuction().getAuctionId());

        return itemDto;
    }


}
