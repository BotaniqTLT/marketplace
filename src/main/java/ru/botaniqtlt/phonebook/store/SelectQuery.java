package ru.botaniqtlt.phonebook.store;

/**
 * Интерфейс запроса поиска записей
 */
public class SelectQuery {

    private final Long id;

    private final String firstNameSearch;

    private final String lastNameSearch;

    private final String phoneSearch;

    public SelectQuery(Long id) {
        firstNameSearch = null;
        lastNameSearch = null;
        phoneSearch = null;

        this.id = id;
    }

    public SelectQuery(String firstNameSearch, String lastNameSearch, String phoneSearch) {
        this.id = null;
        this.firstNameSearch = firstNameSearch;
        this.lastNameSearch = lastNameSearch;
        this.phoneSearch = phoneSearch;
    }

    public Long getId() {
        return id;
    }

    public String getFirstNameSearch() {
        return firstNameSearch;
    }

    public String getLastNameSearch() {
        return lastNameSearch;
    }

    public String getPhoneSearch() {
        return phoneSearch;
    }
}
