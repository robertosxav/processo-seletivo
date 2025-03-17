package br.com.robertoxavier;

public class PageQuery {

    private int page = 0;
    private int sizePage = 10;
    private Direction sort;
    private String[] sortProperties;

    public PageQuery() {
    }

    public PageQuery(int page, int sizePage) {
        this.page = page;
        this.sizePage = sizePage;
    }

    public PageQuery(int page, int sizePage, Direction sort, String[] sortProperties) {
        this.page = page;
        this.sizePage = sizePage;
        this.sort = sort;
        this.sortProperties = sortProperties;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSizePage() {
        return this.sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public Direction getSort() {
        return this.sort;
    }

    public void setSort(Direction sort) {
        this.sort = sort;
    }

    public String[] getSortProperties() {
        return this.sortProperties;
    }

    public void setSortProperties(String[] sortProperties) {
        this.sortProperties = sortProperties;
    }
}
