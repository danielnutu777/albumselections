package myAlbums;

import myAlbums.db.DemoCRUDOperations;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/main")
public class MyAlbumsServlet extends HttpServlet {
    private static final String LIST_ACTION = "list";

    public void service(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        System.out.println("albums action = " + action);

        if (action != null && action.equals(LIST_ACTION)) {
            listAction(request, response);
        } else if (action != null && action.equals("add")) {
            addAction(request, response);
        }else if (action != null && action.equals("delete")) {
            deleteAction(request, response);
        }
    }

    private void addAction(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String band = request.getParameter("band");
        String year = request.getParameter("year");
        String rating = request.getParameter("rating");

        Album albumNou = new Album(name, band, Integer.parseInt(year), Integer.parseInt(rating));

        try {
            DemoCRUDOperations.writeAlbums(albumNou);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAction(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");

        Album album = new Album(Integer.parseInt(id));

        try {
            DemoCRUDOperations.deleteAlbum(album);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listAction(HttpServletRequest request, HttpServletResponse response) {
        String jsonResponse = "[";
        List<Album> albums = new ArrayList<>();

        try {
            albums = DemoCRUDOperations.readAlbums();
        } catch (ClassNotFoundException e) {
            albums.add(new Album("Class Error :" + e.getMessage(), e.getMessage(), -234758, -23525));
        } catch (SQLException e) {
            albums.add(new Album("SQL Error: " + e.getMessage(), e.getMessage(), -43678, -3623));
        }

        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            String element = album.toJson();
            jsonResponse += element;
            if (i < albums.size() - 1) {
                jsonResponse += ",";
            }
        }
        jsonResponse += "]";
        returnJsonResponse(response, jsonResponse);
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;

        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}
