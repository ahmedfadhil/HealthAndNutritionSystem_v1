package hns.presentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener; 
import hns.objects.Journal;
import hns.objects.Patient;
import hns.objects.Record;
import hns.presentation.StatusBar;
import org.eclipse.swt.widgets.List;

public class JournalWindow {

	private Display display;
	private static int addRecordWindow;
	private static int analysisWindow;
	private static Shell shell;
	private static Text statusText;
	private Patient currentPatient;
	private static Journal journal;
	private static Record currentRecord;
	private static Label uDate;
	private static Label lblLbluserweight;
	private Label lblWeight;
	private Label lblFood;
	private Label lblBmr;
	private Button btnPrev;
	private Button btnNext;
	private Button btnAddFood;
	private Button btnAddAct;
	private Button btnClose;
	private Button btnCreateRecord;
	private Composite nameComp ;
	private Composite ageComp;
	private Composite bmrComp;
	private Composite heightWeightComp;
	private Composite closeComp;
	private static List foodList;
	private static List activityList;
	private Button btnDailyCalorieAnalysis;
	
	/**
	 * Launch the application.
	 * @param currentPatient 
	 * @param args
	 */
	public JournalWindow(Patient currentPatient){
		addRecordWindow=1;
		analysisWindow=1;
		display = Display.getDefault();
        this.currentPatient= currentPatient;
        journal = currentPatient.getJournal();
        currentRecord = journal.getLatestRecord(); //need this line here
		createWindow();
		
	}

	/**
	 * Open the window.
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
				
           } 
        }); 
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){
	
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(51, 204, 255));
		shell.setSize(413, 623);
		shell.setText("Journal");
		
		nameComp = new Composite(shell, SWT.BORDER);
		nameComp.setBounds(10, 10, 377, 34);
		
		uDate = new Label(nameComp, SWT.BORDER);
		uDate.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		uDate.setAlignment(SWT.CENTER);
		uDate.setBackground(SWTResourceManager.getColor(0, 255, 255));
		uDate.setBounds(36, 5, 303, 20);
		
		btnPrev = new Button(nameComp, SWT.NONE);
		btnPrev.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(currentRecord != null){
					Record prev;
					//previous button...
					prev = journal.getPreviousRecord(currentRecord);
					if (prev!=null){
						//update the current record and the update
						currentRecord = prev;
						StatusBar.clear(statusText);
						updateWindow();					
					}
					else{
						StatusBar.clear(statusText);
						StatusBar.setWarning(statusText, "There is no more previous record.");
					}
				}
				else{
					StatusBar.clear(statusText);
					StatusBar.setWarning(statusText, "Please add a record.");
				}
			}
		});
		btnPrev.setBounds(10, 5, 20, 20);
		btnPrev.setText("<");
		
		
		btnNext = new Button(nameComp, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(currentRecord != null){
					Record next;
					next = journal.getNextRecord(currentRecord);
					if (next!=null){
						currentRecord = next;
						StatusBar.clear(statusText);
						updateWindow();
					}
					else{
						StatusBar.clear(statusText);
						StatusBar.setWarning(statusText, "There is no more next record.");
					}
				}
				else{
					StatusBar.clear(statusText);
					StatusBar.setWarning(statusText, "Please add a record.");
				}
			}
		});
		btnNext.setBounds(345, 5, 20, 20);
		btnNext.setText(">");
		
		
		ageComp = new Composite(shell, SWT.BORDER);
		ageComp.setBounds(10, 50, 377, 34);
		
		
		lblWeight = new Label(ageComp, SWT.NONE);
		lblWeight.setBounds(10, 10, 41, 15);
		lblWeight.setText("Weight:");
		
		lblLbluserweight = new Label(ageComp, SWT.NONE);
		lblLbluserweight.setBounds(57, 10, 41, 15);
		
		heightWeightComp = new Composite(shell, SWT.BORDER);
		heightWeightComp.setBounds(10, 90, 377, 34);
		
		lblFood = new Label(heightWeightComp, SWT.NONE);
		lblFood.setBounds(10, 10, 30, 15);
		lblFood.setText("Food:");
		
		btnAddFood = new Button(heightWeightComp, SWT.NONE);
		btnAddFood.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {		
				if(currentRecord != null){
					StatusBar.clear(statusText);
					new AddFoodWindow(currentPatient, currentRecord);	
				}
				else{
					StatusBar.clear(statusText);
					StatusBar.setWarning(statusText, "Please add a record.");
				}
		}});
		btnAddFood.setBounds(46, 5, 20, 20);
		btnAddFood.setText("+");
		
		bmrComp = new Composite(shell, SWT.BORDER);
		bmrComp.setBounds(10, 276, 377, 34);
		
		lblBmr = new Label(bmrComp, SWT.SHADOW_NONE);
		lblBmr.setBounds(10, 10, 51, 15);
		lblBmr.setText("Activities:");


		btnAddAct = new Button(bmrComp, SWT.NONE);
		btnAddAct.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(currentRecord != null){
					StatusBar.clear(statusText);
					new AddActivityWindow(currentPatient, currentRecord);
				}
				else{
					StatusBar.clear(statusText);
					StatusBar.setWarning(statusText, "Please add a record.");
				}
			}
		});
		btnAddAct.setText("+");
		btnAddAct.setBounds(67, 5, 20, 20);
		
		
		closeComp = new Composite(shell, SWT.BORDER);
		closeComp.setBounds(10, 460, 377, 84);
		
		
		btnClose = new Button(closeComp, SWT.NONE);
		btnClose.setBounds(247, 3, 118, 29);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				display.close();
				new MainUserWindow(currentPatient);
				
			}
		});
		
		btnClose.setText("Close");
		btnCreateRecord = new Button(closeComp, SWT.NONE);
		btnCreateRecord.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				if (addRecordWindow==1){
					addRecordWindow--;
					new AddNewRecordWindow(journal, currentPatient);
					updateWindow();
				}else
					StatusBar.setWarning(statusText, "Add Record window is running");
			}
		});
		btnCreateRecord.setBounds(10, 3, 231, 29);
		btnCreateRecord.setText("Create New Record");
		
		btnDailyCalorieAnalysis = new Button(closeComp, SWT.NONE);
		btnDailyCalorieAnalysis.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				StatusBar.clear(statusText);
				if (currentPatient.getJournal().size()==0){
					StatusBar.setWarning(statusText, "Please create a record first");
				}else{
					if (analysisWindow==1){
						analysisWindow--;
						new DailyCalorieAnalysisWindow(currentPatient, currentRecord);
					}else
						StatusBar.setWarning(statusText, "Daily Calorie analysis window is already open");
				}
			}
		});
		btnDailyCalorieAnalysis.setBounds(10, 38, 355, 29);
		btnDailyCalorieAnalysis.setText("Daily Calorie Analysis");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(267, 34);
		statusText.setLocation(10, 550);
		StatusBar.create(display, shell, statusText);
		
		foodList = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		foodList.setBounds(10, 130, 377, 140);
		
		activityList = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		activityList.setBounds(10, 316, 377, 138);

		updateWindow();
	}
	public static void updateWindow(){
		
		if(currentRecord != null){
			shell.redraw();
			lblLbluserweight.setText(currentRecord.getWeight()+"Kg");
			uDate.setText(currentRecord.getDate()+"");
			foodList.setItems(currentRecord.getFoodList());
			activityList.setItems(currentRecord.getActivityList());
			MainUserWindow.updateWindow();
		}
		else{
			StatusBar.clear(statusText);
			StatusBar.setWarning(statusText, "Please add a record.");
		}

	}
	public static void updateCurrentRecord(){
		currentRecord = journal.getLatestRecord();
	}

	public static void freeNewRecordWindow() {
		addRecordWindow=1;
		StatusBar.clear(statusText);
	}
	
	public static void freeDailyWindow(){
		analysisWindow=1;
		StatusBar.clear(statusText);
	}
}
