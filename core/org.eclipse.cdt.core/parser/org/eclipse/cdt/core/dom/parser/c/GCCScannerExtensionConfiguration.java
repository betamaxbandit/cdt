/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM - Initial API and implementation
 *    Ed Swartz (Nokia)
 *    Markus Schorn (Wind River Systems)
 *    Sergey Prigogin (Google)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.c;

import org.eclipse.cdt.core.dom.parser.GNUScannerExtensionConfiguration;

/**
 * Configures the preprocessor for parsing c-sources as accepted by gcc.
 */
public class GCCScannerExtensionConfiguration extends GNUScannerExtensionConfiguration {

	private static GCCScannerExtensionConfiguration sInstance= new GCCScannerExtensionConfiguration();
	/**
	 * @since 5.1
	 */
	public static GCCScannerExtensionConfiguration getInstance() {
		return sInstance;
	}

	public GCCScannerExtensionConfiguration() {
		addMacro("__null", "(void *)0");  //$NON-NLS-1$//$NON-NLS-2$
		addMacro("_Pragma(arg)", "");  //$NON-NLS-1$//$NON-NLS-2$
	}

    /* (non-Javadoc)
     * @see org.eclipse.cdt.internal.core.parser.scanner2.IScannerConfiguration#supportMinAndMaxOperators()
     */
    @Override
	public boolean supportMinAndMaxOperators() {
        return false;
    }
}
