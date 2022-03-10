package com.example.springbootstarterthymeleaf.database;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    Item findItemByItemId(Integer itemId);
}
