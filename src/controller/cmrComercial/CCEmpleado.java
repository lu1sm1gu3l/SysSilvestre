package controller.cmrComercial;


import abm.CLABM;
import java.util.ArrayList;
import modelo.cmrComercial.entidad.CEEmpleado;
import modelo.cmrComercial.modelo.CDEmpleado;

public class CCEmpleado
{


    public static int INSEmpleado(ArrayList<CEEmpleado> oLista)
    {
        return CDEmpleado.ABMEmpleado(oLista, CLABM.INSERT);
    }

    public static int DELEmpleado(ArrayList<CEEmpleado> oLista)
    {
        return CDEmpleado.ABMEmpleado(oLista, CLABM.DELETE);
    }

    public static int UPDEmpleado(ArrayList<CEEmpleado> oLista)
    {
        return CDEmpleado.ABMEmpleado(oLista, CLABM.UPDATE);
    }
     public static ArrayList<CEEmpleado> ListaABMEmpleado( String DatoEmpleado)
     {
        return CDEmpleado.ListaEmpleado(1,0,DatoEmpleado);
     }  
       public static ArrayList<CEEmpleado> BuscarDNI( int Estado,String DatoEmpleado)
     {
        return CDEmpleado.ListaEmpleado(2,Estado,DatoEmpleado);
     }  
         public static ArrayList<CEEmpleado> BuscarNombre( int Estado,String DatoEmpleado)
     {
        return CDEmpleado.ListaEmpleado(3, Estado,DatoEmpleado);
     }  
             public static ArrayList<CEEmpleado> ListarCNSEmpleado( int Estado,String DatoEmpleado)
     {
            return CDEmpleado.ListaEmpleado(4,Estado,DatoEmpleado);
     }  
     
     
     
 
}
