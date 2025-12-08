package edu.kirkwood.dao;

import edu.kirkwood.model.Issue;

import java.util.List;

public interface IssueDAO {

    /***
     * Gets a List of Issues from the database or an API, that
     * have a matching title.
     * @param title Used to find matching Issues
     * @return Returns a list of Issues where the title matches
     */
    public List<Issue> selectIssuesByTitle(String title);
}
