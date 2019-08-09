package rcpbook.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
//import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.editor.AddressBookNewContactImput;
//import rcpbook.editor.CreateEditorUI;

public class EditorView extends EditorPart {

	public static final String ID = "RCPBook.Create";

	private boolean dirty = false;
	private ViewerTools vTools = new ViewerTools();
	private ContactsModel newContact = new ContactsModel();
	private ContactsManager contact;
	private Text[] textNames = new Text[8];

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		EditorUI(parent);
	}

	public void EditorUI(Composite parent) {

		createEditorFields(parent);

		createAddButton(parent);
	}

	private void createEditorFields(Composite parent) {

		int i = 0;

		String[] labelTags = { "FirstName: ", "Last Name: ", "Phone Number: ", "Email: ", "Country: ", "City: ",
				"Street: ", "Postal Code: " };

		for (String str : labelTags) {
			new Label(parent, SWT.NONE).setText(str);
			textNames[i] = new Text(parent, SWT.BORDER | SWT.SEARCH);
			textNames[i].setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
			textNames[i].addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
				}
			});
			i++;
		}
	}

	private void createAddButton(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setText("Submit");
		button.addSelectionListener(makeButtonSelectionListner(button));
	}

	private SelectionListener makeButtonSelectionListner(Button button) {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (contact != null)
					editContact();
				else
					newContact();
			}
		};
	}

	private void editContact() {

		// this is better if you delete the contact and insert it back at ID - 1
		
		ContactsManager contact = newContact.getElements().get(this.contact.getIdForComparator() - 1);

		contact.setFirst(textNames[0].getText());
		contact.setSecond(textNames[1].getText());
		contact.setPhoneNumber(textNames[2].getText());
		contact.setEmail(textNames[3].getText());
		contact.getAddress().setCountry(textNames[4].getText());
		contact.getAddress().setCity(textNames[5].getText());
		contact.getAddress().setStreet(textNames[6].getText());
		contact.getAddress().setPostalCode(textNames[7].getText());
		
        //#########################################################################
		
		setDirty(false);
		// CheckSelected.setSelectedToNull();
		contact = null;
		vTools.refreshContactsViewer();

	}

	private void newContact() {
		newContact.addNewContact(new ContactsManager(
				textNames[0].getText(), textNames[1].getText(), new AddressManager(textNames[2].getText(),
						textNames[3].getText(), textNames[4].getText(), textNames[5].getText()),
				textNames[6].getText(), textNames[7].getText()));

		setDirty(false);

		vTools.refreshContactsViewer();
	}

	public void updateWidgets() {
		if (contact != null) {
			textNames[0].setText(contact.getFirst());
			textNames[1].setText(contact.getSecond());
			textNames[2].setText(contact.getPhone());
			textNames[3].setText(contact.getEmail());
			textNames[4].setText(contact.getAddress().getCountry());
			textNames[5].setText(contact.getAddress().getCity());
			textNames[6].setText(contact.getAddress().getStreet());
			textNames[7].setText(contact.getAddress().getPostalCode());
		}
	}

	public void setModel(ContactsManager model) {
		this.contact = model;
	}

	public static void openEditor(ContactsManager model) {
		AddressBookNewContactImput input = new AddressBookNewContactImput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			EditorView addressBookEditor = (EditorView) activePage.openEditor(input, ID);
			addressBookEditor.setModelAndWidget(model);

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	public void setModelAndWidget(ContactsManager model) {
		setModel(model);
		updateWidgets();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
//		final EditorPart editor = (EditorPart) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//	
//
//		    editor.doSave(monitor);
		
		if (contact != null)
			editContact();
		else
			newContact();
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
}
