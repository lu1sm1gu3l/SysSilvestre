/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;
import modelo.grlGeneral.entidad.CEComponente;
import modelo.vtaVenta.entidad.CEConcepto;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CESucursal;
import modelo.vtaVenta.entidad.CETipoComprobante;
import modelo.vtaVenta.entidad.CETipoDescuento;

/**
 *
 * @author Katya
 */
public class CLCargarCombo {


    public static CETipoDescuento buscarIdTipoDescuento(ComboBoxModel oModelo, int idTipoDescuento)
    {
        int id=0;
    for(int i=0; i<oModelo.getSize();i++)
    {
        CETipoDescuento oCETipoDescuento=(CETipoDescuento)oModelo.getElementAt(i);
        if(oCETipoDescuento.getIdTipoDescuento()==idTipoDescuento)
        {
            id=i;
            return oCETipoDescuento;
        }
    }
    return null;
    }
    public static void buscarIdTipoDescuento(JComboBox oComboBox, int idTipoDescuento)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CETipoDescuento oCETipoDescuento=(CETipoDescuento)oComboBox.getModel().getElementAt(i);
        if(oCETipoDescuento.getIdTipoDescuento()==idTipoDescuento)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }

    }
    public static void buscarIdTipoComprobante(JComboBox oComboBox, int idTipoComprabante)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)oComboBox.getModel().getElementAt(i);
        if(oCETipoComprobante.getIdTipoComprobante()==idTipoComprabante)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }

    }
    public static void buscarIdTipoIngresoSalida(JComboBox oComboBox, int idTipoIngresoSalida)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)oComboBox.getModel().getElementAt(i);
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==idTipoIngresoSalida)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }

    }

     public static void buscarIdSucursal(JComboBox oComboBox, int idSucursal)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CESucursal oCESucursal=(CESucursal)oComboBox.getModel().getElementAt(i);
        if(oCESucursal.getIdSucursal()==idSucursal)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }
    }
     public static void buscarIdMoneda(JComboBox oComboBox, int idMoneda)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CEMoneda oCEMoneda=(CEMoneda)oComboBox.getModel().getElementAt(i);
        if(oCEMoneda.getId_moneda()==idMoneda)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }
    }
    public static void buscarIdAlmacen(JComboBox oComboBox, int idalmacen)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CEAlmacen oCEAlmacen=(CEAlmacen)oComboBox.getModel().getElementAt(i);
        if(oCEAlmacen.getIdAlmacen()==idalmacen)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }

    }


    public static void buscarIdConcepto(JComboBox oComboBox, int idTipoConcepto)
    {

    for(int i=0; i<oComboBox.getModel().getSize();i++)
    {
        CEConcepto oCETipoComprobante=(CEConcepto)oComboBox.getModel().getElementAt(i);
        if(oCETipoComprobante.getIdConcepto()==idTipoConcepto)
        {
            oComboBox.setSelectedIndex(i);
            return;
        }
    }

    }

}
