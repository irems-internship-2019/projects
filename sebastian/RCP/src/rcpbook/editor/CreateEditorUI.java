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
import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.view.Editor;
import rcpbook.view.ViewerTools;

public class CreateEditorUI {

	ViewerTools vTools = new ViewerTools();
	private ContactsModel newContact = new ContactsModel();
	private Text[] textNames = new Text[8];
	private Editor edit;

	public void EditorUI(Composite parent) {

		Label mode = new Label(parent, SWT.NONE);

		if (CheckIfElementIsSelected.getEditorMode() && CheckIfElementIsSelected.getEditMode()) {

			mode.setText("Editor Mode");

			createEditorFields(parent);

			assignSelectedFieldsToText();

			createAddButton(parent);

		}

		else {
			mode.setText("Create Mode");

			createEditorFields(parent);

			createAddButton(parent);

		}

	}

	private void createEditorFields(Composite parent) {
		int i = 0;
		// need one extra :(

		new Label(parent, SWT.NONE);

		String[] labelTags = { "FirstName: ", "Last Name: ", "Phone Number: ", "Email: ", "Country: ", "City: ",
				"Street: ", "Postal Code: " };

		for (String str : labelTags) {
			new Label(parent, SWT.NONE).setText(str);
			textNames[i] = new Text(parent, SWT.BORDER | SWT.SEARCH);
			textNames[i].setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

//			   DirtyListener listener = new DirtyListenerImpl();
//		       DirtyUtils.registryDirty(listener, controls);
			
			textNames[i].addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					edit.setDirty(true);
					System.out.print("Dirty");
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
//				System.out.println(CheckIfElementIsSelected.getEditorMode());
//				System.out.println(CheckIfElementIsSelected.getEditMode());
				if (CheckIfElementIsSelected.getEditorMode() && CheckIfElementIsSelected.getEditMode())
					editOldContact();
				else
					createNewContact();

			}
		};
	}

	private void editOldContact() {

		ContactsManager contact = newContact.getElements()
				.get(CheckIfElementIsSelected.getSelectedItem().getIdForComparator() - 1);

		contact.setFirst(textNames[0].getText());
		contact.setSecond(textNames[1].getText());
		contact.setPhoneNumber(textNames[2].getText());
		contact.setEmail(textNames[3].getText());
		contact.getAddress().setCountry(textNames[4].getText());
		contact.getAddress().setCity(textNames[5].getText());
		contact.getAddress().setStreet(textNames[6].getText());
		contact.getAddress().setPostalCode(textNames[7].getText());

		CheckIfElementIsSelected.setEditMode(false);
		CheckIfElementIsSelected.setEditorMode(false);

		edit.setDirty(false);
		
		vTools.refreshContactsViewer();

	}

	private void createNewContact() {
		newContact.addNewContact(new ContactsManager(
				textNames[0].getText(), textNames[1].getText(), new AddressManager(textNames[2].getText(),
						textNames[3].getText(), textNames[4].getText(), textNames[5].getText()),
				textNames[6].getText(), textNames[7].getText()));

		edit.setDirty(false);
		
		vTools.refreshContactsViewer();
	}

	private void assignSelectedFieldsToText() {
		// textNames[0].setText("Dorel");
		// textNames[1].setText("Gigele");
		textNames[0].setText(CheckIfElementIsSelected.getSelectedItem().getFirst());
		textNames[1].setText(CheckIfElementIsSelected.getSelectedItem().getSecond());
		textNames[2].setText(CheckIfElementIsSelected.getSelectedItem().getPhone());
		textNames[3].setText(CheckIfElementIsSelected.getSelectedItem().getEmail());
		textNames[4].setText(CheckIfElementIsSelected.getSelectedItem().getAddress().getCountry());
		textNames[5].setText(CheckIfElementIsSelected.getSelectedItem().getAddress().getCity());
		textNames[6].setText(CheckIfElementIsSelected.getSelectedItem().getAddress().getStreet());
		textNames[7].setText(CheckIfElementIsSelected.getSelectedItem().getAddress().getPostalCode());
	}

}
