package rcpbook.application;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

//this RCPBook_UI_TEST activator
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator_TEST extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "RCPBook_UI_TEST"; //$NON-NLS-1$

	// The shared instance
	private static Activator_TEST plugin;
	
	/**
	 * The constructor
	 */
	public Activator_TEST() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator_TEST getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
