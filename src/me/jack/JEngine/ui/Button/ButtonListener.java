package me.jack.JEngine.ui.Button;

/**
 * 
 * @author Jack
 * A interface to implement for Listening to button activity
 */
public interface ButtonListener {

	public void onButtonSelect(Button button);
	public void onButtonDeselect(Button button);
	public void onButtonActivate(Button button);
}
