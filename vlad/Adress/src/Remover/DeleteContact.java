package Remover;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import checker.SelectChecker;

public class DeleteContact extends AbstractHandler {
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		SelectChecker check = new SelectChecker();
		if (check.isSelected() == true) {
 
			check.deleteContact();
		
		}
		return null;
	}
}
