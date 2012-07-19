/*
 * NewProjectConfigurationPanel.java
 *
 * Created on 2011/09/27
 */
package org.cakephp.netbeans.ui.wizards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JList;
import javax.swing.JRadioButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.Exceptions;

/**
 *
 * @author junichi11
 */
public class NewProjectConfigurationPanel extends javax.swing.JPanel {

    private static final String GITHUB_API_REPOS_TAGS = "https://api.github.com/repos/cakephp/cakephp/tags"; // NOI18N
    private static final long serialVersionUID = 7874450246517944114L;
    private Map<String, String> tagsMap = new HashMap<String, String>();
    private String errorMessage;

    /**
     * Creates new form NewProjectConfigurationPanel
     */
    public NewProjectConfigurationPanel() {
        initComponents();
        unzipRadioButton.setSelected(true);
        try {
            // Get JSON
            URL tagsJson = new URL(GITHUB_API_REPOS_TAGS);
            BufferedReader reader = new BufferedReader(new InputStreamReader(tagsJson.openStream(), "UTF-8")); // NOI18N
            StringBuilder contents = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                contents.append(str);
            }

            JSONArray json = new JSONArray(contents.toString());
            String[] tagsArray = new String[json.length()];
            for (int i = 0; i < json.length(); i++) {
                JSONObject jObject = (JSONObject) json.get(i);
                tagsArray[i] = jObject.getString("name"); // NOI18N
                tagsMap.put(jObject.getString("name"), jObject.getString("zipball_url")); // NOI18N
            }
            Arrays.sort(tagsArray, new Comparator<String>() {

                public int compare(String a, String b) {
                    String[] aArray = a.split("[., -]"); // NOI18N
                    String[] bArray = b.split("[., -]"); // NOI18N
                    for (int i = 0; i < aArray.length; i++) {
                        try {
                            Integer aInt = Integer.parseInt(aArray[i]);
                            Integer bInt = Integer.parseInt(bArray[i]);
                            if (aInt == bInt) {
                                continue;
                            } else {
                                return bInt - aInt;
                            }
                        } catch (NumberFormatException ex) {
                            return 1;
                        }
                    }
                    return 1;
                }
            });
            versionList.setListData(tagsArray);
            versionList.setSelectedIndex(0);
        } catch (JSONException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            errorMessage = "Is not connected to the network.";
        }
    }

    public Map<String, String> getTagsMap() {
        return tagsMap;
    }

    public JList getVersionList() {
        return versionList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public JRadioButton getUnzipRadioButton() {
        return unzipRadioButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        versionList = new javax.swing.JList();
        versionLabel = new javax.swing.JLabel();
        unzipRadioButton = new javax.swing.JRadioButton();
        gitCloneRadioButton = new javax.swing.JRadioButton();

        versionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        versionList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(versionList);

        versionLabel.setText(org.openide.util.NbBundle.getMessage(NewProjectConfigurationPanel.class, "NewProjectConfigurationPanel.versionLabel.text")); // NOI18N

        buttonGroup.add(unzipRadioButton);
        unzipRadioButton.setText(org.openide.util.NbBundle.getMessage(NewProjectConfigurationPanel.class, "NewProjectConfigurationPanel.unzipRadioButton.text")); // NOI18N

        buttonGroup.add(gitCloneRadioButton);
        gitCloneRadioButton.setText(org.openide.util.NbBundle.getMessage(NewProjectConfigurationPanel.class, "NewProjectConfigurationPanel.gitCloneRadioButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gitCloneRadioButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unzipRadioButton)
                    .addComponent(versionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unzipRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(versionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gitCloneRadioButton)
                .addContainerGap(99, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton gitCloneRadioButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton unzipRadioButton;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JList versionList;
    // End of variables declaration//GEN-END:variables
}
