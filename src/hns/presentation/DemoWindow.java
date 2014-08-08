package hns.presentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;
import hns.presentation.StatusBar;

public class DemoWindow {

	private static Shell shell;
	private Display display;
	private static Text statusText;
	private static Patient currentPatient;
	private Label lblAge;
	private Label lblUserName;
	private Label lblHeight;
	private static Label lblWeight;
	private static Label lblBmi;
	private static Label lblBmr ;
	private static Label lblWeightLevel;
	private Composite bmiComp;
	private Composite bmrComp;
	private Composite recComp;
	private Composite composite;
	private Composite nameComp;
	private Composite ageComp;
	private Button btnCalculate;
	private Button btnClose;
	private Text txtAge;
	private Text txtHeight;
	private Text txtWeight;
	/**
	 * Launch the application.
	 * @param currentPatient 
	 * @param args
	 */
	public DemoWindow(){
        display = Display.getDefault();
        createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() 
        { 
           public void handleEvent(Event event) 
           { 
        	   display.close();
        	   new SelectUserWindow();
           } 
        }); 
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(51, 204, 255));
		shell.setSize(300, 322);
		shell.setText("HNS");
		
		nameComp = new Composite(shell, SWT.BORDER);
		nameComp.setBounds(10, 10, 264, 34);

		lblUserName = new Label(nameComp, SWT.NONE);
		lblUserName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblUserName.setAlignment(SWT.CENTER);
		lblUserName.setBackground(SWTResourceManager.getColor(102, 204, 255));
		lblUserName.setBounds(10, 10, 196, 15);
		lblUserName.setText("Welcome!");
		
		Button calculator = new Button(nameComp, SWT.NONE);
		calculator.setBounds(221, 0, 29, 29);
		calculator.setImage(new Image(null, "images/calc.jpg"));
		calculator.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new CalculatorWindow();
			}
		});
		ageComp = new Composite(shell, SWT.BORDER);
		ageComp.setBounds(10, 50, 264, 34);
		
		lblAge = new Label(ageComp, SWT.NONE);
		lblAge.setBounds(10, 10, 25, 15);
		lblAge.setText("Age: ");
		
		lblHeight = new Label(ageComp, SWT.NONE);
		lblHeight.setBounds(70, 10, 39, 15);
		lblHeight.setText("Height:");
		
		txtHeight = new Text(ageComp, SWT.BORDER);
		txtHeight.setBounds(110, 7, 30, 21);
		ValidateSwtText.enableInt(txtHeight);
		txtHeight.setTextLimit(3);
		
		lblWeight = new Label(ageComp, SWT.NONE);
		lblWeight.setBounds(140, 10, 61, 15);
		lblWeight.setText("cm Weight:");
		
		txtWeight = new Text(ageComp, SWT.BORDER);
		txtWeight.setBounds(202, 7, 30, 21);
		ValidateSwtText.enableInt(txtWeight);
		txtWeight.setTextLimit(3);
		
		txtAge = new Text(ageComp, SWT.BORDER);
		txtAge.setBounds(37, 7, 27, 21);
		txtAge.setToolTipText("");
		ValidateSwtText.enableInt(txtAge);
		txtAge.setTextLimit(3);
		
		Label lblKG = new Label(ageComp, SWT.NONE);
		lblKG.setBounds(235, 10, 23, 15);
		lblKG.setText("kg");
		
		bmiComp = new Composite(shell, SWT.BORDER);
		bmiComp.setToolTipText("Body mass index (BMI) is a measure of body fat based on height and weight that applies to adult men and women.");
		//source : http://www.nhlbisupport.com/bmi/bmi-m.htm
		bmiComp.setBounds(10, 90, 264, 34);
		
		lblBmi = new Label(bmiComp, SWT.NONE);
		lblBmi.setBounds(10, 10, 110, 15);
		lblBmi.setText("BMI: ");
		
		bmrComp = new Composite(shell, SWT.BORDER);
		bmrComp.setToolTipText("Your total daily energy expenditure, or TDEE, is the total number of calories that your body expends in 24 hours, including all activities. ");
		//source : www.shapefit.com/basal-metabolic-rate.html
		bmrComp.setBounds(10, 170, 264, 34);
		
		lblBmr = new Label(bmrComp, SWT.SHADOW_NONE);
		lblBmr.setText("Required Calorie Intake:");
		lblBmr.setToolTipText("");
		lblBmr.setBounds(10, 10, 227, 15);
		
		recComp = new Composite(shell, SWT.BORDER);
		recComp.setBounds(10, 210, 264, 34);
		
		btnCalculate = new Button(recComp, SWT.NONE);
		btnCalculate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				getInfo();
			}
		});
		btnCalculate.setBounds(10, 3, 75, 25);
		btnCalculate.setText("Calculate");
		
		btnClose = new Button(recComp, SWT.NONE);
		btnClose.setBounds(158, 3, 75, 25);
		btnClose.addSelectionListener(new SelectionAdapter() 
        { 
	           public void widgetSelected(SelectionEvent arg0) 
	           { 
	         	  //SelectUserWindow.freeDemoWindow();
	         	  display.close();
	        	  new SelectUserWindow();
	           } 
	        }); 
		
		btnClose.setText("Close");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(267, 34);
		statusText.setLocation(10, 250);
		StatusBar.create(display, shell, statusText);
		
		composite = new Composite(shell, SWT.BORDER);
		composite.setToolTipText("Body mass index (BMI) is a measure of body fat based on height and weight that applies to adult men and women.");
		composite.setBounds(10, 130, 264, 34);
		
		lblWeightLevel = new Label(composite, SWT.NONE);
		lblWeightLevel.setText("Weight Level:");
		lblWeightLevel.setBounds(10, 10, 240, 15);
		
		txtAge.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        getInfo();
		      }
	    });
		txtHeight.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        getInfo();
		      }
	    });
		txtWeight.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        getInfo();
		      }
	    });
	}
	public static void updateWindow(){
		shell.redraw();
		if(currentPatient==null){
			lblWeightLevel.setText("Weight Level: ");
			lblBmr.setText("Required Daily Calories: ");
			lblBmi.setText("BMI: ");

		}else{
			lblBmi.setText("BMI: "+currentPatient.getBMI()+"");
			lblBmr.setText("Required Daily Calories: "+currentPatient.getCalories()+" kCal");
			lblWeightLevel.setText("Weight Level: "+currentPatient.getWeightLevel());
		}
	}

	public boolean isValidString(String input){
		
		boolean result = true;
		if(input.trim().length() <= 0 || input.trim().length() > 12){
			result = false;
		}
		return result;
	}
	public boolean isValidInt(String input){
		
		int num;
		try{
			num = Integer.parseInt(input);
		}
		catch(Exception e){
			return false;
		}
		if(num <= 0 || num > 1000){
			return false;
		}
		return true;
	}
	public void getInfo(){
		StatusBar.clear(statusText);
		if(!isValidInt(txtAge.getText()) || !isValidInt(txtWeight.getText()) || 
				   !isValidInt(txtHeight.getText())){
					
					StatusBar.setWarning(statusText, "please enter Missing information");
				}
				else{

					try {
						currentPatient = new Patient(1000, 1000, "Demo", 
								"User", Integer.parseInt(txtAge.getText()),Integer.parseInt(txtWeight.getText()),
								Integer.parseInt(txtHeight.getText()), Gender.MALE, ActivityLevel.MODERATE);
						updateWindow();
					} catch (NumberFormatException e1) {
						StatusBar.setWarning(statusText, "Please enter a valid Number");
					} catch (IllegalArgumentException e1) {
						StatusBar.setWarning(statusText, e1.getMessage());
					} catch (Exception e1) {
						StatusBar.setWarning(statusText, "Unknown Exception");
						e1.printStackTrace();
					}
					
				}
	}
}
