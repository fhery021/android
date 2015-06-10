package readerTests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.xml.sax.SAXException;

import reader.DropdownButton;
import reader.SessionHelper;
import reader.XMLReader;
/**
 * 
 * @author fbondar
 *
 * The purpose of this class is to read from the actions_dropdown.xml file and get the 
 * Possible inputs: XML file and a SessionHelper 
 * Outputs: List of DropdownButtons
 * 
 */
public class XMLReaderTest {

	XMLReader xmlReader = new XMLReader();
	SessionHelper sessionHelper = new SessionHelper("all", "IN_PREPARATION", "true");
	String xPATHExpression = "/dropdown/session[@state='"+sessionHelper.getState()+"'][@type='"+sessionHelper.getType()+"' or @type='all'][@admin='"+sessionHelper.getIsAdmin()+"']/dropdownbutton";
	
	String expectedResult = "";
	String result = "";
	/*@Test
	public void getAllTheSessionsFromXML() {
		try {
			xmlReader.read(ActionsDropdownConstants.ACTIONS_DROPDOWN_XML_LOCATION, xPATHExpression);
		} catch (XPathExpressionException | ParserConfigurationException
				| SAXException | IOException e) {
			fail("An exception has occured"+e.getMessage());
			e.printStackTrace();
		}
	}
	*/
	@Test
	public void StoppingAdminTest(){
		sessionHelper = new SessionHelper("SystemDefault","STOPPING", "true");
		/*No buttons in GUI*/
		expectedResult="";
		result = getResultListOfDropdownButtons(sessionHelper, xmlReader, expectedResult);
		if (!result.equals(expectedResult)){
			fail("Invalid result"+"Expected result: "+expectedResult+"\nResult: "+result);
		}
	}
	
	@Test
	public void StoppingNonAdminTest(){
		sessionHelper = new SessionHelper("CCO", "STOPPING", "false");
		expectedResult = "";
		result = getResultListOfDropdownButtons(sessionHelper, xmlReader, expectedResult);
		if (!result.equals(expectedResult)){
			fail("Invalid result"+"Expected result: "+expectedResult+"\nResult: "+result);
		}
	}
	
	@Test
	public void InPreparationAdminCCOTest(){
		sessionHelper = new SessionHelper("CCO","IN_PREPARATION","true");
		expectedResult = "Button: Start tooltip: Start session is disabled: false"
				+ "Button: Delete tooltip: Delete session is disabled: false"
				+ "Button: Configure tooltip: Configure Session is disabled: false"
				+ "Button: Clusters tooltip: Clusters is disabled: false"
				+ "Button: Work Orders tooltip: Work Orders is disabled: false";
		result = getResultListOfDropdownButtons(sessionHelper, xmlReader, expectedResult);
		System.out.println(result);
		if (!result.equals(expectedResult)){
			fail("Invalid result\n"+"Expected result: "+expectedResult+"\nResult: "+result);
		}
	}
	/*
	@Test
	public void InPreparationAdminNotCCOTest(){
		sessionHelper = new SessionHelper("System-default", "IN_PREPARATION", "true");
		expected
	}
	*/
	/**
	 * This method helps to make the code more shorter. 
	 * Gets all the button's information and returns it for each test case.
	 * @param sessionHelper
	 * @param xmlReader
	 * @param expectedResult
	 * @return
	 */
	public String getResultListOfDropdownButtons(SessionHelper sessionHelper, XMLReader xmlReader, String expectedResult){
		String result = "";
		List<DropdownButton> buttons;
		System.out.println(">SESSION "+sessionHelper.getState()+" "+sessionHelper.getType()+" "+sessionHelper.getIsAdmin());
		try {
			buttons = xmlReader.getListOfDropdownButtons(sessionHelper);
			for (DropdownButton button:buttons){
				result = result+button.toString();
			}
		} catch (XPathExpressionException | ParserConfigurationException
				| SAXException | IOException e) {
			e.printStackTrace();
			fail("Failed to get the list of options for the dropdown buttons for"+sessionHelper.getType()+"-"+sessionHelper.getState()+"-"+sessionHelper.getType());
		}
		return result;
	}
}
