package com.bidAndWin.service;

import com.bidAndWin.dto.ItemDto;
import com.bidAndWin.exceptions.EntityNotFoundException;
import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.auction.Auction;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ObjectMapper objectMapper;
    private final ItemRepository itemRepository;
    private ClientService clientService;
    private AuctionService auctionService;


    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(ItemDto::toItemDto).toList();
    }

    public ItemDto getItemDtoById(Long id) {
        return itemRepository.findById(id)
                .stream()
                .map(ItemDto::toItemDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
    }
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
    }

    public List<ItemDto> getItemsByClientId(Long clientId) {
        return itemRepository.findAllByClient_ClientId(clientId).stream().map(ItemDto::toItemDto).toList();
    }


    public Long saveItem(ItemDto itemDto) {
        Item item = this.toItem(itemDto);
        // save the item
        return itemRepository.save(item).getItemId();
    }

    public Long updateItem(Long itemId,ItemDto itemDto){
        Item itemById = this.getItemById(itemId);
        itemById.setItemTitle(itemDto.getItemTitle());
        itemById.setItemDescription(itemDto.getItemDescription());
        itemById.setCurrentPrice(itemDto.getCurrentPrice());
        itemById.setStartingPrice(itemDto.getStartingPrice());
        return itemRepository.save(itemById).getItemId();
    }

    public void deleteItem(Long itemId){
        itemRepository.deleteById(itemId);
    }

    private Item toItem(ItemDto itemDto){
        Item item = new Item();
        item.setItemTitle(itemDto.getItemTitle());
        item.setItemDescription(itemDto.getItemDescription());
        item.setStartingPrice(itemDto.getStartingPrice());
        item.setCurrentPrice(itemDto.getCurrentPrice());

        // set the client
        Client client = clientService.findClientById(itemDto.getClientId());
        item.setClient(client);
        // set the auction
        Auction auctionById = auctionService.getAuctionById(itemDto.getAuctionId());
        item.setAuction(auctionById);
        return item;
    }

    public byte[] convertItemToJsonBytes(Item item) {
        try {
            String jsonString = objectMapper.writeValueAsString(item);
            return jsonString.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error processing item to JSON", e);
        }
    }
}
