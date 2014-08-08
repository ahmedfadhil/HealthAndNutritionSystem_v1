package hns.presentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener; 
import org.eclipse.wb.swt.SWTResourceManager;
import hns.presentation.StatusBar;

public class CalculatorWindow {

	private Display display;
	private Shell shell;
	private Text statusText;
	private Label lblTitle;
	private Composite titleComp;
	private Composite uIdComp;
	private Composite updateFieldComp;
	private Composite buttonsComp ;
	private Button btnClose;
	private Text txtLbs;
	private Text txtFt;
	private Text txtIn;
	private Label lblFt;
	private Label lblIn;
	private Label lblLbs;
	private Text txtKg;
	private Text txtCm;
	private Button convertLbs;

	public CalculatorWindow(){
		display = Display.getDefault();
		createWindow();
	}
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void createWindow(){
		createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() 
        { 
           public void handleEvent(Event event) 
           { 
        	  shell.dispose(); 
              if (!display.readAndDispatch()) {
  				display.sleep();
  			}
           } 
        }); 
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shell.setSize(216, 241);
		shell.setText("Simple converter");
		
		titleComp = new Composite(shell, SWT.NONE);
		titleComp.setBounds(10, 10, 179, 35);
		
		lblTitle = new Label(titleComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTitle.setBounds(10, 10, 159, 15);
		lblTitle.setText("Simple Converter");
		
		uIdComp = new Composite(shell, SWT.NONE);
		uIdComp.setBounds(10, 59, 179, 33);
		
		txtLbs = new Text(uIdComp, SWT.BORDER);
		txtLbs.setBounds(10, 7, 36, 21);
		txtLbs.setTextLimit(3);
		ValidateSwtText.enableInt(txtLbs);
		
		lblLbs = new Label(uIdComp, SWT.NONE);
		lblLbs.setBounds(52, 10, 20, 15);
		lblLbs.setText("lbs");
		
		convertLbs = new Button(uIdComp, SWT.NONE);
		convertLbs.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				calculateKG();
			}
		});
		
		convertLbs.setBounds(78, 3, 20, 25);
		convertLbs.setText(">");
		
		txtKg = new Text(uIdComp, SWT.BORDER);
		txtKg.setEditable(false);
		txtKg.setText("kg");
		txtKg.setBounds(104, 7, 65, 21);
		
		updateFieldComp= new Composite(shell, SWT.NONE);
		updateFieldComp.setBounds(10, 98, 179, 35);
		
		txtFt = new Text(updateFieldComp, SWT.BORDER);
		txtFt.setBounds(10, 7, 19, 21);
		txtFt.setTextLimit(1);
		ValidateSwtText.enableInt(txtFt);
		
		txtIn = new Text(updateFieldComp, SWT.BORDER);
		txtIn.setBounds(46, 7, 26, 21);
		txtIn.setTextLimit(2);
		ValidateSwtText.enableInt(txtIn);
		
		lblFt = new Label(updateFieldComp, SWT.NONE);
		lblFt.setBounds(30, 10, 19, 15);
		lblFt.setText("ft");
		
		lblIn = new Label(updateFieldComp, SWT.NONE);
		lblIn.setBounds(72, 10, 19, 15);
		lblIn.setText("in");
		
		Button convertFt = new Button(updateFieldComp, SWT.NONE);
		convertFt.setText(">");
		convertFt.setBounds(91, 3, 20, 25);
		convertFt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				calculateFT();
			}
		});
		
		txtCm = new Text(updateFieldComp, SWT.BORDER);
		txtCm.setEditable(false);
		txtCm.setText("cm");
		txtCm.setBounds(117, 7, 52, 21);
		
		
		buttonsComp= new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(10, 139, 179, 33);
		
		btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				shell.dispose();
			}

		});
		btnClose.setBounds(48, 5, 75, 25);
		btnClose.setText("Cancel");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(10, 178);
		StatusBar.create(display, shell, statusText);
		shell.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        System.out.println(e.widget + " - Default Selection");
		        System.out.println(e.toString());
		      }
	    });
		txtLbs.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        calculateKG();
		      }
	    });
		txtFt.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        calculateFT();
		      }
	    });
		txtIn.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        calculateFT();
		      }
	    });
	}

	public boolean isValidInt(String input){
		
		int num;
		try{
			num = Integer.parseInt(input);
		}
		catch(Exception e){
			return false;
		}
		if(num < 0 || num > 1000){
			return false;
		}
		return true;
	}
	public void calculateKG(){
		if(txtLbs.getText().equals("")){
			txtLbs.setText("0");
		}
		if(isValidInt(txtLbs.getText())){
			int temp = (int) Math.round(0.45359237*Double.parseDouble(txtLbs.getText()));
			txtKg.setText(temp+"kg");
		}
	}
	public void calculateFT(){
		if(txtFt.getText().equals("")){
			txtFt.setText("0");
		}
		if(txtIn.getText().equals("")){
			txtIn.setText("0");
		}
		if(isValidInt(txtFt.getText())&&isValidInt(txtIn.getText())){
			int temp=(int)Math.round(Double.parseDouble(txtIn.getText())+Double.parseDouble(txtFt.getText())*12);
			temp = (int) Math.round(2.54*temp);
			txtCm.setText(temp+"cm");
		}else {
			StatusBar.setWarning(statusText, "Please fill both ft and in fields");
		}
	}

}
