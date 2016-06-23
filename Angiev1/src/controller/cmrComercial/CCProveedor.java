package controller.cmrComercial;


import abm.CLABM;
import java.util.ArrayList;
import modelo.cmrComercial.entidad.CEProveedor;
import modelo.cmrComercial.modelo.CDProveedor;

public class CCProveedor
{


    public static int INSProveedor(ArrayList<CEProveedor> oLista)
    {
        return CDProveedor.ABMProveedor(oLista, CLABM.INSERT);
    }

    public static int DELProveedor(ArrayList<CEProveedor> oLista)
    {
        return CDProveedor.ABMProveedor(oLista, CLABM.DELETE);
    }

    public static int UPDProveedor(ArrayList<CEProveedor> oLista)
    {
        return CDProveedor.ABMProveedor(oLista, CLABM.UPDATE);
    }
     public static ArrayList<CEProveedor> ListaABMProveedor( String DatoProveedor)
     {
        return CDProveedor.ListaProveedor(1,0,DatoProveedor);
     }  
       public static ArrayList<CEProveedor> BuscarDNI( int Estado,String DatoProveedor)
     {
        return CDProveedor.ListaProveedor(2,Estado,DatoProveedor);
     }  
         public static ArrayList<CEProveedor> BuscarNombre( int Estado,String DatoProveedor)
     {
        return CDProveedor.ListaProveedor(3, Estado,DatoProveedor);
     }  
             public static ArrayList<CEProveedor> ListarCNSProveedor( int Estado,String DatoProveedor)
     {
            return CDProveedor.ListaProveedor(4,Estado,DatoProveedor);
     }  
     
     
     
 
}
