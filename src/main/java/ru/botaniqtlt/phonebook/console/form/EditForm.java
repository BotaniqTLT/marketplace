package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;

import java.util.List;

@Component
public class EditForm extends AbstractSearchForm {
    public EditForm(ConsoleHelper helper, RecordStorage storage, SingleChoiceMenu choiceMenu) {
        super(helper, storage, choiceMenu);
    }

    @Override
    FormResponse action(List<PhoneRecord> records) {
        return edit(records.get(0));

    }

    private FormResponse edit(PhoneRecord record) {
        record.setFirstName(helper.readLine("Имя?", record.getFirstName()));
        record.setLastName(helper.readLine("Фамилия?", record.getLastName()));
        record.setPhone(helper.readLine("Телефон", record.getPhone()));
        if (storage.save(record) == -1) {
            helper.prompt("Ошибка записи");
        }
        return BACK_RESPONSE;
    }

    @Override
    boolean isMultiply() {
        return false;
    }

    @Override
    String getQuestion() {
        return "Какую запись редактировать?";
    }

}
