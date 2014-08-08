package hns.presentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddressBookUI {

	private static Text text_4;
	private static Text text_3;
	private static Text text_2;
	private static Text text_1;
	private static Text text;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell addressBookShell = new Shell();
		addressBookShell.setSize(469, 337);
		addressBookShell.setText("Address Book");
		//

		addressBookShell.open();

		final Button newButton = new Button(addressBookShell, SWT.NONE);
		newButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		newButton.setText("New");
		newButton.setBounds(5, 245, 56, 23);

		final Button deleteButton = new Button(addressBookShell, SWT.NONE);
		deleteButton.setText("Delete");
		deleteButton.setBounds(65, 245, 56, 23);

		final Button editButton = new Button(addressBookShell, SWT.NONE);
		editButton.setText("Edit");
		editButton.setBounds(125, 245, 56, 23);

		final Button previousButton = new Button(addressBookShell, SWT.NONE);
		previousButton.setText("Previous");
		previousButton.setBounds(205, 245, 56, 23);

		final Button nextButton = new Button(addressBookShell, SWT.NONE);
		nextButton.setText("Next");
		nextButton.setBounds(265, 245, 56, 23);

		final Button saveButton = new Button(addressBookShell, SWT.NONE);
		saveButton.setText("Save");
		saveButton.setBounds(335, 245, 56, 23);

		final Button cancelButton = new Button(addressBookShell, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setBounds(390, 245, 56, 23);

		final Group detailsGroup = new Group(addressBookShell, SWT.NONE);
		detailsGroup.setText("Details");
		detailsGroup.setBounds(10, 10, 436, 213);

		final Label firstNameLabel = new Label(detailsGroup, SWT.NONE);
		firstNameLabel.setText("First Name:");
		firstNameLabel.setBounds(10, 42, 55, 13);

		text = new Text(detailsGroup, SWT.BORDER);
		text.setBounds(65, 35, 360, 25);

		final Label lastNameLabel = new Label(detailsGroup, SWT.NONE);
		lastNameLabel.setText("Last Name:");
		lastNameLabel.setBounds(10, 73, 55, 13);

		text_1 = new Text(detailsGroup, SWT.BORDER);
		text_1.setBounds(65, 66, 360, 25);

		final Label phoneLabel = new Label(detailsGroup, SWT.NONE);
		phoneLabel.setText("Phone:");
		phoneLabel.setBounds(10, 103, 55, 13);

		text_2 = new Text(detailsGroup, SWT.BORDER);
		text_2.setBounds(65, 96, 360, 25);

		final Label emailLabel = new Label(detailsGroup, SWT.NONE);
		emailLabel.setText("Email:");
		emailLabel.setBounds(10, 133, 55, 13);

		text_3 = new Text(detailsGroup, SWT.BORDER);
		text_3.setBounds(65, 126, 360, 25);

		final Label addressLabel = new Label(detailsGroup, SWT.NONE);
		addressLabel.setText("Address:");
		addressLabel.setBounds(10, 168, 55, 13);

		text_4 = new Text(detailsGroup, SWT.BORDER);
		text_4.setBounds(65, 161, 360, 25);

		final Menu menu = new Menu(addressBookShell, SWT.BAR);
		addressBookShell.setMenuBar(menu);

		final MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
		fileMenuItem.setText("File");

		final Menu menu_1 = new Menu(fileMenuItem);
		fileMenuItem.setMenu(menu_1);

		final MenuItem newMenuItem = new MenuItem(menu_1, SWT.NONE);
		newMenuItem.setText("New");

		final MenuItem windowMenuItem = new MenuItem(menu, SWT.CASCADE);
		windowMenuItem.setText("Window");

		final Menu menu_2 = new Menu(windowMenuItem);
		windowMenuItem.setMenu(menu_2);

		final MenuItem helpMenuItem = new MenuItem(menu, SWT.CASCADE);
		helpMenuItem.setText("Help");

		final Menu menu_3 = new Menu(helpMenuItem);
		helpMenuItem.setMenu(menu_3);

		final MenuItem aboutMenuItem = new MenuItem(menu_3, SWT.NONE);
		aboutMenuItem.setText("About");
		addressBookShell.layout();
		while (!addressBookShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
