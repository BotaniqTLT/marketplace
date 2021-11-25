package ru.botaniqtlt.phonebook.console.form;

/**
 * Интерфейс для форм
 */
public interface Form {

    /**
     * Заготовки для ответов (константы)
     */
    FormResponse<Form> STAY_RESPONSE = new FormResponse<>(FormAction.STAY, null);
    FormResponse<Form> BACK_RESPONSE = new FormResponse<>(FormAction.BACK, null);
    FormResponse<Form> EXIT_RESPONSE = new FormResponse<>(FormAction.EXIT, null);



    /**
     * Главный метод, в нем происходит основная работа формы
     *
     * @return
     */
    FormResponse<? extends Form> run();
}
