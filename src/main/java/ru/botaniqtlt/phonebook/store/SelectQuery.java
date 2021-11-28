package ru.botaniqtlt.phonebook.store;

/**
 * Интерфейс запроса поиска записей
 */
public class SelectQuery {

    private Long id;

    private String firstNameSearch;

    private String lastNameSearch;

    private String phoneSearch;

    private String firstNameOrder;

    private String lastNameOrder;

    private String phoneOrder;

    private Integer page=1;

    private Integer size=10;

    private String sort="f_a";

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

    public String getFirstNameOrder() {
        return firstNameOrder;
    }

    public String getLastNameOrder() {
        return lastNameOrder;
    }

    public String getPhoneOrder() {
        return phoneOrder;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public String getSort() {
        return sort;
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

    public void setFirstNameOrder(String firstNameOrder) {
        this.firstNameOrder = firstNameOrder;
    }

    public void setLastNameOrder(String lastNameOrder) {
        this.lastNameOrder = lastNameOrder;
    }

    public void setPhoneOrder(String phoneOrder) {
        this.phoneOrder = phoneOrder;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSort(String sort) {
        this.sort = sort;
        if(sort !=null){
            mapSort();
        }
    }

    private void mapSort() {
        if(sort.startsWith("f_")){
            firstNameOrder= getOrdering();
        }
        if(sort.startsWith("l_")){
            lastNameOrder=getOrdering();
        }
        if(sort.startsWith("p_")){
            phoneOrder=getOrdering();
        }
    }

    private String getOrdering() {
        return sort.endsWith("d") ? "desc" : "asc";
    }
}
