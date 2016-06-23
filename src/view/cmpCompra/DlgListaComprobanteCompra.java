package view.cmpCompra;


import controller.cmpCompra.CCComprobanteCompra;
import controller.grlGeneral.CCValores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumnModel;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import table.ArrayListTableModel;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.vtaVenta.DlgNotaCredito;


public class DlgListaComprobanteCompra extends DialogoPadre implements ActionListener
{

    private List<CEComprobanteCompraMatriz> oListaComprobanteCompra;
    public DlgListaComprobanteCompra(java.awt.Frame parent, boolean modal)
    {
        super(parent, false);
        initComponents();
        configurarTablaPedidosPedientes();
        DtChFecha.setDate(new Date());
        TxtParametro.setVisible(false);
    }
    private void configurarTablaPedidosPedientes()
    {
        TableColumnModel oTableColumnModel=TblPedidosPendientes.getColumnModel();
        oTableColumnModel.getColumn(0).setMinWidth(120);
        oTableColumnModel.getColumn(0).setMaxWidth(120);
        oTableColumnModel.getColumn(1).setMinWidth(80);
        oTableColumnModel.getColumn(1).setMaxWidth(80);
        oTableColumnModel.getColumn(2).setMinWidth(300);
        oTableColumnModel.getColumn(2).setMaxWidth(300);
        oTableColumnModel.getColumn(3).setMinWidth(100);
        oTableColumnModel.getColumn(3).setMaxWidth(100);
        oTableColumnModel.getColumn(4).setMinWidth(65);
        oTableColumnModel.getColumn(4).setMaxWidth(65);
        oTableColumnModel.getColumn(5).setMinWidth(95);
        oTableColumnModel.getColumn(5).setMaxWidth(95);
  ;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenuPedido = new javax.swing.JPopupMenu();
        jMenuItemCancelarPedido = new javax.swing.JMenuItem();
        jMenuItemAnularPedido = new javax.swing.JMenuItem();
        TbpPago = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        RbtCodigo = new javax.swing.JRadioButton();
        RbtCliente = new javax.swing.JRadioButton();
        RbtFecha = new javax.swing.JRadioButton();
        DtChFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        TxtParametro = new TextField.JTxtNinguno();
        BtnAnular = new javax.swing.JButton();
        BtnRefrescar = new javax.swing.JButton();
        LblColaPedidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPedidosPendientes = new javax.swing.JTable()
        ;

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión Comprobante de Venta");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(java.awt.SystemColor.info);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "[Búsqueda]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        RbtCodigo.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(RbtCodigo);
        RbtCodigo.setFont(new java.awt.Font("Arial", 0, 14));
        RbtCodigo.setText("Código");
        RbtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtCodigoActionPerformed(evt);
            }
        });

        RbtCliente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(RbtCliente);
        RbtCliente.setFont(new java.awt.Font("Arial", 0, 14));
        RbtCliente.setText("Cliente");
        RbtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtClienteActionPerformed(evt);
            }
        });

        RbtFecha.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(RbtFecha);
        RbtFecha.setFont(new java.awt.Font("Arial", 0, 14));
        RbtFecha.setSelected(true);
        RbtFecha.setText("Fecha");
        RbtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtFechaActionPerformed(evt);
            }
        });

        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });

        TxtParametro.setText("jTxtNinguno1");
        TxtParametro.setTamanio(150);
        TxtParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtParametroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtParametroKeyReleased(evt);
            }
        });

        BtnAnular.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.png"))); // NOI18N
        BtnAnular.setText("Anular");
        BtnAnular.setToolTipText("Anular Pedido");
        BtnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAnular.setMargin(new java.awt.Insets(2, 0, 0, 0));
        BtnAnular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnularActionPerformed(evt);
            }
        });

        BtnRefrescar.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refrescar.png"))); // NOI18N
        BtnRefrescar.setText("Refrescar");
        BtnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnRefrescar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(RbtFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RbtCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RbtCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RbtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(RbtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RbtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
        LblColaPedidos.setText("Lista Comprobante Venta");

        TblPedidosPendientes.setFont(new java.awt.Font("Arial", 0, 14));
        TblPedidosPendientes.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "Código",
                "Cliente",
                "Tip.Comprob.",
                "Moneda",
                "Total",
                "Estado"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.Float.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPedidosPendientes.setRowHeight(20);
        TblPedidosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPedidosPendientesMouseClicked(evt);
            }
        });
        TblPedidosPendientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TblPedidosPendientesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(TblPedidosPendientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LblColaPedidos))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblColaPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        TbpPago.addTab("Comprobantes", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TbpPago, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TbpPago, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed
    oListaComprobanteCompra=listarPedidosPendientes();
    filtrarTabla();
    }//GEN-LAST:event_BtnRefrescarActionPerformed

    private boolean  ValidarFecha(String pFecha)
    {
     int isFechaActual=CCValores.obenerFechaDelSistema(pFecha);
     if(isFechaActual==1)
     {
         return true;
     }
         return false;

    }
    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
        int fila = TblPedidosPendientes.getSelectedRow();
        if(fila!=-1){
        CEComprobanteCompraMatriz oCEComprobanteCompraMatriz=(CEComprobanteCompraMatriz)TblPedidosPendientes.getValueAt(fila,2);
        if(oCEComprobanteCompraMatriz.getUltimoIdEstado()!=4){
        if(ValidarFecha(oCEComprobanteCompraMatriz.getFecha()))
        {

            DlgNotaCredito oDlgNotaCredito= new DlgNotaCredito(null, true,1,oCEComprobanteCompraMatriz.getIdComprobanteCompra(),true);
            oDlgNotaCredito.setTitle("Formulario Comprobante Devolución");
            oDlgNotaCredito.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(this,"<html><b>La fecha del comprobante de venta no es del día, <br>antes de anular debe generar una Nota Credito</b></html>");
            DlgNotaCredito oDlgNotaCredito= new DlgNotaCredito(null, true,1,oCEComprobanteCompraMatriz.getIdComprobanteCompra(),false);
            oDlgNotaCredito.setVisible(true);
            
        }

        refreshColaPagos();
        }
        else{
             JOptionPane.showMessageDialog(this,"<html><b>El comprobante ya fue anulado</b></html>");
        }
        }
 else{
       JOptionPane.showMessageDialog(this,"Seleccione una fila");
    }
    }//GEN-LAST:event_BtnAnularActionPerformed

    private void TblPedidosPendientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TblPedidosPendientesFocusLost

}//GEN-LAST:event_TblPedidosPendientesFocusLost

    private void TblPedidosPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPedidosPendientesMouseClicked
     
}//GEN-LAST:event_TblPedidosPendientesMouseClicked

    private void RbtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtClienteActionPerformed
      if(RbtCliente.isSelected()){
        DtChFecha.setVisible(false);
        TxtParametro.setVisible(true);
        TxtParametro.requestFocus();
        TxtParametro.setText("");
    }
      limpiarTabla();
    }//GEN-LAST:event_RbtClienteActionPerformed

    private void RbtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtCodigoActionPerformed
       if(RbtCodigo.isSelected()){
        DtChFecha.setVisible(false);
        TxtParametro.setVisible(true);
        TxtParametro.requestFocus();
        TxtParametro.setText("");
       }
      limpiarTabla();
    }//GEN-LAST:event_RbtCodigoActionPerformed

    private void RbtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtFechaActionPerformed

        if(RbtFecha.isSelected()){
            DtChFecha.setVisible(true);
            TxtParametro.setVisible(false);}
        TxtParametro.setText("");
        DtChFecha.setDate(new Date());
       

}//GEN-LAST:event_RbtFechaActionPerformed

    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            refreshColaPagos();
            }
        }
    }//GEN-LAST:event_DtChFechaPropertyChange
 boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void TxtParametroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtParametroKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER)
       {
            refreshColaPagos();
       }
    }//GEN-LAST:event_TxtParametroKeyPressed

    private void TxtParametroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtParametroKeyReleased
      if(evt.getKeyCode()==evt.VK_DOWN)
       {
            TblPedidosPendientes.requestFocus();
            TblPedidosPendientes.setRowSelectionInterval(0,0);
       }
    }//GEN-LAST:event_TxtParametroKeyReleased
    private void refreshColaPagos()
    {
    oListaComprobanteCompra=listarPedidosPendientes();
    filtrarTabla();
    }
    private List<CEComprobanteCompraMatriz> listarPedidosPendientes()
    {
        String dato=TxtParametro.getText();
        if(!dato.equals("")){
        if(RbtCliente.isSelected())
        {
            return CCComprobanteCompra.BuscarComprobanteCompra(3, dato,1);
        }
        if(RbtCodigo.isSelected()){
            return CCComprobanteCompra.BuscarComprobanteCompra(5, dato,1);
                }
        }
        if(RbtFecha.isSelected())
        {
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();
           return CCComprobanteCompra.BuscarComprobanteCompra(4, pFecha,1);
        }
        return null;
      

    }

    private void filtrarTabla()
    {
       limpiarTabla();
       if(oListaComprobanteCompra!=null)
       {

                for(CEComprobanteCompraMatriz oCEComprobanteCompraMatriz:oListaComprobanteCompra )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(oCEComprobanteCompraMatriz.getFecha());
                        oArrayList.add(oCEComprobanteCompraMatriz.getNumComprobante());
                        oArrayList.add(oCEComprobanteCompraMatriz);
                        oArrayList.add(oCEComprobanteCompraMatriz.getTipoComprobante());
                        oArrayList.add(oCEComprobanteCompraMatriz.getMoneda());
                        oArrayList.add(oCEComprobanteCompraMatriz.getMontoTotal());
                        oArrayList.add(oCEComprobanteCompraMatriz.getUltimoEstado());
                        ((ArrayListTableModel)TblPedidosPendientes.getModel()).addRow(oArrayList);
                    
                }
                LblColaPedidos.setText("Cola de Pedidos: "+TblPedidosPendientes.getRowCount()+" Pedido(s) Encontrados");
            
        }

    }
    private void limpiarTabla()
    {
        int size=TblPedidosPendientes.getRowCount();
        for(int i=0;i<size;i++ )
        {
        ((ArrayListTableModel)TblPedidosPendientes.getModel()).removeRow(0);
        }
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnular;
    private javax.swing.JButton BtnRefrescar;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private javax.swing.JLabel LblColaPedidos;
    private javax.swing.JRadioButton RbtCliente;
    private javax.swing.JRadioButton RbtCodigo;
    private javax.swing.JRadioButton RbtFecha;
    private javax.swing.JTable TblPedidosPendientes;
    private javax.swing.JTabbedPane TbpPago;
    private TextField.JTxtNinguno TxtParametro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_REFRESCAR = "ACTION_REFRESCAR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_R,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_REFRESCAR, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
        else
        {
            if(e.getActionCommand().equals(ACTION_REFRESCAR))
            {
               oListaComprobanteCompra=listarPedidosPendientes();
               TxtParametro.requestFocus();
            }
        }
    }
}
