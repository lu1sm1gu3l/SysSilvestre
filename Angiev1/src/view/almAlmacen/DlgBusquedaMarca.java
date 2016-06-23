package view.almAlmacen;

import controller.almAlmacen.CCMarca;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import modelo.almAlmacen.entidad.CEMarca;
import util.clases.almAlmacen.CLMarca;
import util.clases.grlGeneral.DialogoPadre;


public class DlgBusquedaMarca extends DialogoPadre
{
    private CLMarca oCLogica;
    public CEMarca oCEMarca;
  
    public DlgBusquedaMarca(java.awt.Frame parent, boolean modal,String marca)
    {
         super(parent, modal);
         initComponents();
         oCLogica=new CLMarca(TblMarca);
         oCLogica.armarModelo();
         TxtMarca.setText(marca);
         TxtMarca.requestFocus();
     ArrayList<CEMarca> oListMarca=(ArrayList<CEMarca>)CCMarca.consultarListaMarcasPorDescripcion(marca);
     oCLogica.llenarDatosATabla(TblMarca,oListMarca);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TxtMarca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblMarca = new javax.swing.JTable();
        BtnAgregarMarca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Marca:");

        TxtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMarcaActionPerformed(evt);
            }
        });
        TxtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtMarcaKeyReleased(evt);
            }
        });

        TblMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblMarca);
        TblMarca.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblMarca.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow=TblMarca.getSelectedRow();
                oCEMarca=(CEMarca)TblMarca.getValueAt(selectedRow,1);
                dispose();
            }
        });

        BtnAgregarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnAgregarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnAgregarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnAgregarMarca))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMarcaActionPerformed
     String marca=TxtMarca.getText();
     ArrayList<CEMarca> oListMarca=(ArrayList<CEMarca>)CCMarca.consultarListaMarcasPorDescripcion(marca);
     oCLogica.llenarDatosATabla(TblMarca,oListMarca);
    }//GEN-LAST:event_TxtMarcaActionPerformed

    private void TblMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblMarcaMouseClicked
     if(evt.getClickCount()==2)
     {
     oCEMarca=(CEMarca)TblMarca.getValueAt(TblMarca.getSelectedRow(),1);
     dispose();
     }
    }//GEN-LAST:event_TblMarcaMouseClicked

    private void TxtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtMarcaKeyReleased
     if(evt.getKeyCode()==evt.VK_DOWN) {
            int size =TblMarca.getRowCount();
            if(size>0) {
                TblMarca.requestFocus();
                TblMarca.setRowSelectionInterval(0,0);
            }
        }
    }//GEN-LAST:event_TxtMarcaKeyReleased

    private void BtnAgregarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarMarcaActionPerformed
        DlgMantenimientoMarca oDlgMantenimientoMarca=new DlgMantenimientoMarca(null,true,1);
        oDlgMantenimientoMarca.setLocationRelativeTo(null);
        oDlgMantenimientoMarca.setVisible(true);
        TxtMarca.requestFocus();
        String marca=TxtMarca.getText();
     ArrayList<CEMarca> oListMarca=(ArrayList<CEMarca>)CCMarca.consultarListaMarcasPorDescripcion(marca);
     oCLogica.llenarDatosATabla(TblMarca,oListMarca);
}//GEN-LAST:event_BtnAgregarMarcaActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarMarca;
    private javax.swing.JTable TblMarca;
    private javax.swing.JTextField TxtMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
