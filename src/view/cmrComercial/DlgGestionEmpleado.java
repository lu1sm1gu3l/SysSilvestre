package view.cmrComercial;
import abm.CLABM;
import abm.CLBotonesABM;
import controller.cmrComercial.CCEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CEEmpleado;
import util.clases.cmrComercial.CLEmpleado;
import util.clases.grlGeneral.CLMgs;
import util.clases.grlGeneral.DialogoPadre;




public class DlgGestionEmpleado extends DialogoPadre implements ActionListener {
private CEEmpleado CEEmpleado;
private int OpcionDialogo=0;// 0=amb 1=busqueda
private int IdEstado;// 1=habilitado
CLBotonesABM oCLBoton;
CLEmpleado oCLogica;
int acc=1;
 
    public DlgGestionEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
         oCLogica=new CLEmpleado(TblListaEmpleado);
       oCLogica.armarModelo();
        TxtEmpleado.requestFocus();
      }
    public DlgGestionEmpleado(java.awt.Frame parent, boolean modal,int Opcion,int TipoEmpleado,int Estado){
        super(parent, modal);
        initComponents();
        oCLogica=new CLEmpleado(TblListaEmpleado);
        oCLogica.armarModelo();
        OpcionDialogo=Opcion;
         IdEstado=Estado;         
        this.setLocationRelativeTo(null);
        TxtEmpleado.requestFocus();
        
        oCLBoton = new CLBotonesABM();
        if(OpcionDialogo==0)
        {
         opcionDialogo(true);
         buscarEmpleado();
        }
        else
        {
            opcionDialogo(false);
        }
        
       
     }
    public void setCajaTexo(String texto)
    {
        TxtEmpleado.setText(texto);
        buscarEmpleado();
    }

   
      private void opcionDialogo(boolean  p)
      {
          btnEditar.setVisible(p);
       //   btnNuevo.setVisible(p);
          btnDardeBaja.setVisible(p);
      }
  


      public CEEmpleado getoCEEmpleado() {
        return CEEmpleado;
     }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtgrDatos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        PnlDatos = new javax.swing.JPanel();
        rbtDNIOpcion = new javax.swing.JRadioButton();
        rbtApellidoNombreOpcion = new javax.swing.JRadioButton();
        TxtEmpleado = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        PnBotonesABM = new javax.swing.JPanel();
        btnDardeBaja = new javax.swing.JButton();
        btnEditar = new BotonesABM.BtnEditar();
        btnNuevo = new BotonesABM.BtnNuevo();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListaEmpleado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setToolTipText("Consulta Cliente");

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

        TxtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtEmpleadoKeyReleased(evt);
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
                .addComponent(TxtEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
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
                .addComponent(TxtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

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

        javax.swing.GroupLayout PnBotonesABMLayout = new javax.swing.GroupLayout(PnBotonesABM);
        PnBotonesABM.setLayout(PnBotonesABMLayout);
        PnBotonesABMLayout.setHorizontalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        PnBotonesABMLayout.setVerticalGroup(
            PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnBotonesABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnBotonesABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDardeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        TblListaEmpleado.setFont(new java.awt.Font("Verdana", 0, 12));
        TblListaEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaEmpleadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TblListaEmpleadoMouseEntered(evt);
            }
        });
        TblListaEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblListaEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaEmpleadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblListaEmpleado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnBotonesABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnBotonesABM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    private void btnDardeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDardeBajaActionPerformed

       if(TblListaEmpleado.getSelectedRow()!=-1){
        acc= CLABM.DELETE;
   int rspt= JOptionPane.showConfirmDialog(null,"Desea dar de baja al empleado seleccionado?","Aviso",
                                                    JOptionPane.YES_NO_OPTION);
        try
        {       CEEmpleado oEmpleadoAux= new CEEmpleado();
                ArrayList<CEEmpleado> olista=new ArrayList<CEEmpleado>();
                
                if (rspt == CLMgs.OK())
                {   sacarDatosTabla();
                    oEmpleadoAux.setIdEmpleado(CEEmpleado.getIdEmpleado());
                   olista.add(oEmpleadoAux);
                  int resul=CCEmpleado.DELEmpleado(olista);
                    if(resul!=0)
                    {
                     JOptionPane.showMessageDialog(null,"Operación exitosa");
//                    cargarUtilidades();
                    TxtEmpleado.setText("");
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
        TxtEmpleado.setText("");
        TxtEmpleado.requestFocus();
//      cargarUtilidades();
    }//GEN-LAST:event_rbtDNIOpcionActionPerformed

    private void rbtApellidoNombreOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtApellidoNombreOpcionActionPerformed
        // TODO add your handling code here:
        TxtEmpleado.setText("");
        TxtEmpleado.requestFocus();
  //    cargarUtilidades();
    }//GEN-LAST:event_rbtApellidoNombreOpcionActionPerformed

    private void TblListaEmpleadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaEmpleadoMouseEntered
        // TODO add your handling code here:
}//GEN-LAST:event_TblListaEmpleadoMouseEntered

    private void TblListaEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaEmpleadoMouseClicked
        
        
        if (evt.getClickCount()==2) {
            selecionarRegistro();
          //  cargarUtilidades();
        }
}//GEN-LAST:event_TblListaEmpleadoMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       selecionarRegistro();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
  EventoNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed
private void EventoNuevo()
        {
       acc = CLABM.INSERT;
        dlgRegistroEmpleado oDialogoEmpleado=new dlgRegistroEmpleado(null,true,null,1);
        oDialogoEmpleado.setABM(1);
        oDialogoEmpleado.setVisible(true);
        buscarEmpleado();
}
    private void TblListaEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaEmpleadoKeyReleased

     
    }//GEN-LAST:event_TblListaEmpleadoKeyReleased

    private void TblListaEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaEmpleadoKeyPressed
          TblListaEmpleado.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblListaEmpleado.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selecionarRegistro();
          
                return;
            }
        });

        
    }//GEN-LAST:event_TblListaEmpleadoKeyPressed

    private void TxtEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtEmpleadoKeyReleased

        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarEmpleado();
        }

        if(evt.getKeyCode()==evt.VK_DOWN) {
            TblListaEmpleado.requestFocus();
            TblListaEmpleado.setRowSelectionInterval(0,0);
        }
}//GEN-LAST:event_TxtEmpleadoKeyReleased

    private void TxtEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtEmpleadoKeyPressed

      if(evt.getKeyCode()==evt.VK_ENTER) {
            if(TblListaEmpleado.getRowCount()==0)
            {
                EventoNuevo();
            }
        }
    }//GEN-LAST:event_TxtEmpleadoKeyPressed
    private void selecionarRegistro()
    {
         sacarDatosTabla();
         if(TblListaEmpleado.getSelectedRow()!=-1){
            if(OpcionDialogo==0)
            {
              
                //lista Empleado
                 dlgRegistroEmpleado oJDABMEmpleado=new dlgRegistroEmpleado(null, true, CEEmpleado,1);
                 oJDABMEmpleado.setABM(2);
                  oJDABMEmpleado.setVisible(true);
                  buscarEmpleado();
                    
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
        int fila=TblListaEmpleado.getSelectedRow();
             if (fila>-1) {
                   CEEmpleado=(CEEmpleado)TblListaEmpleado.getValueAt(fila ,1);
                   
               }
    }
   private void buscarEmpleado()
     {           
        try
        {
            String datoEmpleado=TxtEmpleado.getText().trim();
            
            
             if(rbtDNIOpcion.isSelected())
                {
                 
                ArrayList<CEEmpleado> oLista=CCEmpleado.BuscarDNI(IdEstado,datoEmpleado);
                oCLogica.llenarDatosATabla(TblListaEmpleado,oLista);
                 
                }
                else
                {
                
                 ArrayList<CEEmpleado> oLista=CCEmpleado.BuscarNombre(IdEstado, datoEmpleado);
                oCLogica.llenarDatosATabla(TblListaEmpleado, oLista);
                 
                }
            
          
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
    private javax.swing.JTable TblListaEmpleado;
    private javax.swing.JTextField TxtEmpleado;
    private javax.swing.JButton btnDardeBaja;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnNuevo btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
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
