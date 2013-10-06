import lejos.nxt.comm.*;
import lejos.nxt.*;

public class RDebugReceiver {
	public static void runReceiver() {
		LCD.drawString("Select the connection you're debugging through", 0, 0);
		LCD.drawString("For USB, push ENTER", 0, 1);
		LCD.drawString("For Bluetooth, push RIGHT", 0, 2);
		int pressed = Button.waitForAnyPress();
		if (pressed == Button.ID_ENTER) {
			new LCPResponder(USB.getConnector()).start();
		} else if (pressed == Button.ID_RIGHT) {
			new LCPResponder(Bluetooth.getConnector()).start();
		} else {
			Sound.buzz();
			LCD.drawString("Incorrect Button", 0, 0);
		}
		LCD.clear();
	}
	
	public static void main(String[] args) {
		runReceiver();
	}
}