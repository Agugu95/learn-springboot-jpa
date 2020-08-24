package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService { // 상품 서비스 클래스는 상품 레포에 위임만 하는 클래스임
    private final ItemRepository itemRepository;

    @Transactional // 트랜잭셔널 어노테이션은 가까운게 우선권을 가
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // 메소드 종료 시 트랜잭셔널 동작, 커밋
    public void updateItem(Long itemId, String name, int price , int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // 영속성 엔티티로 찾아온 상태
        findItem.change(name, price, stockQuantity); // setter가 아닌 변경 메소드로 바꾼 상태
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
