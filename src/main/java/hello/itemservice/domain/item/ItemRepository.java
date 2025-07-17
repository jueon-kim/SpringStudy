package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 컴포넌트 스캔 대상
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // 실제로는 hashMap쓰면 안됨 동시에 접속 하기 때문에 안됨
    private static long sequence = 0L; // 이것도 안됨

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;

    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStrore() {
        store.clear();
    }
}
