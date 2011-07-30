package org.wso2.tools.humantask.editor.editors.xmleditor;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class HTScanner extends RuleBasedScanner {

	public HTScanner(ColorManager manager) {
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IHTColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		//Add rule for processing instructions
		rules[0] = new SingleLineRule("<?", "?>", procInstr);
		// Add generic whitespace rule.
		rules[1] = new WhitespaceRule(new HTWhitespaceDetector());

		setRules(rules);
	}
}
