package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.JsonIssueDAO;
import edu.kirkwood.dao.impl.MySQLIssueDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IssueDAOFactory {
    private static Properties properties = new Properties();

    // Try to load the application.properties file
    static {
        try(InputStream input = MovieDAOFactory.class.getClassLoader()
                .getResourceAsStream("application.properties");) {
            if(input == null) {
                throw new RuntimeException("application.properties file not found");
            }
            properties.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //
    public static IssueDAO getIssueDAO() {
        String sourceType =  properties.getProperty("datasource.type");
        if(sourceType == null || sourceType.isEmpty()) {
            throw new RuntimeException("Unknown datasource type");
        }
        switch (sourceType.toUpperCase()) {
            case "MYSQL":
                return new MySQLIssueDAO();
            case "JSON":
                String jsonApiURL = properties.getProperty("json.apiURL");
                if(jsonApiURL == null || jsonApiURL.isEmpty()) {
                    throw new IllegalArgumentException("json.apiURL is required");
                }
                String jsonReadAccessToken = properties.getProperty("json.apiReadAccessToken");
                if(jsonReadAccessToken == null || jsonReadAccessToken.isEmpty()) {
                    throw new IllegalArgumentException("json.apiReadAccessToken");
                }
                return new JsonIssueDAO(jsonApiURL, jsonReadAccessToken);
//            case "XML":
//                break;
        }
        return null;
    }
}
