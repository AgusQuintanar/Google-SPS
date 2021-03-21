package com.google.sps.servlets;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Message;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/getMessagesList")
public class GetMessages extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
            Query.newEntityQueryBuilder().setKind("Contact").setOrderBy(OrderBy.desc("__key__")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Message> messages = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();


        long id = entity.getKey().getId();
        String      name = entity.getString("name"),
                    email = entity.getString("email"),
                    message = entity.getString("message");
      


      Message msg = new Message(id, name, email, message);
      messages.add(msg);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(messages));
  }
}
