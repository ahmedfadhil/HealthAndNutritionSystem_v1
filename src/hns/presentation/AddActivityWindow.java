package hns.presentation;

import java.util.LinkedList;

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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener; 

import hns.businesslogic.AccessActivities;
import hns.businesslogic.AccessFood;
import hns.businesslogic.AccessRecord;
import hns.objects.*;
import hns.presentation.StatusBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class AddActivityWindow {

	private Display display;
	private Shell shell;
	private Text upText;
	private Text statusText;
	private Composite titleComp;
	private Composite addFieldComp;
	private Composite buttonsComp;
	private Label lblTitle;
	private Button btnClose;
	private Button btnAdd;
	private AccessActivities accessActivity;
	private Activity selectedActivity;
	private Record record;
	private Patient patient;
	private AccessRecord accessRecord;
	private int durationEntered;
	private Text searchText;
	private Label lblDurationminutes;
	private Table tableActivity;

	public AddActivityWindow(Patient patient, Record record){
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
        display = Display.getDefault();
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
		shell.setSize(626, 644);
		shell.setText("Add Activity");
		
		titleComp = new Composite(shell, SWT.NONE);
		titleComp.setBounds(10, 17, 179, 35);
		
		lblTitle = new Label(titleComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTitle.setBounds(10, 10, 159, 15);
		lblTitle.setText("Search for Activities");

		addFieldComp = new Composite(shell, SWT.NONE);
		addFieldComp.setBounds(393, 58, 206, 43);
		
		upText = new Text(addFieldComp, SWT.BORDER);
		upText.setToolTipText("eg. 25 minutes is \"25\"");
		upText.setText("Duration (minutes)");
		upText.setBounds(127, 10, 63, 21);

		buttonsComp = new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(10, 536, 179, 33);
		
		btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				shell.dispose();
			}
		});
		btnClose.setBounds(94, 5, 75, 25);
		btnClose.setText("Cancel");
		
	
		btnAdd = new Button(buttonsComp, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result= null;
				StatusBar.clear(statusText);
				if(selectedActivity == null){
					StatusBar.setWarning(statusText, "Please select activity");
				}
				else if (upText.getText().equals("")){
					StatusBar.setWarning(statusText, "Please enter duration in minutes");
				}else{
					if(accessRecord == null){
						accessRecord = new AccessRecord();
					}
					durationEntered = Integer.parseInt((upText.getText()));
					if (selectedActivity == null){
						StatusBar.setWarning(statusText, "Please select an activity.");
					}else{
						if (durationEntered > 0){
							record.AddToList(selectedActivity);
							selectedActivity.setDuration(durationEntered);
							result = accessRecord.insertActivityRecord(patient, record, selectedActivity);
							if(result!=null){
								StatusBar.setWarning(statusText, result);
							}else{
								JournalWindow.updateWindow();
								shell.close();
							}
						}else{
							StatusBar.setWarning(statusText, "Please enter greater than 0 minutes.");
						}
					}
				}
			}
		});
		btnAdd.setBounds(10, 5, 75, 25);
		btnAdd.setText("Add");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(9, 575);
		StatusBar.create(display, shell, statusText);
		
		upText.setTextLimit(3);
		upText.setText("");
		ValidateSwtText.enableInt(upText);
		
		lblDurationminutes = new Label(addFieldComp, SWT.NONE);
		lblDurationminutes.setBounds(10, 10, 111, 17);
		lblDurationminutes.setText("Duration (minutes): ");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 58, 377, 43);
		
		Label lblSearch = new Label(composite, SWT.NONE);
		lblSearch.setBounds(10, 10, 55, 17);
		lblSearch.setText("Search:");
		
		tableActivity = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableActivity.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				if(accessActivity == null){
					accessActivity = new AccessActivities();
				}
				int selectionIndex = tableActivity.getSelectionIndex();
				TableItem item = new TableItem(tableActivity, SWT.NONE);
				item = tableActivity.getItem(selectionIndex);

				selectedActivity = accessActivity.getActivity(item.getText(0));
			}
			
		});
		tableActivity.setBounds(10, 107, 589, 423);
		tableActivity.setHeaderVisible(true);
		tableActivity.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(tableActivity, SWT.NONE);
		tblclmnNewColumn.setWidth(375);
		tblclmnNewColumn.setText("Activity");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(tableActivity, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Calories Burned (in 1 hour)");
		
		searchText = new Text(composite, SWT.BORDER);
		searchText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String searchQuery = searchText.getText().trim();
				LinkedList<Activity> searchList = null;
				
				tableActivity.removeAll();
				
				if(accessActivity == null){
					accessActivity = new AccessActivities();
				}
				if(searchQuery.length() > 1){
					
					searchList = accessActivity.getActivityList(searchQuery);
				}
				if(searchList != null){
					
					for(int i = 0; i < searchList.size(); i ++){
						TableItem item = new TableItem(tableActivity, SWT.NONE);
						item.setText(new String[] {searchList.get(i).getName(), Integer.toString(searchList.get(i).getCalories())});
					}
					
				}
				
			}
		});

		searchText.setBounds(68, 10, 299, 21);
		

	}
}