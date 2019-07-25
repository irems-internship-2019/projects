package calc.pckg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CreateUi {

	public static Button makeButton(Shell shell, String strNum, GridData grid)
	{
	Button btn = new Button(shell, SWT.PUSH);
	btn.setText(strNum);
	btn.setLayoutData(grid);
	
	return btn;
	}
}
