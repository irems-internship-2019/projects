package adress.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class AddressbookEditorInput implements IEditorInput {

   @Override
   public Object getAdapter(Class adapter) {
       return null;
   }

   @Override
   public boolean exists() {
       return false;
   }

   @Override
   public ImageDescriptor getImageDescriptor() {
       return null;
   }

   @Override
   public String getName() {
       return "Adress Book New Contact";
   }

   @Override
   public IPersistableElement getPersistable() {
       return null;
   }

   @Override
   public String getToolTipText() {
       return "Adress Book New Contact";
   }

}