package address;

import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		  // Show MenuBar
	       configurer.setShowMenuBar(true);
	       // Show CoolBar.
	       configurer.setShowCoolBar(true);
	       // Show Status Line.
	       configurer.setShowStatusLine(true);
	       // Show PerspectiveBar
	       configurer.setShowPerspectiveBar(true);
	       // Show FastViewBars
	       configurer.setShowFastViewBars(true);
	       // Show ProgressIndicator
	       configurer.setShowProgressIndicator(true);
		configurer.setTitle("RCP persistence Application");
	}
}
