/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.lrparser.tests;

import junit.framework.TestSuite;

import org.eclipse.cdt.core.dom.ICodeReaderFactory;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.lrparser.c99.C99Language;
import org.eclipse.cdt.core.dom.lrparser.cpp.ISOCPPLanguage;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.cdt.core.parser.tests.ast2.AST2SelectionParseTest;
import org.eclipse.cdt.internal.core.dom.SavedCodeReaderFactory;
import org.eclipse.cdt.internal.core.parser.ParserException;
import org.eclipse.core.resources.IFile;

@SuppressWarnings("restriction")
public class LRSelectionParseTest extends AST2SelectionParseTest {
	
	public static TestSuite suite() {
    	return new TestSuite(LRSelectionParseTest.class);
    }
	
	public LRSelectionParseTest() {}
	public LRSelectionParseTest(String name) { super(name); }

	@Override
	protected IASTNode parse(String code, ParserLanguage lang, int offset, int length) throws ParserException {
		return parse(code, lang, false, false, offset, length);
	}
	
	@Override
	protected IASTNode parse(IFile file, ParserLanguage lang, int offset, int length) throws ParserException {
		IASTTranslationUnit tu = parse(file, lang, false, false);
		return tu.selectNodeForLocation(tu.getFilePath(), offset, length);
	}
	
	@Override
	protected IASTNode parse(String code, ParserLanguage lang, int offset, int length, boolean expectedToPass) throws ParserException {
		return parse(code, lang, false, expectedToPass, offset, length);
	}
	
	@Override
	protected IASTNode parse(String code, ParserLanguage lang, boolean useGNUExtensions, boolean expectNoProblems, int offset, int length) throws ParserException {
		ILanguage language = lang.isCPP() ? getCPPLanguage() : getC99Language();
    	
		IASTTranslationUnit tu = ParseHelper.parse(code, language, useGNUExtensions, expectNoProblems, 0);
		return tu.selectNodeForLocation(tu.getFilePath(), offset, length);
	}	
	
	protected IASTTranslationUnit parse( IFile file, ParserLanguage lang, IScannerInfo scanInfo, boolean useGNUExtensions, boolean expectNoProblems ) {

		ILanguage language = lang.isCPP() ? getCPPLanguage() : getC99Language();
    	
		String fileName = file.getLocation().toOSString();
		ICodeReaderFactory fileCreator = SavedCodeReaderFactory.getInstance();
		CodeReader reader = fileCreator.createCodeReaderForTranslationUnit(fileName);
		return ParseHelper.parse(reader, language, scanInfo, fileCreator, expectNoProblems, true, 0, null, true);
	}

	@Override
	protected IASTTranslationUnit parse( IFile file, ParserLanguage lang, boolean useGNUExtensions, boolean expectNoProblems ) 
	    throws ParserException {
		return parse(file, lang, new ScannerInfo(), useGNUExtensions, expectNoProblems);
	}
	
	protected ILanguage getC99Language() {
    	return C99Language.getDefault();
    }
	
	protected ILanguage getCPPLanguage() {
		return ISOCPPLanguage.getDefault();
	}
	
}
