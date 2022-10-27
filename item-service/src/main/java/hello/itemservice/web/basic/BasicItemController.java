package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired        /* lombok 이 자동 생성해줌 */
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName
                    ,@RequestParam int price
                    ,@RequestParam Integer quantity
                    , Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
//        model.addAttribute("item", item); // ModelAttribute 로 자동 추가

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        // ModelAttribute 이후 이름 생략시 클래스의 첫글자만 소문자로 저장

        itemRepository.save(item);
//        model.addAttribute("item", item); // ModelAttribute 로 자동 추가
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {
        // ModelAttribute 이후 이름 생략시 클래스의 첫글자만 소문자로 저장

        itemRepository.save(item);
//        model.addAttribute("item", item); // ModelAttribute 로 자동 추가
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        // ModelAttribute 이후 이름 생략시 클래스의 첫글자만 소문자로 저장

        itemRepository.save(item);

        Long itemId = item.getId();
//        model.addAttribute("item", item); // ModelAttribute 로 자동 추가
        return "redirect:/basic/items/"+itemId;
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        // ModelAttribute 이후 이름 생략시 클래스의 첫글자만 소문자로 저장

        Item saveItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId",saveItem.getId());
        // 하기에 변수로 안들어간 것은 쿼리 파라미터 형태로 넘어간다
        redirectAttributes.addAttribute("status", true);
//        model.addAttribute("item", item); // ModelAttribute 로 자동 추가
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 30000, 20));
    }
}
