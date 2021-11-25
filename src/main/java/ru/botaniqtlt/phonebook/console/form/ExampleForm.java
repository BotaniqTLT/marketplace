package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;


/**
 * Пример формы
 */
@Component
public class ExampleForm implements Form {


    private ConsoleHelper helper;

    public ExampleForm(ConsoleHelper helper) {
        this.helper = helper;
    }

    /**
     * Главный метод будет запускаться до тех пор, пока не будет дан корректный ответ.
     * Запросим ввести число 13.
     * Если пользовател ввел что-то другое, остаемся на форме и повторяем вопрос.
     * Иначе, возвращаемся назад.
     *
     * @return
     */
    @Override
    public FormResponse<? extends Form> run() {
        helper.prompt("Введите число 13:\n");
        if (helper.readInt() == 13) {
            return Form.BACK_RESPONSE;
        }
        return Form.STAY_RESPONSE;
    }
}
