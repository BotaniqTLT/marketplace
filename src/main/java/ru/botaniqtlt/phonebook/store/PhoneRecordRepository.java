package ru.botaniqtlt.phonebook.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface PhoneRecordRepository  extends PagingAndSortingRepository<PhoneRecord,Long> {

    @Query(value = "SELECT m FROM PhoneRecord m WHERE m.firstName LIKE %:firstName% AND " +
            "m.lastName LIKE %:lastName% AND m.phone LIKE %:phone%",
    countQuery = "SELECT count(1) FROM PhoneRecord m WHERE m.firstName LIKE %:firstName% AND " +
            "m.lastName LIKE %:lastName% AND m.phone LIKE %:phone%")
    Page<PhoneRecord> search(String lastName, String firstName, String phone, Pageable pageable);
}
