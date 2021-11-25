package ru.botaniqtlt.phonebook.store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Заготовка под реализацию хранилища в оперативной памяти. Будет очищаться при перезапуске приложения.
 * <p>
 * Нужно реализовать:
 * - коллекцию, в которой будет храниться записи
 * - счетчик идентификаторов
 * - методы интерфейса RecordStorage
 */
//@Component
public class RecordStorageInMemory implements RecordStorage {

    private List<PhoneRecord> list;
    private long idCount = 0;

    public RecordStorageInMemory() {
        list = new ArrayList<>();
    }

    @Override
    public long save(PhoneRecord phoneRecord) {
        if (phoneRecord.getId() == null) {
            phoneRecord.setId(++idCount);
            list.add(phoneRecord);
            return idCount;
        }
        for (PhoneRecord element : list) {
            if (element.getId().equals(phoneRecord.getId())) {
                element.setFirstName(phoneRecord.getFirstName());
                element.setLastName(phoneRecord.getLastName());
                element.setPhone(phoneRecord.getPhone());
                return element.getId();
            }

        }
        return -1;

    }


    @Override
    public List<PhoneRecord> find(SelectQuery query) {
        List<PhoneRecord> res = new ArrayList<>();
        for (PhoneRecord element : list) {
            if (match(element, query)) {
                res.add(element.copy());
            }
        }
        return res;
    }

    private boolean match(PhoneRecord element, SelectQuery query) {
        if(query.getId() != null){
            return query.getId()== element.getId();
        }
        return matchSrting(element.getFirstName(), query.getFirstNameSearch()) &&
                matchSrting(element.getLastName(), query.getLastNameSearch()) &&
                matchSrting(element.getPhone(), query.getPhoneSearch());

    }

    private boolean matchSrting(String value, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return true;
        }
        if (pattern.contains("*")) {
            if (pattern.startsWith("*")) {
                return value.endsWith(clearPattern(pattern));
            }
            if (pattern.endsWith("*")) {
                return value.startsWith(clearPattern(pattern));
            }
        }
        return value.equals(pattern);
    }

    private String clearPattern(String pattern) {
        return pattern.replaceAll("\\*", "");
    }

    @Override
    public boolean remove(long id) {
        Iterator<PhoneRecord> iterator = list.iterator();
        while (iterator.hasNext()) {
            PhoneRecord next = iterator.next();
            if (next.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
