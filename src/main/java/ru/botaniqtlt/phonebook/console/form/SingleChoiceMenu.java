package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;

import java.util.List;

@Component
public class SingleChoiceMenu {
    private ConsoleHelper helper;

    public SingleChoiceMenu(ConsoleHelper helper) {

        this.helper = helper;
    }

    public FormResponse intMenu(String question, List<MenuItem> items) {
        while (true) {
            helper.prompt(question + ":\n");
            int index = 1;
            for (MenuItem item : items) {
                helper.prompt(String.format("\t%d -%s\n", index++, item.name));
            }
            try {
                index = helper.readInt() - 1;
                MenuItem selected = items.get(index);
                if (selected != null) {
                    return selected.action.get();
                }
            } catch (Exception e) {
                helper.prompt("неправильный ответ\n");
            }
        }
    }
}
