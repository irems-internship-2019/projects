package calc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class ButtonClass {
	
	static public ArrayList<String> operation = new ArrayList<>();
	static public ArrayList<Integer> operators = new ArrayList<>(); 
	
	public void buttons(Shell shell)
	{
		 Composite composite = new Composite(shell, SWT.NONE);
		    
		    GridLayout layout = new GridLayout(4,true);
		    composite.setLayout(layout); 
			 
			GridData dataFirst = new GridData(SWT.FILL,SWT.FILL,true,true,4,1);
			GridData dataSecond = new GridData(SWT.FILL,SWT.FILL,true,true);
			GridData dataThird = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
		    
		    List list = new List(composite, SWT.BORDER |SWT.RIGHT_TO_LEFT);
			list.setLayoutData(dataFirst);
			
			Button buttonBack = new Button(composite, SWT.NONE);
			buttonBack.setLayoutData(dataThird);
			buttonBack.setText("Back");
			
			buttonBack.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonBack.getText());
	            }
	        });
				
			Button buttonDivide = new Button(composite, SWT.NONE);
			buttonDivide.setLayoutData(dataThird);
			buttonDivide.setText("÷");	
			
			buttonDivide.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonDivide.getText());
	                operation.add(0,buttonDivide.getText());
	            }
	        });
				
					
			Button button7 = new Button(composite, SWT.NONE);
			button7.setLayoutData(dataSecond);
			button7.setText("7");
			
			button7.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button7.getText());
	                if(check() == true) operators.add(0,7);
	                else operators.add(1,7);	            }
	        });
				
			Button button8 = new Button(composite, SWT.NONE);
			button8.setLayoutData(dataSecond);
			button8.setText("8");
			
			button8.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button8.getText());
	                if(check() == true) operators.add(0,8);
	                else operators.add(1,8);
	            }
	        });
			
			Button button9 = new Button(composite, SWT.NONE);
			button9.setLayoutData(dataSecond);
			button9.setText("9");
			
			button9.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button9.getText());
	                if(check() == true) operators.add(0,9);
	                else operators.add(1,9);
	            }
	        });
			
			Button buttonMultiple = new Button(composite, SWT.NONE);
			buttonMultiple.setLayoutData(dataSecond);
			buttonMultiple.setText("x");
			
			buttonMultiple.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonMultiple.getText());
	                operation.add(0,buttonMultiple.getText());
	            }
	        });
			
			Button button4 = new Button(composite, SWT.NONE);
			button4.setLayoutData(dataSecond);
			button4.setText("4");
			
			button4.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button4.getText());
	                if(check() == true) operators.add(0,4);
	                else operators.add(1,4);
	            }
	        });
			
			Button button5 = new Button(composite, SWT.NONE);
			button5.setLayoutData(dataSecond);
			button5.setText("5");
			
			button5.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button5.getText());
	                if(check() == true) operators.add(0,5);
	                else operators.add(1,5);
	            }
	        });
			
			Button button6 = new Button(composite, SWT.NONE);
			button6.setLayoutData(dataSecond);
			button6.setText("6");
			
			button6.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button6.getText());
	                if(check() == true) operators.add(0,6);
	                else operators.add(1,6);
	            }
	        });
			
			Button buttonMinus = new Button(composite, SWT.NONE);
			buttonMinus.setLayoutData(dataSecond);
			buttonMinus.setText("-");
			
			buttonMinus.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonMinus.getText());
	                operation.add(0,buttonMinus.getText());
	            }
	        });
			
			Button button1 = new Button(composite, SWT.NONE);
			button1.setLayoutData(dataSecond);
			button1.setText("1");
			
			button1.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button1.getText());
	                if(check() == true) operators.add(0,1);
	                else operators.add(1,1);
	            }
	        });
			
			Button button2 = new Button(composite, SWT.NONE);
			button2.setLayoutData(dataSecond);
			button2.setText("2");
			
			button2.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button2.getText());
	                if(check() == true) operators.add(0,2);
	                else operators.add(1,2);
	            }
	        });
			
			Button button3 = new Button(composite, SWT.NONE);
			button3.setLayoutData(dataSecond);
			button3.setText("3");
			
			button3.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button3.getText());
	                if(check() == true) operators.add(0,3);
	                else operators.add(1,3);
	            }
	        });
			
			Button buttonPlus = new Button(composite, SWT.NONE);
			buttonPlus.setLayoutData(dataSecond);
			buttonPlus.setText("+");
			
			buttonPlus.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonPlus.getText());
	                operation.add(0,buttonPlus.getText());
	            }
	        });
			
			Button buttonPunct = new Button(composite, SWT.NONE);
			buttonPunct.setLayoutData(dataSecond);
			buttonPunct.setText(".");
			
			buttonPunct.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonPunct.getText());
	            }
	        });
			
			Button button0 = new Button(composite, SWT.NONE);
			button0.setLayoutData(dataSecond);
			button0.setText("0");
			
			button0.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(button0.getText());
	            }
	        });
			
			Button buttonEqual = new Button(composite, SWT.NONE);
			buttonEqual.setLayoutData(dataThird);
			buttonEqual.setText("=");
			
			buttonEqual.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	                list.add(buttonEqual.getText());
	                operation.add(buttonEqual.getText());
	                decide(list,buttonEqual);
	                
	            }
	        });
			
	}
	
	public boolean check()
	{
		boolean check = false;
		
		if(operators.size() <1)
			
		check =true;
		
		return check;
	}
	
	public void decide(List list,Button buttonEqual)
	{
		
		 if(operation.get(operation.indexOf(buttonEqual.getText() ) - 1) == "+")
         	list.add(Operation.plus());
         
         if(operation.get(operation.indexOf(buttonEqual.getText() ) - 1) == "-")
         	list.add(Operation.minus());
		
	}
	
}
