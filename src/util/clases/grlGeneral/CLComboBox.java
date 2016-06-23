/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.grlGeneral;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.vtaVenta.entidad.CEConcepto;
import modelo.vtaVenta.entidad.CEPuntoVenta;

/**
 *
 * @author Luiggi
 */
public class CLComboBox {

    public static ComboBoxModel cargarCombo(ArrayList modelo)
    {
        try{
        JComboBox oCbx= new JComboBox();

        for(int i=0;i<modelo.size();i=i+1)
          {
            oCbx.addItem(modelo.get(i));
           }
        return oCbx.getModel();
        }catch(Exception e)
        {
            return null;
        }
    }

    public static ComboBoxModel cargarComboConPivot(ArrayList modelo,Object obj )
    {
        try{
        JComboBox oCbx= new JComboBox();

        for(int i=0;i<modelo.size();i=i+1)
          {
            oCbx.addItem(modelo.get(i));
           }
        return oCbx.getModel();
        }catch(Exception e)
        {
            return null;
        }
    }

    public static ComboBoxModel cargarComboAlmacenConPivot(ArrayList modelo)
    {
        try{
        JComboBox oCbx= new JComboBox();
        CEAlmacen oCEAlmacen= new CEAlmacen();
        oCEAlmacen.setIdAlmacen(0);
        oCEAlmacen.setAbreviatura("");
        oCbx.addItem(oCEAlmacen);
        for(int i=0;i<modelo.size();i=i+1)
          {
            oCbx.addItem(modelo.get(i));
           }
        return oCbx.getModel();
        }catch(Exception e)
        {
            return null;
        }
    }

    public static ComboBoxModel cargarComboConceptoConPivot(ArrayList modelo)
    {
        try{
        JComboBox oCbx= new JComboBox();
        CEConcepto oCEConcepto= new CEConcepto();
        oCEConcepto.setIdConcepto(0);
        oCEConcepto.setDescripcion("Todos");
        oCbx.addItem(oCEConcepto);
        for(int i=0;i<modelo.size();i=i+1)
          {
            oCbx.addItem(modelo.get(i));
           }
        return oCbx.getModel();
        }catch(Exception e)
        {
            return null;
        }
    }


    public static ComboBoxModel cargarComboCajaConPivot(ArrayList modelo)
    {
        try{
        JComboBox oCbx= new JComboBox();
        CEPuntoVenta oCEPuntoVenta= new CEPuntoVenta();
        oCEPuntoVenta.setIdPuntoVenta(0);
        oCEPuntoVenta.setDescrpcion("");
        oCbx.addItem(oCEPuntoVenta);
        for(int i=0;i<modelo.size();i=i+1)
          {
            oCbx.addItem(modelo.get(i));
           }
        return oCbx.getModel();
        }catch(Exception e)
        {
            return null;
        }
    }
}
