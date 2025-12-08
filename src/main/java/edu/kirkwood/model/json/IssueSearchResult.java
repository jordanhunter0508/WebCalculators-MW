package edu.kirkwood.model.json;

public class IssueSearchResult {
    private int id;
    private String name;
    private String description;
    private int issue_number;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIssue_number() {
        return issue_number;
    }

    @Override
    public String toString() {
        return "IssueSearchResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", issue_number=" + issue_number +
                '}';
    }
}
