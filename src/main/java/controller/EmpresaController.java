package controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {

    public static ModelAndView home(Request req, Response res) {
        return new ModelAndView(null, "homePage/empresa.hbs");
    }

}
