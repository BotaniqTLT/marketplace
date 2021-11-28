package ru.botaniqtlt.phonebook.store;

import org.springframework.data.domain.Page;

import java.util.List;

public interface RecordStoragePageable extends  RecordStorage{

    /**
     * Поиск записей
     *
     * @param query запрос с условиями поиска
     * @return
     */
   Page<PhoneRecord> findPage(SelectQuery query);
}
