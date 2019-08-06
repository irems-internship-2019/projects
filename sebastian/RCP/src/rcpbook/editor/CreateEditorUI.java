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

import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.view.ViewerTools;

public class CreateEditorUI {

	ViewerTools vTools = new ViewerTools();
	private ContactsModel newContact = new ContactsModel();
	// private String first;
	// private String second;
	// private String phoneNumber;
	// private String email;
	// private String country;
	// private String city;
	// private String street;
	// private String postalCode;

	public void EditorUI(Composite parent) {

		Label mode = new Label(parent, SWT.NONE);
		
		if (CheckIfElementIsSelected.getEditorMode() == 1 && CheckIfElementIsSelected.getEditMode() == 1)
		{
			CheckIfElementIsSelected.setEditMode(0);
			CheckIfElementIsSelected.setEditorMode(0);
			mode.setText("Edit");
			}
			
		else {
			mode.setText("New");
			}
		
		
		//spatiu gol
		new Label(parent, SWT.NONE);

		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("First Name: ");

		final Text firstNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		firstNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("Last Name: ");

		final Text lastNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		lastNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label phoneNumberLabel = new Label(parent, SWT.NONE);
		phoneNumberLabel.setText("Phone Number: ");

		final Text phoneNumberText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		phoneNumberText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label emailLabel = new Label(parent, SWT.NONE);
		emailLabel.setText("Email: ");

		final Text emailText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		emailText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country: ");

		final Text countryText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		countryText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label cityLabel = new Label(parent, SWT.NONE);
		cityLabel.setText("City: ");

		final Text cityText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		cityText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label streetLabel = new Label(parent, SWT.NONE);
		streetLabel.setText("Street: ");

		final Text streetText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		streetText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label postalCodeLabel = new Label(parent, SWT.NONE);
		postalCodeLabel.setText("Postal Code: ");

		final Text postalCodeText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		postalCodeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Button button = new Button(parent, SWT.NONE);
		button.setText("Create new entry");
		// button.addSelectionListener(makeButtonSelectionListner());

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				newContact.addNewContact(new ContactsManager(
						firstNameText.getText(), lastNameText.getText(), new AddressManager(countryText.getText(),
								cityText.getText(), streetText.getText(), postalCodeText.getText()),
						phoneNumberText.getText(), emailText.getText()));
				vTools.refreshContactsViewer();
			}

		});

		// firstNameText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// first = firstNameText.getText();
		//
		// }
		// });
		//
		// lastNameText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// second = lastNameText.getText();
		//
		// }
		// });
		//
		// phoneNumberText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// phoneNumber = phoneNumberText.getText();
		//
		// }
		// });
		//
		// emailText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// email = emailText.getText();
		// }
		// });
		//
		// countryText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// country = countryText.getText();
		// }
		// });
		//
		// cityText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// city = cityText.getText();
		// }
		// });
		//
		// streetText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// street = streetText.getText();
		// }
		// });
		//
		// postalCodeText.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent ke) {
		// postalCode = postalCodeText.getText();
		// }
		// });
	}

	// private SelectionListener makeButtonSelectionListner()
	// {
	// return new SelectionAdapter()
	// {
	// @Override
	// public void widgetSelected(SelectionEvent e)
	// {
	// newContact.addNewContact(new ContactsManager(firstname, null, null, null,
	// null));
	// }
	// };
	// }
}
