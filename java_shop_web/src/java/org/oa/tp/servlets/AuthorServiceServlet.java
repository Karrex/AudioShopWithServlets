package org.oa.tp.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.oa.tp.dao.DaoFacade;
import org.oa.tp.data.Author;

public class AuthorServiceServlet extends HttpServlet {

    private static final String PARAMETR_METHOD = "method";
    private static final String PARAMETR_ID = "id";
    private static final String PARAMETR_FIRST_NAME = "firstName";
    private static final String PARAMETR_LAST_NAME = "lastName";
    private static final String PARAMETR_AGE = "age";
    private static final String PARAMETR_GENDER = "gender";

    private static final String GET_ALL_METHOD = "get";
    private static final String CREATE_METHOD = "create";
    private static final String DELETE_METHOD = "delete";
    private static final String UPDATE_METHOD = "update";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String queryMethod = request.getParameter(PARAMETR_METHOD);
        response.setContentType("application/json;charset=UTF-8");
        DaoFacade daoFacade = new DaoFacade(getServletContext());
        if (GET_ALL_METHOD.equalsIgnoreCase(queryMethod)) {
            List<Author> authors = daoFacade.getAuthorDao().loadAll();
            try (PrintWriter pw = response.getWriter()) {
                Gson gson = new Gson();
                gson.toJson(authors, pw);
            }
        } else if (CREATE_METHOD.equalsIgnoreCase(queryMethod)) {
            String firstName = request.getParameter(PARAMETR_FIRST_NAME);
            String lastName = request.getParameter(PARAMETR_LAST_NAME);
            String StringAge = request.getParameter(PARAMETR_AGE);
            String gender = request.getParameter(PARAMETR_GENDER);
            int age = Integer.parseInt(StringAge);
            Author author = new Author(firstName, lastName, age, gender);
            boolean isAdded = daoFacade.getAuthorDao().add(author);
            try (PrintWriter pw = response.getWriter()) {
                if (isAdded) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    pw.println("{\"response\":\"Author created\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    pw.println("{\"response\":\"Failed author created\"}");
                }
            }
        } else if (DELETE_METHOD.equalsIgnoreCase(queryMethod)) {
            String StringId = request.getParameter(PARAMETR_ID);
            long id = Long.parseLong(StringId);
            boolean isDeleted = daoFacade.getAuthorDao().delete(id);
            try (PrintWriter pw = response.getWriter()) {
                if (isDeleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    pw.println("{\"response\":\"Author deleted\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    pw.println("{\"response\":\"Failed author deleted\"}");
                }
            }
        } else if (UPDATE_METHOD.equalsIgnoreCase(queryMethod)) {
            String idString = request.getParameter(PARAMETR_ID);
            String firstName = request.getParameter(PARAMETR_FIRST_NAME);
            String lastName = request.getParameter(PARAMETR_LAST_NAME);
            String ageString = request.getParameter(PARAMETR_AGE);
            String gender = request.getParameter(PARAMETR_GENDER);
            long id = Long.parseLong(idString);
            int age = Integer.parseInt(ageString);
            Author author = new Author(id, firstName, lastName, age, gender);
            boolean isUpdated = daoFacade.getAuthorDao().update(author);
            try (PrintWriter pw = response.getWriter()) {
                if (isUpdated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    pw.println("{\"response\":\"Author updated\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    pw.println("{\"response\":\"Failed author updated\"}");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String queryString = request.getQueryString();
        System.out.println("query string " + queryString);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
