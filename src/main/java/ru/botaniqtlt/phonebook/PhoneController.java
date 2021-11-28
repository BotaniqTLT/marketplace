package ru.botaniqtlt.phonebook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;
import ru.botaniqtlt.phonebook.store.SelectQuery;

import java.util.List;

@Controller
public class PhoneController {

    private RecordStorage recordStorage;

    public PhoneController(RecordStorage recordStorage) {
        this.recordStorage = recordStorage;
    }

    @GetMapping("/")
    public String index(Model model,  SelectQuery select) {
        model.addAttribute("list",recordStorage.find(select));
        model.addAttribute("select",select);
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
