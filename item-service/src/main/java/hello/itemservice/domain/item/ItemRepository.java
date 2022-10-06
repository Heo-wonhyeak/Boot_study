package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    // 여러 쓰레드가 동시에 접속할때는 HashMap 이 아닌 ConcurrentHashMap 사용!
    private static final Map<Long, Item> store = new HashMap<>();   //static
    //여러 쓰레드가 동시에 접속할때는 long 이 아닌 AtomicLong 사용!
    private static long sequence = 0L;  //static

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

    public void clearStore() {
        store.clear();
    }
}
