/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgComplementoCompDevolucion.java
 *
 * Created on 08-abr-2012, 15:44:23
 */

package view.vtaVenta;

import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Morales
 */
public class DlgComplementoCompDevolucion extends DialogoPadre {

    /** Creates new form DlgComplementoCompDevolucion */
    public DlgComplementoCompDevolucion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CbxConcepto = new ComboBox.ComboBox();
        label10 = new Label.Label();
        CbxConcepto1 = new ComboBox.ComboBox();
        label11 = new Label.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        CbxConcepto.setFont(new java.awt.Font("Verdana", 1, 11));
        CbxConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxConceptoActionPerformed(evt);
            }
        });

        label10.setText("Seleccione La Caja de donde Se Devolvera El dinero :");
        label10.setFont(new java.awt.Font("Verdana", 1, 11));

        CbxConcepto1.setFont(new java.awt.Font("Verdana", 1, 11));
        CbxConcepto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxConcepto1ActionPerformed(evt);
            }
        });

        label11.setText("Seleccione El Almacen donde se devolveran los Productos:");
        label11.setFont(new java.awt.Font("Verdana", 1, 11));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CbxConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CbxConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxConceptoActionPerformed
//        HabilitarcolumnasporConcepto();
}//GEN-LAST:event_CbxConceptoActionPerformed

    private void CbxConcepto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxConcepto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxConcepto1ActionPerformed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ComboBox.ComboBox CbxConcepto;
    private ComboBox.ComboBox CbxConcepto1;
    private Label.Label label10;
    private Label.Label label11;
    // End of variables declaration//GEN-END:variables

}
