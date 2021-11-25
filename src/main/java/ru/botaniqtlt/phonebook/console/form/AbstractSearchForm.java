package ru.botaniqtlt.phonebook.console.form;

import ru.botaniqtlt.phonebook.console.ConsoleHelper;
import ru.botaniqtlt.phonebook.store.PhoneRecord;
import ru.botaniqtlt.phonebook.store.RecordStorage;
import ru.botaniqtlt.phonebook.store.SelectQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.botaniqtlt.phonebook.console.form.MenuItem.menuItem;


public abstract class AbstractSearchForm implements  Form{

    protected ConsoleHelper helper;
    protected RecordStorage storage;
    private SingleChoiceMenu choiceMenu;

    public AbstractSearchForm(ConsoleHelper helper, RecordStorage storage, SingleChoiceMenu choiceMenu) {
        this.helper = helper;
        this.storage = storage;
        this.choiceMenu = choiceMenu;
    }

    @Override
    public FormResponse<? extends Form> run() {
        helper.prompt("Введите id\n");
        Long id = getId();
            if(id != null){
                List<PhoneRecord> records = storage.find(new SelectQuery(id));
               if ( !records.isEmpty()){
                   return action(records);
               }

            }
        return searchByFields();
    }

    private FormResponse<? extends Form> searchByFields() {
        List<PhoneRecord> records = storage.find(new SelectQuery(
                helper.readLine("Имя?"),
                helper.readLine("Фамилия?"),
                helper.readLine("Телефон?")

        ));
        if(records.isEmpty()){
            helper.prompt("записи не найдены\n");
            return  BACK_RESPONSE;
        }
        if(records.size() > 1){
            return selectFromList(records);
        }
        return action(records);


    }

    private FormResponse<? extends Form> selectFromList(List<PhoneRecord> records) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (PhoneRecord record : records) {
            menuItems.add(menuItem(
                    String.format("%s %s %s", record.getLastName(),record.getFirstName(),record.getPhone()),
                    ()-> action(Arrays.asList(record))
            ));
        }
        if(isMultiply()){
            menuItems.add(menuItem(
                    "Все",
                    ()->action(records)
            ));
        }
        menuItems.add(menuItem(
                "назад",
                ()->BACK_RESPONSE
        ));

        return choiceMenu.intMenu(getQuestion(), menuItems);
    }

    abstract FormResponse action(List<PhoneRecord> records);

   abstract boolean isMultiply();



    abstract String getQuestion();



    private Long getId() {

        String s = helper.readLine();
        try {
            return Long.valueOf(s);
        }
        catch (Exception e){
            return null;
        }

    }

}
