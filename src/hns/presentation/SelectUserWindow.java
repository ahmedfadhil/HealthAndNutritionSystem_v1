package hns.presentation;

import java.util.LinkedList;

import hns.businesslogic.AccessPatients;
import hns.objects.Patient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class SelectUserWindow {
	
	private Display display;
	private Shell shlHnsV;
	private static Text statusText;
	private static int demoWindow;
	
	private AccessPatients accessPatients;
	private LinkedList<Patient> patientList;
	private Patient selectedPatient;

	public SelectUserWindow(){
		demoWindow=1;
        display = Display.getDefault();
		createWindow();
	}
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void createWindow(){
		Display display = Display.getDefault();
		createContents();
		shlHnsV.open();
		shlHnsV.layout();
		while (!shlHnsV.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(){
		shlHnsV = new Shell();
		shlHnsV.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shlHnsV.setSize(216, 330);
		shlHnsV.setText("HNS v1.3");
		
		Composite titleComp = new Composite(shlHnsV, SWT.NONE);
		titleComp.setBounds(10, 63, 179, 35);
		
		Label lblTitle = new Label(titleComp, SWT.NONE);
		lblTitle.setAlignment(SWT.CENTER);
		lblTitle.setBackground(SWTResourceManager.getColor(204, 255, 255));
		lblTitle.setBounds(10, 10, 159, 15);
		lblTitle.setText("Select User From List");
		
		Composite dbListComp = new Composite(shlHnsV, SWT.NONE);
		dbListComp.setBounds(10, 104, 179, 33);
		
		final CCombo combo = new CCombo(dbListComp, SWT.BORDER);
		combo.setBackground(SWTResourceManager.getColor(255, 255, 255));
		combo.setEditable(false);
		combo.setBounds(10, 5, 159, 21);
		if(accessPatients == null){
			accessPatients = new AccessPatients();
		}
		patientList = accessPatients.getPatientList();
		accessPatients = null;
		for(Patient patient : patientList){
			combo.add(patient.toString());
		}
		
		combo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
								
				selectedPatient = patientList.get(combo.getSelectionIndex());
			}
		});
		Composite buttonsComp = new Composite(shlHnsV, SWT.NONE);
		buttonsComp.setBounds(10, 182, 179, 33);
		
		Button btnClose = new Button(buttonsComp, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				shlHnsV.dispose();
			}
		});
		btnClose.setBounds(94, 5, 75, 25);
		btnClose.setText("Close");
		
		Button btnSelect = new Button(buttonsComp, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				if (selectedPatient == null){
					StatusBar.setWarning(statusText, "Select User or create new User");
				}
				else{
					shlHnsV.dispose();
					new MainUserWindow(selectedPatient);
				}
			}
		});
		btnSelect.setBounds(10, 5, 75, 25);
		btnSelect.setText("Select");
		
		statusText = new Text(shlHnsV, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(180, 30);
		statusText.setLocation(9, 262);
		StatusBar.create(display, shlHnsV, statusText);
		
		Composite newUserComp = new Composite(shlHnsV, SWT.NONE);
		newUserComp.setBounds(10, 143, 179, 33);
		
		Button btnNewUser = new Button(newUserComp, SWT.NONE);
		btnNewUser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				shlHnsV.dispose();
				new NewUserWindow();
			}
		});
		btnNewUser.setBounds(39, 5, 99, 25);
		btnNewUser.setText("Create New User");
		
		Composite TryComp = new Composite(shlHnsV, SWT.NONE);
		TryComp.setBounds(10, 221, 179, 33);
		
		Button btnTryItOut = new Button(TryComp, SWT.NONE);
		btnTryItOut.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				StatusBar.clear(statusText);
				if (demoWindow==1){
					demoWindow--;
					new DemoWindow();
				}else
					StatusBar.setWarning(statusText, "Trial window is running");
			}
		});
		btnTryItOut.setText("Try it out");
		btnTryItOut.setBounds(39, 5, 99, 25);
		
		Composite HNSComp = new Composite(shlHnsV, SWT.NONE);
		HNSComp.setBounds(10, 10, 179, 47);
		
		Label lblHns = new Label(HNSComp, SWT.NONE);
		lblHns.setFont(SWTResourceManager.getFont("Copperplate Gothic Light", 9, SWT.BOLD));
		lblHns.setText("Health and Nutrition System");
		lblHns.setBackground(SWTResourceManager.getColor(204, 204, 255));
		lblHns.setAlignment(SWT.CENTER);
		lblHns.setBounds(10, 10, 159, 27);
	}	
	public static void freeDemoWindow(){
		demoWindow=1;
		StatusBar.clear(statusText);
	}
}
