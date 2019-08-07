package addressbook.contentofeditor;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import addressbook.editor.AddressBookEditor;
import addressbook.persons.Address;
import addressbook.persons.Contact;

public class ContentOfAdd extends AddressBookEditor {
	public void addContent(Composite parent) {
		parent.setLayout(new FillLayout());
		Composite body = new Composite(parent, SWT.NONE);
		body.setLayout(new GridLayout(4, true));

		createLabel(body, TitlesOfColumns.ID.getSignOfElements());
		Text textId = new Text(body, SWT.BORDER);
		textId.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.FIRSTNAME.getSignOfElements());
		Text textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.LASTNAME.getSignOfElements());
		Text textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.COUNTRY.getSignOfElements());
		Text textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.CITY.getSignOfElements());
		Text textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.STREET.getSignOfElements());
		Text textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.POSTALCODE.getSignOfElements());
		Text textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.PHONE.getSignOfElements());
		Text textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.EMAIL.getSignOfElements());
		Text textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textEmail.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				setDirty(true);
			}
			
		});

		Button button = new Button(body, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, true, 1, 1));
		button.setText("OK");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Contact person;
				if (textId.getText().matches(checkNumbers) && textId.getText().length() != 0
						&& textFirstName.getText().length() != 0 && textLastName.getText().length() != 0
						&& textCountry.getText().length() != 0 && textCity.getText().length() != 0
						&& textStreet.getText().length() != 0 && textPostalCode.getText().length() != 0
						&& textPhoneNumber.getText().matches(checkNumbers) && textPhoneNumber.getText().length() != 0
						&& textEmail.getText().length() != 0) {
					person = new Contact(Integer.parseInt(textId.getText()), textFirstName.getText(),
							textLastName.getText(),
							new Address(textCountry.getText(), textCity.getText(), textStreet.getText(),
									textPostalCode.getText()),
							Integer.parseInt(textPhoneNumber.getText()), textEmail.getText());
					persons.getContacts().add(person);
					object.refresh();
					setDirty(false);
				}
			}
		});

	}

}
