/*
 *  Copyright (c) 2009, Wrocław University of Technology
 *  All rights reserved.
 *  Redistribution  and use in source  and binary  forms,  with or
 *  without modification,  are permitted provided that the follow-
 *  ing conditions are met:
 * 
 *   • Redistributions of source code  must retain the above copy-
 *     right  notice, this list  of conditions and  the  following
 *     disclaimer.
 *   • Redistributions  in binary  form must  reproduce the  above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the  documentation and/or other mate provided
 *     with the distribution.
 *   • Neither  the name of the  Wrocław University of  Technology
 *     nor the names of its contributors may be used to endorse or
 *     promote products derived from this  software without speci-
 *     fic prior  written permission.
 * 
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRI-
 *  BUTORS "AS IS" AND ANY  EXPRESS OR IMPLIED WARRANTIES, INCLUD-
 *  ING, BUT NOT  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTA-
 *  BILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 *  NO EVENT SHALL THE COPYRIGHT HOLDER OR  CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT,  INCIDENTAL, SPECIAL,  EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCURE-
 *  MENT OF SUBSTITUTE  GOODS OR SERVICES;  LOSS OF USE,  DATA, OR
 *  PROFITS; OR BUSINESS  INTERRUPTION) HOWEVER  CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *  TORT (INCLUDING  NEGLIGENCE  OR  OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSI-
 *  BILITY OF SUCH DAMAGE.
 */ 

/*
 * JD_ART2_Help.java
 *
 * Created on 2009-10-02, 12:56:46
 */

package apw.art2.working;

import java.awt.Dimension;

/**
 *
 * @author nitric
 */
public class JD_ART2_Help extends javax.swing.JDialog {

    /** Creates new form JD_ART2_Help */
    public JD_ART2_Help(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(new Dimension(470, 500));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_title = new javax.swing.JLabel();
        jl_abcd = new javax.swing.JLabel();
        jl_e = new javax.swing.JLabel();
        jl_alpha = new javax.swing.JLabel();
        jl_theta = new javax.swing.JLabel();
        jl_rho = new javax.swing.JLabel();
        jl_iterations = new javax.swing.JLabel();
        jb_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jl_title.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_title.setText("Help for: ART-2 Parameters");

        jl_abcd.setText("<html><body align=\"justify\">a, b, c, d - default values for this parameters should work properly, You are discouraged to change them!</body></html>");

        jl_e.setText("<html><body align=\"justify\"> e - this parameter is only used to prevent division by zero. You should set this parameter to small positive value if you think that some of Your input vectors can be zero vectors after denoising.</body></html>");

        jl_alpha.setText("<html><body align=\"justify\">alpha - learning parameter. Value should be between 0 and 1. Greater value forces network to adjust weights values so that they are more suitable for the actual input vector and 'forget' information about previously presented vectors. Lower values causes that network retains this information, but learns slowlier. Values 0.2 - 0.6 should work properly, but feel free to experiment with other values.</body></html>");

        jl_theta.setText("<html><body align=\"justify\">theta - denoising parameter. Set it to non-zero value (theta < 1) when You think there might be some noised vectors. Noise vector is supposed small positive values after normalizing - these values will be treated as zeroes if they are greater than value of theta parameter.</body></html>");

        jl_rho.setText("<html><body align=\"justify\">rho - vigilance parameter. It should be more than [sqrt(2) / 2] and lower than 1. Greater value forces network to create more classes.</body></html>");

        jl_iterations.setText("learning iterations - values 50 to 200 should be sufficient.");

        jb_close.setText("Close");
        jb_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .addComponent(jb_close)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jl_title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_rho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(jl_theta, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jl_abcd, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jl_e, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jl_alpha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jl_iterations, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title)
                .addGap(26, 26, 26)
                .addComponent(jl_abcd)
                .addGap(18, 18, 18)
                .addComponent(jl_e)
                .addGap(18, 18, 18)
                .addComponent(jl_alpha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jl_theta)
                .addGap(18, 18, 18)
                .addComponent(jl_rho)
                .addGap(18, 18, 18)
                .addComponent(jl_iterations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jb_close)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_closeActionPerformed
        dispose();
    }//GEN-LAST:event_jb_closeActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JD_ART2_Help dialog = new JD_ART2_Help(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jb_close;
    private javax.swing.JLabel jl_abcd;
    private javax.swing.JLabel jl_alpha;
    private javax.swing.JLabel jl_e;
    private javax.swing.JLabel jl_iterations;
    private javax.swing.JLabel jl_rho;
    private javax.swing.JLabel jl_theta;
    private javax.swing.JLabel jl_title;
    // End of variables declaration//GEN-END:variables

}