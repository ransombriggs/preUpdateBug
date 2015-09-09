package controllers;

import play.db.jpa.JPA;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        renderText(JPA.em().getFlushMode().toString());
    }

}