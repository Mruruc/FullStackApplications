package com.bidAndWin.controller;

import com.bidAndWin.dto.ItemDto;
import com.bidAndWin.model.Item.Item;
import com.bidAndWin.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController{
    private final ItemService itemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto getItem(@PathVariable Long itemId) {
        return itemService.getItemDtoById(itemId);
    }


    @GetMapping(value = "/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItems(@PathVariable Long clientId) {
        return itemService.getItemsByClientId(clientId);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> createItem(@RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(URI.create("/api/items/"+itemService.saveItem(itemDto)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> updateItem(@PathVariable("itemId") Long itemId, @RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(URI.create("/api/items/"+itemService.updateItem(itemId, itemDto)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping(value = "/info/{itemId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadItem(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);

        byte[] data = itemService.convertItemToJsonBytes(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("item-" + itemId + ".json")
                .build());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
