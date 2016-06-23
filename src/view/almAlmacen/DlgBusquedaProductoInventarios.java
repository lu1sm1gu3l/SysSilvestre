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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;


public class DlgBusquedaProductoInventarios extends DialogoPadre
{
   private CEAlmacenProducto oCEAlmacenProducto;
   private List<CEProductoPrecio> listaPrecioProducto;
   private List<CEAlmacenProducto> lstCEAlmacenProducto;
   private int Opcion=0;
   private int vIdAlmacen=0;
   public DlgBusquedaProductoInventarios(java.awt.Frame parent, boolean modal,int opcion,int idAlmacen)
   {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        armarModelo();
        TxtProducto.requestFocus();
        this.Opcion=opcion;// 0:lista todos los almacenes ; diferente a 0=lista por almacen
        this.vIdAlmacen=idAlmacen;

        TblUnidad.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {


                eventoSelecUnidad();

            }
        });
    }
    public void setCajaTexto(String TxtProducto)
    {
        if(TxtProducto!=null){
        this.TxtProducto.setText(TxtProducto);
        if(!this.TxtProducto.getText().equals("")){
            List<CEProducto> listaProducto=CCProducto.consultarProducto(this.TxtProducto.getText());
            AgregarProducto(listaProducto);}
        }
    }
    public void InhabilitarBusqedaProd()
    {
        TxtProducto.setEditable(false);
        if(TblProducto.getRowCount()>0)
        {
            TblProducto.requestFocus();
            TblProducto.changeSelection(0, 0, false, false);
            SeleccionarProducto();
        }
    }
    public void setListaOrdenCompra(List<CEAlmacenProducto> pLstCEAlmacenProducto)
    {

        this.lstCEAlmacenProducto=pLstCEAlmacenProducto;

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
      oColPr.setPreferredWidth(50);

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

      TblAlmacen.getColumnModel().getColumn(3).setMinWidth(0);
      TblAlmacen.getColumnModel().getColumn(3).setPreferredWidth(0);
      TblAlmacen.getColumnModel().getColumn(3).setMaxWidth(0);
      TblAlmacen.getColumnModel().getColumn(3).setMinWidth(0);


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
                        
                        ((ArrayListTableModel)TblProducto.getModel()).addRow(oArrayList);
                        i++;
                }
                LblItem.setText(""+i);

      }
    }

      private void AgregarDetalles(long idProducto)
    {
          if(!TxtProducto.getText().equals("")){

              

              listaPrecioProducto = new ArrayList<CEProductoPrecio>();
                List<CEUnidadMedidaProducto> listaUnidad=CCUnidadMedidaProducto.consultarEquivalenciaPorProducto(idProducto);
                List<CEAlmacenProducto> listaAlamcen;
                if(Opcion==0){
                listaAlamcen=CCAlmacenProducto.consultarAlamcenesPorProducto(idProducto);}
                else
                {
                   listaAlamcen=CCAlmacenProducto.consultarProductoPorAlmacen(idProducto,vIdAlmacen);
                }
                listaPrecioProducto=CCProductoPrecio.consultarPrecioProducto(idProducto);
             if(listaUnidad!=null)
            {
                 limpiarTablaAlmacen();
                 limpiarTablaUnidad();
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
                        oVector.add(oCEAlmacenProductoTemp.getCostoUnitario());
                        ((DefaultTableModel)TblAlmacen.getModel()).addRow(oVector);
                        
                }
                if(TblAlmacen.getRowCount()>0)
                {
                TblAlmacen.setRowSelectionInterval(0,0);
                }
                
                TblUnidad.requestFocus();
                TblUnidad.setRowSelectionInterval(0,0);
                productoExisteenOrdenCompra((int)idProducto);
                CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);
                calcularStock();
                }
            }

        }

    }


      private float productoExisteenOrdenCompra(int idProducto)
    {
          if(TblUnidad.getSelectedRow()!=-1){
           CEUnidadMedidaProducto oCEUnidadMedida=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);

           if(lstCEAlmacenProducto!=null){
                  for(CEAlmacenProducto oCEAlmacenProductoTem: lstCEAlmacenProducto)
                  {
                      if(oCEAlmacenProductoTem.getIdProducto()==idProducto&&oCEUnidadMedida.getIdUnidadMedidaVenta()==oCEAlmacenProductoTem.getIdUnidadBase())
                      {

                          ActualizarUnidades(oCEAlmacenProductoTem);

                      }
                  }
              }
        }
          return 0;
      }

      private void ActualizarUnidades(CEAlmacenProducto oCEAlmacenProductoExistente)
        {

                    int fila=TblUnidad.getSelectedRow();
                 CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(fila, 1);

                if(oCEUnidadMedidaProductoTemp.getIdUnidadMedidaVenta()==oCEAlmacenProductoExistente.getIdUnidadBase())
                {
                   
                    float Equivalencia=Float.parseFloat(TblUnidad.getValueAt(fila, 2).toString());
                    ActualizarStockPorUnidad(Equivalencia,oCEAlmacenProductoExistente);

                }
            
        }
      private void ActualizarStockPorUnidad(float equivalencia,CEAlmacenProducto oCEAlmacenProductoExistente)
        {
            for(int i=0;i<TblAlmacen.getRowCount();i++)
            {
                 CEAlmacenProducto oCEAlmacenProductoTemp=(CEAlmacenProducto)TblAlmacen.getValueAt(i, 1);
                if(oCEAlmacenProductoTemp.getIdAlmacen()==oCEAlmacenProductoExistente.getIdAlmacen())
                {
                    TblAlmacen.setValueAt(true,i, 0);
                    float stockReal=Float.parseFloat(TblAlmacen.getValueAt(i, 3).toString());
                    float StockDisponible=stockReal-(oCEAlmacenProductoExistente.getCantidad()*equivalencia);
                    TblAlmacen.setValueAt(StockDisponible,i, 3);

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        LblItem = new Label.Label();
        TxtProducto = new TextField.JTxtNinguno();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblProducto = new javax.swing.JTable();
        label5 = new Label.Label();
        label6 = new Label.Label();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblUnidad = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblAlmacen = new javax.swing.JTable();
        label7 = new Label.Label();
        LblCodigo = new Label.Label();
        label8 = new Label.Label();
        LblProductoSel = new Label.Label();
        btnAceptar1 = new BotonesABM.BtnAceptar();
        btnNuevo1 = new BotonesABM.BtnNuevo();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda de Producto");

        jPanel1.setBackground(new java.awt.Color(254, 254, 239));

        LblItem.setText("0");
        LblItem.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtProducto.setTamanio(200);
        TxtProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtProductoFocusGained(evt);
            }
        });
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
                "Codigo","Impuesto"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.Object.class,
                java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                                .addGap(73, 73, 73)))
                        .addGap(133, 133, 133))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda Producto", jPanel1);

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
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(254, 254, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Stock de Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TblAlmacen.setFont(new java.awt.Font("Verdana", 0, 11));
        TblAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.", "Almacen", "Stock Real", "Stock Ub", "Costo Unit."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        label7.setText("Producto :");
        label7.setFont(new java.awt.Font("Verdana", 1, 12));

        LblCodigo.setFont(new java.awt.Font("Verdana", 1, 12));

        label8.setText("Codigo :");
        label8.setFont(new java.awt.Font("Verdana", 1, 12));

        LblProductoSel.setFont(new java.awt.Font("Verdana", 1, 12));

        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblProductoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblProductoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
          if(!TxtProducto.getText().equals("")){
                List<CEProducto> listaProducto=CCProducto.consultarProducto(TxtProducto.getText());
                AgregarProducto(listaProducto);}
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
         LblCodigo.setText(oCEProducto.getCodigo());
         LblProductoSel.setText(oCEProducto.getDescripcion());
    }
    private boolean agregarCantidadOrdenCompra= false;
    private void SeleccionarRegistro()
    {
        if(TblProducto.getSelectedRow()!=-1&&TblUnidad.getSelectedRow()!=-1&&TblAlmacen.getSelectedRow()!=-1){

                CEProducto oCEProductoTemp=(CEProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
                CEAlmacenProducto oCEAlmacenProductoTemp=(CEAlmacenProducto)TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 1);
                CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);


                boolean isAlmacenSel=Boolean.parseBoolean(TblAlmacen.getValueAt(TblAlmacen.getSelectedRow(), 0)+"");
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
                oCEAlmacenProducto.setCostoUnitario(oCEAlmacenProductoTemp.getCostoUnitario());
                if(!isAlmacenSel){
                
                dispose();
                }else{
                    int rspt= JOptionPane.showConfirmDialog(null,"<html><center>El producto "+oCEProductoTemp.getDescripcion()+" en "+oCEUnidadMedidaProductoTemp.getTipoUnidad()+"<BR> en el "
                                                    +oCEAlmacenProductoTemp+" ya se registro<BR> Desea adicionarle mas cantidades" +" </center></html>","Aviso",
                                                    JOptionPane.YES_NO_OPTION);
                        if(rspt==JOptionPane.OK_OPTION)
                        {
                            agregarCantidadOrdenCompra=true;
                            dispose();
                        }
                    }
              }else
                {
                    if(TblAlmacen.getRowCount()==0)
                    {
                        JOptionPane.showMessageDialog(null,"El producto no existe en almacen");
                    }else{
                    JOptionPane.showMessageDialog(null,"Selecionar una unidad de Producto");
                    }
                }
    }
    public boolean getisagregarCantidadOrdenCompra()
    {
        return agregarCantidadOrdenCompra;
    }
   
    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
     SeleccionarRegistro();
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        EventoNuevo();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

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

            TblAlmacen.setValueAt(CLRedondear.Redondear((stock/Equivalencia),3), i, 2);
            }
        }
    }
    
}
private void LimpiarSelTblAlmacen()
    {
    if(TblAlmacen.getRowCount()>0)
    {
        for(int i=0;i<TblAlmacen.getRowCount();i++){

        TblAlmacen.setValueAt(false, i, 0);
        }
    }

}

    private void TblUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblUnidadKeyPressed
          if(TblUnidad.getRowCount()>0){
            TblUnidad.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblUnidad.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                SeleccionarRegistro();
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
               }
    }//GEN-LAST:event_TblUnidadKeyReleased

    private void TblProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblProductoKeyReleased

        if(evt.getKeyCode()==evt.VK_UP|| evt.getKeyCode()==evt.VK_DOWN|| evt.getKeyCode()==evt.VK_TAB) {
                LimpiarDetalle();
               }

    }//GEN-LAST:event_TblProductoKeyReleased

    private void TblUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUnidadMouseClicked
                if(evt.getClickCount()==2){
                SeleccionarRegistro();
                }
    }//GEN-LAST:event_TblUnidadMouseClicked
private void eventoSelecUnidad()
    {
        LimpiarSelTblAlmacen();
        if(TblUnidad.getSelectedRow()!=-1&&TblProducto.getSelectedRow()!=-1){
        CEProducto oCEProducto=(CEProducto)TblProducto.getValueAt(TblProducto.getSelectedRow(), 1);
        productoExisteenOrdenCompra((int)oCEProducto.getIdProducto());
        calcularStock();
        CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp=(CEUnidadMedidaProducto)TblUnidad.getValueAt(TblUnidad.getSelectedRow(), 1);
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

    private void TxtProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtProductoFocusGained
       
    }//GEN-LAST:event_TxtProductoFocusGained

    private void LimpiarDetalle()
    {
          LblProductoSel.setText("");
          LblCodigo.setText("");
        if(TblUnidad.getRowCount()>0)
        {
            limpiarTablaUnidad();
            limpiarTablaAlmacen();

        }
    }


 private void EventoNuevo()
{

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
    private Label.Label LblCodigo;
    private Label.Label LblItem;
    private Label.Label LblProductoSel;
    private javax.swing.JTable TblAlmacen;
    private javax.swing.JTable TblProducto;
    private javax.swing.JTable TblUnidad;
    private TextField.JTxtNinguno TxtProducto;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnCancelar btnCancelar1;
    private BotonesABM.BtnNuevo btnNuevo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    // End of variables declaration//GEN-END:variables
    
}
