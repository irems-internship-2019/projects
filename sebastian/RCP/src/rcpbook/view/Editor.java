package rcpbook.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import rcpbook.Dirty.DirtyListener;
import rcpbook.editor.AddressBookNewContactImput;
import rcpbook.editor.CreateEditorUI;

public class Editor extends EditorPart {

	public static final String ID = "RCPBook.Create";
	private CreateEditorUI createNewEditor = new CreateEditorUI();
	private boolean dirty = false;


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
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof AddressBookNewContactImput)) {
			throw new PartInitException("Invalid Input: Must be " + AddressBookNewContactImput.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {

		if (dirty)
			return true;
		else
			return false;
	}

	public final void setDirty(boolean dirty) {

		if (this.isDirty() == dirty)
			return;
		
		this.dirty = dirty;

//			 //Notify PROP_DIRTY changes to Workbench.
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	}

	
	
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

//	class DirtyListenerImpl implements DirtyListener {
//		 
//	       @Override
//	       public void fireDirty() {
//	           // If has any change, fire to Editor.
//	           Editor.this.setDirty(true);
//	       }
//	 
//	   }

}
