package hns.presentation;

import hns.businesslogic.GiveRecommendations;
import hns.objects.Patient;
import hns.objects.Record;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DailyCalorieAnalysisWindow {

	private Display display;
	private Shell shell;
	private Composite titleComp;
	
	
	private Patient patient;
	private Record record;
	private Composite composite_2;
	private Label dateLabel;
	private Button btnClose;

	public DailyCalorieAnalysisWindow(Patient patient, Record record){
		this.patient = patient;
		this.record = record;
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
		shell.addListener(SWT.Close, new Listener() { 
	           public void handleEvent(Event event) { 
	        	  JournalWindow.freeDailyWindow();
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
		shell.setSize(545, 376);
		shell.setText("Daily Calories Analysis");
		
		titleComp = new Composite(shell, SWT.BORDER);
		titleComp.setBounds(10, 51, 517, 229);
		
		Label textDisplayBox = new Label(titleComp, SWT.NONE);
		textDisplayBox.setBounds(10, 10, 493, 205);
		
		String toBeDisplayed = "";
		try {
			String dailyCaloriesAnalysis = GiveRecommendations.giveDailyCalorieAnalysis(patient, record);
			String recommendation = GiveRecommendations.giveRecommendations(patient, patient.getJournal());
			toBeDisplayed += (dailyCaloriesAnalysis + "\n\nRecommendations:\n" + recommendation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		textDisplayBox.setText(toBeDisplayed);
		
		composite_2 = new Composite(shell, SWT.BORDER);
		composite_2.setBounds(10, 10, 517, 35);
		
		dateLabel = new Label(composite_2, SWT.BORDER);
		dateLabel.setBackground(SWTResourceManager.getColor(204, 255, 255));
		dateLabel.setAlignment(SWT.CENTER);
		dateLabel.setBounds(79, 7, 347, 19);
		dateLabel.setText(record.getDate()+"");
		btnClose = new Button(shell, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				JournalWindow.freeDailyWindow();
				shell.dispose();
			}
		});
		btnClose.setBounds(10, 290, 517, 33);
		btnClose.setText("Close");		
	}
}