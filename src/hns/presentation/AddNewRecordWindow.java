package hns.presentation;

import hns.businesslogic.*;
import hns.objects.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

public class AddNewRecordWindow {

	private Display display;
	private Shell shell;
	private Text statusText;
	private Composite buttonsComp;
	private Button btnClose;
	private Button btnAdd;
	private Text textDay;
	private Text textYear;
	private Text textWeight;

	private int month;
	private int day;
	private int year;
	private int weight;
	
	private Record newRecord;
	private Journal journal;
	private Patient patient;
	private AccessPatients accessPatient;
	private AccessRecord accessRecord;
	
	public AddNewRecordWindow(Journal journal, Patient patient){
		this.journal = journal;
		this.patient = patient;
        display = Display.getDefault();
		createWindow();
	}
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void createWindow(){
        display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() 
        { 
           public void handleEvent(Event event) 
           { 
        	  JournalWindow.freeNewRecordWindow();
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
		shell.setBackground(SWTResourceManager.getColor(51, 204, 255));
		shell.setSize(275, 180);
		shell.setText("Add New Record");
		
		buttonsComp = new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(44, 88, 179, 33);

		btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				JournalWindow.freeNewRecordWindow();
				shell.dispose();
			}
		});
		btnClose.setBounds(94, 5, 75, 25);
		btnClose.setText("Cancel");
	
		btnAdd = new Button(buttonsComp, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			String result=null;
				try {
					day = Integer.parseInt(textDay.getText());
					year = Integer.parseInt(textYear.getText());
					weight = Integer.parseInt(textWeight.getText());
				
					CheckValidInput.validateDateArguments(month, day, year);
					if (CheckValidInput.validInputNum(weight, Patient.WEIGHT_LOWER_BOUND, Patient.HEIGHT_HIGHER_BOUND)){
						newRecord = new Record(new Date(month,day,year),weight);
						if (journal.insertNewRecord(newRecord)){
							//insert a record in db
							if(accessRecord == null){
								accessRecord = new AccessRecord();
							}
							result = accessRecord.insertNewRecord(newRecord, journal);
							if(result!=null){
								StatusBar.setWarning(statusText, result);
							}else{
								accessRecord = null;	
								//update patient's weight only if this record is the latest!
								if((journal.getLatestRecord()).equals(newRecord)){
									patient.updateWeight(weight);
									if(accessPatient == null){
										accessPatient = new AccessPatients();
									}
									result = accessPatient.updatePatient(patient);
									if(result !=null){
										StatusBar.setWarning(statusText, result);
									}
									accessPatient=null;
								}
					        	JournalWindow.freeNewRecordWindow();
								JournalWindow.updateCurrentRecord();
					        	JournalWindow.updateWindow();

								shell.dispose();
							}
						}
						else{
							StatusBar.setWarning(statusText, "Can't add null or duplicate records of the same date");
						}
					}else{
						StatusBar.setWarning(statusText, "Invalid age!");
					}
				} catch (NumberFormatException e1){
					StatusBar.setWarning(statusText, "Invalid Number");
				} catch (IllegalArgumentException e1){
					StatusBar.setWarning(statusText, "Invalid date!");
				}
				catch (Exception e1) {
					StatusBar.setError(statusText, "Unknown Error");
				}
		}
		});
		btnAdd.setBounds(10, 5, 75, 25);
		btnAdd.setText("Add");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(10, 178);
		StatusBar.create(display, shell, statusText);
		
		Composite dateComp = new Composite(shell, SWT.NONE);
		dateComp.setBounds(10, 10, 236, 33);
		
		Label lblDate = new Label(dateComp, SWT.CENTER);
		lblDate.setBounds(10, 8, 37, 15);
		lblDate.setText("Date:");
		
		final Combo comboMonth = new Combo(dateComp, SWT.NONE);
		comboMonth.setBounds(49, 5, 74, 23);
		
		comboMonth.add("JAN");
		comboMonth.add("FEB");
		comboMonth.add("MAR");
		comboMonth.add("APR");
		comboMonth.add("MAY");
		comboMonth.add("JUN");
		comboMonth.add("JUL");
		comboMonth.add("AUG");
		comboMonth.add("SEP");
		comboMonth.add("OCT");
		comboMonth.add("NOV");
		comboMonth.add("DEC");
				
		textDay = new Text(dateComp, SWT.BORDER);
		textDay.setBounds(129, 5, 37, 23);
		
		textYear = new Text(dateComp, SWT.BORDER);
		textYear.setBounds(172, 5, 59, 23);
		textYear.setText("2012");
		comboMonth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				month = (comboMonth.getSelectionIndex()+1); //combo index starts at 0, so we need to add 1.
			}
		});
		
		Composite weightComp = new Composite(shell, SWT.NONE);
		weightComp.setBounds(44, 49, 179, 33);
		
		textWeight = new Text(weightComp, SWT.BORDER);
		textWeight.setBounds(84, 5, 78, 23);
		
		Label lblWeight = new Label(weightComp, SWT.CENTER);
		lblWeight.setBounds(10, 8, 59, 15);
		lblWeight.setText("Weight:");
		
		textDay.setTextLimit(2);
		textDay.setText("");
		textYear.setTextLimit(4);
		textYear.setText("2012");
		textWeight.setTextLimit(4);
		textWeight.setText("");
		ValidateSwtText.enableInt(textWeight);
		ValidateSwtText.enableInt(textYear);
		ValidateSwtText.enableInt(textDay);

	}
}
