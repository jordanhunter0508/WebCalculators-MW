package edu.kirkwood.dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import edu.kirkwood.dao.IssueDAO;
import edu.kirkwood.model.Issue;
import edu.kirkwood.model.json.ComicVineResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonIssueDAO implements IssueDAO {
    private String apiURL;
    private String apiKey;

    public JsonIssueDAO(String apiURL, String apiKey) {
        this.apiURL = apiURL;
        this.apiKey = apiKey;
    }

    public String fetchRawData(String title, int page){
        String responseBody = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiURL + "api_key=" + apiKey + "&format=json&resources=issue&query=" + title + "&page=" + page)
                .get()
                .addHeader("Authorization", "api_key " + apiKey)
                .addHeader("User-Agent", "MyApp/1.0")
                .addHeader("accept", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public List<Issue> selectIssuesByTitle(String title) {
        List<Issue> issues = new ArrayList<>();
        int currentPage = 1;
        while (true) {
            String rawData = fetchRawData(title, currentPage);
            Gson gson = new GsonBuilder()
                    .create();
            // prettyPrint(rawData);
            ComicVineResponse issueResponse = null;
            try {
                issueResponse = gson.fromJson(rawData, ComicVineResponse.class);
            } catch (JsonSyntaxException e) {
                throw new RuntimeException(e);
            }
            // System.out::println is an example of a method reference
            // issueResponse.getResults().forEach(System.out::println);
            // result -> is an example of a lambda expression
            issueResponse.getResults().forEach(result -> {
                Issue issue = new Issue();
                issue.setIssueID(result.getId());
                issue.setTitle(result.getName());
                issue.setDescription(result.getDescription());
                issue.setIssueNumber(result.getIssue_number());
                issues.add(issue);
            });
            if(issueResponse.getTotal_pages() > currentPage) {
                currentPage++;
            } else {
                break;
            }
        }
        return issues;
    }
}
