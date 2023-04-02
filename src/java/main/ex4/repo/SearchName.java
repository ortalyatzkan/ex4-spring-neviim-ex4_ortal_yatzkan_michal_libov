package main.ex4.repo;


/**
 * A class of search object.
 */
public class SearchName {

        private String searchName;

    /**
     * A get function of search name.
     * @return A name of book.
     */
    public String getSearchName(){
            return searchName;
        }

    /**
     * A set function of search book by name.
     * @param searchName name of book to search.
     */
    public void setSearchName(String searchName){
            this.searchName = searchName;
        }
}

