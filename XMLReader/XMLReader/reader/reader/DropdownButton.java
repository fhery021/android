package reader;
/**
 * 
 * @author fbondar
 *
 * This the object used to represent a button in the dropdown list.
 * From the XML file: 
 * <dropdownbutton name="Start" tooltip="Start session" disabled="false" />
 */
public class DropdownButton {

	private String name;
	private String tooltip;
	private String disabled;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	public DropdownButton(String buttonName, String buttonTooltip, String buttonDisabled){
		this.name = buttonName;
		this.tooltip =  buttonTooltip;
		this.disabled = buttonDisabled;
	}
	
	public String toString(){
		return "Button: "+this.name+" tooltip: "+this.tooltip+ " is disabled: "+this.disabled;
	}
}