package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRespository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRespository.clearStrore();
    }

    @Test
    void save() {

        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRespository.save(item);
        //then
        Item findItem = itemRespository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {

        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 10);

        itemRespository.save(item1);
        itemRespository.save(item2);

        //when
        List<Item> result = itemRespository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }


    @Test
    void updateItem() {

        //given
        Item item = new Item("item1", 20000, 10);

        Item saveItem = itemRespository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRespository.update(itemId, updateParam);

        //then
        Item findItem = itemRespository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
