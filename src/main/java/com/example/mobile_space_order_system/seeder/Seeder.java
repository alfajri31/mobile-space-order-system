package com.example.mobile_space_order_system.seeder;

import com.example.mobile_space_order_system.entity.Item;
import com.example.mobile_space_order_system.entity.Type;
import com.example.mobile_space_order_system.repository.ItemRepository;
import com.example.mobile_space_order_system.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void run(String... args) throws Exception {
        Type type1 = typeRepository.findByTypeName("Laptop");
        if(type1==null) {
            type1 = Type.builder()
                   .typeName("Laptop")
                   .build();
            typeRepository.save(type1);
            Item item1 = Item.builder()
                    .typeId(type1.getId())
                    .itemName("Macbook Pro")
                    .itemPrice(25000000)
                    .build();
            itemRepository.saveAll(Arrays.asList(item1));
        }
        Type type2 = typeRepository.findByTypeName("Book");
        if(type2==null) {
            type2 = Type.builder()
                    .typeName("Book")
                    .build();
            typeRepository.save(type2);
            Item item2 = Item.builder()
                    .typeId(type2.getId())
                    .itemName("Head First Java")
                    .itemPrice(80000)
                    .build();
            itemRepository.saveAll(Arrays.asList(item2));
        }
    }
}