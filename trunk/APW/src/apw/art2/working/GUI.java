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
 * ParametersInput.java
 *
 * Created on 2009-09-11, 00:09:48
 */

package apw.art2.working;

import apw.art2a.ART2A_Util;
import apw.core.Attribute;
import apw.core.Sample;
import apw.core.Samples;
import apw.core.loader.ARFFLoader;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author nitric
 */
public class GUI extends javax.swing.JFrame {
    private double a, b, c, d, e, alpha, theta, rho;
    private Network network = null;
    private Samples samples = null;
    private JFileChooser fileChooser = new JFileChooser();
    private File file;
    private int dimension;
    private int iterations;
    private static boolean shuffle = true;

    /** Creates new form ParametersInput */
    public GUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void retrieveParameters() {
        try {
            a = Double.parseDouble(jt_a.getText());
            b = Double.parseDouble(jt_b.getText());
            c = Double.parseDouble(jt_c.getText());
            d = Double.parseDouble(jt_d.getText());
            e = Double.parseDouble(jt_e.getText());
            alpha = Double.parseDouble(jt_alpha.getText());
            theta = Double.parseDouble(jt_theta.getText());
            rho = Double.parseDouble(jt_rho.getText());
            iterations = Integer.parseInt(jt_learningIterations.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Illegal number format!");
            return;
        }
        if (a < 0 || b < 0 || c < 0 || d < 0 || e < 0 
                  || alpha < 0 || theta < 0 || rho < 0 || iterations < 0) {
            JOptionPane.showMessageDialog(this,
                    "All parameters should be non-negative."
            );
            return;
        }
        if (theta >= 1 || rho >= 1 || alpha >= 1) {
            JOptionPane.showMessageDialog(this,
                    "'theta', 'alpha', 'vigilance' must be less than 1!"
            );
            return;
        }
        if (c * d > (1 - d)) {
            JOptionPane.showMessageDialog(this,
                    "Parameters 'c' and 'd' must satisfy constraint: cd > 1-d"
            );
            return;
        }
        try {
            samples = new ARFFLoader(new File(jt_samples.getText())).getSamples();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Couldn't read / parse data file!");
        }
        if (!ART2A_Util.checkAttributes(samples)) {
            throw new IllegalArgumentException("All non-class attributes must be numeric!");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jt_a = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jt_b = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jt_c = new javax.swing.JTextField();
        jt_d = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jt_e = new javax.swing.JTextField();
        jt_theta = new javax.swing.JTextField();
        jt_rho = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jt_samples = new javax.swing.JTextField();
        jb_browse = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();
        jb_train = new javax.swing.JButton();
        jcb_shuffle = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jt_alpha = new javax.swing.JTextField();
        jb_help = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jt_learningIterations = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Input parameters");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("a:");

        jt_a.setText("10");
        jt_a.setToolTipText("Fixed weight in F1 layer; usualy 10");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("b:");

        jt_b.setText("10");
        jt_b.setToolTipText("Fixed weight in F1 layer; usualy 10");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("c:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("d:");

        jt_c.setText("0.1");
        jt_c.setToolTipText("Fixed weight used in testing for reset; usually 0.1");

        jt_d.setText("0.9");
        jt_d.setToolTipText("Activation of winner F2 neuron; usually 0.9");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("e:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("theta:");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("rho:");

        jt_e.setText("0");
        jt_e.setToolTipText("Prevents division by zero when vector norm is zero; usually 0");

        jt_theta.setText("0.2");
        jt_theta.setToolTipText("Noise suppresion;  theta < (1/sqrt(dimension))");

        jt_rho.setText("0.9");
        jt_rho.setToolTipText("Vigilance parameter; 0.7 < rho < 1");

        jLabel10.setText("samples:");

        jt_samples.setText("/home/nitric/workspace/NetBeansProjects/trunk/APW/data/iris.arff");

        jb_browse.setText("...");
        jb_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_browseActionPerformed(evt);
            }
        });

        jb_cancel.setText("Cancel");
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });

        jb_train.setText("Train");
        jb_train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_trainActionPerformed(evt);
            }
        });

        jcb_shuffle.setSelected(true);
        jcb_shuffle.setText("Shuffle instances");
        jcb_shuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_shuffleActionPerformed(evt);
            }
        });

        jLabel7.setText("alpha:");

        jt_alpha.setText("0.6");
        jt_alpha.setToolTipText("Constant in formua for initial bottom-up weights; should be between 0.5 and 1.");

        jb_help.setText("Help");
        jb_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_helpActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("learning iterations:");

        jt_learningIterations.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcb_shuffle)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jt_samples, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jb_help)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jb_train)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jb_cancel)
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jt_a)
                                            .addComponent(jt_b)
                                            .addComponent(jt_d, 0, 0, Short.MAX_VALUE)
                                            .addComponent(jt_c, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jt_learningIterations)
                                    .addComponent(jt_e)
                                    .addComponent(jt_alpha)
                                    .addComponent(jt_theta)
                                    .addComponent(jt_rho, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt_samples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jb_browse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jt_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jt_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jt_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jt_alpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jt_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jt_theta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jt_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jt_rho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jt_learningIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcb_shuffle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_help)
                    .addComponent(jb_train)
                    .addComponent(jb_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_jb_cancelActionPerformed

    private void jb_trainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_trainActionPerformed
        retrieveParameters();
        // checking whether all attribute's (excluding class) are real numbers:
        ArrayList<Attribute> atts = samples.getAtts();
        int attCount = atts.size();
        boolean labeled = samples.getClassAttributeIndex() != -1;
        dimension = labeled ? attCount - 1 : attCount;
        network = new Network(dimension, a, b, c, d, e, alpha, rho, theta, iterations);
        trainNetwork(network, samples);
    }//GEN-LAST:event_jb_trainActionPerformed

    private void jb_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_browseActionPerformed
        int wynik = fileChooser.showOpenDialog(this);
        if (wynik == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            jt_samples.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jb_browseActionPerformed

    private void jcb_shuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_shuffleActionPerformed
        shuffle = jcb_shuffle.isSelected();
    }//GEN-LAST:event_jcb_shuffleActionPerformed

    private void jb_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_helpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jb_helpActionPerformed


    private void trainNetwork(Network network, Samples samples) {
        HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
        ArrayList<Integer> shuffled = shuffle(samples.size());
        int attsCount = samples.getAtts().size();
        int classIndex = samples.getClassAttributeIndex();
        int attCounter = 0;
        Sample sample;
        int cluster;
        int index;
        for (int i = 0; i < samples.size(); i++) {
            sample = samples.get(index = shuffled.get(i));
            attCounter = 0;
            // Create table:
            double[] input = new double[dimension];
            for (int j = 0; j < attsCount; j++)
                if (j != classIndex)
                    input[attCounter++] = (Double) sample.get(j);
            cluster = network.process(input);
            System.out.println("Sample [" + shuffled.get(i) + "] --> " + cluster);
            results.put(index, cluster);
        }
        System.out.println("Training completed.");
        showResults(results, samples);
    }

    private void showResults(HashMap<Integer, Integer> results, Samples samples) {
        File f = null;
        HashMap<String, Integer> clusters = new HashMap<String, Integer>();
        String key;
        try {
            FileWriter out = new FileWriter(f = new File("results" + System.currentTimeMillis() + ".htm"));
            out.append("<html><title>Clustering resutls </title><body>\n");
            out.append("<center><b>Parameters</b><br><br>\n");
            // Parameters:
            out.append("<table cellpadding=\"5\" cellspacing=\"5\" border=\"thin\">\n");
            out.append("<tr align=\"center\"><td>Name</td><td>Value</td>");
            out.append("<tr align=\"center\"><td>a</td><td>" + a + "</td></tr>");
            out.append("<tr align=\"center\"><td>b</td><td>" + b + "</td></tr>");
            out.append("<tr align=\"center\"><td>c</td><td>" + c + "</td></tr>");
            out.append("<tr align=\"center\"><td>d</td><td>" + d + "</td></tr>");
            out.append("<tr align=\"center\"><td>e</td><td>" + e + "</td></tr>");
            out.append("<tr align=\"center\"><td>alpha</td><td>" + alpha + "</td></tr>");
            out.append("<tr align=\"center\"><td>rho</td><td>" + rho + "</td></tr>");
            out.append("<tr align=\"center\"><td>theta</td><td>" + theta + "</td></tr>");
            out.append("</table><br><br>\n");
            out.append("<b>Clustering results</b><br><br>\n");
            out.append("<table cellpadding=\"5\" cellspacing=\"5\" border=\"thin\"><tr>\n");
            out.append("<td><center>Sample Id</center></td>");
            out.append("<td><center>Whole sample</center></td>");
            out.append("<td><center>Cluster</td></center></tr>");
            // Cluster for each sample:
            for (int i = 0; i < samples.size(); i++) {
                out.append(
                        "<tr align=\"center\"><td>" + (i + 1) + "</td>"
                        + "<td>" + samples.get(i) + "</td>"
                        + "<td>" + results.get(i) + "</td></tr>\n");
                key = samples.get(i).get(samples.getClassAttributeIndex()) + "_" + results.get(i);
                if (clusters.containsKey(key))  {
                    clusters.put(key, clusters.get(key) + 1);
                }
                else {
                    clusters.put(key, 1);
                }
            }
            out.append("</table><br><br>");
            // Summary:
            if (samples.getClassAttributeIndex() != -1) {
                for (String str : clusters.keySet())
                    out.append(str + " --> " + clusters.get(str) + "<br>");
            }
            out.append("</center></body></html>");
            out.close();
            Desktop.getDesktop().open(f);
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ArrayList<Integer> shuffle(int size) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (shuffle) {
            HashSet<Integer> shuffled = new HashSet<Integer>();
            Random r = new Random();
            while(result.size() != size) {
                int i = r.nextInt(size);
                if (shuffled.add(i)) {
                    result.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < size; i++)
                result.add(i);
        }
        return result;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jb_browse;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JButton jb_help;
    private javax.swing.JButton jb_train;
    private javax.swing.JCheckBox jcb_shuffle;
    private javax.swing.JTextField jt_a;
    private javax.swing.JTextField jt_alpha;
    private javax.swing.JTextField jt_b;
    private javax.swing.JTextField jt_c;
    private javax.swing.JTextField jt_d;
    private javax.swing.JTextField jt_e;
    private javax.swing.JTextField jt_learningIterations;
    private javax.swing.JTextField jt_rho;
    private javax.swing.JTextField jt_samples;
    private javax.swing.JTextField jt_theta;
    // End of variables declaration//GEN-END:variables

}
