package com.theironyard.charlotte;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {
    static User user;

    public static void main(String[] args) {
//why cant I add this paragraph without issues?
        Spark.get(
                "/Welcome",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    m.put("name", user.username);
                    return new ModelAndView(m, "home.html");

                }),
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/",

                ((request, response) -> {
                    HashMap n = new HashMap();
                    if (user == null) {
                        return new ModelAndView(n, "login.html");
                    } else {
                        n.put("name", user.username);
                        n.put("password", user.password);
                    }
                    n.put("messages3", user.messages);



                    return new ModelAndView(n, "home.html");

                }),
                new MustacheTemplateEngine()


        );
        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String firstField = request.queryParams("username");
                    String secondField = request.queryParams("password");
                    user = new User(firstField, secondField);

                    response.redirect("/");
                    //redirects back to the home page. when the server receives response, will receive 302 response. status cdoe indicating need to go somewherelese
                    //gives place they should go, and that place is the slash

                    return "";

                }));
        Spark.post(
                "/create-message",
                ((request, response) -> {
                    String mensaje = request.queryParams("message");
                    Message message = new Message(mensaje);
                    user.messages.add(message);
                    response.redirect("/");
                    //redirects back to the home page. when the server receives response, will receive 302 response. status cdoe indicating need to go somewherelese
                    //gives place they should go, and that place is the slash



                    return "";
                }));

    }
}








