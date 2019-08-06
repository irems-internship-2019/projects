package rcpbook.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import rcpbook.editor.CreateEditorUI;

public class EditorView extends ViewPart {

	private CreateEditorUI createNewEditor = new CreateEditorUI();
	public EditorView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
	
		parent.setLayout(new GridLayout(2, false));
		createNewEditor.EditorUI(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
