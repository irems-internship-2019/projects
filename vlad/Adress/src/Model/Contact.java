package Model;

import java.util.ArrayList;
import java.util.List;


public class Contact {

	   private int id; 
	   private String firstName;
	   private String lastName;
	 //  private Address adress;
	   private String phoneNumber;
	   private String emailAdress;
	    
	   
	    public Contact() {
	    	
	    }

	public Contact(String firstName, String lastName, String phoneNumber, String emailAdress) {
		this.id= id++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber=phoneNumber;
		this.emailAdress=emailAdress;

	}
	

	   public String getId()
	   {
		    return Integer.toString(id);
	   }
	   
	   public String getFirstName()
	   {
		   return firstName;
	   }
	   
	   public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	   
	   public String getLastName()
	   {
		   return lastName;
	   }
	   
	   public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	   
	   public String getphoneNumber()
	   {
		   return phoneNumber;
	   }
	   
	   public String getEmailAdress()
	   {
		   return emailAdress;
	   }

}
