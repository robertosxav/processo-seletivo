package br.com.robertoxavier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageResponse<R> {
    private List<R> content = new ArrayList<>();
    private int pageActual;
    private int totalPages;
    private long totalRecords;
    private int sizePage;

    public PageResponse() {
    }

    public PageResponse(int pageActual, int totalPages, long totalRecords, int sizePage, List<R> records) {
        this.pageActual = pageActual;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.sizePage = sizePage;
        this.content = records;
    }

    public List<R> getContent() {
        return content;
    }

    public void setContent(List<R> content) {
        this.content = content;
    }

    public int getPageActual() {
        return pageActual;
    }

    public void setPageActual(int pageActual) {
        this.pageActual = pageActual;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public <O> PageResponse<O> map(Function<? super R, ? extends O> mapper) {
        return new PageResponse<O>(
                this.pageActual,
                this.totalPages,
                this.totalRecords,
                this.sizePage,
                this.getConvertedContent(mapper)
        );
    }

    private <O> List<O> getConvertedContent(Function<? super R, ? extends O> converter) {
        // Use Stream<R> to avoid raw types
        Stream<R> listConvert = this.content.stream();
        Objects.requireNonNull(converter);
        return listConvert.map(converter)
                .collect(Collectors.toList());
    }
}
