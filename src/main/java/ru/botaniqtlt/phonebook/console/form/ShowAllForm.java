package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;
import ru.botaniqtlt.phonebook.store.SelectQuery;

import java.util.List;

@Component
public class ShowAllForm implements  Form{

    private ConsoleHelper helper;

    private RecordStorage storage;

    public ShowAllForm(ConsoleHelper helper, RecordStorage storage) {
        this.helper = helper;
        this.storage = storage;
    }

    @Override
    public FormResponse<? extends Form> run() {
        List<PhoneRecord> records = storage.find(new SelectQuery(null, null, null));
        if(records.isEmpty()){
            helper.prompt("Записей нет\n");
            return Form.BACK_RESPONSE;
        }
        helper.prompt("Имя\tФамилия\tТелефон\n");
        for (PhoneRecord record : records) {
            helper.prompt(String.format("%s\t%s\t%s\n",record.getFirstName(),record.getLastName(),record.getPhone()));

        }
        return Form.BACK_RESPONSE;
    }
}
