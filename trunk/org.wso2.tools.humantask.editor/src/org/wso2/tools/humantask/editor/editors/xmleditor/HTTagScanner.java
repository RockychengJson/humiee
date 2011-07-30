package org.wso2.tools.humantask.editor.editors.xmleditor;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class HTTagScanner extends RuleBasedScanner {

	public HTTagScanner(ColorManager manager) {
		IToken string =
			new Token(
				new TextAttribute(manager.getColor(IHTColorConstants.STRING)));

		IRule[] rules = new IRule[3];

		// Add rule for double quotes
		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		// Add a rule for single quotes
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new HTWhitespaceDetector());

		setRules(rules);
	}
}
