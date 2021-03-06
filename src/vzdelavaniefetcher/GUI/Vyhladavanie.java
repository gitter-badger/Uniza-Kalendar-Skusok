/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vzdelavaniefetcher.GUI;

import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vzdelavaniefetcher.FetchException;
import vzdelavaniefetcher.Fetcher;
import vzdelavaniefetcher.Predmet;
import vzdelavaniefetcher.Termin;
import vzdelavaniefetcher.tools.GUISwingTools;
import static vzdelavaniefetcher.tools.ThreadingTools.runInThreadingPool;
import static vzdelavaniefetcher.tools.ThreadingTools.runOnUIThread;

/**
 *
 * @author Unlink
 */
public class Vyhladavanie extends javax.swing.JDialog {

    private final List<Predmet> aPredmety;
    
    /**
     * Creates new form Vyhladavanie
     * @param parent
     * @param aPredmety
     */
    public Vyhladavanie(java.awt.Frame parent, final List<Predmet> aPredmety) {
        super(parent, false);
        initComponents();
        this.aPredmety = aPredmety;
        jTextField2.setEnabled(false);
        jButton1.setEnabled(false);
        jLabel2.setText("Nahrávam zoznamy");
        setLocationRelativeTo(parent);
        runInThreadingPool(new Runnable() {

            @Override
            public void run() {
                try {
                    for (final Predmet p:aPredmety){
                        runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                jLabel2.setText("Nahrávam zoznamy príhlasených - "+p.getNazov());
                            }
                        }, true);
                        for(Termin t:p){
                            if (t.getDatum().compareTo(new Date()) == 1){
                                if (t.getPrihlasenyStudenti() == null){
                                    Fetcher.dajInstanciu().featch(t, null);
                                }
                            }
                        }
                    }
                    runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            jTextField2.setEnabled(true);
                            jButton1.setEnabled(true);
                            jLabel2.setText(null);
                        }
                    }, true);

                } catch (FetchException ex) {
                    GUISwingTools.displayErrorMessage(Vyhladavanie.this, "Nepodarilo sa načítat zoznam prihlásených\n"+ex);
                    Vyhladavanie.this.dispose();
                }
            }
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vyhľadávanie");

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));

        jButton1.setText("Hľadaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Meno:");

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0x82878f)));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel4);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField2.getText().isEmpty()){
            GUISwingTools.displayErrorMessage(this, "Zabudli ste zadať meno, ktoré sa ma vyhľadať");
        }
        else {
            Kalendar.generujZoznamPredmetov(jPanel4, aPredmety, new Kalendar.TerminyFilter() {
                @Override
                public boolean filter(Termin termin) {
                    return (termin.getDatum().compareTo(new Date()) == 1 && termin.getPrihlasenyStudenti().toLowerCase().contains(jTextField2.getText().toLowerCase()));
                }
            }, false, null);
            
            if (jPanel4.getComponentCount() == 0) {
                JLabel mm = new JLabel("No Results");
                mm.setFont(new java.awt.Font("Tahoma", 1, 15));
                JPanel jp = new JPanel();
                jp.add(mm);
                jPanel4.add(jp);
            }
            jPanel4.validate();
            jPanel4.repaint();
            jScrollPane1.validate();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jButton1ActionPerformed(null);
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
