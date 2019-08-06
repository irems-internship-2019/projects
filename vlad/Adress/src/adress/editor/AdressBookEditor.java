package adress.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

	private Text firstNameText;
	private Text lastNameText;
	private Text phoneNumberText;
	private Text emailAdressText;
	private Text adressText;

	ContactProvider contacts = ContactProvider.INSTANCE;

	@Override
	public void createPartControl(Composite parent) {
		// Add Code.
		// If you want to design with WindowBuilder Designer
		// Change code like: (Important!!!)
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);

		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("first name");
		firstNameText = new Text(parent, SWT.BORDER);

		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("last name");
		lastNameText = new Text(parent, SWT.BORDER);

		Label phoneNumberLabel = new Label(parent, SWT.NONE);
		phoneNumberLabel.setText("phone number");
		phoneNumberText = new Text(parent, SWT.BORDER);

		Label adressLabel = new Label(parent, SWT.NONE);
		adressLabel.setText("address ");
		adressText = new Text(parent, SWT.BORDER);

		Label emailAdressLabel = new Label(parent, SWT.NONE);
		emailAdressLabel.setText("email Adress");
		emailAdressText = new Text(parent, SWT.BORDER);

		Button insertButton = new Button(parent, SWT.PUSH);
		insertButton.setText("New Contact");
		insertButton.setFont(JFaceResources.getDialogFont());
		insertButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				contacts.addContacts(new Contact(firstNameText.getText(),
						new Address("country", "city", adressText.getText(), "postal code"), lastNameText.getText(),
						phoneNumberText.getText(), emailAdressText.getText()));

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
