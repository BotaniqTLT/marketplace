package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;

import java.util.List;

@Component
public class DeleteForm extends AbstractSearchForm {
    public DeleteForm(ConsoleHelper helper, RecordStorage storage, SingleChoiceMenu choiceMenu) {
        super(helper, storage, choiceMenu);
    }

    @Override
    FormResponse action(List<PhoneRecord> records) {
        for (PhoneRecord record : records) {
            storage.remove(record.getId());
        }
        helper.prompt("Удалено записией: "+records.size()+"\n");
        return BACK_RESPONSE;

    }



    @Override
    boolean isMultiply() {
        return true;
    }

    @Override
    String getQuestion() {
        return "Какую запись удалить?";
    }

}
