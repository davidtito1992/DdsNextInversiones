package controller;

import main.repositories.RepositorioMetodologia;
import model.Metodologia;
import org.apache.commons.lang.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetodologiaController {

    public static ModelAndView home(Request req, Response res) {
        HashMap<String, List<Metodologia>> mapMetodologias = new HashMap<>();
        String idUsuarioAux = req.params("userId");
        Long idUsuario = idUsuarioAux != null && StringUtils.isNumeric(idUsuarioAux) ?
                Long.parseLong(idUsuarioAux) : null;
        List<Metodologia> metodologiasObtenidas = idUsuario != null ?
                RepositorioMetodologia.getSingletonInstance().findFromUser(idUsuario) : new ArrayList<>();
        mapMetodologias.put("metodologias", metodologiasObtenidas);
        return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
    }
}
