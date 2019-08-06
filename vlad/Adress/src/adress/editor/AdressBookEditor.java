package adress.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import Model.Address;
import Model.Contact;
import Model.ContactProvider;
import adress.View;

public class AdressBookEditor extends EditorPart {

	private Text firstNameText;
	private Text lastNameText;
	private Text phoneNumberText;
	private Text emailAdressText;
	private Text adressText;
	private Text countryText;
	 private Text cityText;
	 private Text postalCodeText;

	ContactProvider contacts = ContactProvider.INSTANCE;
	
	public static final String ID = "adress.book.editor";

	public AdressBookEditor() {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		if (!(input instanceof AdressbookEditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + AdressbookEditorInput.class.getName());
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

	

	@Override
	public void createPartControl(Composite parent) {
		// Add Code.
		// If you want to design with WindowBuilder Designer
		// Change code like: (Important!!!)
		parent.setLayout(new GridLayout(2,false));	
		Label postalCodeLabel = new Label(parent, SWT.NONE);
		postalCodeLabel.setText("Postal Code");
		postalCodeText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		postalCodeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		postalCodeText.setText("test");


	     
		parent.setLayout(new GridLayout(2,false));	
		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("First Name");
		firstNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		firstNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		firstNameText.setText("test");

		
		parent.setLayout(new GridLayout(2,false));	
		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("Last Name");
		lastNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		lastNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		lastNameText.setText("test");

		
		
		parent.setLayout(new GridLayout(2,false));	
		Label phoneNumberLabel = new Label(parent, SWT.NONE);
		phoneNumberLabel.setText("Phone Number");
		phoneNumberText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		phoneNumberText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		phoneNumberText.setText("test");

		parent.setLayout(new GridLayout(2,false));	
		Label adressLabel = new Label(parent, SWT.NONE);
		adressLabel.setText("Adress");
		adressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		adressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		adressText.setText("test");

		parent.setLayout(new GridLayout(2,false));	
		Label emailAdressLabel = new Label(parent, SWT.NONE);
		emailAdressLabel.setText("E-mail");
		emailAdressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		emailAdressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		emailAdressText.setText("test");
		
    	parent.setLayout(new GridLayout(2,false));	
		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		countryText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		countryText.setText("test");
		
		parent.setLayout(new GridLayout(2,false));	
		Label cityLabel = new Label(parent, SWT.NONE);
		cityLabel.setText("E-mail");
		cityText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		cityText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		cityText.setText("test");
		
		Button insertButton = new Button(parent, SWT.PUSH);
		insertButton.setText("New Contact");
		insertButton.setFont(JFaceResources.getDialogFont());
		insertButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				contacts.addContacts(new Contact(firstNameText.getText(),
						new Address(countryText.getText(), cityText.getText(), adressText.getText(),
								postalCodeText.getText()),
						lastNameText.getText(), phoneNumberText.getText(), emailAdressText.getText()));

				View part = (View) getSite().getPage().findView(View.ID);
				part.refresh();

			}
		});

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
