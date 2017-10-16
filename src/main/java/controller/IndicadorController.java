package controller;

import main.repositories.RepositorioIndicador;
import model.RegistroIndicador;
import org.apache.commons.lang.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndicadorController {

    public static ModelAndView home(Request req, Response res) {
        HashMap<String, List<RegistroIndicador>> mapIndicadores = new HashMap<>();
        String idUsuarioAux = req.params("userId");
        Long idUsuario = idUsuarioAux != null && StringUtils.isNumeric(idUsuarioAux) ?
                Long.parseLong(idUsuarioAux) : null;
        List<RegistroIndicador> indicadoresObtenidas = idUsuario != null ?
                RepositorioIndicador.getSingletonInstance().findFromUser(idUsuario) : new ArrayList<>();
        mapIndicadores.put("indicadores", indicadoresObtenidas);
        return new ModelAndView(mapIndicadores, "homePage/indicador.hbs");
    }

}
