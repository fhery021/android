package readerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import reader.DropdownButton;

public class DropdownButtonTest {

	DropdownButton dropdownButton;
	
	@Test
	public void ConstructorTest() {
		//<dropdownbutton name="Start" tooltip="Start session" disabled="false" />
		dropdownButton = new DropdownButton("Start", "Start session", "false");
		if (dropdownButton == null)
			fail("Failed to initialize Dropdown button");
	}

	@Test
	public void gettersAndSettersTest(){
		//	<dropdownbutton name="Configure" tooltip="Configure Session" disabled="false" />
		dropdownButton = new DropdownButton("Configure", "Configure Session", "false");
		if ((dropdownButton.getName() != "Configure") || 
				(dropdownButton.getTooltip() != "Configure Session")||
				(dropdownButton.getDisabled() != "false")){
			fail("Failed to get the name of the button");
		}
	}
	
	
}
