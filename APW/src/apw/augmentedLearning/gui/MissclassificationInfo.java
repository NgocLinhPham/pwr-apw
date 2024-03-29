/*
 * MissclassificationInfo.java
 *
 * Created on 2009-05-23, 20:19:34
 */

package apw.augmentedLearning.gui;

/**
 *
 * @author Nitric
 */
public class MissclassificationInfo extends javax.swing.JPanel {
    private MissclassificationInfoFrame parent;
    private int action = 1;
    private int missclassifiedCount;

    /** Creates new form MissclassificationInfo */
    public MissclassificationInfo() {
        initComponents();
    }

    public MissclassificationInfo(MissclassificationInfoFrame parent, int missclassifiedCount) {
        this.parent = parent;
        this.missclassifiedCount = missclassifiedCount;
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

        bg_action = new javax.swing.ButtonGroup();
        jl_info = new javax.swing.JLabel();
        jrb_deleteSamples = new javax.swing.JRadioButton();
        jrb_correctingRule = new javax.swing.JRadioButton();
        jrb_cancel = new javax.swing.JRadioButton();
        jrb_showSamples = new javax.swing.JRadioButton();
        jb_ok = new javax.swing.JButton();

        jl_info.setFont(new java.awt.Font("Tahoma", 1, 11));
        jl_info.setText("<html><center>Wprowadzona reguła została skonfrontowana z&nbsp;przykładami w&nbsp;bazie i&nbsp;zostały znalezione przykłady (" + missclassifiedCount + "), które są błędnie przez nią klasyfikowane. Co zrobić?</center></html>");

        bg_action.add(jrb_deleteSamples);
        jrb_deleteSamples.setSelected(true);
        jrb_deleteSamples.setText("Moja reguła jest pewna - usuń błędne przykłady");
        jrb_deleteSamples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_deleteSamplesActionPerformed(evt);
            }
        });

        bg_action.add(jrb_correctingRule);
        jrb_correctingRule.setText("Chcę poprawić regułę");
        jrb_correctingRule.setEnabled(false);
        jrb_correctingRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_correctingRuleActionPerformed(evt);
            }
        });

        bg_action.add(jrb_cancel);
        jrb_cancel.setText("Nie zapamiętuj tej reguły");
        jrb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_cancelActionPerformed(evt);
            }
        });

        bg_action.add(jrb_showSamples);
        jrb_showSamples.setText("Chcę zobaczyć te przykłady");
        jrb_showSamples.setEnabled(false);
        jrb_showSamples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_showSamplesActionPerformed(evt);
            }
        });

        jb_ok.setText("Dalej");
        jb_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrb_correctingRule)
                    .addComponent(jrb_showSamples)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrb_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jb_ok))
                    .addComponent(jl_info, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(jrb_deleteSamples))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrb_deleteSamples)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrb_showSamples, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrb_correctingRule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_cancel)
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_ok)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jrb_deleteSamplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_deleteSamplesActionPerformed
        action = 1;
    }//GEN-LAST:event_jrb_deleteSamplesActionPerformed

    private void jrb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_cancelActionPerformed
        action = 4;
    }//GEN-LAST:event_jrb_cancelActionPerformed

    private void jrb_showSamplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_showSamplesActionPerformed
        action = 2;
    }//GEN-LAST:event_jrb_showSamplesActionPerformed

    private void jrb_correctingRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_correctingRuleActionPerformed
        action = 3;
    }//GEN-LAST:event_jrb_correctingRuleActionPerformed

    private void jb_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_okActionPerformed
        parent.action(action);
    }//GEN-LAST:event_jb_okActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_action;
    private javax.swing.JButton jb_ok;
    private javax.swing.JLabel jl_info;
    private javax.swing.JRadioButton jrb_cancel;
    private javax.swing.JRadioButton jrb_correctingRule;
    private javax.swing.JRadioButton jrb_deleteSamples;
    private javax.swing.JRadioButton jrb_showSamples;
    // End of variables declaration//GEN-END:variables

}
