package ru.botaniqtlt.phonebook.store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecordStorageDB implements RecordStorage {

    private PhoneRecordRepository phoneRecordRepository;

    public RecordStorageDB(PhoneRecordRepository phoneRecordRepository) {
        this.phoneRecordRepository = phoneRecordRepository;
    }


    @Override
    public long save(PhoneRecord phoneRecord) {

        return phoneRecordRepository.save(phoneRecord).getId();
    }

    @Override
    public List<PhoneRecord> find(SelectQuery query) {
        ArrayList<PhoneRecord> result = new ArrayList<>();
        phoneRecordRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public boolean remove(long id) {
        Optional<PhoneRecord> byId = phoneRecordRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        phoneRecordRepository.delete(byId.get());
        return true;
    }
}
