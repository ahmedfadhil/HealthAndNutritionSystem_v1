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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import hns.businesslogic.AccessPatients;
import hns.enums.ActivityLevel;
import hns.enums.Gender;
import hns.objects.Patient;
import hns.presentation.StatusBar;

public class NewUserWindow {

	private Shell shell;
	private Display display;
	private Text fNameText;
	private Text lNameText;
	private Text ageText;
	private Text weightText;
	private Text heightText;
	private static Text statusText;
	private AccessPatients patientList;
	private Patient currentPatient;
	private ActivityLevel activityLevel;
	private Gender gender;
	private static int userID = 1000;
	private static int journalID = 1000;

	public NewUserWindow(){
        display = Display.getDefault();
		createWindow();
		runWindow();
	}
	
    public void runWindow(){
    	
		while (!shell.isDisposed()){
			if (!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
    }
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void createWindow(){
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
	protected void createContents(){
		//Set Default gender and activity level
		gender = Gender.MALE;
		activityLevel = ActivityLevel.MODERATE;
		
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shell.setSize(303, 389);
		shell.setText("Create an Account");
		Composite idpwComp = new Composite(shell, SWT.NONE);
		idpwComp.setBounds(10, 10, 267, 35);
		
		Label lblTitle = new Label(idpwComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(102, 204, 255));
		lblTitle.setBounds(45, 10, 170, 15);
		lblTitle.setText("Enter Your information");
		Button calculator = new Button(idpwComp, SWT.NONE);
		calculator.setBounds(228, 3, 29, 29);
		calculator.setImage(new Image(null, "images/calc.jpg"));
		calculator.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new CalculatorWindow();
			}
		});
		
		Composite nameComp = new Composite(shell, SWT.NONE);
		nameComp.setBounds(10, 51, 267, 35);
		
		Label lblName = new Label(nameComp, SWT.NONE);
		lblName.setBounds(10, 10, 37, 15);
		lblName.setText("Name:");
		
		fNameText = new Text(nameComp, SWT.BORDER);
		fNameText.setText("Average");
		fNameText.setBounds(47, 7, 76, 21);
		ValidateSwtText.enableText(fNameText);
		fNameText.setTextLimit(30);

		
		lNameText = new Text(nameComp, SWT.BORDER);
		lNameText.setText("American");
		lNameText.setBounds(129, 7, 76, 21);
		ValidateSwtText.enableText(lNameText);
		lNameText.setTextLimit(30);
		
		Composite ageGenComp = new Composite(shell, SWT.NONE);
		ageGenComp.setBounds(10, 92, 267, 35);
		
		Label lblAge = new Label(ageGenComp, SWT.NONE);
		lblAge.setBounds(10, 10, 35, 15);
		lblAge.setText("Age:");
		
		ageText = new Text(ageGenComp, SWT.BORDER);
		ageText.setText("18");
		ageText.setBounds(45, 7, 35, 21);
		ValidateSwtText.enableInt(ageText);
		ageText.setTextLimit(3);
		
		Label lblGender = new Label(ageGenComp, SWT.NONE);
		lblGender.setText("Gender:");
		lblGender.setBounds(92, 10, 47, 15);
		
		Button btnMale = new Button(ageGenComp, SWT.RADIO);
		btnMale.setSelection(true);
		btnMale.setBounds(145, 9, 47, 16);
		btnMale.setText("Male");
		btnMale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				gender = Gender.MALE;			
			}
		});
		
		Button btnFemale = new Button(ageGenComp, SWT.RADIO);
		btnFemale.setBounds(198, 9, 59, 16);
		btnFemale.setText("Female");
		btnFemale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				gender = Gender.FEMALE;			
			}
		});
		
		Composite weightComp = new Composite(shell, SWT.NONE);
		weightComp.setToolTipText("Convertion approximation \n1 lbs = .45 kg");
		weightComp.setBounds(10, 133, 267, 35);
		
		Label lblWeight = new Label(weightComp, SWT.NONE);
		lblWeight.setBounds(10, 10, 50, 15);
		lblWeight.setText("Weight:");
		
		weightText = new Text(weightComp, SWT.BORDER);
		weightText.setToolTipText("Convertion approximation \n1 lbs = .45 kg");
		weightText.setText("75");
		weightText.setBounds(66, 7, 50, 21);
		weightText.setTextLimit(3);
		ValidateSwtText.enableInt(weightText);
		
		Label lblLbs = new Label(weightComp, SWT.NONE);
		lblLbs.setBounds(121, 10, 55, 15);
		lblLbs.setText("kg");
		
		Composite heightComp = new Composite(shell, SWT.NONE);
		heightComp.setToolTipText("Convertion approximations\n1 foot = 30cm\n1 inch=2 cm");
		heightComp.setBounds(10, 174, 267, 35);
		
		Label lblHeight = new Label(heightComp, SWT.NONE);
		lblHeight.setBounds(10, 10, 55, 15);
		lblHeight.setText("Height:");
		
		heightText = new Text(heightComp, SWT.BORDER);
		heightText.setToolTipText("Convertion approximations \n1 foot = 30cm\n1 inch=2 cm");
		heightText.setText("162");
		heightText.setBounds(66, 7, 50, 21);
		heightText.setTextLimit(3);
		ValidateSwtText.enableInt(heightText);
		Label lblFtin = new Label(heightComp, SWT.NONE);
		lblFtin.setBounds(121, 10, 55, 15);
		lblFtin.setText("cm");
		
		Composite actComp = new Composite(shell, SWT.NONE);
		actComp.setBounds(10, 215, 267, 52);
		
		Label lblActivityLevel = new Label(actComp, SWT.NONE);
		lblActivityLevel.setBounds(10, 10, 73, 15);
		lblActivityLevel.setText("Activity Level:");
		
		Button btnSedentary = new Button(actComp, SWT.RADIO);
		btnSedentary.setBounds(89, 9, 77, 16);
		btnSedentary.setText("Sedentary");
		btnSedentary.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				activityLevel = ActivityLevel.SEDENTARY;
			}
		});
		
		Button btnLight = new Button(actComp, SWT.RADIO);
		btnLight.setText("Light");
		btnLight.setBounds(172, 9, 50, 16);
		btnLight.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				activityLevel = ActivityLevel.LIGHT;
			}
		});
		
		Button btnModerate = new Button(actComp, SWT.RADIO);
		btnModerate.setSelection(true);
		btnModerate.setText("Moderate");
		btnModerate.setBounds(10, 31, 73, 16);
		btnModerate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				activityLevel = ActivityLevel.MODERATE;	
			}
		});
		
		Button btnVery = new Button(actComp, SWT.RADIO);
		btnVery.setText("Very");
		//setBounds(x coord, y coord, width, height)
		btnVery.setBounds(89, 31, 65, 16);
		btnVery.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				activityLevel = ActivityLevel.VERY;		
			}
		});
		
		
		Button btnExtra = new Button(actComp, SWT.RADIO);
		btnExtra.setText("Extra");
		btnExtra.setBounds(172, 31, 80, 16);
		btnExtra.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				activityLevel = ActivityLevel.EXTRA;			
			}
		});
		
		Composite buttonsComp = new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(10, 273, 267, 35);
		
		Button btnCancel = new Button(buttonsComp, SWT.NONE);
		btnCancel.setBounds(182, 5, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new SelectUserWindow();
			}
		});
		
		
		Button btnCreate = new Button(buttonsComp, SWT.NONE);
		btnCreate.setBounds(10, 5, 75, 25);
		btnCreate.setText("Create");
		btnCreate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				submitInfo();
			}
		});

		Button btnClear = new Button(buttonsComp, SWT.NONE);
		btnClear.setText("Clear");
		btnClear.setBounds(95, 5, 75, 25);
		btnClear.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				fNameText.setText("First");
				lNameText.setText("Last");
				ageText.setText("");
				weightText.setText("");
				heightText.setText("");
			}
		});
		
		fNameText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		lNameText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		ageText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		weightText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		heightText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(267, 30);
		statusText.setLocation(10, 314);
		StatusBar.create(display, shell, statusText);
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
	public void submitInfo(){
		String result=null;
		StatusBar.clear(statusText);
		
		if(!isValidString(fNameText.getText()) || !isValidString(lNameText.getText()) ||
		   !isValidInt(ageText.getText()) || !isValidInt(weightText.getText()) || 
		   !isValidInt(heightText.getText()) || gender == null || activityLevel == null){
			
			StatusBar.setWarning(statusText, "please enter valid info (max string length 15)");
		}
		else{

			try {
				if (patientList == null){
					patientList = new AccessPatients();
				}
				currentPatient = new Patient(patientList.countPatients()+1, journalID, lNameText.getText(), fNameText.getText(), Integer.parseInt(ageText.getText()),
						Integer.parseInt(weightText.getText()), Integer.parseInt(heightText.getText()), gender, activityLevel);
				userID ++;
				journalID ++;
				
				result = patientList.insertPatient(currentPatient);
				if(result!=null){
					StatusBar.setWarning(statusText, result);
				}else{
					shell.dispose();
					new SelectUserWindow();
				}
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


