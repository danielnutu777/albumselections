package myAlbums;
import myAlbums.db.DemoCRUDOperations;
import javax.servlet.ServletException;
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
        private int counter;
        private static final String LIST_ACTION = "list";

        List <Item> items = new ArrayList<>();

@Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("my albums service called now");

        String action = request.getParameter("action");
        counter++;

        if (action != null && action.equals(LIST_ACTION)) {
        listAction(request, response);
        } else if (action != null && action.equals("add")) {
        addAction(request, response);
        } else if (action != null && action.equals("remove")) {
            removeAction(request, response);
        }

        System.out.println("I was used" + counter + "times!");
}

    private void addAction(HttpServletRequest request, HttpServletResponse response) {
        String album = request.getParameter("album");
        String band = request.getParameter("band");
        String year = request.getParameter("year");
        String rating = request.getParameter("rating");

        Item itemulNou = new Item(album, band, Integer.parseInt(year), Integer.parseInt(rating));

        try {
            DemoCRUDOperations.writeAlbums(itemulNou);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        items.add(itemulNou);

        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeAction(HttpServletRequest request, HttpServletResponse response) {
        String album = request.getParameter("album");

        Item itemulNou = new Item(album, null, 0, 0);

        try {
            DemoCRUDOperations.removeAlbums(itemulNou);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        items.remove(itemulNou);

        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listAction(HttpServletRequest request, HttpServletResponse response) {
        String jsonResponse = "[";
        List<Item> items = new ArrayList<>();

        try {
            items = DemoCRUDOperations.readAlbums();
        } catch (ClassNotFoundException e) {
            items.add(new Item("Class Error :" + e.getMessage(), e.getMessage(), -234758, -23525 ));
        } catch (SQLException e) {
            items.add(new Item("SQL Error: " + e.getMessage(), e.getMessage(), -43678, -3623));
        }

        for(int i = 0; i < items.size(); i++) {
            String album = items.get(i).getAlbum();
            String band = items.get(i).getBand();
            int year = items.get(i).getYear();
            int rating = items.get(i).getRating();
            String element = "{\"album\": \"" + album + "\", \"band\": \"" + band + "\", \"year\": \"" + year + "\", \"rating\": \"" + rating + "\"}";
            jsonResponse += element;
            if(i < items.size() -1) {
                jsonResponse += ",";
            }
        }
        jsonResponse += "]";
        returnJsonResponse(response, jsonResponse);
    }
@Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is:" + counter);
}
@Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
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
