package simplexmleditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class HTEditor extends TextEditor {

	private ColorManager colorManager;

	public HTEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new HTConfiguration(colorManager));
		setDocumentProvider(new HTDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
