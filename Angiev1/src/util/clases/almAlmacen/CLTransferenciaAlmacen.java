/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.almAlmacen;

import util.clases.grlGeneral.CLRedondear;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.almAlmacen.entidad.CEIngresoProductoDetalle;
/**
 *
 * @author Luis Morales
 */
public class CLTransferenciaAlmacen 
{
    private JTable oTabla;
    private DefaultTableModel oModelo;
     
    public int colItem=0;
    public int colCodigo=1;    
    public int colProducto=2;
    public int colStock=3;
    public int colCantidad=4;
    public int colUnidadMedida=5;
    public int colPrecio=6;// costo
    public int colImporte=7;
    public int colAlm=8;



    public CLTransferenciaAlmacen(JTable oTabla)
    {
        this.oTabla = oTabla;
        cargarEventoTablaGrupos(oTabla);
        oModelo=(DefaultTableModel)oTabla.getModel();
        armarModelo();
    }


private void calcularItem()
    {
    for(int i =0 ; i<oTabla.getRowCount();i++)
    {
        oTabla.setValueAt(i+1, i, colItem);
    }
}
    public DefaultTableModel getoModelo()
    {
        return oModelo;
    }
 private void cargarEventoTablaGrupos(final JTable tbl)
    {
        ((DefaultTableModel)tbl.getModel()).addTableModelListener(
         new TableModelListener()
          {
            public void tableChanged(TableModelEvent tme)
              {
                  {
                      eventoTablaGrupos(tme,tbl);

                  }
               }
          });

    }
    private void eventoTablaGrupos(TableModelEvent tme,JTable source)
    {
             int fil= oTabla.getSelectedRow();
             int col=oTabla.getSelectedColumn();
             if(tme.getColumn()==colCantidad||tme.getColumn()==colPrecio)
              {

                CalcularCelda(fil, col);
//                CalcularSubtotales();
//                eventoDescuento();
//                calcularImportes();
             }

             if(tme.getType()==tme.INSERT||tme.getType()==tme.DELETE)
             {
                 calcularItem();
             }
               

//             }


    }

    private void armarModelo()
    {
        

    }
 
    public void setoModelo(DefaultTableModel oModelo) {
        this.oModelo = oModelo;
    }


 public void limpiarTabla()
    {
        int filas=oModelo.getRowCount();

        if(filas>0)
            for(int i=0;i<filas;i++)
            {
                oModelo.removeRow(0);
            }
    }
    public void QuitarFila()
    {
                if(oTabla.getSelectedRow()!=-1){
                oModelo.removeRow(oTabla.getSelectedRow());
                }
                else JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }

    public void QuitarFilaSel(int fila)
    {

                oModelo.removeRow(fila);


    }

public float convertirImporteMonedaExtranjeraaSoles(float precio,float tipocambio,float cantidad,float descuento,int idtipoDescuento)
{
    float precioEnSoles=0;
    float Importe=0;
    float descuentoEnval=0;
    precioEnSoles=precio/tipocambio;
    Importe=precioEnSoles*cantidad;// importe bruto
    if(idtipoDescuento==2)
    {
        descuentoEnval=(Importe*descuento)/100;
        descuentoEnval=Float.parseFloat(CLRedondear.RedondearString(descuentoEnval, 2));
    }else
    {
        descuentoEnval=descuento;
    }

    Importe=Importe-descuentoEnval;// importe neto
    
 return  Float.parseFloat(CLRedondear.RedondearString(Importe, 2));
}
public void CalcularCelda(int fil,int col)
    {

  try {
    if(fil!=-1){
    if(oTabla.getValueAt(fil, col)!=null){


        String valor=oTabla.getValueAt(fil, col).toString();
        if(valor.equals(""))
            {
            oTabla.setValueAt(""+0, fil,col);
            }
        
         if(col==colUnidadMedida||col==colCantidad||col==colPrecio)
            {
            float precio=0;
            float cantidad=0;

            if(oTabla.getValueAt(fil, colPrecio)!=null){
                precio=Float.parseFloat(oTabla.getValueAt(fil, colPrecio).toString());
            }
            if(oTabla.getValueAt(fil, colCantidad)!=null){
                cantidad=Float.parseFloat(oTabla.getValueAt(fil, colCantidad).toString());
            }

            oTabla.setValueAt(CLRedondear.RedondearString(cantidad*precio,2), fil, colImporte);//sin
            
            }
                  
                
           }
            else
            {
                 if(col==colCantidad)
                {
                 oTabla.setValueAt("0.00", fil, colImporte);
                }

            }
        
        }
    }
    catch(Exception e)
            {
        
            }
    }

public void calcularPrecio(int fila)
{     
        float cantidad=0;
        if(oTabla.getValueAt(fila, colCantidad)!=null){
           cantidad=Float.parseFloat(oTabla.getValueAt(fila, colCantidad).toString());
           }

        CEIngresoProductoDetalle oCEIngresoProductoDetalle= (CEIngresoProductoDetalle)oTabla.getValueAt(fila, colCodigo);
         if(oCEIngresoProductoDetalle!=null){
                    CalcularCelda(fila, colCantidad);   
        }        

 }
}
