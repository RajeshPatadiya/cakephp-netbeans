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
package org.cakephp.netbeans.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.UiUtils;
import org.netbeans.modules.php.spi.framework.commands.FrameworkCommand;
import org.netbeans.modules.php.spi.framework.commands.FrameworkCommandSupport;
import org.netbeans.modules.php.spi.framework.commands.FrameworkCommandSupport.CommandDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public final class CakePhpCommandSupport extends FrameworkCommandSupport {

    public CakePhpCommandSupport(PhpModule phpModule) {
        super(phpModule);
    }

    @NbBundle.Messages("CakePhpCommandSupport.name=CakePHP")
    @Override
    public String getFrameworkName() {
        return Bundle.CakePhpCommandSupport_name();
    }

    @Override
    public void runCommand(CommandDescriptor commandDescriptor, Runnable postExecution) {
        String[] commands = commandDescriptor.getFrameworkCommand().getCommands();
        String[] commandParams = commandDescriptor.getCommandParams();
        List<String> params = new ArrayList<>(commands.length + commandParams.length);
        params.addAll(Arrays.asList(commands));
        params.addAll(Arrays.asList(commandParams));
        try {
            CakeScript.forPhpModule(phpModule, false).runCommand(phpModule, params, postExecution);
        } catch (InvalidPhpExecutableException ex) {
            UiUtils.invalidScriptProvided(ex.getLocalizedMessage(), CakeScript.OPTIONS_SUB_PATH);
        }
    }

    @Override
    protected String getOptionsPath() {
        return CakeScript.getOptionsPath();
    }

    @Override
    protected File getPluginsDirectory() {
        return null;
    }

    @Override
    protected List<FrameworkCommand> getFrameworkCommandsInternal() {
        try {
            return CakeScript.forPhpModule(phpModule, true).getCommands(phpModule);
        } catch (InvalidPhpExecutableException ex) {
            UiUtils.invalidScriptProvided(ex.getLocalizedMessage(), CakeScript.OPTIONS_SUB_PATH);
        }
        return null;
    }
}
