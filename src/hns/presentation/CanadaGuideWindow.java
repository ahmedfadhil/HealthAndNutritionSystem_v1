package hns.presentation;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class CanadaGuideWindow {

	private Shell shell;
	private Display display;
	private int currentPage;
	private int maxPage;
	private String folder;
	private Label label;
	private Label image;
	
	public CanadaGuideWindow(String folder, int maxPage ){
		this.folder = folder;
		this.maxPage=maxPage;
		currentPage=1;
		display = Display.getDefault();
		createWindow();
		
	}
	public void createWindow() {
		createContents();
		shell.open();
		shell.layout();
		shell.addListener(SWT.Close, new Listener() 
        { 
           public void handleEvent(Event event) 
           { 
        	  MainUserWindow.freeGuideWindow(folder);
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
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN );
		shell.setSize(795, 681);
		shell.setText("Canada's Food Guide");
		shell.setBackground(SWTResourceManager.getColor(51, 204, 255));

		Composite imageComp = new Composite(shell, SWT.NONE);
		imageComp.setBounds(10, 60, 760, 575);
		
		image = new Label(imageComp, SWT.NONE);
		image.setBounds(10, 10, 740, 555);
		//image.setSize(734,130);
		image.setImage(new Image(null, "images/"+folder+"/Slide"+currentPage+".JPG"));
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBounds(10, 10, 760, 44);
		
		Button btnPrev = new Button(composite_1, SWT.NONE);
		btnPrev.setBounds(10, 10, 75, 25);
		btnPrev.setText("Previous");
		btnPrev.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				if(currentPage>1)
					currentPage--;
				updateWindow();
			}
		});
		
		Button btnNext = new Button(composite_1, SWT.NONE);
		btnNext.setText("Next");
		btnNext.setBounds(172, 10, 75, 25);
		btnNext.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)
			{
				if(currentPage<maxPage)
					currentPage++;
				updateWindow();
			}
		});
		
		label = new Label(composite_1, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setBounds(91, 15, 75, 15);
		label.setText(currentPage+"/9");
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(526, 10, 224, 15);
		lblNewLabel.setText("Source: Health Canada (2011)");

	}
	private void updateWindow(){
		label.setText(currentPage+"/"+maxPage);
		image.setImage(new Image(null, "images/"+folder+"/Slide"+currentPage+".JPG"));

	}
}
