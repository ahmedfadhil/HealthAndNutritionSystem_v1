package hns.presentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener; 
import org.eclipse.swt.custom.CCombo;
import org.eclipse.wb.swt.SWTResourceManager;

import hns.businesslogic.AccessPatients;
import hns.businesslogic.AccessRecord;
import hns.objects.Patient;
import hns.presentation.StatusBar;

public class UpdateWindow {

	private Display display;
	private Shell shell;
	private Shell shlHns;
	private Text updateInfoText;
	private Text statusText;
	private CCombo combo;
	private String selectionInfo;
	private Label lblTitle;
	private Composite titleComp;
	private Composite uIdComp;
	private Composite updateFieldComp;
	private Composite buttonsComp ;
	private Button btnUpdate;
	private Button btnClose;
	private Patient currentPatient;
	private AccessPatients patientList;
	private AccessRecord accessRecord;

	public UpdateWindow(Patient currentPatient, Shell shlHns, Display display){
		
        this.display = display;
        this.currentPatient = currentPatient;
        this.shlHns = shlHns;
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
         	  display.close();
         	  new MainUserWindow(currentPatient);
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
		selectionInfo="";
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shell.setSize(216, 241);
		shell.setText("Update Info");
		
		titleComp = new Composite(shell, SWT.NONE);
		titleComp.setBounds(10, 10, 179, 35);
		
		lblTitle = new Label(titleComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTitle.setBounds(10, 10, 121, 15);
		lblTitle.setText("Select Info To Update");
		
		uIdComp = new Composite(shell, SWT.NONE);
		uIdComp.setBounds(10, 51, 179, 33);
		
		combo = new CCombo(uIdComp, SWT.BORDER);
		combo.setBackground(SWTResourceManager.getColor(255, 255, 255));
		combo.setEditable(false);
		combo.setItems(new String[] {"First Name", "Last Name", "Age", "Height", "Weight"});
		combo.setBounds(10, 5, 159, 21);
		combo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) 
			{
				selectionInfo = combo.getText();
				updateInfoText.setText("");
				if (selectionInfo.contains("Age")||selectionInfo.contains("Height")
						||selectionInfo.contains("Weight")){
					ValidateSwtText.enableInt(updateInfoText);
					updateInfoText.setTextLimit(3);
					updateInfoText.setToolTipText("Convertion approximations \n1 lbs = .45 kg\n1 foot = 30cm\n1 inch=2 cm");

				}else{
					updateInfoText.setTextLimit(12);
					ValidateSwtText.enableText(updateInfoText);
				}
			}
		});
		
		updateFieldComp= new Composite(shell, SWT.NONE);
		updateFieldComp.setBounds(10, 90, 179, 43);
		
		updateInfoText = new Text(updateFieldComp, SWT.BORDER);
		updateInfoText.setText("Enter New Info Here");
		updateInfoText.setBounds(10, 10, 153, 21);
		
		
		buttonsComp= new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(10, 139, 179, 33);
		Button calculator = new Button(titleComp, SWT.NONE);
		calculator.setBounds(140, 3, 29, 29);
		calculator.setImage(new Image(null, "images/calc.jpg"));
		calculator.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				new CalculatorWindow();
			}
		});
		btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				display.close();
				new MainUserWindow(currentPatient);
			}
		});
		btnClose.setBounds(94, 5, 75, 25);
		btnClose.setText("Cancel");
		
		
		btnUpdate = new Button(buttonsComp, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				submitInfo();
			}
		});
		btnUpdate.setBounds(10, 5, 75, 25);
		btnUpdate.setText("Update");
		
		updateInfoText.addListener(SWT.DefaultSelection, new Listener() {
		      public void handleEvent(Event e) {
		        submitInfo();
		      }
	    });
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(10, 178);
		StatusBar.create(display, shell, statusText);
	}
	
	protected void submitInfo() {
		String result=null;
		StatusBar.clear(statusText);
		
		if(selectionInfo.contains("First") || selectionInfo.contains("Last")){
			if(!isValidString(selectionInfo)){
				StatusBar.setWarning(statusText, "Enter a valid string");
			}
		}
		else if(selectionInfo.contains("Age") || selectionInfo.contains("Height") || selectionInfo.contains("Weight")){
			if(!isValidInt(selectionInfo)){
				StatusBar.setWarning(statusText, "Enter a valid int");
			}
		}
		
		if (selectionInfo.equals("")){
			StatusBar.setWarning(statusText, "No info Selected");
		}else if (updateInfoText.getText().equals("")){
			StatusBar.setWarning(statusText, "No update info");
			
		}else{
			try{
				
				if(selectionInfo.contains("First")){
					currentPatient.updateFirstName(updateInfoText.getText());
				}else if(selectionInfo.contains("Last")){
					currentPatient.updateLastName(updateInfoText.getText());
				}else if(selectionInfo.contains("Age")){
					currentPatient.updateAge(Integer.parseInt(updateInfoText.getText()));
				}else if(selectionInfo.contains("Height")){
					currentPatient.updateHeight(Integer.parseInt(updateInfoText.getText()));
				}else if(selectionInfo.contains("Weight")){
					currentPatient.updateWeight(Integer.parseInt(updateInfoText.getText()));
					((currentPatient.getJournal()).getLatestRecord()).setWeight(Integer.parseInt(updateInfoText.getText()));
					if(accessRecord == null){
						accessRecord = new AccessRecord();
					}
					result = accessRecord.updateRecordWeight(currentPatient.getJournal(), currentPatient.getJournal().getLatestRecord());	
					if(result != null){
						StatusBar.setWarning(statusText, result);
					}
				}
				if(patientList == null){
					patientList = new AccessPatients();
				}
				result = patientList.updatePatient(currentPatient);
				if(result != null){
					StatusBar.setWarning(statusText, result);
				}

				display.close();
				new MainUserWindow(currentPatient);
			}catch (NumberFormatException e1) {
				StatusBar.setWarning(statusText, "Invalid Number");
			}catch (IllegalArgumentException e1) {
				StatusBar.setWarning(statusText, e1.getMessage());
			}catch (NullPointerException e1) {
				StatusBar.setWarning(statusText, "Please make a new record first");
			}catch (Exception e1) {
				StatusBar.setError(statusText, "Unknown Error");
				e1.printStackTrace();
			}					
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
}
