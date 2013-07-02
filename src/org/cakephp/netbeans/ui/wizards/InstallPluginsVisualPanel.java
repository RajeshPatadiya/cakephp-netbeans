/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2012 Sun Microsystems, Inc.
 */
package org.cakephp.netbeans.ui.wizards;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.cakephp.netbeans.options.CakePhpOptions;
import org.cakephp.netbeans.options.CakePhpPlugin;
import org.cakephp.netbeans.preferences.CakePreferences;
import org.cakephp.netbeans.util.CakeVersion;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.openide.util.NbBundle;

public final class InstallPluginsVisualPanel extends JPanel {

    private static final long serialVersionUID = 4577339233353249978L;
    private InstallPluginTableModel model = new InstallPluginTableModel();

    /**
     * Creates new form InstallPluginsVisualPanel
     */
    public InstallPluginsVisualPanel(PhpModule pm) {
        model.setPlugins(CakePhpOptions.getInstance().getPlugins());
        initComponents();
        String appName = CakePreferences.getAppName(pm);
        if (CakeVersion.getInstance(pm).getMajor() >= 2) {
            installPathTextField.setText(appName + "/Plugin"); // NOI18N
        } else {
            installPathTextField.setText(appName + "/plugins"); // NOI18N
        }

        pluginTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        pluginTable.setSize(new Dimension(600, 600));
        TableColumnModel columnModel = pluginTable.getColumnModel();
        TableColumn column = columnModel.getColumn(InstallPluginTableModel.INSTALL);
        column.setMinWidth(45);
        column.setPreferredWidth(45);
        column = columnModel.getColumn(InstallPluginTableModel.NAME);
        column.setMinWidth(100);
        column.setPreferredWidth(100);
        column = columnModel.getColumn(InstallPluginTableModel.URL);
        column.setMinWidth(450);
        column.setPreferredWidth(450);
    }

    @Override
    public String getName() {
        return "Install plugins...";
    }

    public String getInstallPathTextField() {
        return installPathTextField.getText();
    }

    public List<CakePhpPlugin> getCakePhpPluginList() {
        return model.getPlugins();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pluginTable = new javax.swing.JTable();
        installPathTextField = new javax.swing.JTextField();
        installPathLabel = new javax.swing.JLabel();

        pluginTable.setModel(model);
        pluginTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(pluginTable);

        installPathTextField.setText(org.openide.util.NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.installPathTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(installPathLabel, org.openide.util.NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.installPathLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(installPathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(installPathTextField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(installPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(installPathLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel installPathLabel;
    private javax.swing.JTextField installPathTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable pluginTable;
    // End of variables declaration//GEN-END:variables

    private class InstallPluginTableModel extends AbstractTableModel {

        private static final int INSTALL = 0;
        private static final int NAME = 1;
        private static final int URL = 2;
        private static final long serialVersionUID = 1795493071005593192L;
        private List<CakePhpPlugin> plugins;
        private String[] column;

        public InstallPluginTableModel() {
            column = new String[]{
                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title0"),
                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title1"),
                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title2")
            };
            plugins = new ArrayList<CakePhpPlugin>();
        }

        @Override
        public int getRowCount() {
            return plugins.size();
        }

        @Override
        public int getColumnCount() {
            return column.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            CakePhpPlugin plugin = plugins.get(rowIndex);
            if (columnIndex == INSTALL) {
                return plugin.isInstall();
            } else if (columnIndex == NAME) {
                return plugin.getName();
            } else if (columnIndex == URL) {
                return plugin.getUrl();
            }
            return null;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return column[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case INSTALL:
                    return Boolean.class;
                case NAME:
                case URL:
                    return String.class;
                default:
                    return Object.class;
            }
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == INSTALL) {
                return true;
            }
            return false;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (col == INSTALL) {
                plugins.get(row).setInstall(!plugins.get(row).isInstall());
                fireTableCellUpdated(row, col);
            }
        }

        public void setPlugins(List<CakePhpPlugin> plugins) {
            this.plugins = plugins;
        }

        public List<CakePhpPlugin> getPlugins() {
            return plugins;
        }
    }
}
