package rcpbook.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import rcpbook.Dirty.DirtyListener;
import rcpbook.cmd.EditElement;
import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.view.Editor;
import rcpbook.view.ViewerTools;

public class CreateEditorUI {

	ViewerTools vTools = new ViewerTools();
	private ContactsModel newContact = new ContactsModel();
	private Text[] textNames = new Text[8];
	// private Editor edit;

	public void EditorUI(Composite parent) {
		
		createEditorFields(parent);
		assignSelectedFieldsToText();
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
				
				if (CheckSelected.getSelectedItem() != null)
					editContact();
				else
					newContact();
			}
		};
	}

	private void editContact() {

		ContactsManager contact = newContact.getElements()
				.get(CheckSelected.getSelectedItem().getIdForComparator() - 1);

		contact.setFirst(textNames[0].getText());
		contact.setSecond(textNames[1].getText());
		contact.setPhoneNumber(textNames[2].getText());
		contact.setEmail(textNames[3].getText());
		contact.getAddress().setCountry(textNames[4].getText());
		contact.getAddress().setCity(textNames[5].getText());
		contact.getAddress().setStreet(textNames[6].getText());
		contact.getAddress().setPostalCode(textNames[7].getText());

		// edit.setDirty(false);
		CheckSelected.setSelectedToNull();
		vTools.refreshContactsViewer();

	}

	private void newContact() {
		newContact.addNewContact(new ContactsManager(
				textNames[0].getText(), textNames[1].getText(), new AddressManager(textNames[2].getText(),
						textNames[3].getText(), textNames[4].getText(), textNames[5].getText()),
				textNames[6].getText(), textNames[7].getText()));

		// edit.setDirty(false);

		vTools.refreshContactsViewer();
	}

	private void assignSelectedFieldsToText() {

		if (CheckSelected.getSelectedItem() != null) {
			textNames[0].setText(CheckSelected.getSelectedItem().getFirst());
			textNames[1].setText(CheckSelected.getSelectedItem().getSecond());
			textNames[2].setText(CheckSelected.getSelectedItem().getPhone());
			textNames[3].setText(CheckSelected.getSelectedItem().getEmail());
			textNames[4].setText(CheckSelected.getSelectedItem().getAddress().getCountry());
			textNames[5].setText(CheckSelected.getSelectedItem().getAddress().getCity());
			textNames[6].setText(CheckSelected.getSelectedItem().getAddress().getStreet());
			textNames[7].setText(CheckSelected.getSelectedItem().getAddress().getPostalCode());
		}
	}

}
