package controller.vtaVenta;

import java.util.List;
import modelo.vtaVenta.datos.CDInstitucionFinanciera;
import modelo.vtaVenta.entidad.CEInstitucionFinanciera;

public class CCInstituicionFinanciera {

    public static List<CEInstitucionFinanciera> consultarInstituicionFinanciera() {
        return CDInstitucionFinanciera.listarInstitucionFinanciera();
    }
}
