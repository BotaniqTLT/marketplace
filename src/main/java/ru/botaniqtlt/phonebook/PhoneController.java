package ru.botaniqtlt.phonebook;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStoragePageable;
import ru.botaniqtlt.phonebook.store.SelectQuery;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PhoneController {

    private RecordStoragePageable recordStorage;

    public PhoneController(RecordStoragePageable recordStorage) {
        this.recordStorage = recordStorage;
    }

    @GetMapping("/")
    public String index(Model model,  SelectQuery select) {
        Page<PhoneRecord> page = recordStorage.findPage(select);
        model.addAttribute("list", page);
        model.addAttribute("select",select);
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }

    @GetMapping("/add")
    public  String addForm(){
        return "add";
    }

    @PostMapping("/add")
    public String addProcessing( @ModelAttribute PhoneRecord  record){
        recordStorage.save(record);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public  String editForm(@PathVariable("id") Long id, Model model){
        List<PhoneRecord> phoneRecords = recordStorage.find(new SelectQuery(id));
        if (phoneRecords.isEmpty()){
            return "redirect:/?error=not_Found";
        }
        model.addAttribute("item",phoneRecords.get(0));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcessing( @ModelAttribute PhoneRecord  record, @PathVariable("id") Long id){
        record.setId(id);
        recordStorage.save(record);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id){
        recordStorage.remove(id);
        return "redirect:/";
    }
}
