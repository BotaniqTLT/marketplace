package ru.botaniqtlt.phonebook.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRecordRepository  extends CrudRepository<PhoneRecord,Long> {

    @Query("SELECT m FROM PhoneRecord m WHERE m.firstName LIKE %:firstName% AND " +
            "m.lastName LIKE %:lastName% AND m.phone LIKE %:phone%")
    Iterable<PhoneRecord> search(String lastName, String firstName, String phone);
}
