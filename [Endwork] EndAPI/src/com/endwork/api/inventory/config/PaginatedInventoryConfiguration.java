package com.endwork.api.inventory.config;

public class PaginatedInventoryConfiguration extends InventoryConfiguration {

    // The message to display when the user tries to visit a page before the first.
    private String noPreviousPages = "Il n'y a pas de page précédente.";
    // The message to display when the user tries to visit a page after the last.
    private String noAdditionalPages = "Il n'y a pas de page supplémentaire.";

    // The label for the previous page button.
    private String previousPage = "&c&lPage précédente";
    // The label for the current page button.
    private String currentPage = "&c&lPage {currentPage} / {maxPages}";
    // The label for the next page button.
    private String nextPage = "&c&lPage suivante";

    public PaginatedInventoryConfiguration(
            String chatPrefix,
            String noPreviousPages,
            String noAdditionalPages,

            String previousPage,
            String currentPage,
            String nextPage
    ){
        super(chatPrefix);
        this.noPreviousPages = noPreviousPages;
        this.noAdditionalPages = noAdditionalPages;

        this.previousPage = previousPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public PaginatedInventoryConfiguration(
            String noPreviousPages,
            String noAdditionalPages,

            String previousPage,
            String currentPage,
            String nextPage
    ){
        this.noPreviousPages = noPreviousPages;
        this.noAdditionalPages = noAdditionalPages;

        this.previousPage = previousPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
    }

    public PaginatedInventoryConfiguration(){

    }


    public String getNoPreviousPages() {
        return noPreviousPages;
    }

    public void setNoPreviousPages(String noPreviousPages) {
        this.noPreviousPages = noPreviousPages;
    }

    public String getNoAdditionalPages() {
        return noAdditionalPages;
    }

    public void setNoAdditionalPages(String noAdditionalPages) {
        this.noAdditionalPages = noAdditionalPages;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}

