package hns.presentation;

import hns.objects.Date;
import hns.objects.Journal;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProgressGraphWindow {

	protected Shell shell;
	private Text statusText;
	Label lblDate;
	Label lblWeight;
	Canvas canvas;
	Date [] dates;
	int [] weights;
	int currentDateIndex;
	int indexMax;
	Display display;
	public ProgressGraphWindow(Journal journal){
        display = Display.getDefault();
        this.dates = new Date [journal.size()];
        this.weights = new int [journal.size()];
        currentDateIndex=0;
        fillDates(journal); 
		createWindow();
		
	}



	/**
	 * Open the window.
	 */
	public void createWindow() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() { 
           public void handleEvent(Event event) { 
        	  MainUserWindow.freeGraphWindow();
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
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(102, 204, 255));
		shell.setSize(477, 337);
		shell.setText("Progress Graph of last ten entries");
		
		Composite labelComp = new Composite(shell, SWT.NONE);
		labelComp.setBounds(10, 10, 441, 35);
		
		lblDate = new Label(labelComp, SWT.NONE);
		lblDate.setBounds(172, 10, 151, 15);
		
		lblWeight = new Label(labelComp, SWT.NONE);
		lblWeight.setBounds(329, 10, 75, 15);
		
		Button btnPrev = new Button(labelComp, SWT.NONE);
		btnPrev.setBounds(10, 5, 75, 25);
		btnPrev.setText("Previous");
		btnPrev.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				if(currentDateIndex==0){
					currentDateIndex=dates.length-1;
				}else{
					currentDateIndex--;
				}
				canvas.redraw();
				updateWindow();
				canvas.addPaintListener(new DrawGraph(weights, currentDateIndex));

			}
		});
		
		Button btnNext = new Button(labelComp, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				
				if(currentDateIndex==dates.length-1){
					currentDateIndex=0;
				}else{
					currentDateIndex++;
				}
				canvas.redraw();
				updateWindow();
				canvas.addPaintListener(new DrawGraph(weights, currentDateIndex));

			}
		});
		btnNext.setText("Next");
		btnNext.setBounds(91, 5, 75, 25);
		
		statusText = new Text(shell, SWT.NONE);
		statusText.setBackground(SWTResourceManager.getColor(51, 204, 255));
		statusText.setSize(414, 30);
		statusText.setLocation(10, 269);
		StatusBar.create(display, shell, statusText);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 51, 441, 212);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLocation(10, 10);
		composite_1.setSize(421, 192);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		canvas = new Canvas(composite_1, SWT.NONE);
		canvas.setBounds(10, 10, 401, 172);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.addPaintListener(new DrawGraph(weights,0));
		updateWindow();
		
	}
	private void fillDates(Journal journal){
		int count=journal.size();
		if(count==0){
			StatusBar.setWarning(statusText, "Please create a new record first");
		}else{
			dates=journal.getLastTenDates();
			weights = journal.getLastTenWeights();
		}

		

	}
	private void updateWindow(){
		lblDate.setText("Date:"+dates[currentDateIndex]);
		lblWeight.setText("Weight:"+weights[currentDateIndex]+"kg");
	}
}
