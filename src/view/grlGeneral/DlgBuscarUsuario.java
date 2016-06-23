package view.grlGeneral;

import controller.grlGeneral.CCUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CEUsuario;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;

public class DlgBuscarUsuario extends DialogoPadre {

    public DlgBuscarUsuario(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.configurarTablaHistorial();
        TxtUsuario.requestFocus();
        TblResultados.getTableHeader().setReorderingAllowed(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        TxtUsuario = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta de Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Ingrese los datos:");

        TxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtUsuarioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(TxtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblResultados.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TblResultados.setModel( new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario","Empleado"
            }
        ) {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblResultados.setRowHeight(22);
        TblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblResultados);
        TblResultados.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ProjSave");
        TblResultados.getActionMap().put("ProjSave", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int fila = TblResultados.getSelectedRow();
                    if ((fila > -1))
                    {
                        dispose();
                    }
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    private void limpiarTabla()
    {
        ArrayListTableModel oModelTbl=(ArrayListTableModel)TblResultados.getModel();
        int i=oModelTbl.getRowCount();
        for(int a=0;a<i;a++)
        {
           oModelTbl.removeRow(0);
        }
    }
    public DlgBuscarUsuario(java.awt.Frame parent, boolean modal,String parametro)
    {
        super(parent, modal);
        initComponents();
        TxtUsuario.setText(parametro);
        buscarUsuario();
        TxtUsuario.requestFocus();
        this.configurarTablaHistorial();
    }
    public CEUsuario getUsuario()
    {
        int selectedRow=TblResultados.getSelectedRow();
        CEUsuario oCEUsuario=null;
        if(selectedRow!=-1)
        {
        oCEUsuario=(CEUsuario)TblResultados.getValueAt(selectedRow,0);
        dispose();
        }
        return oCEUsuario;
    }

    private void cargarTabla(List<CEUsuario>  oVctUsuario)
    {
        limpiarTabla();
        ArrayList oList;
        if(oVctUsuario!=null)
        {
            int size=oVctUsuario.size();
            for(int a=0;a<size;a++)
            {
            oList=new ArrayList();
            oList.add(oVctUsuario.get(a));
            oList.add(oVctUsuario.get(a).getNombreCompleto());
            ((ArrayListTableModel)TblResultados.getModel()).addRow(oList);
            }
         }
    }

    private void configurarTablaHistorial()
        {
            TblResultados.getColumnModel().getColumn(0).setMaxWidth(150);
            TblResultados.getColumnModel().getColumn(0).setMinWidth(150);
            TblResultados.getColumnModel().getColumn(1).setMaxWidth(250);
            TblResultados.getColumnModel().getColumn(1).setMinWidth(250);
        }

    private void TxtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsuarioKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarUsuario();
        }
        if(evt.getKeyCode()==evt.VK_DOWN) {
            int size =TblResultados.getRowCount();
            if(size>0) {
                TblResultados.requestFocus();
                TblResultados.setRowSelectionInterval(0,0);
            }
        }
}//GEN-LAST:event_TxtUsuarioKeyReleased
        private void buscarUsuario()
        {
            String datoUsuario=TxtUsuario.getText().trim();
                CCUsuario oCCUsuario=new CCUsuario();
                List<CEUsuario> oVctUsuario = oCCUsuario.buscarUsuario(datoUsuario);
                cargarTabla(oVctUsuario);
        }
    private void TblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblResultadosMouseClicked
        if(evt.getClickCount()==2)
        {
            int FilaSeleccionada =TblResultados.getSelectedRow();
            if(FilaSeleccionada!=-1)
            {
                dispose();
            }
        }
}//GEN-LAST:event_TblResultadosMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(evt.getClickCount()==2)
    {
        getUsuario();
    }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblResultados;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
