/*
 * TODO: add license
 */
package org.cakephp.netbeans.ui.logicalview;

import org.cakephp.netbeans.CakePhpFrameworkProvider;
import org.cakephp.netbeans.util.CakePhpUtils;
import org.netbeans.api.project.Project;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author junichi11
 */
@NodeFactory.Registration(projectType = "org-netbeans-modules-php-project", position = 800)
public class HelperNodeFactoryImpl implements NodeFactory {

    @Override
    public NodeList createNodes(Project prj) {
        PhpModule module = prj.getLookup().lookup(PhpModule.class);
        // check cake project
        if(!CakePhpFrameworkProvider.getInstance().isInPhpModule(module)){
            return NodeFactorySupport.fixedNodeList();
        }
        
        FileObject targetDir = CakePhpUtils.getDirectory(module, CakePhpUtils.DIR.APP, CakePhpUtils.FILE.HELPER, null);
        if (targetDir != null) {
            try {
                HelperNode node = new HelperNode(targetDir);
                return NodeFactorySupport.fixedNodeList(node);
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return NodeFactorySupport.fixedNodeList();
    }
}
