package addressbook.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import addressbook.contentofeditor.ContentOfAdd;
import addressbook.contentofeditor.ContentOfEdit;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookView;

public class AddressBookEditor extends EditorPart {
	public static final String ID = "addressbook.editor.addressbookeditor";
	protected ContactElements persons = ContactElements.INSTANCE;
	protected String checkNumbers = "[0-9]+";
	protected AddressBookView object = new AddressBookView();
	protected boolean dirty = false;
	public static int numberOfChoice;
	private boolean dirtyCheck;

	protected enum TitlesOfColumns {
		ID("Id"),
		FIRSTNAME("First Name"),
		LASTNAME("Last Name"),
		COUNTRY("Country"),
		CITY("City"),
		STREET("Street"),
		POSTALCODE("Postal Code"),
		PHONE("Phone Number"),
		EMAIL("Email Address");

		private final String signOfElements;

		private TitlesOfColumns(String signOfElements) {
			this.signOfElements = signOfElements;
		}

		public String getSignOfElements() {
			return signOfElements;
		}
	}

	protected void createLabel(Composite parent, String element) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(element);
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
	}

	public AddressBookEditor() 
	{
		AddressBookEditorInput input = new AddressBookEditorInput();
		
		// updateWidgets();
	}

	
	
	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof AddressBookEditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + AddressBookEditorInput.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		if(dirtyCheck == true)
			return true;
		else
			return false;
		
	}
	
	 public final void setDirty(boolean isDirty)
	    {
	       if(this.isDirty() == isDirty)
	    	   return;
	       this.dirtyCheck = isDirty;
	       firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	    }

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		if (numberOfChoice == 1) {
			ContentOfAdd firstObject = new ContentOfAdd();
			firstObject.addContent(parent);
		} else if (numberOfChoice == 2) {
			ContentOfEdit secondObject = new ContentOfEdit();
			secondObject.editContent(parent);
		}
	}

	@Override
	public void setFocus() {

	}
}