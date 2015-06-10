package reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * 
 * @author fbondar
 *
 * The purpose of this class is to read from the actions_dropdown.xml file and get the 
 * Possible inputs: XML file and a SessionHelper 
 * Outputs: List of DropdownButtons
 * 
 */
public class XMLReader {

	public void read (String path, String xPATHExpression ) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document dDoc = builder.parse(path);
		System.out.println("From this XML: "+path);
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		NodeList nodes = (NodeList) xPath.evaluate(xPATHExpression, dDoc, XPathConstants.NODESET);
		System.out.println("XPath expression used: "+xPATHExpression);
		for (int i = 0; i < nodes.getLength(); i++) {
		    Node node = nodes.item(i);
	//	    System.out.println("-----------------"+i+"---------------------------");
		    /*
		     *Get the name of the button 
		     */
		    String buttonName = node.getAttributes().getNamedItem("name").getNodeValue();
		    System.out.print("Button name: "+buttonName);
		    
		    /*
		     * Get the tooltip for the button
		     */
		    try{
		    	String buttonTooltip = node.getAttributes().getNamedItem("tooltip").getNodeValue();
		    	System.out.print(" .Tooltip: "+buttonTooltip);
		    }catch(NullPointerException e){
		    	e.printStackTrace();
		    	System.out.println("NO tooltip");
		    }
		    /*
		     * Get the disabled Parameter
		     * This parameter is used if the user is not administrator.
		     * The button is visible, but not available.
		     */
		    try{
		    	String buttonIsDisabled = node.getAttributes().getNamedItem("disabled").getNodeValue();
		    	System.out.print(". Is this button disabled?"+buttonIsDisabled+"\n");
		    }catch(NullPointerException e){
		    	System.out.println("No disabled parameter defined");
		    }
		    System.out.println("--------------------------------------------");
		}
		
	}
	
	public List<DropdownButton> getListOfDropdownButtons (SessionHelper sessionHelper) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		String path =  ActionsDropdownConstants.ACTIONS_DROPDOWN_XML_LOCATION;
		String xPATHExpression = "/dropdown/session[@state='"+sessionHelper.getState()+"'][@type='"+sessionHelper.getType()+"' or @type='all'][@admin='"+sessionHelper.getIsAdmin()+"']/dropdownbutton";;
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document dDoc = builder.parse(path);
		//System.out.println("From this XML: "+path);
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		
		NodeList nodes = (NodeList) xPath.evaluate(xPATHExpression, dDoc, XPathConstants.NODESET);
		//System.out.println("XPath expression used: "+xPATHExpression);

		ArrayList <DropdownButton> dropdownButtons = new ArrayList<DropdownButton>();
		String buttonName;
		String buttonTooltip;
		String buttonIsDisabled; 
		
		for (int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			try{
				buttonName = node.getAttributes().getNamedItem("name").getNodeValue();
				buttonTooltip = node.getAttributes().getNamedItem("tooltip").getNodeValue();
				buttonIsDisabled = node.getAttributes().getNamedItem("disabled").getNodeValue();
				
				dropdownButtons.add( new DropdownButton(buttonName, buttonTooltip, buttonIsDisabled));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dropdownButtons;
	}
	
}
