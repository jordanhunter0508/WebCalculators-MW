package edu.kirkwood.controller;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value="/movies")
public class MovieServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/movies.jsp").forward(req, resp);

        String search = req.getParameter("search");
        req.setAttribute("search",search);
        List<Movie> movies = null;

        if(search == null || search.replace(" ", "").isEmpty()){
            return;
        }

        try{
            movies = getResults(search);
        }catch (Exception e){
            req.setAttribute("searchError","<p>Failed to get movie results.</p>");
        }

        if(movies == null || movies.size() == 0)
        {
            req.setAttribute("searchError","<p>There are no movies with the name. "+ search + "</p>");
        }

        req.setAttribute("movies",movies);

    }

    public static List<Movie> getResults(String search) {
        try {
            MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
            List<Movie> movies = new ArrayList<>();

            if(movieDAO instanceof XmlMovieDAO) {
                movies.addAll(((XmlMovieDAO)movieDAO).search(search));
            } else if(movieDAO instanceof MySQLMovieDAO) {
                movies.addAll(((MySQLMovieDAO)movieDAO).search(search));
            } else if(movieDAO instanceof JsonMovieDAO) {
                movies.addAll(((JsonMovieDAO)movieDAO).search(search));
            }

            return movies;
        } catch (Exception e) {
        }
        return null;
    }
}
