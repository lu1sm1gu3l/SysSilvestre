package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDMarca;
import modelo.almAlmacen.entidad.CEMarca;

public class CCMarca
{
    public static List<CEMarca> consultarListaMarcas(int IdSubfamilia,int IdFamilia,int IdCategoria,int IdRubro)
    {
       return CDMarca.consultarListaMarcasxSubfamilia( IdSubfamilia, IdFamilia, IdCategoria, IdRubro);
    }
       public static List<CEMarca> consultarListaMarcasPorDescripcion(String marca)
    {
       return CDMarca.consultarListaMarcas(marca);
    }
       public static List<CEMarca>  consultarListaMarca()
      {
           return CDMarca.consultarListaMarca();
       }
       public static int ABMMarca(int pIntAMB,CEMarca oCDUnidadMedidaVenta)
        {
           return CDMarca.ABMMarca(pIntAMB, oCDUnidadMedidaVenta);
       }

}
