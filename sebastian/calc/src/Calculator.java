

import java.util.ArrayList;
import java.util.Stack;

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


//Mai trebuie sa fac sa mearga cu decimal si sa ai un ( ) checker + counter
//Dupe ce ai facut un calcul si pui imediat pe un numar ... pusca
 

class Calculator {
	

static Stack<String> operands = new Stack<>();
static Stack<String> multipleDigitStack = new Stack<>();
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
  
     
  //first String in the stack
  operands.push("dorel");
  
  
  Text theText = new Text(shell,  SWT.LEFT_TO_RIGHT);
  theText.setLayoutData(span_colums);
  theText.setFont(font);

  Button btn_openBrackets = new Button(shell, SWT.PUSH);
  btn_openBrackets.setText("(");
  btn_openBrackets.setLayoutData(col_1);
  btn_openBrackets.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("(");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"(");}
	    }
	});
  
  Button btn_closedBrackets = new Button(shell, SWT.PUSH);
  btn_closedBrackets.setText(")");
  btn_closedBrackets.setLayoutData(col_1);
  btn_closedBrackets.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push(")");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+")");}
	    }
	});
  
  Button btnClear = new Button(shell, SWT.PUSH);
  btnClear.setText("C");
  btnClear.setLayoutData(col_1);
  btnClear.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.clear();
	    	//the stack can't be empty
	    	operands.push("dorel");
	    	theText.setText("");
	    }
	});
  
  Button btnBackspace = new Button(shell, SWT.PUSH);
  btnBackspace.setText("DEL");
  btnBackspace.setLayoutData(col_1);
  btnBackspace.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.pop();
	    	if(operands.isEmpty()) 
	    	operands.push("dorel");
	    	if(!theText.getText().isEmpty())
	    	theText.setText(theText.getText().substring(0, theText.getText().length()-1));
	    	
	    }
	});
  
  Button btn_7 = new Button(shell, SWT.PUSH);
  btn_7.setText("7");
  btn_7.setLayoutData(col_1);
  btn_7.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("7");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"7");}
	    }
	});
  
  Button btn_8 = new Button(shell, SWT.PUSH);
  btn_8.setText("8");
  btn_8.setLayoutData(col_1);
  btn_8.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("8");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"8");}
	    }
	});
  
  Button btn_9 = new Button(shell, SWT.PUSH);
  btn_9.setText("9");
  btn_9.setLayoutData(col_1);
  btn_9.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("9");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"9");}
	    }
	});
  
  Button btn_divide = new Button(shell, SWT.PUSH);
  btn_divide.setText("/");
  btn_divide.setLayoutData(col_1);
  btn_divide.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("/");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"/");}
	    }
	});
  
  
  Button btn_4 = new Button(shell, SWT.PUSH);
  btn_4.setText("4");
  btn_4.setLayoutData(col_1);
  btn_4.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("4");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"4");}
	    }
	});
  
  Button btn_5 = new Button(shell, SWT.PUSH);
  btn_5.setText("5");
  btn_5.setLayoutData(col_1);
  btn_5.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("5");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"5");}
	    }
	});
  
  Button btn_6 = new Button(shell, SWT.PUSH);
  btn_6.setText("6");
  btn_6.setLayoutData(col_1);
  btn_6.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("6");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"6");}
	    }
	});
  
  Button btn_multiply = new Button(shell, SWT.PUSH);
  btn_multiply.setText("*");
  btn_multiply.setLayoutData(col_1);
  btn_multiply.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("*");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"*");}
	    }
	});
  
 

  Button btn_1 = new Button(shell, SWT.PUSH);
  btn_1.setText("1");
  btn_1.setLayoutData(col_1);
  btn_1.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("1");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"1");}
	    }
	});
  
  Button btn_2 = new Button(shell, SWT.PUSH);
  btn_2.setText("2");
  btn_2.setLayoutData(col_1);
  btn_2.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("2");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"2");}
	    }
	});
  
  Button btn_3 = new Button(shell, SWT.PUSH);
  btn_3.setText("3");
  btn_3.setLayoutData(col_1);
  btn_3.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("3");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"3");}
	    }
	});
  
  Button btn_minus = new Button(shell, SWT.PUSH);
  btn_minus.setText("-");
  btn_minus.setLayoutData(col_1);
  btn_minus.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("-");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"-");}
	    }
	});

  
  Button btn_0 = new Button(shell, SWT.PUSH);
  btn_0.setText("0");
  btn_0.setLayoutData(col_1);
  btn_0.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("0");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"0");}
	    }
	});
  
  Button btn_dot = new Button(shell, SWT.PUSH);
  btn_dot.setText(".");
  btn_dot.setLayoutData(col_1);
  btn_dot.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push(".");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+".");}
	    }
	});
  
  Button btn_equals = new Button(shell, SWT.PUSH);
  btn_equals.setText("=");
  btn_equals.setLayoutData(col_1);
  btn_equals.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	String[] infixDecimalDouble = ToCalculate.CheckifDecimal( AfterEqualsOperations.ComplexNumbers(operands, operands.size()), operands.size());;
            //infixDecimalDouble = ToCalculate.CheckifDecimal( AfterEqualsOperations.ComplexNumbers(operands, operands.size()), operands.size());
            String[] infixFinal = AfterEqualsOperations.RemoveNull(infixDecimalDouble, operands.size()); 	
	       int infixCount = infixFinal.length;
	       theText.setText(theText.getText()+"="+ToCalculate.PostFixCalculator(ToCalculate.InfixToPostfixConverter(infixFinal, infixCount)));
	       operands.clear();
	      //the stack can't be empty
	       operands.push("dorel");
	    }
	});
  
	    
	    	
  Button btn_plus = new Button(shell, SWT.PUSH);
  btn_plus.setText("+");
  btn_plus.setLayoutData(col_1);
  btn_plus.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	    	operands.push("+");
	    	if(!ToCalculate.Verify(operands.peek(), operands.get(operands.size()-2)))
	    	{operands.pop();}
	    	else{theText.setText(theText.getText()+"+");}
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












