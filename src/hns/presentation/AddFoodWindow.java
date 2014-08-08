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
import org.eclipse.swt.custom.CCombo;

import hns.businesslogic.AccessFood;
import hns.businesslogic.AccessRecord;
import hns.objects.*;
import hns.presentation.StatusBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class AddFoodWindow {

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
	private AccessFood accessFood;
	private Food selectedFood;
	private Record record;
	private Patient patient;
	private AccessRecord accessRecord;
	private Text searchText;
	private int weightGramEntered;
	private Table tableFood;
	private TableColumn tblclmnNewColumn;
	private TableColumn tblclmnNewColumn_1;

	public AddFoodWindow(Patient patient, Record record){
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
		shell.addListener(SWT.Close, new Listener() { 
           public void handleEvent(Event event) { 
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
		shell.setSize(627, 642);
		shell.setText("Add Food");

		titleComp = new Composite(shell, SWT.NONE);
		titleComp.setBounds(10, 19, 179, 35);
		
		lblTitle = new Label(titleComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTitle.setBounds(10, 10, 159, 15);
		lblTitle.setText("Search for Food");

		addFieldComp = new Composite(shell, SWT.NONE);
		addFieldComp.setBounds(392, 60, 208, 43);
		
		upText = new Text(addFieldComp, SWT.BORDER);
		upText.setBounds(124, 13, 74, 21);
		
		Label lblServings = new Label(addFieldComp, SWT.NONE);
		lblServings.setBounds(10, 13, 111, 20);
		lblServings.setText("Weight (grams):");

		buttonsComp = new Composite(shell, SWT.NONE);
		buttonsComp.setBounds(10, 534, 179, 33);
		
		btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				JournalWindow.updateWindow();
				shell.dispose();
			}
		});
		btnClose.setBounds(94, 5, 75, 25);
		btnClose.setText("Cancel");
		
		btnAdd = new Button(buttonsComp, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String result =null;
				StatusBar.clear(statusText);
				if(selectedFood == null){
					StatusBar.setWarning(statusText, "Please select food");
				}
				else if (upText.getText().equals("")){
					StatusBar.setWarning(statusText, "Please enter servings");
				}else{
					if(accessRecord == null){
						accessRecord = new AccessRecord();
					}
					weightGramEntered = Integer.parseInt(upText.getText());
					if (weightGramEntered > 0){
						record.AddToList(selectedFood);
						selectedFood.setWeightGram(weightGramEntered);
						result = accessRecord.insertFoodRecord(patient, record, selectedFood);
						if(result!=null){
							StatusBar.setWarning(statusText, result);
						}else{
							JournalWindow.updateWindow();
							shell.close();
						}
					}else{
						StatusBar.setWarning(statusText, "Please enter greater than 0 serving(s)");
					}
				}
			}
		});
		btnAdd.setBounds(10, 5, 75, 25);
		btnAdd.setText("Add");
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(10, 573);
		StatusBar.create(display, shell, statusText);
		
		upText.setTextLimit(3);
		upText.setText("");
		ValidateSwtText.enableInt(upText);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 60, 376, 43);
		
		searchText = new Text(composite, SWT.BORDER);
		searchText.setBounds(72, 12, 294, 21);
		
		Label lblSearch = new Label(composite, SWT.NONE);
		lblSearch.setBounds(10, 12, 58, 17);
		lblSearch.setText("Search:");
		
		tableFood = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableFood.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				if(accessFood == null){
					accessFood = new AccessFood();
				}
				int selectionIndex = tableFood.getSelectionIndex();
				TableItem item = new TableItem(tableFood, SWT.NONE);
				item = tableFood.getItem(selectionIndex);
				selectedFood = accessFood.getFood(item.getText(0));
			}
		});
		tableFood.setBounds(10, 109, 590, 419);
		tableFood.setHeaderVisible(true);
		tableFood.setLinesVisible(true);
		
		tblclmnNewColumn = new TableColumn(tableFood, SWT.NONE);
		tblclmnNewColumn.setWidth(412);
		tblclmnNewColumn.setText("Food");
		
		tblclmnNewColumn_1 = new TableColumn(tableFood, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Calories (per 100g)");
		
		searchText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				String searchQuery = searchText.getText().trim();
				LinkedList<Food> searchList = null;
				
				tableFood.removeAll();
				
				if(accessFood == null){
					accessFood = new AccessFood();
				}
				if(searchQuery.length() > 1){
					
					searchList = accessFood.getFoodList(searchQuery);
				}
				if(searchList != null){
					for(int i = 0; i < searchList.size(); i ++){
						TableItem item = new TableItem(tableFood, SWT.NONE);
						item.setText(new String[] {searchList.get(i).getName(), Integer.toString(searchList.get(i).getCalories())});
					}
					
				}
				
			}
		});

	}

}