package rcpbook;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

//this SQL2 activator
/**
 * The activator class controls the plug-in life cycle
 */
public class ActivatorSQL extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "RCPBook_SQL2"; //$NON-NLS-1$

	// The shared instance
	private static ActivatorSQL plugin;
	
	/**
	 * The constructor
	 */
	public ActivatorSQL() {
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
	public static ActivatorSQL getDefault() {
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
