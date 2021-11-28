package ru.botaniqtlt.phonebook.store;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class RecordStorageDB implements RecordStoragePageable {

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
        findPage(query).forEach(result::add);
        return result;
    }

    @Override
    public Page<PhoneRecord> findPage(SelectQuery query) {

        Pageable pr = getPageRequest(query);
        if (query.getFirstNameSearch() != null || query.getLastNameSearch() != null || query.getPhoneSearch() != null) {
            return phoneRecordRepository.search(query.getLastNameSearch(), query.getFirstNameSearch(), query.getPhoneSearch(), pr);

        } else if (query.getId() == null) {
            return phoneRecordRepository.findAll(pr);

        }
        return phoneRecordRepository.findById(query.getId())
                .map((r) -> new PageImpl<PhoneRecord>(List.of(r)))
                .orElse(new PageImpl<PhoneRecord>(Collections.emptyList()));


    }

    private PageRequest getPageRequest(SelectQuery query) {
        return PageRequest.of(query.getPage()-1, query.getSize(), getSort(query));
    }

    private Sort getSort(SelectQuery query) {
        if (query.getFirstNameOrder() != null && !query.getFirstNameOrder().isEmpty()) {
            return Sort.by("asc".equals(query.getFirstNameOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC, "firstName");
        }
        if (query.getLastNameOrder() != null && !query.getLastNameOrder().isEmpty()) {
            return Sort.by("asc".equals(query.getLastNameOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC, "lastName");
        }
        if (query.getPhoneOrder() != null && !query.getPhoneOrder().isEmpty()) {
            return Sort.by("asc".equals(query.getPhoneOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC, "phone");
        }

        return Sort.by(Sort.Direction.ASC, "firstName");
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
