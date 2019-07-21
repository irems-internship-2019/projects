package calc;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;



class Calculator {
	
	
private static Label Comands;
private static Text text;
private static Text text_1;

static ArrayList<String> results = new ArrayList<String>();
static int res = 0;

public static void main(String[] args) {
	 
  
  Display display = new Display();
  Shell shell = new Shell(display);
  shell.setText("Calculator");
  shell.setSize(300, 400);
  
  Font font = new Font(shell.getDisplay(), new FontData("Arial", 20, SWT.NONE));

  
  //Image backImage = new Image(display, "the.png_folder/backspace.png"); 
  
  //Seteaza dimensiunea gridului, nr linii si coloane
  GridLayout gridLayout = new GridLayout(4, false);
  //gridLayout.verticalSpacing = 5;
  shell.setLayout(gridLayout);
  
  //GRID DATA##############################################################
  
     //GridData pentru results
     GridData span_colums = new  GridData(SWT.FILL, SWT.FILL, true, true);
     span_colums.horizontalSpan = 4;
  
     //GridDAta pentru butoane
     GridData col_1 = new GridData(SWT.FILL, SWT.FILL, true, true);
     col_1.horizontalSpan = 1;
  
     //GridData pentru history
     GridData rowHistory = new GridData(SWT.FILL, SWT.FILL, true, true);
     rowHistory.horizontalSpan = 3;
  
  
  
  List theList = new List(shell,  SWT.RIGHT_TO_LEFT | SWT.V_SCROLL);
  theList.setLayoutData(span_colums);
  theList.setFont(font);

  
  Button btnHistory = new Button(shell, SWT.PUSH);
  btnHistory.setText("History");
  btnHistory.setLayoutData(col_1);
  
  
  Button btn_openBrackets = new Button(shell, SWT.PUSH);
  btn_openBrackets.setText("(");
  btn_openBrackets.setLayoutData(col_1);
  
  Button btn_closedBrackets = new Button(shell, SWT.PUSH);
  btn_closedBrackets.setText(")");
  btn_closedBrackets.setLayoutData(col_1);
  
  Button btnBackspace = new Button(shell, SWT.PUSH);
  btnBackspace.setText("DEL");
  //btnBackspace.setImage(backImage);
  //new Image(getDisplay(), getClass().getResourceAsStream("the.png_folder/backspace.png"));
  btnBackspace.setLayoutData(col_1);
  
  
  Button btn_7 = new Button(shell, SWT.PUSH);
  btn_7.setText("7");
  btn_7.setLayoutData(col_1);
  
  Button btn_8 = new Button(shell, SWT.PUSH);
  btn_8.setText("8");
  btn_8.setLayoutData(col_1);
  
  Button btn_9 = new Button(shell, SWT.PUSH);
  btn_9.setText("9");
  btn_9.setLayoutData(col_1);
  
  Button btn_divide = new Button(shell, SWT.PUSH);
  btn_divide.setText("/");
  btn_divide.setLayoutData(col_1);
  
  
  
  Button btn_4 = new Button(shell, SWT.PUSH);
  btn_4.setText("4");
  btn_4.setLayoutData(col_1);
  
  Button btn_5 = new Button(shell, SWT.PUSH);
  btn_5.setText("5");
  btn_5.setLayoutData(col_1);
  
  Button btn_6 = new Button(shell, SWT.PUSH);
  btn_6.setText("6");
  btn_6.setLayoutData(col_1);
  
  Button btn_multiply = new Button(shell, SWT.PUSH);
  btn_multiply.setText("x");
  btn_multiply.setLayoutData(col_1);
  
  
 

  Button btn_1 = new Button(shell, SWT.PUSH);
  btn_1.setText("1");
  btn_1.setLayoutData(col_1);
  
  Button btn_2 = new Button(shell, SWT.PUSH);
  btn_2.setText("2");
  btn_2.setLayoutData(col_1);
  btn_2.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	results.add("2");
	    }
	});
  
  Button btn_3 = new Button(shell, SWT.PUSH);
  btn_3.setText("3");
  btn_3.setLayoutData(col_1);
  btn_3.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	results.add("3");
	    }
	});
  
  Button btn_minus = new Button(shell, SWT.PUSH);
  btn_minus.setText("-");
  btn_minus.setLayoutData(col_1);
  

  
  Button btn_0 = new Button(shell, SWT.PUSH);
  btn_0.setText("0");
  btn_0.setLayoutData(col_1);
  
  Button btn_dot = new Button(shell, SWT.PUSH);
  btn_dot.setText(".");
  btn_dot.setLayoutData(col_1);
  
  Button btn_equals = new Button(shell, SWT.PUSH);
  btn_equals.setText("=");
  btn_equals.setLayoutData(col_1);
  btn_equals.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	/*
	    	//theList.add("=");
	    	//List<Integer> intLIst = Arrays.asList(myList);
	    	  
	    	for(String print : results)
	            theList.add(print);    
	    	
	    	System.out.println(results);
	    	
	    	 for(String a : results) {
	    	 res = res + Integer.parseInt(a);
	    	 }
	    	 
	    	 //trebe facut Switch case sau if else pentru fiecare din variantele posibile...
	    	  *sau folosind PostFix cu stiva LIFO
	    	 
	    	 System.out.println(res);
	    	*/
	    	
	    	
	    	//String[] exp = {"(","1","+","4",")","*","(","2","+","3",")"}; 
	    	String[] exp = {"12","+","3"}; 
	    	
	    	
//	    	 String[] res = ToCalculate.InfixToPostfixConverter(exp);
//	    	    for(String str : res)
//	    	        System.out.println(str);
	        
	    	
	    	
	    	
	    	
	    	             // (1+4)*(2+3)     -  "1" ,"4", "+", "2" ,"3","+","*"
	    	             // (10+3)*5/(16-4)    -  "10" ,"3" ,"+" ,"5" ,"*" ,"16" ,"4" ,"-" ,"/"
	    	             //  10+3*5/(16-4)    - 10 3 5 * 16 4 - / +    
	    	
	    	
	    	
	        //String[] strArr = {"10", "3", "5", "*", "16", "4", "-", "/", "+"};
	       System.out.println(ToCalculate.PostFixCalculator(ToCalculate.InfixToPostfixConverter(exp)));

	    	 
	    	 
	    }
	});
  
  Button btn_plus = new Button(shell, SWT.PUSH);
  btn_plus.setText("+");
  btn_plus.setLayoutData(col_1);
  btn_plus.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	results.add("+");
	    }
	});
  
  

  shell.open();
  while (!shell.isDisposed()) {
    if (!display.readAndDispatch())
      display.sleep();
  }
  display.dispose();
}
}








