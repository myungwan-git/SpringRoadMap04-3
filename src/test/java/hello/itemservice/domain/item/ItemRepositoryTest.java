package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

  ItemRepository itemRepository = new ItemRepository();

  @AfterEach
  void afterEach() {
    itemRepository.clearStore();
  }

  @Test
  void save() {
    Item item = new Item("itemA",10000,5);

    Item save = itemRepository.save(item);
    Item byId = itemRepository.findById(item.getId());
    assertThat(byId).isEqualTo(save);
  }

  @Test
  void findAll() {
    Item item1 = new Item("itemA",10000,5);
    Item item2 = new Item("itemB",20000,7);

    itemRepository.save(item1);
    itemRepository.save(item2);

    List<Item> result = itemRepository.findAll();

    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(item1,item2);
  }

  @Test
  void updateItem() {
    Item item = new Item("itemA",10000,5);
    Item save = itemRepository.save(item);
    Long id = save.getId();

    Item updateItem = new Item("itemAAA", 20000, 555);
    itemRepository.update(id, updateItem);

    Item findItem = itemRepository.findById(id);

    assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
  }

}