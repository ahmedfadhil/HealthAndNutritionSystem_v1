package hns.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class StatusBar {
	
	private static Color statusColorPlain;
	private static Color statusColorYellow;
	private static Color statusColorRed;

	protected static void create(Display display, Shell shell, Text statusText){
		
		statusText.setBounds(0, shell.getSize().y-59, shell.getSize().x-8, 25);
		statusText.setTextLimit(500);
		statusColorPlain = shell.getBackground();
		statusColorYellow = new Color(display,255,255,0);
		statusColorRed = new Color(display,255,0,0);
		statusText.setFont(new Font(display, "Times", 8, SWT.NORMAL));
		clear(statusText);
	}

	protected static void clear(Text statusText){
		
		statusText.setBackground(statusColorPlain);
		statusText.setText("");
	}

	protected static void setWarning(Text statusText, String message){
		
		if (message != null){
			
			statusText.setBackground(statusColorYellow);
			statusText.setText(message);
		}
	}

	protected static void setError(Text statusText, String message){
		
		if (message != null){
			
			statusText.setBackground(statusColorRed);
			statusText.setText(message);
		}
	}
}
