/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgBusquedaProducto.java
 *
 * Created on 08/07/2011, 12:12:00 PM
 */

package view.almAlmacen;

import controller.almAlmacen.CCProducto;
import controller.almAlmacen.CCAlmacenProducto;
import controller.almAlmacen.CCProductoPrecio;
import controller.almAlmacen.CCUnidadMedidaProducto;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import table.ArrayListTableModel;
import util.clases.almAlmacen.CLPrecio;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;


public class DlgBusquedaProducto extends DialogoPadre
{
   private CEAlmacenProducto oCEAlmacenProducto;
   private List<CEProductoPrecio> listaPrecioProducto;
   private List<CEPedidoDetalle> lstPedidoDetalle;
   private CEMoneda oCEMoneda;
   private float TipoCambio;
   private boolean conPercepcion;
   public DlgBusquedaProducto(java.awt.Frame parent, boolean modal,CEMoneda vMoneda,float vTipoCambio)
   {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        armarModelo();
        oCEMoneda=vMoneda;
        TipoCambio=vTipoCambio;
        LblMoneda.setText(""+oCEMoneda.getDescripcion());
        LblTipoCambio.setText(""+TipoCambio);
        TxtProducto.requestFocus();
        TxtPU.setDocument(new VerificadorDeTxt("Numero",0,TxtPU));
        TxtCantidad.setDocument(new VerificadorDeTxt("Numero",0,TxtCantidad));
        LblMsj.setText("");
        conPercepcion=false;
        TblPrecios.getColumnModel().getColumn(3).setHeaderValue("Precio "+oCEMoneda.getDescripcion().toLowerCase());
                            TblUnidad.updateUI();
        lblAbrevMon.setText(oCEMoneda.getAbreviatura());
        if(oCEMoneda.getId_moneda()==1)
        {
          TblPrecios.getColumnModel().getColumn(2).setMaxWidth(0);
          TblPrecios.getColumnModel().getColumn(2).setMinWidth(0);
          TblPrecios.getColumnModel().getColumn(2).setPreferredWidth(0);
        }
        TblUnidad.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {


                eventoSelecUnidad();

            }
        });
    }
   public void setConPercepcion(boolean bol)
    {
       conPercepcion=bol;
   }
    public void setCajaTexto(String pTxtProducto)
    {
        if(pTxtProducto!=null){
        this.TxtProducto.setText(pTxtProducto);
        this.TxtProducto.selectAll();
        if(!this.TxtProducto.getText().equals("")){
            List<CEProducto> listaProducto=null;
            if(!conPercepcion){
             listaProducto=CCProducto.consultarProducto(this.TxtProducto.getText());
            }
            else
            {
             listaProducto=CCProducto.consultarProductoconPercepcion(this.TxtProducto.getText());
            }
            AgregarProducto(listaProducto);}
        }
    }
    public void setListaPedido(List<CEPedidoDetalle> pLstPedidoDetalle)
    {

        this.lstPedidoDetalle=pLstPedidoDetalle;

    }
     private void armarModelo()
    {
          
      TableColumn oColPr = TblProducto.getColumnModel().getColumn(0);
      oColPr.setPreferredWidth(15);
      oColPr = TblProducto.getColumnModel().getColumn(1);
      oColPr.setPreferredWidth(380);
      oColPr = TblProducto.getColumnModel().getColumn(2);
      oColPr.setPreferredWidth(90);
      oColPr = TblProducto.getColumnModel().getColumn(3);
      oColPr.setPreferredWidth(35);
      oColPr = TblProducto.getColumnModel().getColumn(4);
      oColPr.setPreferredWidth(35);

      TableColumn oColUn = TblUnidad.getColumnModel().getColumn(0);
      oColUn.setPreferredWidth(50);
      oColUn = TblUnidad.getColumnModel().getColumn(1);
      oColUn.setPreferredWidth(200);

      TblUnidad.getColumnModel().getColumn(0).setMaxWidth(0);
      TblUnidad.getColumnModel().getColumn(0).setMinWidth(0);
      TblUnidad.getColumnModel().getColumn(0).setPreferredWidth(0);

     TblUnidad.getColumnModel().getColumn(2).setMinWidth(90);
//      TblUnidad.getColumnModel().getColumn(2).setPreferredWidth(0);
//      TblUnidad.getColumnModel().getColumn(2).setMaxWidth(0);

      TblAlmacen.getColumnModel().getColumn(3).setMaxWidth(0);
      TblAlmacen.getColumnModel().getColumn(3).setMinWidth(0);
      TblAlmacen.getColumnModel().getColumn(3).setPreferredWidth(0);

      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblPrecios.getColumnModel().getColumn(2).setCellRenderer(tcr);

    }
     private void AgregarProducto(List<CEProducto> oLitaProducto)
    {        
        if(oLitaProducto!=null)
        {
            int i=0;
                for (CEProducto CEProducto : oLitaProducto)
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(""+(i+1));
                        oArrayList.add(CEProducto);
                        oArrayList.add(CEProducto.getCodigo());
                        if(CEProducto.isSiNoImpuesto()){
                        oArrayList.add("SI");}
                         else{
                             oArrayList.add("NO");
                              }
                        if(CEProducto.isPercepcion()){
                        oArrayList.add("SI");}
                         else{
                             oArrayList.add("NO");
                              }
                        ((ArrayListTableModel)TblProducto.getModel()).addRow(oArrayList);
                        i++;
                }
                LblItem.setText(""+i);
                pintarTabla();

      }
    }
private void pintarTabla(){
    for (int i =0; i<TblProducto.getColumnCount();i++) {
    TblProducto.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(table.getValueAt(row,4)!=null)
                {
                    String Percepcion=(table.getValueAt(row,4)+"");
                    if(Percepcion.equals("SI"))
                    {
                        if(isSelected)
                        {
                         cell.setForeground(table.getSelectionForeground());
                         cell.setForeground(new Color(183,0,0));
                        }
                        else
                        {
                            cell.setBackground(table.getBackground());
                            cell.setForeground(new Color(153,0,0));
                        }
                        return cell;
                    }
                }
                if(isSelected)
                {
                    cell.setForeground(table.getSelectionForeground());
                    cell.setBackground(table.getSelectionBackground());
                }
                else
                {
                    cell.setBackground(table.getBackground());
                    cell.setForeground(table.getForeground());
                }
                return cell;
            }
        });

   }
}
      private void AgregarDetalles(long idProducto)
    {
          
             
              listaPrecioProducto = new ArrayList<CEProductoPrecio>();
                List<CEUnidadMedidaProducto> listaUnidad=CCUnidadMedidaProducto.consultarEquivalenciaPorProducto(idProducto);
                List<CEAlmacenProducto> listaAlamcen=CCAlmacenProducto.consultarAlamcenPorProductoPedido(idProducto);
                listaPrecioProducto=CCProductoPrecio.consultarPrecioProducto(idProducto);
             if(listaUnidad!=null)
            {
                 limpiarTablaAlmacen();
                 limpiarTablaUnidad();
                 limpiarTablaPrecios();
                 TxtCantidad.setText("0");
                 TxtPTotal.setText("0");
                 TxtPU.setText("0");
                 int i=0;
                for (CEUnidadMedidaProducto oCEUnidadMedidaProducto : listaUnidad)
                {
                    ArrayList oArrayList=new ArrayList();
                        oArrayList.add(false);
                        oArrayList.add(oCEUnidadMedidaProducto);
                        oArrayList.add(oCEUnidadMedidaProducto.getEquivalenciaConBase());
                        ((ArrayListTableModel)TblUnidad.getModel()).addRow(oArrayList);

                        if(oCEUnidadMedidaProducto.getSiNoUnidadBase()==1){
                            TblUnidad.getColumnModel().getColumn(2).setHeaderValue("Equiv. en "+oCEUnidadMedidaProducto);
                            TblUnidad.updateUI();
                            }
                        if(oCEUnidadMedidaProducto.getSiNoUnidadMasComercial()==1){
                            TblUnidad.changeSelection(i, 0, false, false);
                            }
                        i++;
                }
                if(TblUnidad.getRowCount()>0)
                {
                for (CEAlmacenProducto oCEAlmacenProductoTemp : listaAlamcen)
                {
                    Vector oVector=new Vector();
                        oVector.add(false);
                        oVector.add(oCEAlmacenProductoTemp);
                        oVector.add(oCEAlmacenProductoTemp.getStockReal());
                        oVector.add(oCEAlmacenProductoTemp.getStockReal());
                        ((DefaultTableModel)TblAlmacen.getModel()).addRow(oVector);
                        
                }
                if(TblAlmacen.getRowCount()>0)
                {
                TblAlmacen.setRowSelectionInterval(0,0);
                
                TxtCantidad.setEditable(true);
                }
                else
                {
                    TxtCantidad.setEditable(false);
                    TxtPU.setEditable(false);
                }
                
                TblUnidad.requestFocus();
                TblUnidad.setRowSelectionInterval(0,0);
                CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);
                calcularStock();
                
                cargarTablaPrecio(oCEUnidadMedidaProductoTemp.getIdUnidadMedidaVenta());
                }
            }

        

    }


      private float VerificarProductoSiExisteEnPedido(long idProducto)
    {
          if(TblUnidad.getSelectedRow()!=-1){
              if(lstPedidoDetalle!=null){
                       

                      for(CEPedidoDetalle oCEPedidoDetalleTem: lstPedidoDetalle)
                      {
                          if(oCEPedidoDetalleTem.getIdProducto()==idProducto)
                          {

                              ActualizarStockPorUnidad(oCEPedidoDetalleTem);

                          }
                      }
              }
        }
          return 0;
      }


     
      private void ActualizarStockPorUnidad(CEPedidoDetalle oCEPedidoDetalleExistente)
        {
            for(int i=0;i<TblAlmacen.getRowCount();i++)
            {
                 CEAlmacenProducto oCEAlmacenProductoTemp=(CEAlmacenProducto)TblAlmacen.getValueAt(i, 1);
                if(oCEAlmacenProductoTemp.getIdAlmacen()==oCEPedidoDetalleExistente.getIdAlmacen())
                {
                    float stockReal=Float.parseFloat(TblAlmacen.getValueAt(i, 3).toString());
                    float StockDisponible=stockReal+(oCEPedidoDetalleExistente.getCantidadBaseAnte());
                    TblAlmacen.setValueAt(StockDisponible,i, 3);
                    TblAlmacen.setValueAt(StockDisponible,i, 2);

                }
            }
        }
 private List<CEProductoPrecio> listaPrecioProductoTemp;
      private void cargarTablaPrecio(int idunidad)
      {
          limpiarTablaPrecios();
          String Unidad="Precios por "+TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1)+"";
          PnlPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), Unidad, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12)));

          if(listaPrecioProducto!=null){
              listaPrecioProductoTemp=new ArrayList<CEProductoPrecio>();

          for(CEProductoPrecio oCEProductoPrecioTemp : listaPrecioProducto)
          {
              if(oCEProductoPrecioTemp.getIdUnidadMedidaVenta()==idunidad)
              {
                  Vector oArrayList=new Vector();
                        oArrayList.add(""+oCEProductoPrecioTemp.getRangoInicial());
                        oArrayList.add(""+oCEProductoPrecioTemp.getRangoFinal());
                        oArrayList.add(""+CLRedondear.RedondearString(oCEProductoPrecioTemp.getPrecioUnitario(),4));
                        oArrayList.add(""+CLRedondear.RedondearString(oCEProductoPrecioTemp.getPrecioUnitario()*TipoCambio,4));
                        ((DefaultTableModel)TblPrecios.getModel()).addRow(oArrayList);
                        listaPrecioProductoTemp.add(oCEProductoPrecioTemp);
              }
          }
        }
         
      }


      private void limpiarTabla()
    {
         int size =TblProducto.getRowCount();
           for(int i=0;i<size;i++)
           {
                 ((ArrayListTableModel)TblProducto.getModel()).removeRow(0);
           }
    }
      private void limpiarTablaUnidad()
    {
         int size =TblUnidad.getRowCount();
           for(int i=0;i<size;i++)
           {
                 ((ArrayListTableModel)TblUnidad.getModel()).removeRow(0);
           }
    }
      private void limpiarTablaPrecios()
    {
         int size =TblPrecios.getRowCount();
           for(int i=0;i<size;i++)
           {
                 ((DefaultTableModel)TblPrecios.getModel()).removeRow(0);
           }
    }
      private void limpiarTablaAlmacen()
    {
         int size =TblAlmacen.getRowCount();
           for(int i=0;i<size;i++)
           {
                 ((DefaultTableModel)TblAlmacen.getModel()).removeRow(0);
           }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblUnidad = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblAlmacen = new javax.swing.JTable();
        PnlPrecio = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPrecios = new javax.swing.JTable();
        label7 = new Label.Label();
        LblProductoSel = new Label.Label();
        TxtCantidad = new javax.swing.JTextField();
        label9 = new Label.Label();
        label10 = new Label.Label();
        label12 = new Label.Label();
        TxtPTotal = new Label.Label();
        TxtPU = new javax.swing.JTextField();
        LblMsj = new Label.Label();
        lblAbrevMon = new Label.Label();
        jPanel1 = new javax.swing.JPanel();
        LblItem = new Label.Label();
        TxtProducto = new TextField.JTxtNinguno();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProducto = new javax.swing.JTable();
        label5 = new Label.Label();
        label6 = new Label.Label();
        btnCancelar1 = new BotonesABM.BtnCancelar();
        btnAceptar1 = new BotonesABM.BtnAceptar();
        label8 = new Label.Label();
        LblMoneda = new Label.Label();
        label14 = new Label.Label();
        LblTipoCambio = new Label.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Producto");

        jPanel5.setBackground(new java.awt.Color(254, 254, 239));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "[Detalle]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(254, 254, 239));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Unidades de Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TblUnidad.setFont(new java.awt.Font("Verdana", 0, 11));
        TblUnidad.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "sel.","Unidad","Equiv. UMB"
            }
        ){
            Class[] types = new Class [] {
                java.lang.Boolean.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblUnidad.setRowHeight(20);
        TblUnidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUnidadMouseClicked(evt);
            }
        });
        TblUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblUnidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblUnidadKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TblUnidad);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(254, 254, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Stock de Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TblAlmacen.setFont(new java.awt.Font("Verdana", 0, 11));
        TblAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.", "Almacen", "Stock Real", "Stock Ub"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblAlmacen.setRowHeight(20);
        TblAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblAlmacenMouseClicked(evt);
            }
        });
        TblAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblAlmacenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblAlmacenKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TblAlmacen);
        TblAlmacen.getColumnModel().getColumn(0).setMinWidth(0);
        TblAlmacen.getColumnModel().getColumn(0).setPreferredWidth(0);
        TblAlmacen.getColumnModel().getColumn(0).setMaxWidth(0);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        PnlPrecio.setBackground(new java.awt.Color(254, 254, 239));
        PnlPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Precios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TblPrecios.setFont(new java.awt.Font("Verdana", 1, 16));
        TblPrecios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rango Inicial", "Rango Final", "Precio Soles", "Precio Dolares"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPrecios.setEnabled(false);
        TblPrecios.setRowHeight(25);
        jScrollPane2.setViewportView(TblPrecios);

        javax.swing.GroupLayout PnlPrecioLayout = new javax.swing.GroupLayout(PnlPrecio);
        PnlPrecio.setLayout(PnlPrecioLayout);
        PnlPrecioLayout.setHorizontalGroup(
            PnlPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        PnlPrecioLayout.setVerticalGroup(
            PnlPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        label7.setText("Producto :");
        label7.setFont(new java.awt.Font("Verdana", 1, 12));

        LblProductoSel.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtCantidad.setEditable(false);
        TxtCantidad.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtCantidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtCantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TxtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtCantidadFocusGained(evt);
            }
        });
        TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCantidadKeyReleased(evt);
            }
        });

        label9.setText("Cantidad :");
        label9.setFont(new java.awt.Font("Verdana", 1, 12));

        label10.setText("Precio Unit :");
        label10.setFont(new java.awt.Font("Verdana", 1, 12));

        label12.setText("Precio Total :");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtPTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtPTotal.setText("0.00");
        TxtPTotal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtPU.setEditable(false);
        TxtPU.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtPU.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtPU.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TxtPU.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtPUFocusGained(evt);
            }
        });
        TxtPU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtPUKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPUKeyReleased(evt);
            }
        });

        LblMsj.setForeground(new java.awt.Color(153, 0, 0));
        LblMsj.setText("La cantidad ingresada no tiene un precio establecido, Ingrese un precio");
        LblMsj.setFont(new java.awt.Font("Verdana", 1, 12));

        lblAbrevMon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAbrevMon.setText("s/.");
        lblAbrevMon.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAbrevMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PnlPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblProductoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblMsj, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblProductoSel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAbrevMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblMsj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(254, 254, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        LblItem.setText("0");
        LblItem.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtProducto.setTamanio(200);
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProductoKeyReleased(evt);
            }
        });

        TblProducto.setFont(new java.awt.Font("Verdana", 0, 11));
        TblProducto.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {"Nº","Producto",
                "Codigo","Impuesto","Percepción"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.Object.class,
                java.lang.String.class,java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblProducto.setRowHeight(20);
        TblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProductoMouseClicked(evt);
            }
        });
        TblProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblProductoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblProducto);

        label5.setText("Nombre :");
        label5.setFont(new java.awt.Font("Verdana", 1, 12));

        label6.setText("Productos Encontrados :");
        label6.setFont(new java.awt.Font("Verdana", 1, 12));

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        label8.setText("Moneda :");
        label8.setFont(new java.awt.Font("Verdana", 1, 12));

        LblMoneda.setForeground(new java.awt.Color(102, 0, 0));
        LblMoneda.setText("Soles");
        LblMoneda.setFont(new java.awt.Font("Verdana", 1, 12));

        label14.setText("Tipo Cambio :");
        label14.setFont(new java.awt.Font("Verdana", 1, 12));

        LblTipoCambio.setForeground(new java.awt.Color(102, 0, 0));
        LblTipoCambio.setText("10.0000");
        LblTipoCambio.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void TxtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyReleased

        
        if(TblProducto.getRowCount()>0){
        if(evt.getKeyCode()==evt.VK_DOWN) {
            int size =TblProducto.getRowCount();
            if(size>0) {
                TblProducto.requestFocus();
                TblProducto.setRowSelectionInterval(0,0);
            }
        }
        }
      if(evt.getKeyCode()==evt.VK_ENTER) {
          limpiarTabla();
          LimpiarDetalle();
          
              List<CEProducto> listaProducto=null;
              if(!conPercepcion){
                  if(!TxtProducto.getText().equals("")){
                listaProducto=CCProducto.consultarProducto(this.TxtProducto.getText());
                  }
                }
                else
                {
                 listaProducto=CCProducto.consultarProductoconPercepcion(this.TxtProducto.getText());
                }
                
                AgregarProducto(listaProducto);
      }
        

    }//GEN-LAST:event_TxtProductoKeyReleased

    private void TblProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProductoKeyPressed

           if(TblProducto.getRowCount()>0){
       TblProducto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblProducto.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                SeleccionarProducto();
            }
        });

              if(evt.getKeyCode()==evt.VK_SPACE) {
              SeleccionarProducto();
        }
        if(evt.getKeyCode()==evt.VK_BACK_SPACE) {
              TxtProducto.requestFocus();
        }
        }
     
    }//GEN-LAST:event_TblProductoKeyPressed

    private void SeleccionarProducto()
    {
        CEProducto oCEProducto=(CEProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
         AgregarDetalles(oCEProducto.getIdProducto());
         LblProductoSel.setText(oCEProducto.getDescripcion());
         VerificarProductoSiExisteEnPedido(oCEProducto.getIdProducto());
    }
    private boolean validar()
    {
        float StockReal=0;
        if(TblProducto.getSelectedRow()==-1){
           JOptionPane.showMessageDialog(this,"Seleccione un producto","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        if(TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 2)!=null)
         {
//          StockReal=VerificadorDeTxt.convertFloat(TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 2).toString());
//          if(VerificadorDeTxt.convertFloat(TxtCantidad.getText())>StockReal)
//            {
//             JOptionPane.showMessageDialog(this,"La cantidad Ingresada no debe ser mayor al stock disponible","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
//             TxtCantidad.requestFocus();
//             TxtCantidad.selectAll();
//             return false;
//            }
        }
        if(VerificadorDeTxt.convertFloat(TxtCantidad.getText())==0)
        {
            JOptionPane.showMessageDialog(this,"Ingrese Cantidad","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            TxtCantidad.requestFocus();
            return false;
        }
        if(VerificadorDeTxt.convertFloat(TxtPU.getText())==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese Precio","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtPU.requestFocus();
            return false;
        }
        if(VerificadorDeTxt.convertFloat(TxtCantidad.getText())==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese Precio","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtPU.requestFocus();
            return false;
        }        
        return true;
    }
     private boolean ExisteEnPedido(long idProducto)
    {
          if(TblUnidad.getSelectedRow()!=-1){
              if(lstPedidoDetalle!=null){

                      CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);
                      for(CEPedidoDetalle oCEPedidoDetalleTem: lstPedidoDetalle)
                      {
                          if(oCEPedidoDetalleTem.getIdProducto()==idProducto&&
                             oCEUnidadMedidaProductoTemp.getIdUnidadMedidaVenta()==oCEPedidoDetalleTem.getIdUnidadMedidaVenta() )
                          {
                            if(oCEPedidoDetalleTem.getIdAbm()==4)
                              return false;
                            if(oCEPedidoDetalleTem.getIdAbm()!=3)
                              return true;                            

                          }
                      }
              }
        }
          return false;
      }
    private boolean agregarCantidadPedido= false;
    private void SeleccionarRegistro()
    {
        if(validar()){
        if(TblProducto.getSelectedRow()!=-1&&TblUnidad.getSelectedRow()!=-1&&TblAlmacen.getSelectedRow()!=-1){

                CEProducto oCEProductoTemp=(CEProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
                CEAlmacenProducto oCEAlmacenProductoTemp=(CEAlmacenProducto)TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 1);
                CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);

             //   boolean isProductoRepetido=Boolean.parseBoolean(TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 0)+"");
                boolean isProductoRepetido=ExisteEnPedido(oCEProductoTemp.getIdProducto());
                oCEAlmacenProducto = new CEAlmacenProducto();
                oCEAlmacenProducto.setIdProducto(oCEProductoTemp.getIdProducto());
                oCEAlmacenProducto.setDescripcion(oCEProductoTemp.getDescripcion());
                oCEAlmacenProducto.setIdAlmacen(oCEAlmacenProductoTemp.getIdAlmacen());
                oCEAlmacenProducto.setAlmacen(oCEAlmacenProductoTemp.getDescripcion());
                oCEAlmacenProducto.setStockReal(Float.parseFloat(TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 2).toString()));
                oCEAlmacenProducto.setIdUnidadBase(oCEUnidadMedidaProductoTemp.getIdUnidadMedidaVenta());
                oCEAlmacenProducto.setUnidad_medida(oCEUnidadMedidaProductoTemp.getTipoUnidad());
                oCEAlmacenProducto.setCodigo(oCEProductoTemp.getCodigo());
                oCEAlmacenProducto.setSiNoImpuesto(oCEProductoTemp.isSiNoImpuesto());
                oCEAlmacenProducto.setCantidad(VerificadorDeTxt.convertFloat(TxtCantidad.getText()));
                oCEAlmacenProducto.setPrecio(VerificadorDeTxt.convertFloat(TxtPU.getText()));
                oCEAlmacenProducto.setPrecio_sin_redon(precio_sin_redon);
                oCEAlmacenProducto.setPercepcion(oCEProductoTemp.isPercepcion());
                
                float Equivalencia=0;
                if(TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 2)!=null)
                 {
                   Equivalencia=VerificadorDeTxt.convertFloat(TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 2).toString());
                 }               
                oCEAlmacenProducto.setEquivalenciaUMB(Equivalencia);
                if(!isProductoRepetido){
                
                dispose();
                }else{
                    int rspt= JOptionPane.showConfirmDialog(this,"<html><center>El producto "+oCEProductoTemp.getDescripcion()+" en "+oCEUnidadMedidaProductoTemp.getTipoUnidad()+"<BR> en el "
                                                    +oCEAlmacenProductoTemp+" ya se registro<BR> Desea adicionarle mas cantidades" +" </center></html>","Aviso",
                                                    JOptionPane.YES_NO_OPTION);
                        if(rspt==JOptionPane.OK_OPTION)
                        {
                            agregarCantidadPedido=true;
                            dispose();
                        }
                    }
              }else
                {
                    JOptionPane.showMessageDialog(this,"Selecionar una unidad de Producto");
                }
        }
    }
    public boolean getisagregarCantidadPedido()
    {
        return agregarCantidadPedido;
    }
   
    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
     
        this.ajustarPrecio(VerificadorDeTxt.convertFloat(TxtPU.getText()),VerificadorDeTxt.convertFloat(TxtCantidad.getText()));
        SeleccionarRegistro();
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void TblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProductoMouseClicked

       
        
        if(evt.getClickCount()==2){
              SeleccionarProducto();
                }
        if(evt.getClickCount()==1){
              LimpiarDetalle();
                }
       
    }//GEN-LAST:event_TblProductoMouseClicked
private void calcularStock()
    {
    if(TblUnidad.getSelectedRow()!=-1){
        if(TblAlmacen.getRowCount()>0)
        {
            for(int i=0;i<TblAlmacen.getRowCount();i++){
            float stock=Float.parseFloat(TblAlmacen.getValueAt(i, 3).toString());
            float Equivalencia=Float.parseFloat(TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 2).toString());

            TblAlmacen.setValueAt(CLRedondear.Redondear((stock/Equivalencia),2), i, 2);
            }
        }
        
    }

    
}
private void LimpiarSelTblAlmacen()
    {
    if(TblAlmacen.getRowCount()>0)
    {
        for(int i=0;i<TblAlmacen.getRowCount();i++){

       // TblAlmacen.setValueAt(false, i, 0);
        }
    }

}

    private void TblUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblUnidadKeyPressed
          if(TblUnidad.getRowCount()>0){
            TblUnidad.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblUnidad.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

               TxtCantidad.requestFocus();
            }});
              if(evt.getKeyCode()==evt.VK_RIGHT) {
                TblAlmacen.requestFocus();
                TblAlmacen.changeSelection(0,0, false, false);
                calcularStock();
                }
              if(evt.getKeyCode()==evt.VK_SPACE||evt.getKeyCode()==evt.VK_BACK_SPACE) {
                TblProducto.requestFocus();
                TblProducto.changeSelection(TblProducto.getSelectedRow(),0, false, false);
                }
        }
    }//GEN-LAST:event_TblUnidadKeyPressed

    private void TblAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblAlmacenKeyPressed
   if(TblAlmacen.getRowCount()>0){

            TblAlmacen.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblAlmacen.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                SeleccionarRegistro();
            }
            });
              if(evt.getKeyCode()==evt.VK_LEFT) {
                 if(TblUnidad.getRowCount()>0){
                TblUnidad.requestFocus();
                TblUnidad.changeSelection(TblUnidad.getSelectedRow(),0, false, false);
                 }
             }
               if(evt.getKeyCode()==evt.VK_SPACE) {
                TblProducto.requestFocus();
                TblProducto.changeSelection(TblProducto.getSelectedRow(),0, false, false);
                }
        }
    }//GEN-LAST:event_TblAlmacenKeyPressed

    private void TblUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblUnidadKeyReleased

        if(evt.getKeyCode()==evt.VK_UP|| evt.getKeyCode()==evt.VK_DOWN) {
                eventoSelecUnidad();
                calcularPreciototal();
               }
    }//GEN-LAST:event_TblUnidadKeyReleased

    private void TblProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProductoKeyReleased

        if(evt.getKeyCode()==evt.VK_UP|| evt.getKeyCode()==evt.VK_DOWN|| evt.getKeyCode()==evt.VK_TAB) {
                LimpiarDetalle();
               }
        if(evt.getKeyCode()==evt.VK_UP) {
           // buscarCliente();
            int row=TblProducto.getSelectedRow();

               if(row!=-1)
               {
                   if(row==0)
                   {
                      // TxtProducto.requestFocus();
                      // LimpiarDetalle();
                   }
               }

        }

    }//GEN-LAST:event_TblProductoKeyReleased

    private void TblUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUnidadMouseClicked
                if(evt.getClickCount()==2){
                SeleccionarRegistro();
                }else{
                calcularPreciototal();
                eventoSelecUnidad();}
    }//GEN-LAST:event_TblUnidadMouseClicked
private void eventoSelecUnidad()
    {
        LimpiarSelTblAlmacen();
        if(TblUnidad.getSelectedRow()!=-1&&TblProducto.getSelectedRow()!=-1){        
        calcularStock();
        CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);
        cargarTablaPrecio(oCEUnidadMedidaProductoTemp.getIdUnidadMedidaVenta());
        }
}
    private void TblAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblAlmacenMouseClicked
       if(evt.getClickCount()==2){
        SeleccionarRegistro();
        }
    }//GEN-LAST:event_TblAlmacenMouseClicked

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
      dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void TblAlmacenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblAlmacenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TblAlmacenKeyReleased

    private void TxtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyPressed


        if(evt.getKeyCode()==evt.VK_UP)
        {
           TblUnidad.requestFocus();
           TblUnidad.changeSelection(0, 0, false, false);
        }
      if(evt.getKeyCode()==evt.VK_ENTER)
        {
          if(VerificadorDeTxt.convertFloat(TxtCantidad.getText()) != 0)
          {
             if(VerificadorDeTxt.convertFloat(TxtPU.getText()) != 0)
             {

                this.ajustarPrecio(VerificadorDeTxt.convertFloat(TxtPU.getText()),VerificadorDeTxt.convertFloat(TxtCantidad.getText()));
                
                 //btnAceptar1.requestFocus();
                SeleccionarRegistro();
             }
                else
               TxtPU.requestFocus();
          }else
          {
              LblMsj.setText("Ingrese Cantidad!");
          }
        }
    }//GEN-LAST:event_TxtCantidadKeyPressed

    float precio_sin_redon=0;
    private void ajustarPrecio(float precioNuevo,float cantidad)
{
    if(precioNuevo!=0&&cantidad!=0){
    float monto=(float)precioNuevo*cantidad;
    String MontoStr=CLRedondear.RedondearEnteroInmeditoSup(monto);
    monto=VerificadorDeTxt.convertFloat(MontoStr);
    float precioAjustado=monto/cantidad;
    precio_sin_redon=precioNuevo;
    TxtPU.setText(""+CLRedondear.RedondearString(precioAjustado,4));
    TxtPTotal.setText(""+CLRedondear.RedondearEnteroInmeditoSup(CLPrecio.calcularPrecioTotal(cantidad, precioAjustado)));
    }
}
    private void TxtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyReleased

        calcularPreciototal();
    }//GEN-LAST:event_TxtCantidadKeyReleased

    private void TxtPUFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtPUFocusGained
        TxtPU.selectAll();
    }//GEN-LAST:event_TxtPUFocusGained

    private void TxtPUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPUKeyPressed
       if(evt.getKeyCode()==evt.VK_ENTER)
        {
           this.ajustarPrecio(VerificadorDeTxt.convertFloat(TxtPU.getText()),VerificadorDeTxt.convertFloat(TxtCantidad.getText()));
          
             btnAceptar1.requestFocus();
          
       }
    }//GEN-LAST:event_TxtPUKeyPressed

    private void TxtPUKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPUKeyReleased
        //calcularPreciototal();
    }//GEN-LAST:event_TxtPUKeyReleased

    private void TxtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtCantidadFocusGained
       TxtCantidad.selectAll();
    }//GEN-LAST:event_TxtCantidadFocusGained

    private void calcularPreciototal()
    {
        
        float cantidad=0;

            cantidad=VerificadorDeTxt.convertFloat(TxtCantidad.getText());
        

        float precio=TipoCambio*CLPrecio.obtenerPrecio(listaPrecioProductoTemp,cantidad);
        TxtPU.setText(precio+"");

        if(precio==0&&cantidad!=0)
        {
            TxtPU.setEditable(true);
            LblMsj.setText("La cantidad ingresada no tiene un precio establecido, Ingrese un precio");

        }else
        {
            TxtPU.setEditable(false);
            LblMsj.setText("");
        }
        TxtPTotal.setText(""+CLPrecio.calcularPrecioTotal(cantidad, precio));
    }
    private void LimpiarDetalle()
    {
          LblProductoSel.setText("");
       
        if(TblUnidad.getRowCount()>0)
        {
            TxtCantidad.setText("0");
            TxtPTotal.setText("0");
            TxtPU.setText("");
            LblMsj.setText("");
            limpiarTablaUnidad();
            limpiarTablaAlmacen();
            limpiarTablaPrecios();
            PnlPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Precios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12)));

        }
    }



  public CEAlmacenProducto getProductoAlmacen()
    {
      return oCEAlmacenProducto;
  }
   public List<CEProductoPrecio> getListaPrecio()
    {
      return listaPrecioProducto;
  }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Label.Label LblItem;
    private Label.Label LblMoneda;
    private Label.Label LblMsj;
    private Label.Label LblProductoSel;
    private Label.Label LblTipoCambio;
    private javax.swing.JPanel PnlPrecio;
    private javax.swing.JTable TblAlmacen;
    private javax.swing.JTable TblPrecios;
    private javax.swing.JTable TblProducto;
    private javax.swing.JTable TblUnidad;
    private javax.swing.JTextField TxtCantidad;
    private Label.Label TxtPTotal;
    private javax.swing.JTextField TxtPU;
    private TextField.JTxtNinguno TxtProducto;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private Label.Label label10;
    private Label.Label label12;
    private Label.Label label14;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    private Label.Label label9;
    private Label.Label lblAbrevMon;
    // End of variables declaration//GEN-END:variables
   
}
