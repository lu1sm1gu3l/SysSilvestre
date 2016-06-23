package view.cmrComercial;
import abm.CLABM;
import abm.CLBotonesABM;
import controller.cmrComercial.CCProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CEProveedor;
import util.clases.cmrComercial.CLProveedor;
import util.clases.grlGeneral.CLMgs;
import util.clases.grlGeneral.DialogoPadre;




public class DlgGestionProveedor extends DialogoPadre implements ActionListener {
private CEProveedor CEProveedor;
private int OpcionDialogo=0;// 0=amb 1=busqueda
private int IdEstado;// 1=habilitado
CLBotonesABM oCLBoton;
CLProveedor oCLogica;
int acc=1;
 
    public DlgGestionProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
         oCLogica=new CLProveedor(TblListaProveedor);
       oCLogica.armarModelo();
        TxtProveedor.requestFocus();
      }
    public DlgGestionProveedor(java.awt.Frame parent, boolean modal,int Opcion,int TipoProveedor,int Estado){
        super(parent, modal);
        initComponents();
        oCLogica=new CLProveedor(TblListaProveedor);
        oCLogica.armarModelo();
        OpcionDialogo=Opcion;
         IdEstado=Estado;         
        this.setLocationRelativeTo(null);
        TxtProveedor.requestFocus();
        
        oCLBoton = new CLBotonesABM();
        if(OpcionDialogo==0)
        {
         opcionDialogo(true);
         buscarProveedor();
        }
        else
        {
            opcionDialogo(false);
        }
        
       
     }
    public void setCajaTexo(String texto)
    {
        TxtProveedor.setText(texto);
        buscarProveedor();
    }

   
      private void opcionDialogo(boolean  p)
      {
          btnEditar.setVisible(p);
       //   btnNuevo.setVisible(p);
          btnDardeBaja.setVisible(p);
      }
  


      public CEProveedor getoCEProveedor() {
        return CEProveedor;
     }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtgrDatos = new javax.swing.ButtonGroup();
        PnlDatos = new javax.swing.JPanel();
        rbtDNIOpcion = new javax.swing.JRadioButton();
        rbtApellidoNombreOpcion = new javax.swing.JRadioButton();
        TxtProveedor = new javax.swing.JTextField();
        PnBotonesABM = new javax.swing.JPanel();
        btnDardeBaja = new javax.swing.JButton();
        btnEditar = new BotonesABM.BtnEditar();
        btnNuevo = new BotonesABM.BtnNuevo();
        btnAceptar1 = new BotonesABM.BtnAceptar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListaProveedor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Proveedor");

        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        BtgrDatos.add(rbtDNIOpcion);
        rbtDNIOpcion.setFont(new java.awt.Font("Verdana", 1, 12));
        rbtDNIOpcion.setText("DNI");
        rbtDNIOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtDNIOpcionActionPerformed(evt);
            }
        });

        BtgrDatos.add(rbtApellidoNombreOpcion);
        rbtApellidoNombreOpcion.setFont(new java.awt.Font("Verdana", 1, 12));
        rbtApellidoNombreOpcion.setSelected(true);
        rbtApellidoNombreOpcion.setText("Apellidos y Nombres");
        rbtApellidoNombreOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtApellidoNombreOpcionActionPerformed(evt);
            }
        });

        TxtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProveedorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDatosLayout.createSequentialGroup()
                .addComponent(rbtApellidoNombreOpcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(rbtDNIOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TxtProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtApellidoNombreOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(rbtDNIOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnDardeBaja.setFont(new java.awt.Font("Verdana", 1, 12));
        btnDardeBaja.setText("<html><CENTER>Dar <BR>Baja</CENTER></html>");
        btnDardeBaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDardeBaja.setPreferredSize(new java.awt.Dimension(90, 70));
        btnDardeBaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDardeBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDardeBajaActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnBotonesABMLayout = new javax.swing.GroupLayout(PnBotonesABM);
        PnBotonesABM.setLayout(PnBotonesABMLayout);
        PnBotonesABMLayout.setHorizontalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PnBotonesABMLayout.setVerticalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TblListaProveedor.setFont(new java.awt.Font("Verdana", 0, 12));
        TblListaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaProveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TblListaProveedorMouseEntered(evt);
            }
        });
        TblListaProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblListaProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaProveedorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblListaProveedor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(PnBotonesABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PnBotonesABM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    private void btnDardeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDardeBajaActionPerformed

       if(TblListaProveedor.getSelectedRow()!=-1){
        acc= CLABM.DELETE;
   int rspt= JOptionPane.showConfirmDialog(null,"Desea dar de baja al Proveedor seleccionado?","Aviso",
                                                    JOptionPane.YES_NO_OPTION);
        try
        {       CEProveedor oProveedorAux= new CEProveedor();
                ArrayList<CEProveedor> olista=new ArrayList<CEProveedor>();
                
                if (rspt == CLMgs.OK())
                {   sacarDatosTabla();
                    oProveedorAux.setIdProveedor(CEProveedor.getIdProveedor());
                   olista.add(oProveedorAux);
                  int resul=CCProveedor.DELProveedor(olista);
                    if(resul!=0)
                    {
                     JOptionPane.showMessageDialog(null,"Operación exitosa");
//                    cargarUtilidades();
                    TxtProveedor.setText("");
                    acc = CLABM.INSERT;
                    }
                    else
                    {
                    CLMgs.mgsNOTransaccion(acc);
                    }
                }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null,"Escriba los datos correctamente");
        }
        }
 else
       {
       JOptionPane.showMessageDialog(null,"Selecione una fila");
 }

}//GEN-LAST:event_btnDardeBajaActionPerformed
    private void rbtDNIOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtDNIOpcionActionPerformed
        // TODO add your handling code here:
        TxtProveedor.setText("");
        TxtProveedor.requestFocus();
//      cargarUtilidades();
    }//GEN-LAST:event_rbtDNIOpcionActionPerformed

    private void rbtApellidoNombreOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtApellidoNombreOpcionActionPerformed
        // TODO add your handling code here:
        TxtProveedor.setText("");
        TxtProveedor.requestFocus();
  //    cargarUtilidades();
    }//GEN-LAST:event_rbtApellidoNombreOpcionActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       selecionarRegistro();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
  EventoNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed
private void EventoNuevo()
        {
       acc = CLABM.INSERT;
        dlgRegistroProveedor oDialogoProveedor=new dlgRegistroProveedor(null,true,null,1);
        oDialogoProveedor.setABM(1);
        oDialogoProveedor.setVisible(true);
        TxtProveedor.setText(oDialogoProveedor.getProvNuevo());
        buscarProveedor();
        TxtProveedor.requestFocus();
}
    private void TxtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedorKeyReleased

        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarProveedor();
        }

        if(evt.getKeyCode()==evt.VK_DOWN) {
            
            if(TblListaProveedor.getRowCount()>0)
            {
                TblListaProveedor.requestFocus();
                TblListaProveedor.setRowSelectionInterval(0,0);
            }
        }
}//GEN-LAST:event_TxtProveedorKeyReleased

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        selecionarRegistro();
}//GEN-LAST:event_btnAceptar1ActionPerformed

    private void TblListaProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaProveedorKeyReleased

    }//GEN-LAST:event_TblListaProveedorKeyReleased

    private void TblListaProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaProveedorKeyPressed
        TblListaProveedor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblListaProveedor.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selecionarRegistro();

                return;
            }
        });

    }//GEN-LAST:event_TblListaProveedorKeyPressed

    private void TblListaProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaProveedorMouseEntered
        // TODO add your handling code here:
}//GEN-LAST:event_TblListaProveedorMouseEntered

    private void TblListaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaProveedorMouseClicked


        if (evt.getClickCount()==2) {
            selecionarRegistro();
            //  cargarUtilidades();
        }
}//GEN-LAST:event_TblListaProveedorMouseClicked
    private void selecionarRegistro()
    {
         sacarDatosTabla();
         if(TblListaProveedor.getSelectedRow()!=-1){
            if(OpcionDialogo==0)
            {
              
                //lista Proveedor
                 dlgRegistroProveedor oJDABMProveedor=new dlgRegistroProveedor(null, true, CEProveedor,1);
                 oJDABMProveedor.setABM(2);
                  oJDABMProveedor.setVisible(true);
                  buscarProveedor();
                    
//           cargarUtilidades();
            }else
            {
                
                dispose();
            }
    }
    else{

       JOptionPane.showMessageDialog(null,"Selecione una fila");

        }
    }

   private void  sacarDatosTabla()
    {
        int fila=TblListaProveedor.getSelectedRow();
             if (fila>-1) {
                   CEProveedor=(CEProveedor)TblListaProveedor.getValueAt(fila ,1);
                   
               }
    }
   private void buscarProveedor()
     {           
        try
        {
            String datoProveedor=TxtProveedor.getText().trim();
//            if(!datoProveedor.equals("")){//||TxtProveedor!=null||datoProveedor.isEmpty()
            
             if(rbtDNIOpcion.isSelected())
                {
                 
                ArrayList<CEProveedor> oLista=CCProveedor.BuscarDNI(IdEstado,datoProveedor);
                oCLogica.llenarDatosATabla(TblListaProveedor,oLista);
                 
                }
                else
                {
                
                 ArrayList<CEProveedor> oLista=CCProveedor.BuscarNombre(IdEstado, datoProveedor);
                oCLogica.llenarDatosATabla(TblListaProveedor, oLista);
                 
                }
//            }
//            else
//                {
//                oCLogica.limpiarTabla();
//                }
         }
        catch(Exception e)
        {
            CLMgs.mgsNOTransaccion(acc);

        }
        
        }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtgrDatos;
    private javax.swing.JPanel PnBotonesABM;
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblListaProveedor;
    private javax.swing.JTextField TxtProveedor;
    private BotonesABM.BtnAceptar btnAceptar1;
    private javax.swing.JButton btnDardeBaja;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnNuevo btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtApellidoNombreOpcion;
    private javax.swing.JRadioButton rbtDNIOpcion;
    // End of variables declaration//GEN-END:variables
private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_NUEVO = "ACTION_NUEVO";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_N,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_NUEVO, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
 else{
         if(e.getActionCommand().equals(ACTION_NUEVO))
        {
            EventoNuevo();
        }
    }
    }
}
