package org.wso2.tools.humantask.editor.editors.xmleditor;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class HTWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
