package hns.presentation;

import hns.businesslogic.GiveRecommendations;
import hns.objects.Journal;
import hns.objects.Patient;

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

public class OverAllCalorieAnalysisWindow {

	private Display display;
	private Shell shlOverallCaloriesAnalysis;
	private Composite titleComp;
	
	
	private Patient patient;
	private Journal journal;
	private Button btnClose;

	public OverAllCalorieAnalysisWindow(Patient patient, Journal journal){
		this.patient = patient;
		this.journal = journal;
        display = Display.getDefault();
		createWindow();
	}
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void createWindow(){
		createContents();
		shlOverallCaloriesAnalysis.open();
		shlOverallCaloriesAnalysis.layout();
		shlOverallCaloriesAnalysis.addListener(SWT.Close, new Listener() { 
	           public void handleEvent(Event event) { 
	        	  MainUserWindow.freeOverAllAnalysisWindow();
	              shlOverallCaloriesAnalysis.dispose(); 
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
			
		
		shlOverallCaloriesAnalysis = new Shell();
		shlOverallCaloriesAnalysis.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shlOverallCaloriesAnalysis.setSize(542, 376);
		shlOverallCaloriesAnalysis.setText("Overall Calories Analysis");
		
		titleComp = new Composite(shlOverallCaloriesAnalysis, SWT.BORDER);
		titleComp.setBounds(10, 10, 514, 280);
		
		Label textDisplayBox = new Label(titleComp, SWT.NONE);
		textDisplayBox.setBounds(10, 10, 490, 256);
		
		String toBeDisplayed = "";
		try {
			String dailyCaloriesAnalysis = GiveRecommendations.giveOverAllCalorieAnalysis(patient, journal);
			String recommendation = GiveRecommendations.giveRecommendations(patient, journal);
			toBeDisplayed += (dailyCaloriesAnalysis + "\n\nRecommendations:\n" + recommendation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		textDisplayBox.setText(toBeDisplayed);
		
		btnClose = new Button(shlOverallCaloriesAnalysis, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				MainUserWindow.freeOverAllAnalysisWindow();
				shlOverallCaloriesAnalysis.dispose();
			}
		});
		btnClose.setBounds(10, 296, 514, 26);
		btnClose.setText("Close");		
	}
}