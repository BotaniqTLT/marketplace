package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;

@Component
public class AddForm implements Form {

    private ConsoleHelper helper;

    private RecordStorage storage;

    public AddForm(ConsoleHelper helper, RecordStorage storage) {
        this.helper = helper;
        this.storage = storage;
    }

    @Override
    public FormResponse<? extends Form> run() {
        while (true){
            fillRecord();
            helper.prompt("Хотите добавить ещё 1 запись?\n");
            helper.prompt("\t1 -ДА\n");
            helper.prompt("\t2 -НЕТ\n");
            if(helper.readInt() != 1){
                return Form.BACK_RESPONSE;
            }
        }
    }

    private void fillRecord() {
        PhoneRecord record = new PhoneRecord();
        helper.prompt("Имя: ");
        record.setFirstName(readString());
        helper.prompt("Фамилия: ");
        record.setLastName(readString());
        helper.prompt("Телефон: ");
        record.setPhone(readString());
        if (storage.save(record) == -1){
            helper.prompt("Ошибка добавления\n");
        }
    }

    private String readString() {

        return helper.readLine();
    }
}
