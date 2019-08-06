package rcpbook.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import rcpbook.editor.AddressBookNewContactImput;
import rcpbook.editor.CreateEditorUI;

public class Editor extends EditorPart {

	public static final String ID = "RCPBook.Create";
	private CreateEditorUI createNewEditor = new CreateEditorUI();
	public Editor() {
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

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	 public void init(IEditorSite site, IEditorInput input)
	         throws PartInitException {
	     if (!(input instanceof AddressBookNewContactImput)) {
	         throw new PartInitException("Invalid Input: Must be "
	                 + AddressBookNewContactImput.class.getName());
	     }
	     setSite(site);
	     setInput(input);
	 }


	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

}
