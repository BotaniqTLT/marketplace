package ru.botaniqtlt.phonebook.store;

import java.util.List;

/**
 * Интерфейс работы с хранилищем
 */
public interface RecordStorage {

    /**
     * Сохранение записи в хранилище
     * Если у записи не указан ID, значит будет производиться добавление.
     * Если у записи указан ID, значит обновляем запись.
     *
     * @param phoneRecord запись
     * @return id записи или -1 в случае ошибки
     */
    long save(PhoneRecord phoneRecord);

    /**
     * Поиск записей
     *
     * @param query запрос с условиями поиска
     * @return
     */
    List<PhoneRecord> find(SelectQuery query);

    /**
     * Удаление записи
     *
     * @param id
     * @return
     */
    boolean remove(long id);
}
