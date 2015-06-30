package com.mathapp.utilities;
import java.awt.image.ConvolveOp;
import java.beans.XMLDecoder;
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class XmlHelperForMathApp {

	public String createXmlAsString(MathResponseData response) {

		Document doc = null;
		String result = Long.toString(response.getResult());
		String status = response.getStatus();

		try {
			//We need a Document
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			doc = docBuilder.newDocument();

			//create the root element and add it to the document
			Element root = doc.createElement("math");
			doc.appendChild(root);

			//create child element, add an attribute, and add to root
			Element child = doc.createElement("result");
			child.appendChild(doc.createTextNode(result));
			root.appendChild(child);

			child = doc.createElement("status");
			child.appendChild(doc.createTextNode(status));
			root.appendChild(child);

		} catch (Exception e) {
			System.out.println(e);
		}
		return convertDocumentToString(doc);
	}
	
	public MathResponseData getMathResponse(String xmlString){
		MathResponseData response = new MathResponseData();
		Document doc = convertStringToDocument(xmlString);
		
		NodeList listOfMath = doc.getElementsByTagName("math");
		Node mathNode =listOfMath.item(0);
		if (mathNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) mathNode;
			String result = getTagValue("result", eElement);
			response.setResult(Long.parseLong(result));

			String status = getTagValue("status", eElement);
			response.setStatus(status);
		}
		
		return response;
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	private Document convertStringToDocument(String xmlString){
		Document doc = null;

		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
			.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			doc = db.parse(is);
		} catch (Exception e) {
			System.out.println(e);
		}
		return doc;
	}

	private String convertDocumentToString(Document doc) {

		String xmlString = null;

		try {
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			//create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			xmlString = sw.toString();

			//print xml
			//System.out.println("Here's the xml:\n\n" + xmlString);

		} catch (Exception e) {
			System.out.println(e);
		}
		return xmlString;
	}

	public static void main (String args[]) {
		
		// For testing
		
		XmlHelperForMathApp helper = new XmlHelperForMathApp();
		MathResponseData sent = new MathResponseData();
		sent.setResult(100);
		sent.setStatus("gold");
		

		String xmlOut = helper.createXmlAsString(sent);
		MathResponseData received = helper.getMathResponse(xmlOut);
		
		System.out.println("Result is: " + received.getResult());
		System.out.println("Status is: " + received.getStatus());


	}
}