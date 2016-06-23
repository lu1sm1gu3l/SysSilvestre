package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.almAlmacen.entidad.CEAlmacen;

public class CDAlmacen 
{
public static ArrayList<CEAlmacen> listarAlmacen(int tipoCns,int pIdSucursal)
    {
      Connection cnn= ConexionBD.obtenerConexion();
        try
        {

            ArrayList<CEAlmacen> oLista=null;
                if(cnn!=null)
                {
                     CallableStatement cs= null;
                     cs = cnn.prepareCall("CALL ALMSPRCNSAlmacen(?,?)");

                     cs.setInt(1,tipoCns);
                     cs.setInt(2,pIdSucursal);
                     ResultSet rs=cs.executeQuery();
                      if(rs.next())
                      {
                        oLista= new ArrayList<CEAlmacen>();
                        CEAlmacen oCEAlmacen;
                        
                         do
                          {
                            oCEAlmacen= new CEAlmacen();
                            oCEAlmacen.setIdAlmacen(rs.getInt(1));
                            oCEAlmacen.setDescripcion(rs.getString(2));
                            oCEAlmacen.setAbreviatura(rs.getString(3));
                            oCEAlmacen.setIdSucursal(rs.getInt(4));
                            oCEAlmacen.setSucursal(rs.getString(5));
                            oLista.add(oCEAlmacen);
                          }
                          while(rs.next());
                       }
                           cnn.close();
                 }
             return oLista;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }

}
