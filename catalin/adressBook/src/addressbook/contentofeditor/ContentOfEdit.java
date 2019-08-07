package addressbook.contentofeditor;

import java.util.ArrayList;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
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
import addressbook.persons.Contact;
import addressbook.view.AddressBookDetailsView;

public class ContentOfEdit extends AddressBookEditor {
	private Contact recentlyElement = contactSelected.get(contactSelected.size() - 1);
	public static ArrayList<Contact> contactSelected = new ArrayList<Contact>();

	public void clearContactSelected() {
		contactSelected.clear();
	}

	public void editContent(Composite parent) {
		AddressBookDetailsView secondObject = new AddressBookDetailsView();

		parent.setLayout(new FillLayout());
		Composite body = new Composite(parent, SWT.NONE);
		body.setLayout(new GridLayout(4, true));

		createLabel(body, TitlesOfColumns.ID.getSignOfElements());
		Text textId = new Text(body, SWT.BORDER);
		textId.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textId.setText(Integer.toString(contactSelected.get(contactSelected.size() - 1).getId()));

		createLabel(body, TitlesOfColumns.FIRSTNAME.getSignOfElements());
		Text textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textFirstName.setText(contactSelected.get(contactSelected.size() - 1).getFirstName());

		createLabel(body, TitlesOfColumns.LASTNAME.getSignOfElements());
		Text textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textLastName.setText(contactSelected.get(contactSelected.size() - 1).getLastName());

		createLabel(body, TitlesOfColumns.COUNTRY.getSignOfElements());
		Text textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textCountry.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getCountry());

		createLabel(body, TitlesOfColumns.CITY.getSignOfElements());
		Text textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textCity.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getCity());

		createLabel(body, TitlesOfColumns.STREET.getSignOfElements());
		Text textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textStreet.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getStreet());

		createLabel(body, TitlesOfColumns.POSTALCODE.getSignOfElements());
		Text textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textPostalCode.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getPostal_code());

		createLabel(body, TitlesOfColumns.PHONE.getSignOfElements());
		Text textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textPhoneNumber.setText(Integer.toString(contactSelected.get(contactSelected.size() - 1).getPhoneNumber()));

		createLabel(body, TitlesOfColumns.EMAIL.getSignOfElements());
		Text textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textEmail.setText(contactSelected.get(contactSelected.size() - 1).getEmailAddress());
		
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
				if (textId.getText().matches(checkNumbers) && textId.getText().length() != 0
						&& textFirstName.getText().length() != 0 && textLastName.getText().length() != 0
						&& textCountry.getText().length() != 0 && textCity.getText().length() != 0
						&& textStreet.getText().length() != 0 && textPostalCode.getText().length() != 0
						&& textPhoneNumber.getText().matches(checkNumbers) && textPhoneNumber.getText().length() != 0
						&& textEmail.getText().length() != 0) {
					if (!textId.getText()
							.equals(Integer.toString(contactSelected.get(contactSelected.size() - 1).getId()))) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setId(Integer.parseInt(textId.getText()));
						object.refresh();
					}
					if (!textFirstName.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getFirstName())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setFirstName(textFirstName.getText());
						object.refresh();
					}
					if (!textLastName.getText().equals(contactSelected.get(contactSelected.size() - 1).getLastName())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setLastName(textLastName.getText());
						object.refresh();
					}
					if (!textCountry.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getCountry())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setCountry(textCountry.getText());
						object.refresh();
					}
					if (!textCity.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getCity())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setCity(textCity.getText());
						object.refresh();
					}
					if (!textStreet.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getStreet())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setStreet(textStreet.getText());
						object.refresh();
					}
					if (!textPostalCode.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getPostal_code())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setPostal_code(textPostalCode.getText());
						object.refresh();
					}
					if (!textPhoneNumber.getText().equals(
							Integer.toString(contactSelected.get(contactSelected.size() - 1).getPhoneNumber()))) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setPhoneNumber(Integer.parseInt(textPhoneNumber.getText()));
						object.refresh();
					}
					if (!textEmail.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getEmailAddress())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setEmailAddress(textEmail.getText());
						object.refresh();
					}
					secondObject.refresh();
				}
			}
		});
	}

}
