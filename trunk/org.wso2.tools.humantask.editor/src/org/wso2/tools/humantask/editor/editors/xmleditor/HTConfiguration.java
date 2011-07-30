package org.wso2.tools.humantask.editor.editors.xmleditor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class HTConfiguration extends SourceViewerConfiguration {
	private HTDoubleClickStrategy doubleClickStrategy;
	private HTTagScanner tagScanner;
	private HTScanner scanner;
	private ColorManager colorManager;

	public HTConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			HTPartitionScanner.XML_COMMENT,
			HTPartitionScanner.XML_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new HTDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected HTScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new HTScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IHTColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected HTTagScanner getXMLTagScanner() {
		if (tagScanner == null) {
			tagScanner = new HTTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IHTColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getXMLTagScanner());
		reconciler.setDamager(dr, HTPartitionScanner.XML_TAG);
		reconciler.setRepairer(dr, HTPartitionScanner.XML_TAG);

		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IHTColorConstants.XML_COMMENT)));
		reconciler.setDamager(ndr, HTPartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, HTPartitionScanner.XML_COMMENT);

		return reconciler;
	}

}