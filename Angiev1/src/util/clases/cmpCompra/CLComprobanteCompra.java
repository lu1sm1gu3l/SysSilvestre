/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.cmpCompra;

import controller.grlGeneral.CCValores;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import util.clases.grlGeneral.CLRedondear;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.grlGeneral.VerificadorDeTxt;
/**
 *
 * @author Luis Morales
 */
public class CLComprobanteCompra
{
    private JTable oTabla;
    private DefaultTableModel oModelo;
    private float SubtotalNeto;
    private float SubtotalBruto;    
    private float descuentoEnSubtotal;
    private float descuento=0;
    private float TotalConDescuento;
    private Boolean sinigv=true;
    private float Montoigv;
     
    public int colItem=0;
    public int colCodigo=1;
    public int colProducto=2;
    public int colStock=3;
   public int colCantidad=4;
    public int colUnidadMedida=5;
    public int colPrecioConIGV=6;
    public int colImporSinDesc=7;
    public int colImporSinDescConIgv=8;
    public int colTipoDescuento=9;
    public int colDescuento=10;
    public int colDestValores=11;
    public int colPrecioConDesc=12;
    public int colImporConDesc=13;
    public int colExonerado=14;
    public int colAlm=15;



    private int idMoneda=1;
    private int idMonedaAnterior=1;
    private float tipoCambioAnterior=1;
    private float tipocambio=1;
    private boolean sinodescuento=true;

    private float igvActual=0;
    private float percepcionActual=0;
    private JLabel lblsubtNeto;
    private JLabel lblIgv;
    private JLabel lblISC;
    private JLabel lblMontoTotal;
    private JLabel lblSubTotalNetoSinIgv;
    private JLabel LblDescuentoEquivalente;
    private JLabel LblSubtotalBruto;
    private JLabel LblDescuentoSubtotal;
    private JLabel LblDescuentoTotal;
    private JLabel LblMontoTotalPagar;
    private JTextField TxtDescuento;
    private JLabel LblPercepcion;
    private JComboBox CbxTipoDescuentoComprobanteCompra;
    private Boolean calcularImport;
    private JCheckBox Chkpercepcion;

    public CLComprobanteCompra(JTable oTabla,JLabel lblsubtSinDesc,
                    JLabel lblIgv,JLabel lblISC,JLabel lblMontoTotal,JLabel LblMontoTotalPagar,
                    JLabel lblSubTotalSinIgv,
                    JLabel LblDescuentoEquivalente,
                    JLabel LblSubtototalBruto,JLabel LblDescuentoSubtotal,JLabel LblDescuentoTotal,
                    JTextField txtDescuento,JComboBox CbxTipoDescuentoComprobanteCompra,JLabel LblPercepcion,JCheckBox percepcion)
    {
        this.oTabla = oTabla;
        cargarEventoTablaGrupos(oTabla);
        oModelo=(DefaultTableModel)oTabla.getModel();
        this.lblsubtNeto=lblsubtSinDesc;
        this.lblIgv=lblIgv;
        this.lblISC=lblISC;
        this.lblMontoTotal=lblMontoTotal;
        this.LblMontoTotalPagar=LblMontoTotalPagar;
        this.lblSubTotalNetoSinIgv=lblSubTotalSinIgv;
        this.LblDescuentoEquivalente=LblDescuentoEquivalente;
        this.LblSubtotalBruto=LblSubtototalBruto;
        this.LblDescuentoSubtotal=LblDescuentoSubtotal;
        this.LblDescuentoTotal=LblDescuentoTotal;
        this.TxtDescuento=txtDescuento;
        this.LblPercepcion=LblPercepcion;
        this.Chkpercepcion=percepcion;

        this.CbxTipoDescuentoComprobanteCompra=CbxTipoDescuentoComprobanteCompra;
        igvActual=CCValores.obenertIGVActual();
        percepcionActual=CCValores.obenertPercpcionActual();
        sinigv=false;
        calcularImport=true;
        armarModelo();
    }
public CLComprobanteCompra()
    {
        igvActual=CCValores.obenertIGVActual();
}

    public Boolean getCalcularImport() {
        return calcularImport;
    }

    public void setCalcularImport(Boolean calcularImport) {
        this.calcularImport = calcularImport;
    }

    public float getIgvActual() {
        return igvActual;
    }

    public Boolean getConigv() {
        return sinigv;
    }

    public void setConigv(Boolean conigv) {

        this.sinigv = conigv;
        CalcularSubtotales();
        eventoDescuento();
        calcularImportes();

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
                      if(calcularImport)
                        {
                            eventoTablaGrupos(tme);
                       }

                  }
               }
          });


    }
    private void eventoTablaGrupos(TableModelEvent tme)
    {
             int fil= oTabla.getSelectedRow();
             int col=oTabla.getSelectedColumn();
             
             if(tme.getColumn()==colImporSinDesc)
             {
             CalcularPrecioDesdeImporte(fil, col);
             }
              else if(tme.getColumn() == colImporSinDescConIgv)
             {
              CalcularPrecioDesdeImporteConIGV(fil, col);
             }
             else if(tme.getColumn() == colCantidad || tme.getColumn() == colTipoDescuento || tme.getColumn() == colPrecioConIGV || tme.getColumn() == colDescuento)
              {
                CalcularCelda(fil, col);
             }
               CalcularSubtotales();
               eventoDescuento();
               calcularImportes();
             if(tme.getType()==tme.INSERT||tme.getType()==tme.DELETE)
             {
                 calcularItem();
             }

    }

       public void calcularEquivalenciaMoneda()
    {
        float precio=0;
        if(oModelo.getRowCount()>0){
            for(int i=0;i<oModelo.getRowCount();i++)
            {
                if(oTabla.getValueAt(i, colPrecioConIGV)!=null){
                        precio=Float.parseFloat(oTabla.getValueAt(i, colPrecioConIGV).toString());
                        float precio_final=(precio/tipoCambioAnterior)*tipocambio;
                        float cantidad=0;
                        if(oTabla.getValueAt(i, colCantidad)!=null)
                        {
                            cantidad=Float.parseFloat(oTabla.getValueAt(i, colCantidad).toString());
                        }
                        oTabla.setValueAt(CLRedondear.RedondearString(precio_final,4), i,colPrecioConIGV );
                        this.CalcularCelda(i, colPrecioConIGV);
                }
            }

            this.CalcularSubtotales();
            this.calcularImportes();
            this.eventoDescuento();
        }
    }

        public float ObtenerTipoCambioMoneda(JComboBox cbxMoneda)
    {
        CEMoneda oCEMoneda=(CEMoneda)cbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
            idMonedaAnterior=idMoneda;
            tipoCambioAnterior=tipocambio;
            tipocambio= oCEMoneda.getUltimoMontoVentaMN();
            idMoneda=oCEMoneda.getId_moneda();
        }

        return tipocambio;
    }
        
    public void setSinoDescuento( boolean  sinoDesc)
    {
        this.sinodescuento=sinoDesc;
    }
    public void eventoDescuento()
    {
        if(calcularImport)
      {
        if(sinodescuento){
              if(TxtDescuento.getText().equals(""))
                {
                setDescuento(0);
              LblDescuentoEquivalente.setText("0.00");
                }else{

                  CETipoDescuento oDescuento=(CETipoDescuento)CbxTipoDescuentoComprobanteCompra.getSelectedItem();
                  if(oDescuento!=null)
                  {
                          float descuentoTemp=0;
                          float descuentoaux=0;
                          if(oDescuento.getIdTipoDescuento()==2)
                          {
                               descuentoTemp=((Float.parseFloat(lblsubtNeto.getText()))*Float.parseFloat(TxtDescuento.getText()))/100;
                          }
                        else{
                            descuentoTemp=VerificadorDeTxt.convertFloat(TxtDescuento.getText());

                            }
                            descuentoaux=VerificadorDeTxt.convertFloat(TxtDescuento.getText());
                          if(Float.parseFloat(lblMontoTotal.getText())!=0){
                                  if(descuentoTemp<=Float.parseFloat(lblMontoTotal.getText())){
                                  LblDescuentoEquivalente.setText(""+CLRedondear.RedondearString(descuentoTemp,2));
                                  setDescuento(descuentoTemp);}
                                  else
                                        {
                                        JOptionPane.showMessageDialog(null,"El Descuento no puede ser mayor al Monto a pagar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        TxtDescuento.setText("0.00");
                                        TxtDescuento.selectAll();
                                        LblDescuentoEquivalente.setText("0.00");
                                        }
                            }else{
                                        if(descuentoaux!=0){
                                        JOptionPane.showMessageDialog(null,"El Monto Total es cero,No se Puede Realizar el Descuento","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                                        TxtDescuento.setText("0.00");
                                        TxtDescuento.selectAll();
                                        LblDescuentoEquivalente.setText("0.00");
                                        }

                            }

                     }
                   }
        }
      }
    }

    private void armarModelo()
    {
        

    }
 
    public void setoModelo(DefaultTableModel oModelo) {
        this.oModelo = oModelo;
    }


    public void CalcularSubtotales()
    {
        if(calcularImport)
       {
        SubtotalNeto=0;
        SubtotalBruto=0;
        descuentoEnSubtotal=0;
        Montoigv=0;

        if(oModelo.getRowCount()>0){
            for(int i=0;i<oModelo.getRowCount();i++)
                {

                float ImporteConDescuento=0;
                float ImporteSinDescuento=0;
                float ImporteSinDescuentoCongIgv=0;
                float DescuentoenImporte=0;
                float imporSubtparaIvg=0;

                 CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=(CEComprobanteCompraDetalle)oTabla.getValueAt(i, colCodigo);
                        if(oCEComprobanteCompraDetalle!=null){
                            ImporteConDescuento=VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colImporConDesc));
                             ImporteConDescuento=ImporteConDescuento+VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colExonerado));
                    }
                    ImporteSinDescuento=VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colImporSinDesc));
                    ImporteSinDescuentoCongIgv=VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colImporSinDescConIgv));
                    DescuentoenImporte=VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colDestValores));                   
                    imporSubtparaIvg=VerificadorDeTxt.convertFloat(oModelo.getValueAt(i, colImporConDesc));                    
                    
                    SubtotalNeto=SubtotalNeto+ImporteConDescuento;
                    SubtotalBruto=SubtotalBruto+ImporteSinDescuentoCongIgv;
                    descuentoEnSubtotal=descuentoEnSubtotal+DescuentoenImporte;
                    Montoigv=Montoigv+imporSubtparaIvg;
            }
        
        }
      }
    }

    public void calcularImportes()
    {
        if(calcularImport)
       {
           float IgvCalculado=0;
           float SubtotalSinIgv=0;
            if(sinigv)
            {
                this.LblSubtotalBruto.setText(CLRedondear.RedondearString(SubtotalBruto,2));
               this.LblDescuentoSubtotal.setText(CLRedondear.RedondearString(descuentoEnSubtotal,2));
                float SubSinIGV=SubtotalNeto;
               this.lblSubTotalNetoSinIgv.setText(CLRedondear.RedondearString(SubSinIGV,2));
               float NetoConigv=Montoigv;
                this.lblsubtNeto.setText(CLRedondear.RedondearString(NetoConigv,2));
                TotalConDescuento=NetoConigv-descuento;
                this.lblMontoTotal.setText(CLRedondear.RedondearString(TotalConDescuento,2));
                this.lblIgv.setText(CLRedondear.RedondearString(Montoigv,2));
                this.LblMontoTotalPagar.setText(CLRedondear.RedondearString(Float.parseFloat(lblMontoTotal.getText()),1)+"0");
                this.lblISC.setText("0.00");

                this.lblMontoTotal.setText(CLRedondear.RedondearString(TotalConDescuento,2));
                this.LblDescuentoTotal.setText(CLRedondear.RedondearString(Float.parseFloat((CLRedondear.Redondear(descuentoEnSubtotal,2)+CLRedondear.Redondear(descuento,2))+""),2));
            }else{
               SubtotalSinIgv=(Montoigv/(1+igvActual));
               IgvCalculado=Montoigv-SubtotalSinIgv;

            this.lblsubtNeto.setText(CLRedondear.RedondearString(SubtotalNeto,2));
            this.lblMontoTotal.setText(CLRedondear.RedondearString(SubtotalNeto-descuento,2));
            TotalConDescuento=SubtotalNeto-descuento;
           this.LblSubtotalBruto.setText(CLRedondear.RedondearString(SubtotalBruto,2));
           this.LblDescuentoSubtotal.setText(CLRedondear.RedondearString(descuentoEnSubtotal,2));
            this.lblIgv.setText(CLRedondear.RedondearString(IgvCalculado,2));
            this.lblISC.setText("0.00");
            this.lblSubTotalNetoSinIgv.setText(CLRedondear.RedondearString(SubtotalNeto-IgvCalculado,2));            
            this.LblDescuentoTotal.setText(CLRedondear.RedondearString(Float.parseFloat((CLRedondear.Redondear(descuentoEnSubtotal,2)+CLRedondear.Redondear(descuento,2))+""),2));
            this.lblMontoTotal.setText(CLRedondear.RedondearString(TotalConDescuento,2));
            this.LblPercepcion.setText("0.00");
            if(Chkpercepcion.isSelected()){
            this.LblPercepcion.setText(CLRedondear.Redondear(percepcionActual*TotalConDescuento,2)+"");
                }
            this.LblMontoTotalPagar.setText(CLRedondear.RedondearString(TotalConDescuento+Float.parseFloat(LblPercepcion.getText()),2));
            }
        }
    }
    public void setDescuento(float descuento) {
        this.descuento = descuento;      
        calcularImportes();
    }
    public float getSubtotalNeto()
    {
        return Float.parseFloat(""+CLRedondear.Redondear(SubtotalNeto,2));
    }
    public float getDescuento()
    {

        float DescTemp=0;
        if(!TxtDescuento.getText().isEmpty())
        {
            DescTemp=Float.parseFloat(TxtDescuento.getText());
        }
        return Float.parseFloat(""+CLRedondear.Redondear(DescTemp,2));
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

    
public void CalcularCelda(int fil,int col)
    {

  try {
    if(fil!=-1){
         if(col==colUnidadMedida||col==colCantidad||col==colTipoDescuento||col==colDescuento||col==colPrecioConIGV)
            {
             float descuentodet=0;
            float precioconIgv=0;
            float cantidad=0;

            if(oTabla.getValueAt(fil, colPrecioConIGV)!=null){
                precioconIgv=Float.parseFloat(oTabla.getValueAt(fil, colPrecioConIGV).toString());
            }
            if(oTabla.getValueAt(fil, colCantidad)!=null){
                cantidad=Float.parseFloat(oTabla.getValueAt(fil, colCantidad).toString());
            }
            if(oTabla.getValueAt(fil, colDescuento)!=null){

                descuentodet=Float.parseFloat(oTabla.getValueAt(fil, colDescuento).toString());
            }

         String ImporSinDescStrConIgv=CLRedondear.RedondearString(cantidad*precioconIgv,2);
         float ImporsinDescConIgvTemp=Float.parseFloat(ImporSinDescStrConIgv);
         float ImporsinDesSinIgv=ImporsinDescConIgvTemp/(1+igvActual);
         oTabla.setValueAt(CLRedondear.RedondearString(ImporsinDesSinIgv,2), fil, colImporSinDesc);

            if(oTabla.getValueAt(fil, colTipoDescuento)!=null){
                    CETipoDescuento oDescuento=(CETipoDescuento)oTabla.getValueAt(fil, colTipoDescuento);

                if(oDescuento!=null)
                     {

                      if(oDescuento.getIdTipoDescuento()==2)
                      {
                           descuentodet=ImporsinDescConIgvTemp*descuentodet/100;
                      }
                       }

                   }
            
            oTabla.setValueAt(CLRedondear.RedondearString(descuentodet,2), fil,colDestValores );
            descuentodet=Float.parseFloat(CLRedondear.RedondearString(descuentodet,2));
            oTabla.setValueAt(ImporSinDescStrConIgv, fil, colImporSinDescConIgv);//sin

            if(oTabla.getValueAt(fil, colCodigo)!=null)
            {
                CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=(CEComprobanteCompraDetalle)oTabla.getValueAt(fil, colCodigo);
                String imporConDesc=(CLRedondear.RedondearString((ImporsinDescConIgvTemp)-descuentodet,2));
                float precioConDesc=0;
                if(cantidad!=0){
                precioConDesc=((ImporsinDescConIgvTemp)-descuentodet)/cantidad;
                }

                if(oCEComprobanteCompraDetalle.isSiNoImpuesto())
                {
                  oTabla.setValueAt(imporConDesc, fil, colImporConDesc);
                }
                else{
                     oTabla.setValueAt(imporConDesc, fil, colExonerado);
                }
                  oTabla.setValueAt(CLRedondear.RedondearString(precioConDesc,4), fil, colPrecioConDesc);
            }

            }
        
        }
    }
    catch(Exception e)
            {
                System.out.println("Error CLComprobanteCompra-CalcularCelda"+e);
            }
    }



public void CalcularPrecioDesdeImporte(int fil,int col)
{
  try {
    if(fil!=-1){

         if(col==colImporSinDesc)
            {
             float descuentodet=0;
             float importebr=0;
             float importebrConIgv=0;
             float cantidad=0;
             float precioConIgv=0;

            importebr=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colImporSinDesc));
            cantidad=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colCantidad));     
            importebrConIgv=(float)CLRedondear.Redondear((importebr*(1+igvActual)),2);
            
            oTabla.setValueAt(importebrConIgv, fil, colImporSinDescConIgv);
            if(cantidad!=0)
            {
                precioConIgv=(float)CLRedondear.Redondear((importebrConIgv/cantidad),4);
            }
            
         oTabla.setValueAt(precioConIgv, fil, colPrecioConIGV);

         descuentodet=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colDescuento));

         String ImporSinDescStr=CLRedondear.RedondearString(importebrConIgv,2);
         float ImporsinDescTemp=Float.parseFloat(ImporSinDescStr);

            if(oTabla.getValueAt(fil, colTipoDescuento)!=null){

                    CETipoDescuento oDescuento=(CETipoDescuento)oTabla.getValueAt(fil, colTipoDescuento);

                if(oDescuento!=null)
                     {

                      if(oDescuento.getIdTipoDescuento()==2)
                      {
                           descuentodet=ImporsinDescTemp*descuentodet/100;
                      }
                       }

                   }

            oTabla.setValueAt(CLRedondear.RedondearString(descuentodet,2), fil,colDestValores );
            descuentodet=Float.parseFloat(CLRedondear.RedondearString(descuentodet,2));
          //  oTabla.setValueAt(ImporSinDescStr, fil, colImporSinDesc);//sin

            if(oTabla.getValueAt(fil, colCodigo)!=null)
            {
                CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=(CEComprobanteCompraDetalle)oTabla.getValueAt(fil, colCodigo);
                String imporConDesc=(CLRedondear.RedondearString((ImporsinDescTemp)-descuentodet,2));
                float precioConDesc=0;
                if(cantidad!=0){

                  precioConDesc  =((ImporsinDescTemp)-descuentodet)/cantidad;
                }
                

                if(oCEComprobanteCompraDetalle.isSiNoImpuesto())
                {
                  oTabla.setValueAt(imporConDesc, fil, colImporConDesc);
                }
                else{
                     oTabla.setValueAt(imporConDesc, fil, colExonerado);
                }
                  oTabla.setValueAt(CLRedondear.RedondearString(precioConDesc,4), fil, colPrecioConDesc);
            }

            }
           }

    }
    catch(Exception e)
            {
                 System.out.println("Error CLComprobanteCompra-CalcularPrecioDesdeImporte"+e);
            }
    }

public void CalcularPrecioDesdeImporteConIGV(int fil,int col)
    {

  try {
    if(fil!=-1){
         if(col==colImporSinDescConIgv)
            {
             float descuentodet=0,ImporteconIgv=0,ImportesinIgv=0,cantidad=0,precioConIgv=0;

            ImporteconIgv=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colImporSinDescConIgv));
            cantidad=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colCantidad));

             ImportesinIgv=ImporteconIgv/(1+igvActual);
             if(cantidad!=0){
                precioConIgv=(float)CLRedondear.Redondear((ImporteconIgv/cantidad),4);
                }
            oTabla.setValueAt(precioConIgv, fil, colPrecioConIGV);

         descuentodet=VerificadorDeTxt.convertFloat(oTabla.getValueAt(fil, colDescuento));
         String ImporSinDescStr=CLRedondear.RedondearString(ImporteconIgv,2);
         float ImporsinDescTemp=Float.parseFloat(ImporSinDescStr);

            if(oTabla.getValueAt(fil, colTipoDescuento)!=null){
               CETipoDescuento oDescuento=(CETipoDescuento)oTabla.getValueAt(fil, colTipoDescuento);
                if(oDescuento!=null)
                     {
                          if(oDescuento.getIdTipoDescuento()==2)
                          {
                               descuentodet=ImporsinDescTemp*descuentodet/100;
                          }
                     }

            }

            oTabla.setValueAt(CLRedondear.RedondearString(descuentodet,2), fil,colDestValores );
            descuentodet=Float.parseFloat(CLRedondear.RedondearString(descuentodet,2));
            oTabla.setValueAt(CLRedondear.RedondearString(ImportesinIgv,2), fil, colImporSinDesc);//sin

                if(oTabla.getValueAt(fil, colCodigo)!=null)
                {
                    CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=(CEComprobanteCompraDetalle)oTabla.getValueAt(fil, colCodigo);
                    String imporConDesc=(CLRedondear.RedondearString((ImporsinDescTemp)-descuentodet,2));

                    float precioConDesc=0;
                    if(cantidad>0)
                    {
                        precioConDesc=((ImporsinDescTemp) - descuentodet) / cantidad;
                    }

                    if(oCEComprobanteCompraDetalle.isSiNoImpuesto())
                    {
                      oTabla.setValueAt(imporConDesc, fil, colImporConDesc);
                      oTabla.setValueAt(null, fil, colExonerado);
                    }
                    else{
                         oTabla.setValueAt(imporConDesc, fil, colExonerado);
                         oTabla.setValueAt(null, fil, colImporConDesc);
                        }
                    oTabla.setValueAt(CLRedondear.RedondearString(precioConDesc,4), fil, colPrecioConDesc);
                }

             }
           }
    }
    catch(Exception e)
            {
                System.out.println("Error CLComprobanteCompra-CalcularPrecioDesdeImporteConIGV"+e);
            }
    }
public void calcularPrecio(int fila)
{     
 

        CEComprobanteCompraDetalle oCEComprobanteCompraDetalle= (CEComprobanteCompraDetalle)oTabla.getValueAt(fila, colCodigo);
         if(oCEComprobanteCompraDetalle!=null){
                    CalcularCelda(fila, colCantidad);   
        }        

 }
}
