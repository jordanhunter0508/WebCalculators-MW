package edu.kirkwood.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Issue implements Comparable<Issue> {

    private int issueID;
    private String title;
    private int issueNumber;
    private String contentRating;
    private String releaseDate;
    private int pageCount;
    private String description;

    public Issue(){}

    public Issue(int issueID, String title, int issueNumber, String contentRating,
                 String releaseDate, int pageCount, String description) {
        this.issueID = issueID;
        this.title = title;
        this.issueNumber = issueNumber;
        this.contentRating = contentRating;
        this.releaseDate = releaseDate;
        this.pageCount = pageCount;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueID=" + issueID +
                ", title='" + title + '\'' +
                ", issueNumber=" + issueNumber +
                ", contentRating=" + contentRating +
                ", releaseDate='" + releaseDate + '\'' +
                ", pageCount=" + pageCount +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Compares the two Issues by their ID's
     * @param o other Issue being compared
     * @return Returns 0 is this objects ID is less than or equal to the other issue
     */
    @Override
    public int compareTo(@NotNull Issue o) {
        return Integer.compare(this.issueID, o.issueID);
    }

    public static Comparator<Issue> compareTitle = (i1, i2) -> i1.title.compareToIgnoreCase(i2.title);
    public static Comparator<Issue> compareReleaseDate = (i1, i2) -> i1.releaseDate.compareToIgnoreCase(i2.releaseDate);

}
