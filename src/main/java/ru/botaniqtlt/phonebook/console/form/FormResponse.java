package ru.botaniqtlt.phonebook.console.form;

/**
 * Контейнер для ответов из формы
 *
 * @param <T> тип формы, на которую нужно перейти
 */
public class FormResponse<T extends Form> {

    private FormAction action;

    private Class<T> link;

    public FormResponse(FormAction action, Class<T> link) {
        this.action = action;
        this.link = link;
    }

    public FormAction getAction() {
        return action;
    }

    public Class<T> getLink() {
        return link;
    }
}
