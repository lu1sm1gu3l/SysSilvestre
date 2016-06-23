package controller.cmrComercial;


import abm.CLABM;
import java.util.ArrayList;
import modelo.cmrComercial.entidad.CECliente;
import modelo.cmrComercial.modelo.CDCliente;

public class CCCliente
{


    public static int INSCliente(ArrayList<CECliente> oLista)
    {
        return CDCliente.ABMCliente(oLista, CLABM.INSERT);
    }

    public static int DELCliente(ArrayList<CECliente> oLista)
    {
        return CDCliente.ABMCliente(oLista, CLABM.DELETE);
    }

    public static int UPDCliente(ArrayList<CECliente> oLista)
    {
        return CDCliente.ABMCliente(oLista, CLABM.UPDATE);
    }
     public static ArrayList<CECliente> ListaABMCliente( String DatoCliente)
     {
        return CDCliente.ListaCliente(1,0,DatoCliente);
     }  
       public static ArrayList<CECliente> BuscarDNI( int Estado,String DatoCliente)
     {
        return CDCliente.ListaCliente(2,Estado,DatoCliente);
     }  
         public static ArrayList<CECliente> BuscarNombre( int Estado,String DatoCliente)
     {
        return CDCliente.ListaCliente(3, Estado,DatoCliente);
     }  
             public static ArrayList<CECliente> ListarCNSCliente( int Estado,String DatoCliente)
     {
            return CDCliente.ListaCliente(4,Estado,DatoCliente);
     }  
     
     
     
 
}
