package hns.presentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener; 

public class ValidateSwtText {
	
	static void enableInt(Text text) {
		text.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				e.doit=true;
				String string = e.text;
				char[] chars= string.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});
	}
	static void enableText(Text text) {
		text.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				e.doit=true;
				String string = e.text;
				char[] chars = string .toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (!('a' <= chars[i] && chars[i] <= 'z')&&
							!('A' <= chars[i] && chars[i] <= 'Z') && chars[i]!='.' && 
							chars[i]!=' ' ) {
						e.doit = false;
						return;
					}
				}
			}
		});
	}
}
