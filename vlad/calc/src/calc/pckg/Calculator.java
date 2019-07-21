package calc.pckg;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class Calculator {

	protected Shell shell;
	private Text calculatorInput;
	private Label label;
	private float data=0;
	private String operation;
	/**
	 * Launch the application.
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		try {
			Calculator window = new Calculator();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(343, 316);
		shell.setText("SWT Calculator");
		
		shell.setLayout(new GridLayout(4, false));
		
		calculatorInput = new Text(shell, SWT.BORDER);
		calculatorInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
	
		
		
		
		Button clearButton = new Button(shell, SWT.NONE);
		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				   calculatorInput.setText("");
			}
		});
		clearButton.setText("C");
		       clearButton.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		         Color clearColor = new Color(shell.getDisplay(), new RGB(0, 0, 255));
		         clearButton.setBackground(clearColor);
		
		
		
		Button deleteButton = new Button(shell, SWT.NONE);				
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 
				if(calculatorInput.getText().length()!=0)
				  calculatorInput.setText(calculatorInput.getText().substring(0,calculatorInput.getText().length()-1));
			}
		});
		deleteButton.setText("DEL");
		           deleteButton.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));	
		
		          
		Button division = new Button(shell, SWT.NONE);
		division.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
							
			     data=Float.parseFloat(calculatorInput.getText());			                 
                 operation="division";
               calculatorInput.setText("");
                 label.setText(operation);				      
			    
              calculatorInput.setText("");
		            label.setText("divison");
			}
		});
		      division.setText("/");
		         
		               division.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1)); 
		               
		               
		               	                
		Button multiply = new Button(shell, SWT.NONE);
		multiply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	 	   
				  data=Float.parseFloat(calculatorInput.getText());			                 
                  operation="multiplication";
                calculatorInput.setText("");
                  label.setText(operation);
			}
		});
		multiply.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		multiply.setText("X"); 
		
		
		 
		   Button seven =new Button(shell,SWT.NONE);
		   seven.addSelectionListener(new SelectionAdapter() {
		   	@Override
		   	public void widgetSelected(SelectionEvent e) {
		   		
		   	 calculatorInput.setText( calculatorInput.getText() + 7 );
		   	}
		   });
			seven.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			seven.setText("7");
			
		   Button  eight=new Button(shell,SWT.NONE);
		   eight.addSelectionListener(new SelectionAdapter() {
		   	@Override
		   	public void widgetSelected(SelectionEvent e) {
		   	 calculatorInput.setText( calculatorInput.getText() + 8 );
		   	}
		   });
			eight.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			eight.setText("8");
			
		    Button nine =new Button(shell,SWT.NONE);
		    nine.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	public void widgetSelected(SelectionEvent e) {
		    		

		    		 calculatorInput.setText( calculatorInput.getText() + 9 );
		    	}
		    });
			nine.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			nine.setText("9");
			
		    Button minus =new Button(shell,SWT.NONE);
		    minus.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	public void widgetSelected(SelectionEvent e) {
		    		
		    		  data=Float.parseFloat(calculatorInput.getText());			                 
	                   operation="subtraction";
	                 calculatorInput.setText("");
	                   label.setText(operation);
		    	}
		    });
			minus.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			minus.setText("-");  
			
			 Button four =new Button(shell,SWT.NONE);
			 four.addSelectionListener(new SelectionAdapter() {
			 	@Override
			 	public void widgetSelected(SelectionEvent e) {
			 		
			 		 calculatorInput.setText(calculatorInput.getText() + 4 );
			 	}
			 });
				four.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
				four.setText("4");  
				
				 Button five =new Button(shell,SWT.NONE);
				 five.addSelectionListener(new SelectionAdapter() {
				 	@Override
				 	public void widgetSelected(SelectionEvent e) {

				 		 calculatorInput.setText( calculatorInput.getText() + 5 );
				 	}
				 });
					five.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					five.setText("5");  
					
					 Button six =new Button(shell,SWT.NONE);
					 six.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		

					 		 calculatorInput.setText( calculatorInput.getText() + 6 );
					 	}
					 });
					 six.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 six.setText("6"); 
					 
					 Button adittion =new Button(shell,SWT.NONE);
					 adittion.addSelectionListener(new SelectionAdapter() {
						 
					 	@Override					 	
					 	public void widgetSelected(SelectionEvent e) { 
					 		
			                 data=Float.parseFloat(calculatorInput.getText());			                 
			                   operation="addition";
			                 calculatorInput.setText("");
			                   label.setText(operation);
					 	}
					 });
					 adittion.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 adittion.setText("+"); 
					 
					 Button one =new Button(shell,SWT.NONE);
					 one.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		 calculatorInput.setText(calculatorInput.getText() + 1);
					 		     				 		 
					 	}
					 });
					 one.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 one.setText("1");  
					 
					 Button two =new Button(shell,SWT.NONE);
					 two.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		
					 		 calculatorInput.setText( calculatorInput.getText() + 2 );
					 	}
					 });
					 two.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 two.setText("2");  
					 
					 Button three =new Button(shell,SWT.NONE);
					 three.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		
					 		 calculatorInput.setText( calculatorInput.getText() + 3);
					 	}
					 });
					 three.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 three.setText("3");  
						 					 
					 
					 Button equal =new Button(shell,SWT.NONE);
					 equal.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		
					 	            Float secondOperand = Float.parseFloat(calculatorInput.getText());
					 	            switch (operation) {
									case "addition": 
										            Float result = data + secondOperand;
										           calculatorInput.setText(String.valueOf(result));
										break;
										
									case"subtraction":
									    result =data - secondOperand;	
                                              calculatorInput.setText(String.valueOf(result));									    
									 break;
									 
									case "multiplication":
									    result =data * secondOperand;	
                                              calculatorInput.setText(String.valueOf(result));									    
									 break;
									    
									 
									case "division":
									    result =data / secondOperand;	
                                              calculatorInput.setText(String.valueOf(result));									    
									 break;
									 
									default:
										break;
									}
					 	}
					 });
					 equal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 equal.setText("=");  
					 Color equalColor = new Color(shell.getDisplay(), new RGB(0, 255, 0));
					 equal.setBackground(equalColor);
					 
					 Button zero =new Button(shell,SWT.NONE);
					 zero.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		

					 		 calculatorInput.setText(calculatorInput.getText( )+ 0 );
					 	}
					 });
					 zero.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
					 zero.setText("0");  
				        		
					 Button comma =new Button(shell,SWT.NONE);
					 comma.addSelectionListener(new SelectionAdapter() {
					 	@Override
					 	public void widgetSelected(SelectionEvent e) {
					 		

					 		 calculatorInput.setText(calculatorInput.getText( )+ "." );
					 	}
					 });
					 comma.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
					 comma.setText(".");  
				        		
					 
				        		 label = new Label(shell, SWT.FILL);
				        		 label.setText("Operation");	 



	}
}