/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ComplexCreatorFrame.java
 *
 * Created on 2009-05-04, 12:11:51
 */

package apw.augmentedLearning.gui;

import apw.augmentedLearning.logic.Complex;
import javax.swing.JComponent;
import apw.augmentedLearning.logic.DataFile;
import apw.augmentedLearning.logic.RuleAcquisition;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nitric
 */
public class ComplexCreatorFrame extends javax.swing.JFrame {

    private DataFile dataFile;
    private boolean conclusionMode = false;          // rule = if (assumption) then (conclusion)
    private LoadingSamples_Step3 parent = null;
    private RuleAcquisition acqusitor;
    private int currentSample;
    private PreviewOfSample preview;

    public ComplexCreatorFrame() {
        myInitComponents(false);
    }

    public Complex getComplex() {
        return complexCreatorPanel.getComplex();
    }

    public ComplexCreatorFrame(DataFile dataFile, boolean conclusionMode, LoadingSamples_Step3 parent) {
        this.dataFile = dataFile;
        this.conclusionMode = conclusionMode;
        this.parent = parent;
        myInitComponents(conclusionMode);
        jb_cancel.setVisible(false);
        jb_nextComplex.setEnabled(false);
        setLocationRelativeTo(parent);
    }

    public ComplexCreatorFrame(DataFile dataFile, int currentSample, RuleAcquisition parent) {
        this.dataFile = dataFile;
        this.conclusionMode = false;
        this.acqusitor = parent;
        this.currentSample = currentSample;
        myInitComponents(conclusionMode);
        setLocationRelativeTo(null);
        // Additional operations
        jb_nextComplex.setEnabled(false);
        jb_ok.setText("Gotowe");
        complexCreatorPanel.setSample(currentSample);

        StringBuilder sb = new StringBuilder();
        sb.append("Numer linijki pliku z danymi: " + currentSample + "\n");
        Object[] sample = dataFile.getRawObjects()[currentSample];
        ArrayList<String> names = dataFile.getAttributesNames();
        for (int i = 0; i < dataFile.getAttributesCount(); i++) {
            sb.append(names.get(i) + " = " + sample[i] + "\n");
        }
        preview = new PreviewOfSample(sb.toString());
    }

    private void myInitComponents(boolean conclusionMode) {
        jsp_ccpScrollPane = new javax.swing.JScrollPane();
        complexCreatorPanel = dataFile != null ?
            new apw.augmentedLearning.gui.ComplexCreatorPanel(this, dataFile, !conclusionMode)
            :
            new apw.augmentedLearning.gui.ComplexCreatorPanel();
        jl_title = new javax.swing.JLabel();
        jb_ok = new javax.swing.JButton();
        jb_nextComplex = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();

        JComponent component = complexCreatorPanel.amount > 6 ?
            jsp_ccpScrollPane : complexCreatorPanel;

        int height = complexCreatorPanel.amount > 6 ? 236 : 40 * complexCreatorPanel.amount;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jsp_ccpScrollPane.setViewportView(complexCreatorPanel);

        jl_title.setFont(new java.awt.Font("Tahoma", 1, 14));
        jl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        if (!conclusionMode) {
            jb_nextComplex.setText("Następny kompleks");
            jb_ok.setText("Przejdź do części przesłankowej");
            jl_title.setText("Wskaż dozwolone wartości atrybutów dla kompleksu:");
        }
        else {
            jb_nextComplex.setVisible(false);
            jb_ok.setText("Gotowe");
            jl_title.setText("Wskaż klasę dla krotek pokrytych wprowadzoną regułą:");
        }
        jb_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_okActionPerformed(evt);
            }
        });
        jb_nextComplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nextComplexActionPerformed(evt);
            }
        });
        jb_cancel.setText("Anuluj");
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(component, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addComponent(jl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_nextComplex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_ok)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(component, height, height, height)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_cancel)
                    .addComponent(jb_ok)
                    .addComponent(jb_nextComplex))
                .addContainerGap())
        );
        pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp_ccpScrollPane = new javax.swing.JScrollPane();
        complexCreatorPanel = new apw.augmentedLearning.gui.ComplexCreatorPanel(null, dataFile, true);
        jl_title = new javax.swing.JLabel();
        jb_ok = new javax.swing.JButton();
        jb_nextComplex = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wprowadzanie kompleksu");

        jsp_ccpScrollPane.setViewportView(complexCreatorPanel);

        jl_title.setFont(new java.awt.Font("Tahoma", 1, 14));
        jl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_title.setText("Wskaż dozwolone wartości atrybutów (kompleks) części przesłankowej:");

        jb_ok.setText("Przejdź do części przesłankowej");
        jb_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_okActionPerformed(evt);
            }
        });

        jb_nextComplex.setText("Jeszcze jeden kompleks");
        jb_nextComplex.setEnabled(false);
        jb_nextComplex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_nextComplexActionPerformed(evt);
            }
        });

        jb_cancel.setText("Anuluj");
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsp_ccpScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addComponent(jl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_nextComplex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_ok)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsp_ccpScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_ok)
                    .addComponent(jb_nextComplex)
                    .addComponent(jb_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_okActionPerformed
        // parent == null -> means that inserted rule is for particular sample with nulls
        final Complex complex = getComplex();
        if (complex == null) {
            JOptionPane.showMessageDialog(null, "Reguła nie może zawierać kompleksu uniwersalnego!");
            return;
        }
        if (parent != null)
            parent.addComplex(complex, true, conclusionMode);
        else {
            new Thread() {
                @Override
                public void run() {
                    additionalRuleConfirmed(complex);
                }
            }.start();
        }
}//GEN-LAST:event_jb_okActionPerformed

    private void jb_nextComplexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_nextComplexActionPerformed
        final Complex complex = getComplex();
        if (complex == null) {
            JOptionPane.showMessageDialog(null, "Reguła nie może zawierać kompleksu uniwersalnego!");
            return;
        }
        parent.addComplex(complex, conclusionMode, false);
    }//GEN-LAST:event_jb_nextComplexActionPerformed

    private void jb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelActionPerformed
        additionalRuleConfirmed(null);
    }//GEN-LAST:event_jb_cancelActionPerformed

    private void additionalRuleConfirmed(Complex c) {
        preview.dispose();
        dispose();
        acqusitor.addComplex(c, currentSample);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private apw.augmentedLearning.gui.ComplexCreatorPanel complexCreatorPanel;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JButton jb_nextComplex;
    private javax.swing.JButton jb_ok;
    private javax.swing.JLabel jl_title;
    private javax.swing.JScrollPane jsp_ccpScrollPane;
    // End of variables declaration//GEN-END:variables

}
