package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the values entered in the form and clean it with jsoup.
        String  name = Jsoup.clean(request.getParameter("name-input"), Whitelist.none()), 
                email = Jsoup.clean(request.getParameter("email-input"), Whitelist.none()),
                message = Jsoup.clean(request.getParameter("message-input"), Whitelist.none());

        // Print the value so you can see it in the server logs.
        System.out.println("You submitted: Name: " + name + " | Email: " + email + " | Message: " + message);

        // Write the value to the response so the user can see it.
        response.getWriter().println("You submitted: Name: " + name + " | Email: " + email + " | Message: " + message);

        // Store the entered values in the database
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
        FullEntity contactEntity = Entity.newBuilder(keyFactory.newKey())
                        .set("name", name)
                        .set("email", email)
                        .set("message", message)
                    .build();
        datastore.put(contactEntity);

        response.sendRedirect("/receivedMessages.html");
        //response.sendRedirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }
}

