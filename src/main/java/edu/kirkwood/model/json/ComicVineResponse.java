package edu.kirkwood.model.json;

import java.util.List;

public class ComicVineResponse {
    private List<IssueSearchResult> results;
    private int total_pages;

    public List<IssueSearchResult> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
