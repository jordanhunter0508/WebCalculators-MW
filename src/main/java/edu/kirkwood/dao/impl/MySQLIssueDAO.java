package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.IssueDAO;
import edu.kirkwood.model.Issue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.dao.MySQLConnection.getConnection;

public class MySQLIssueDAO implements IssueDAO {
    @Override
    public List<Issue> selectIssuesByTitle(String title) {
        try(Connection connection = getConnection()){
            CallableStatement statement = connection.prepareCall("{ CALL sp_select_issues_by_title(?)}");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            List<Issue> issues = new ArrayList<>();

            while(resultSet.next()){
                Issue issue = new Issue();
                issue.setIssueID(resultSet.getInt("issueID"));
                issue.setTitle(resultSet.getString("title"));
                issue.setIssueNumber(resultSet.getInt("issueNumber"));
                issue.setContentRating(resultSet.getString("contentRating"));
                issue.setReleaseDate(resultSet.getString("releaseDate"));
                issue.setPageCount(resultSet.getInt("pageCount"));
                issue.setDescription(resultSet.getString("description"));
                issues.add(issue);
            }
            return issues;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
