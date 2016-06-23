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

import controller.almAlmacen.CCAlmacen;
import controller.almAlmacen.CCAlmacenProducto;
import controller.almAlmacen.CCProductoPrecio;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import table.ArrayListTableModel;
import util.clases.almAlmacen.CLPrecio;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.VerificadorDeTxt;


public class DlgBusquedaProductoResumen extends JDialog implements ActionListener
{
   private CEAlmacenProducto oCEAlmacenProducto;
   private List<CEProductoPrecio> listaPrecioProducto;
   private List<CEPedidoDetalle> lstPedidoDetalle;
   private CEMoneda oCEMoneda;
   private float TipoCambio;
   private boolean conPercepcion;
   public DlgBusquedaProductoResumen(java.awt.Frame parent, boolean modal,CEMoneda vMoneda,float vTipoCambio)
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
        LblEtiqTipoCambio.setVisible(false);
        LblTipoCambio.setVisible(false);
        CbxAlmacen.setModel(CLComboBox.cargarCombo(CCAlmacen.listarAlmacenSucursal()));
        TblPrecios.getColumnModel().getColumn(3).setHeaderValue("Precio "+oCEMoneda.getDescripcion().toLowerCase());
        if(oCEMoneda.getId_moneda()==1)
        {
          TblPrecios.getColumnModel().getColumn(2).setMaxWidth(0);
          TblPrecios.getColumnModel().getColumn(2).setMinWidth(0);
          TblPrecios.getColumnModel().getColumn(2).setPreferredWidth(0);
        }

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
          // filtrarProducto();
          }
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
      oColPr.setPreferredWidth(75);
      oColPr = TblProducto.getColumnModel().getColumn(4);
      oColPr.setPreferredWidth(50);
      oColPr = TblProducto.getColumnModel().getColumn(5);
      oColPr.setPreferredWidth(50);


     

      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblPrecios.getColumnModel().getColumn(2).setCellRenderer(tcr);

    }
     private void AgregarProducto(List<CEAlmacenProducto> oLitaProducto)
    {        
        if(oLitaProducto!=null)
        {
            int i=0;
                for (CEAlmacenProducto vCEAlmacenProducto : oLitaProducto)
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(""+(i+1));
                        oArrayList.add(vCEAlmacenProducto);
                        oArrayList.add(vCEAlmacenProducto.getCodigo());
                        oArrayList.add(vCEAlmacenProducto.getUnidad_medida());
                        oArrayList.add(vCEAlmacenProducto.getStockReal());
                                //-VerificarCantidadAnteSiExisteEnPedido(vCEAlmacenProducto.getIdProducto(),vCEAlmacenProducto.getEquivalenciaUMB()));
                        oArrayList.add(vCEAlmacenProducto.getPrecio());
                        if(vCEAlmacenProducto.isSiNoImpuesto()){
                        oArrayList.add("SI");}
                         else{
                             oArrayList.add("NO");
                              }
                        if(vCEAlmacenProducto.isPercepcion()){
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

                if(table.getValueAt(row,7)!=null)
                {
                    String Percepcion=(table.getValueAt(row,7)+"");
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
   

     private void limpiarDetallerProduto()
    {
      limpiarTablaPrecios();
      TxtCantidad.setText("0");
      TxtPTotal.setText("0");
      TxtPU.setText("0");
     }


      private float VerificarCantidadAnteSiExisteEnPedido(long idProducto,float equivalencia)// si hay un pedido con el mismo producto se verifica haciendo la busqueda en la lista de pedidos y devuelve la cantidad registrada
    {

              if(lstPedidoDetalle!=null){

                        CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();
                      for(CEPedidoDetalle oCEPedidoDetalleTem: lstPedidoDetalle)
                      {
                          if(oCEPedidoDetalleTem.getIdProducto()==idProducto&&oCEPedidoDetalleTem.getIdAlmacen()==oCEAlmacen.getIdAlmacen())
                          {

                              return oCEPedidoDetalleTem.getCantidadBaseAnte()/equivalencia;

                          }
                      }
              }
        
          return 0;
      }



 private List<CEProductoPrecio> listaPrecioProductoTemp;
      private void cargarTablaPrecio()
      {
          limpiarTablaPrecios();
          if(TblProducto.getSelectedRow()!=-1){
         CEAlmacenProducto oCEProducto=(CEAlmacenProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
         listaPrecioProducto=CCProductoPrecio.consultarPrecioProductoUnidad(oCEProducto.getIdProducto(),oCEProducto.getIdUnidadMedida());
          if(listaPrecioProducto!=null){
              listaPrecioProductoTemp=new ArrayList<CEProductoPrecio>();

          for(CEProductoPrecio oCEProductoPrecioTemp : listaPrecioProducto)
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

      private void limpiarTablaPrecios()
    {
         int size =TblPrecios.getRowCount();
           for(int i=0;i<size;i++)
           {
                 ((DefaultTableModel)TblPrecios.getModel()).removeRow(0);
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

        LblEtiqTipoCambio = new Label.Label();
        LblTipoCambio = new Label.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProducto = new javax.swing.JTable();
        LblItem = new Label.Label();
        label7 = new Label.Label();
        btnAceptar1 = new BotonesABM.BtnAceptar();
        btnCancelar1 = new BotonesABM.BtnCancelar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        TxtProducto = new TextField.JTxtNinguno();
        label5 = new Label.Label();
        jPanel2 = new javax.swing.JPanel();
        TxtProducto1 = new TextField.JTxtNinguno();
        label13 = new Label.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPrecios = new javax.swing.JTable();
        label6 = new Label.Label();
        LblMsj = new Label.Label();
        jPanel3 = new javax.swing.JPanel();
        label9 = new Label.Label();
        TxtCantidad = new javax.swing.JTextField();
        label11 = new Label.Label();
        label12 = new Label.Label();
        label10 = new Label.Label();
        TxtPTotal = new Label.Label();
        TxtPU = new javax.swing.JTextField();
        LblMoneda = new Label.Label();
        CbxAlmacen = new ComboBox.ComboBox();
        label14 = new Label.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Producto");

        LblEtiqTipoCambio.setText("Tipo Cambio :");
        LblEtiqTipoCambio.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        LblTipoCambio.setForeground(new java.awt.Color(102, 0, 0));
        LblTipoCambio.setText("10.0000");
        LblTipoCambio.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        TblProducto.setFont(new java.awt.Font("Verdana", 0, 11));
        TblProducto.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {"Nº","Producto",
                "Codigo","Unidad Medida","Stock","Precio Ref.","Impuesto","Percepción"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.Object.class,
                java.lang.String.class,java.lang.String.class,
                java.lang.String.class,java.lang.String.class,
                java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false
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

        LblItem.setText("0");
        LblItem.setFont(new java.awt.Font("Verdana", 1, 12));

        label7.setText("Productos Encontrados :");
        label7.setFont(new java.awt.Font("Verdana", 1, 12));

        btnAceptar1.setText("");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setText("");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(254, 254, 239));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtProducto.setTamanio(200);
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProductoKeyReleased(evt);
            }
        });

        label5.setText("Producto :");
        label5.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda por Nombre", jPanel1);

        jPanel2.setBackground(new java.awt.Color(254, 254, 239));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtProducto1.setTamanio(200);
        TxtProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProducto1KeyReleased(evt);
            }
        });

        label13.setText("Codigo     :");
        label13.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProducto1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda por Codigo", jPanel2);

        TblPrecios.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
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

        label6.setText("Precios X Cantidad");
        label6.setFont(new java.awt.Font("Verdana", 1, 12));

        LblMsj.setForeground(new java.awt.Color(153, 0, 0));
        LblMsj.setText("La cantidad ingresada no tiene un precio establecido, Ingrese un precio");
        LblMsj.setFont(new java.awt.Font("Verdana", 1, 12));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label9.setBackground(new java.awt.Color(204, 255, 204));
        label9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        label9.setForeground(new java.awt.Color(51, 51, 51));
        label9.setText("Cantidad ");
        label9.setFont(new java.awt.Font("Verdana", 1, 12));
        label9.setOpaque(true);
        jPanel3.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        TxtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        TxtCantidad.setEditable(false);
        TxtCantidad.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TxtCantidad.setForeground(new java.awt.Color(0, 51, 153));
        TxtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
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
        jPanel3.add(TxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 220, 30));

        label11.setBackground(new java.awt.Color(204, 255, 204));
        label11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        label11.setForeground(new java.awt.Color(51, 51, 51));
        label11.setText("Precio Unit ");
        label11.setFont(new java.awt.Font("Verdana", 1, 12));
        label11.setOpaque(true);
        jPanel3.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 100, 30));

        label12.setBackground(new java.awt.Color(204, 255, 204));
        label12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        label12.setForeground(new java.awt.Color(51, 51, 51));
        label12.setText("Precio Total ");
        label12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label12.setOpaque(true);
        jPanel3.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 100, 30));

        label10.setBackground(new java.awt.Color(204, 255, 204));
        label10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        label10.setForeground(new java.awt.Color(51, 51, 51));
        label10.setText("Moneda");
        label10.setFont(new java.awt.Font("Verdana", 1, 12));
        label10.setOpaque(true);
        jPanel3.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, 30));

        TxtPTotal.setBackground(new java.awt.Color(255, 255, 204));
        TxtPTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TxtPTotal.setForeground(new java.awt.Color(0, 51, 153));
        TxtPTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtPTotal.setText("0.00");
        TxtPTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        TxtPTotal.setOpaque(true);
        jPanel3.add(TxtPTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 220, 30));

        TxtPU.setBackground(new java.awt.Color(255, 255, 204));
        TxtPU.setEditable(false);
        TxtPU.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TxtPU.setForeground(new java.awt.Color(0, 51, 153));
        TxtPU.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtPU.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TxtPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPUActionPerformed(evt);
            }
        });
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
        jPanel3.add(TxtPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 220, 30));

        LblMoneda.setBackground(new java.awt.Color(255, 255, 204));
        LblMoneda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LblMoneda.setForeground(new java.awt.Color(102, 0, 0));
        LblMoneda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMoneda.setText("Soles");
        LblMoneda.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        LblMoneda.setOpaque(true);
        jPanel3.add(LblMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 220, 30));

        CbxAlmacen.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxAlmacenMouseClicked(evt);
            }
        });
        CbxAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxAlmacenActionPerformed(evt);
            }
        });

        label14.setBackground(new java.awt.Color(224, 219, 219));
        label14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        label14.setForeground(new java.awt.Color(51, 51, 51));
        label14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label14.setText("Almacen");
        label14.setFont(new java.awt.Font("Verdana", 1, 12));
        label14.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblMsj, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblMsj, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
          
            filtrarProducto();
      }
        

    }//GEN-LAST:event_TxtProductoKeyReleased

    private void filtrarProducto()
    {
        limpiarTabla();
          LimpiarDetalle();

        List<CEAlmacenProducto> listaProducto=null;
        CEAlmacen oCEAlmacen= (CEAlmacen)CbxAlmacen.getSelectedItem();
        if(oCEAlmacen!=null)
        {
              if(!conPercepcion){
                      if(!TxtProducto.getText().equals("")){
                          listaProducto=CCAlmacenProducto.listarDetalleProducto(TxtProducto.getText(), oCEAlmacen.getIdAlmacen());
                      }
                }
                else
                {
                        listaProducto=CCAlmacenProducto.listarDetalleProductoConPercep(TxtProducto.getText(), oCEAlmacen.getIdAlmacen());
                    }
        }

         AgregarProducto(listaProducto);
    }
    private void TblProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProductoKeyPressed

           if(TblProducto.getRowCount()>0){
       TblProducto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblProducto.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
              //  SeleccionarProducto();
              TxtCantidad.requestFocus();
            }
        });

              if(evt.getKeyCode()==evt.VK_SPACE) {
             // SeleccionarProducto();
               TxtCantidad.requestFocus();
        }
        if(evt.getKeyCode()==evt.VK_BACK_SPACE) {
              TxtProducto.requestFocus();
        }
        }
     
    }//GEN-LAST:event_TblProductoKeyPressed

    private void SeleccionarProducto()
    {
        cargarTablaPrecio();
        if(TblPrecios.getRowCount()>0)
        {
            TxtCantidad.setEditable(true);
            TxtCantidad.requestFocus();
        }
        else
        {
            TxtCantidad.setEditable(false);
        }
    }
    private boolean validar()
    {
        float StockReal=0;
        if(TblProducto.getSelectedRow()==-1){
           JOptionPane.showMessageDialog(this,"Seleccione un producto","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

        if(VerificadorDeTxt.convertFloat(TxtCantidad.getText())==0)
        {
            JOptionPane.showMessageDialog(this,"Ingrese Cantidad","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            SeleccionarProducto();
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
          if(TblProducto.getSelectedRow()!=-1){
              if(lstPedidoDetalle!=null){


                      CEAlmacenProducto oCEProducto=(CEAlmacenProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
                      for(CEPedidoDetalle oCEPedidoDetalleTem: lstPedidoDetalle)
                      {
                          if(oCEPedidoDetalleTem.getIdProducto()==idProducto&&
                             oCEProducto.getIdUnidadMedida()==oCEPedidoDetalleTem.getIdUnidadMedidaVenta() )
                          {
                            if(oCEPedidoDetalleTem.getIdAbm()==4) // si existe en la lista de pedido
                              return false;
                            if(oCEPedidoDetalleTem.getIdAbm()!=3) // si es igual a tres es un pedido eliminado y por ello no se toma en cuenta
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
        if(TblProducto.getSelectedRow()!=-1){

                


               
                oCEAlmacenProducto = (CEAlmacenProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
                 boolean isProductoRepetido=ExisteEnPedido(oCEAlmacenProducto.getIdProducto());
             //   oCEAlmacenProducto.setIdProducto(oCEProductoTemp.getIdProducto());
              //  oCEAlmacenProducto.setDescripcion(oCEProductoTemp.getDescripcion());
                oCEAlmacenProducto.setIdAlmacen(((CEAlmacen)(CbxAlmacen.getSelectedItem())).getIdAlmacen());
             //   oCEAlmacenProducto.setAlmacen(oCEProductoTemp.getDescripcion());
              //  oCEAlmacenProducto.setStockReal(oCEProductoTemp.getStockReal());
              //  oCEAlmacenProducto.setUnidad_medida(oCEProductoTemp.getUnidad_medida());
               // oCEAlmacenProducto.setCodigo(oCEProductoTemp.getCodigo());
              //  oCEAlmacenProducto.setSiNoImpuesto(oCEProductoTemp.isSiNoImpuesto());
              //  oCEAlmacenProducto.setAlmacen(oCEProductoTemp.getDescripcion());
                oCEAlmacenProducto.setIdUnidadBase(oCEAlmacenProducto.getIdUnidadMedida()); // la UnidadBasere presentara la unidad de medida en el objeto pedido , solo por fines de artificio
                oCEAlmacenProducto.setCantidad(VerificadorDeTxt.convertFloat(TxtCantidad.getText()));
                oCEAlmacenProducto.setPrecio(VerificadorDeTxt.convertFloat(TxtPU.getText()));
                oCEAlmacenProducto.setPrecio_sin_redon(precio_sin_redon);
              //  oCEAlmacenProducto.setPercepcion(oCEProductoTemp.isPercepcion());
                if(!isProductoRepetido){
                
                dispose();
                }else{
                    int rspt= JOptionPane.showConfirmDialog(this,"<html><center>El producto "+oCEAlmacenProducto.getDescripcion()+" en "+oCEAlmacenProducto.getUnidad_medida()+"<BR> en el "
                                                    +oCEAlmacenProducto+" ya se registro<BR> Desea adicionarle mas cantidades" +" </center></html>","Aviso",
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
            //  SeleccionarProducto();
            TxtCantidad.requestFocus();
                }
        if(evt.getClickCount()==1){
              LimpiarDetalle();
                }
       
    }//GEN-LAST:event_TblProductoMouseClicked
private void calcularStock()
    {
//    if(TblUnidad.getSelectedRow()!=-1){
//        if(TblAlmacen.getRowCount()>0)
//        {
//            for(int i=0;i<TblAlmacen.getRowCount();i++){
//            float stock=Float.parseFloat(TblAlmacen.getValueAt(i, 3).toString());
//            float Equivalencia=Float.parseFloat(TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 2).toString());
//
//            TblAlmacen.setValueAt(CLRedondear.Redondear((stock/Equivalencia),2), i, 2);
//            }
//        }
//
//    }

    
}


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

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
      dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void TxtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyPressed


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
       SeleccionarProducto();
    }//GEN-LAST:event_TxtCantidadFocusGained

    private void TxtProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtProducto1KeyReleased

    private void TxtPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPUActionPerformed

    private void CbxAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxAlmacenMouseClicked

}//GEN-LAST:event_CbxAlmacenMouseClicked

    private void CbxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxAlmacenActionPerformed
filtrarProducto();

}//GEN-LAST:event_CbxAlmacenActionPerformed

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
       
        if(TblProducto.getRowCount()>0)
        {
            TxtCantidad.setText("0");
            TxtPTotal.setText("0");
            TxtPU.setText("");
            LblMsj.setText("");
            limpiarTablaPrecios();
            TxtCantidad.setEditable(false);
            

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
    private ComboBox.ComboBox CbxAlmacen;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblItem;
    private Label.Label LblMoneda;
    private Label.Label LblMsj;
    private Label.Label LblTipoCambio;
    private javax.swing.JTable TblPrecios;
    private javax.swing.JTable TblProducto;
    private javax.swing.JTextField TxtCantidad;
    private Label.Label TxtPTotal;
    private javax.swing.JTextField TxtPU;
    private TextField.JTxtNinguno TxtProducto;
    private TextField.JTxtNinguno TxtProducto1;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private Label.Label label10;
    private Label.Label label11;
    private Label.Label label12;
    private Label.Label label13;
    private Label.Label label14;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label9;
    // End of variables declaration//GEN-END:variables

     private static final String ACTION_CLOSE = "ACTION_CLOSE";

    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(TxtCantidad.isFocusOwner())
        {
            TblProducto.requestFocus();
        }else

        {
         dispose();
        }

    }
   
}
