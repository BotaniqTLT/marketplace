package ru.botaniqtlt.phonebook.console.form;

import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;

import java.util.Arrays;

import static ru.botaniqtlt.phonebook.console.form.MenuItem.menuItem;

/**
 * Главная форма, из которого можно переходить на другие экраны
 * 1 Добавление  новых записей 1 форма
 * 2 просмотр записей 1) вывести все -> 1) отрисовать содержимое хранилища
 * 2) Искать  -> 1) Имя, фамилия телефон
 * 3) назад
 * 3) редактирование-> 1) если индентификатор не найден искть по всем полям
 * ( если найдено более 1 записи), предоставить выбрать индентификатор записи
 * 2)Найденную запись изменить  показывать текущие значение поля, если поле пустое, значение не изменять
 * 3) обновить записи в хранилище и вернуться назад
 * 4) удаление -> 1)  если индентификатор не найден искть по всем полям
 * ( если найдено более 1 записи), предоставить выбрать индентификатор записи   либо удалить найденное
 * 2) удалить найденные индентификаторы
 * 3) вернуться назад
 */
@Component
public class MainForm implements Form {


    private ConsoleHelper helper;

    private SingleChoiceMenu choiceMenu;

    public MainForm(ConsoleHelper helper, SingleChoiceMenu choiceMenu) {
        this.helper = helper;
        this.choiceMenu = choiceMenu;
    }

    /**
     * Главный метод формы.
     * Во время работы можно интерактивно общаться с клиентом через ConsoleHelper.
     * <p>
     * По завершению вернуть результат работы FormResponse с указанием действия:
     * - STAY остаться на экране (повторить вызов run)
     * - GO с указанием класса формы, на которую нужно перейти
     * - BACK вернуться на предыдущую форму
     * - EXIT завершить работу приложения
     *
     * @return
     */
    @Override
    public FormResponse<? extends Form> run() {
        return choiceMenu.intMenu("Выберите команду", Arrays.asList(
          menuItem("Добавление",()->new FormResponse<>(FormAction.GO, AddForm.class) ),
          menuItem("Просмотр записей",()->new FormResponse<>(FormAction.GO, ShowForm.class) ),
          menuItem("Редактирование",()->new FormResponse<>(FormAction.GO, EditForm.class) ),
          menuItem("Удаление",()->new FormResponse<>(FormAction.GO, DeleteForm.class) ),
          menuItem("Выйти",()->EXIT_RESPONSE)
        ));

    }
}
