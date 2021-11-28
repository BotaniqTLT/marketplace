package ru.botaniqtlt.phonebook.store;

/**
 * Интерфейс запроса поиска записей
 */
public class SelectQuery {

    private  Long id;

    private  String firstNameSearch;

    private  String lastNameSearch;

    private  String phoneSearch;

    public SelectQuery() {
        this(null);
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstNameSearch(String firstNameSearch) {
        this.firstNameSearch = firstNameSearch;
    }

    public void setLastNameSearch(String lastNameSearch) {
        this.lastNameSearch = lastNameSearch;
    }

    public void setPhoneSearch(String phoneSearch) {
        this.phoneSearch = phoneSearch;
    }
}
