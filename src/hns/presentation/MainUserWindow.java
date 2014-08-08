package hns.presentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import hns.objects.Patient;
import hns.presentation.StatusBar;

public class MainUserWindow {

	private Shell shlHns;
	private Display display;
	private static Text statusText;
	private static Patient currentPatient;
	private static int journalWindow;
	private static int updateWindow;
	private static int foodWindow;
	private static int actWindow;
	private static int graphWindow;
	private static int analysisWindow;
	private Label lblAge;
	private Label lblUserName;
	private Label lblHeight;
	private static Label lblWeight;
	private Label lblActivityLevel;
	private static Label lblBmi;
	private static Label lblBmr ;
	private static Label lblWeightLevel;
	private Composite heightWeightComp;
	private Composite actComp;
	private Composite bmiComp;
	private Composite bmrComp;
	private Composite recComp;
	private Composite closeComp;
	private Composite composite;
	private Composite updtComp;
	private Composite nameComp;
	private Composite ageComp;
	private Button btnUpdateBasicInfo;
	private Button btnJournal;
	private Button btnProgressGraph;
	private Button btnClose;
	private Button btnLogout;
	private Button btnCanadaFood;
	private Button btnCanadaActivity;
	private Composite composite_1;
	/**
	 * Launch the application.
	 * @param currentPatient 
	 * @param args
	 */
	public MainUserWindow(final Patient currentPatient){
		journalWindow=1;
		updateWindow=1;
		foodWindow=1;
		actWindow=1;
		graphWindow=1;
		analysisWindow=1;
        display = Display.getDefault();
        this.currentPatient = currentPatient;
        createContents();
		shlHns.open();
		shlHns.layout();
		while (!shlHns.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	
	protected void createContents(){
		shlHns = new Shell();
		shlHns.setBackground(SWTResourceManager.getColor(51, 204, 255));
		shlHns.setSize(294, 647);
		shlHns.setText("HNS");
		
		nameComp = new Composite(shlHns, SWT.BORDER);
		nameComp.setBounds(10, 10, 264, 34);

		lblUserName = new Label(nameComp, SWT.NONE);
		lblUserName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblUserName.setAlignment(SWT.CENTER);
		lblUserName.setBackground(SWTResourceManager.getColor(102, 204, 255));
		lblUserName.setBounds(10, 10, 240, 15);
		lblUserName.setText(currentPatient.toString());
		
		ageComp = new Composite(shlHns, SWT.BORDER);
		ageComp.setBounds(10, 50, 264, 34);
		
		lblAge = new Label(ageComp, SWT.NONE);
		lblAge.setBounds(10, 10, 110, 15);
		lblAge.setText("Age: "+currentPatient.getAge()+"");
		
		heightWeightComp = new Composite(shlHns, SWT.BORDER);
		heightWeightComp.setBounds(10, 90, 264, 34);
		
		lblHeight = new Label(heightWeightComp, SWT.NONE);
		lblHeight.setBounds(10, 10, 114, 15);
		lblHeight.setText("Height: "+currentPatient.getHeight()+" cm");
		
		lblWeight = new Label(heightWeightComp, SWT.NONE);
		lblWeight.setBounds(130, 10, 120, 15);
		lblWeight.setText("Weight: "+currentPatient.getWeight()+" kg");
		
		actComp = new Composite(shlHns, SWT.BORDER);
		actComp.setBounds(10, 130, 264, 34);
		
		lblActivityLevel = new Label(actComp, SWT.NONE);
		lblActivityLevel.setBounds(10, 10, 143, 15);
		lblActivityLevel.setText("Activity Level: "+currentPatient.getActivityLevel()+"");
		
		bmiComp = new Composite(shlHns, SWT.BORDER);
		bmiComp.setToolTipText("Body mass index (BMI) is a measure of body fat based on height and weight that applies to adult men and women.");
		//source : http://www.nhlbisupport.com/bmi/bmi-m.htm
		bmiComp.setBounds(10, 170, 264, 34);
		
		lblBmi = new Label(bmiComp, SWT.NONE);
		lblBmi.setBounds(10, 10, 110, 15);
		lblBmi.setText("BMI: "+currentPatient.getBMI()+"");
		
		bmrComp = new Composite(shlHns, SWT.BORDER);
		bmrComp.setToolTipText("Your total daily energy expenditure, or TDEE, is the total number of calories that your body expends in 24 hours, including all activities. ");
		//source : www.shapefit.com/basal-metabolic-rate.html
		bmrComp.setBounds(10, 250, 264, 34);
		
		lblBmr = new Label(bmrComp, SWT.SHADOW_NONE);
		lblBmr.setToolTipText("");
		lblBmr.setBounds(10, 10, 227, 15);
		lblBmr.setText("Required Daily Calories: "+currentPatient.getCalories()+" kCal");
		
		recComp = new Composite(shlHns, SWT.BORDER);
		recComp.setBounds(10, 290, 264, 49);
		
		btnJournal = new Button(recComp, SWT.NONE);
		btnJournal.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				if (journalWindow==1){
					journalWindow--;
					new JournalWindow(currentPatient);
				}else
					StatusBar.setWarning(statusText, "Journal window is running");
			}
		});
		btnJournal.setBounds(10, 10, 89, 25);
		btnJournal.setText("Journal");
		
		Button btnDailyCalorie = new Button(recComp, SWT.NONE);
		btnDailyCalorie.setBounds(102, 10, 150, 25);
		btnDailyCalorie.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				StatusBar.clear(statusText);
				if (currentPatient.getJournal().size()==0){
					StatusBar.setWarning(statusText, "Please create a record first");
				}else{
					if (analysisWindow==1){
						analysisWindow--;
						new OverAllCalorieAnalysisWindow(currentPatient, currentPatient.getJournal());
					}else
						StatusBar.setWarning(statusText, "Overall Calorie analysis window is already open");
				}
			}
		});
		btnDailyCalorie.setText("Overall Calorie Analysis");
		updtComp = new Composite(shlHns, SWT.BORDER);
		updtComp.setBounds(10, 345, 264, 48);
		
		btnUpdateBasicInfo = new Button(updtComp, SWT.NONE);
		btnUpdateBasicInfo.setToolTipText("You can set your weight in Journal.");
		btnUpdateBasicInfo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				if (updateWindow==1){
					updateWindow--;
					new UpdateWindow(currentPatient, shlHns, display);
				}else
					StatusBar.setWarning(statusText, "Update window is running");
			}
		});
		btnUpdateBasicInfo.setBounds(10, 10, 124, 24);
		btnUpdateBasicInfo.setText("Update Basic Info");
		
		btnProgressGraph = new Button(updtComp, SWT.NONE);
		btnProgressGraph.setBounds(133, 10, 119, 24);
		btnProgressGraph.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				StatusBar.clear(statusText);
				if (currentPatient.getJournal().size()==0){
					StatusBar.setWarning(statusText, "Please create a journal first");
				}else{
					if (graphWindow==1){
						graphWindow--;
						new ProgressGraphWindow(currentPatient.getJournal());
					}else
						StatusBar.setWarning(statusText, "Graph Window is already open");
				}
			}
		});
		btnProgressGraph.setText("Progress Graph");
		
		closeComp = new Composite(shlHns, SWT.BORDER);
		closeComp.setBounds(10, 490, 264, 49);
		
		btnClose = new Button(closeComp, SWT.NONE);
		btnClose.setBounds(122, 10, 130, 25);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				shlHns.dispose();
			}
		});
		
		btnClose.setText("Close");
		
		btnLogout = new Button(closeComp, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
				new SelectUserWindow();
			}
		});
		btnLogout.setBounds(10, 10, 108, 25);
		btnLogout.setText("Logout");
		
		statusText = new Text(shlHns, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(267, 34);
		statusText.setLocation(7, 559);
		StatusBar.create(display, shlHns, statusText);
		
		composite = new Composite(shlHns, SWT.BORDER);
		composite.setToolTipText("Body mass index (BMI) is a measure of body fat based on height and weight that applies to adult men and women.");
		composite.setBounds(10, 210, 264, 34);
		
		lblWeightLevel = new Label(composite, SWT.NONE);
		lblWeightLevel.setText("Weight Level: "+currentPatient.getWeightLevel());
		lblWeightLevel.setBounds(10, 10, 240, 15);
		
		composite_1 = new Composite(shlHns, SWT.BORDER);
		composite_1.setToolTipText("Your total daily energy expenditure, or TDEE, is the total number of calories that your body expends in 24 hours, including all activities. ");
		composite_1.setBounds(10, 399, 264, 85);
		
		btnCanadaActivity = new Button(composite_1, SWT.NONE);
		btnCanadaActivity.setBounds(10, 40, 242, 31);
		btnCanadaActivity.setText("Canada's Activity Guide");
		
		btnCanadaFood = new Button(composite_1, SWT.NONE);
		btnCanadaFood.setBounds(10, 7, 242, 27);
		btnCanadaFood.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				StatusBar.clear(statusText);
				if (foodWindow==1){
					foodWindow--;
					new CanadaGuideWindow("food", 9);
				}else
					StatusBar.setWarning(statusText, "Food Guide Window is already open");
			}
		});
		btnCanadaFood.setText("Canada's Food Guide");
		btnCanadaActivity.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				int type=0;
				if(currentPatient.getAge()>=65)type=4;
				else if(currentPatient.getAge()>=18)type=3;
				else if(currentPatient.getAge()>=12)type=2;
				else type=1;
				StatusBar.clear(statusText);
				if (actWindow==1){
					actWindow--;
					new CanadaGuideWindow("act"+type, 4);
				}else
					StatusBar.setWarning(statusText, "Activity Guide Window is already open");
			}
		});
	}
	public static void updateWindow(){
		lblWeight.setText("Weight: "+currentPatient.getWeight()+" kg");
		lblBmi.setText("BMI: "+currentPatient.getBMI()+"");
		lblBmr.setText("Required Daily Calories: "+currentPatient.getCalories()+" kCal");
		lblWeightLevel.setText("Weight Level: "+currentPatient.getWeightLevel());
	}
	public static void freeUpdateWindow(){
		updateWindow=1;
		StatusBar.clear(statusText);
	}
	public static void freeGuideWindow(String type){
		if (type.equals("food"))foodWindow=1;
		else actWindow=1;
		StatusBar.clear(statusText);
	}
	public static void freeGraphWindow(){
		graphWindow=1;
		StatusBar.clear(statusText);
	}
	public static void freeOverAllAnalysisWindow(){
		analysisWindow=1;
		StatusBar.clear(statusText);
	}
}
