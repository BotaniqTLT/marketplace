package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;

import java.util.Arrays;

import static ru.botaniqtlt.phonebook.console.form.MenuItem.menuItem;

@Component
public class ShowForm implements Form {
    private ConsoleHelper helper;

    private SingleChoiceMenu choiceMenu;

    public ShowForm(ConsoleHelper helper, SingleChoiceMenu choiceMenu) {
        this.helper = helper;
        this.choiceMenu = choiceMenu;
    }

    @Override
    public FormResponse<? extends Form> run() {
        return choiceMenu.intMenu("Хотите", Arrays.asList(
                menuItem("Показать все", () -> new FormResponse<>(FormAction.GO, ShowAllForm.class)),
                menuItem("Поиск", () -> new FormResponse<>(FormAction.GO, AbstractSearchForm.class)),
                menuItem("Назад", () -> BACK_RESPONSE)
        ));


    }

}

