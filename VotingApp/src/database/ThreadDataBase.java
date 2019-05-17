package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Driver.RegisteredUser;
import Driver.VotingThread;

public class ThreadDataBase {
	private List<VotingThread> myThreads;
	
	public ThreadDataBase() {
		myThreads = new ArrayList<VotingThread>();
		loadThreadsFromDatabase();
	}
	
	public void loadThreadsFromDatabase() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("Database/Threads.xml");
			parseThreadsInDocument(doc);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseThreadsInDocument(Document databaseDoc) {
		NodeList userList = databaseDoc.getElementsByTagName("VotingThread");
		for(int i = 0; i < userList.getLength(); i++) {
			Node userNode = userList.item(i);
			if(userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userElement = (Element)userNode;
				getThreadFromElement(userElement);
			}
		}
	}
	
	private void getThreadFromElement(Element threadElement) {
		String title = threadElement.getAttribute("Title");
		String description = threadElement.getAttribute("Description");
		
		NodeList threadInfo = threadElement.getElementsByTagName("Option");
	    String[] options = new String[threadInfo.getLength()];
		for (int i = 0; i < threadInfo.getLength(); i++) {
			String option = threadInfo.item(i).getTextContent();
			options[i] = option;
		}
		
		myThreads.add(new VotingThread(title, description, options, null, options.length));
	}
	
	public List<VotingThread> getThreadsFromDatabase() {
		return myThreads;
	}
	
	public void addVotingThread(VotingThread thread) {
		myThreads.add(thread);
	}
	
	public void saveNewThreads() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element root = doc.createElement("Threads");
			
			doc.appendChild(root);
			
			for (int i = 0; i < myThreads.size(); i++) {
				Element thread = doc.createElement("VotingThread");
				Attr title = doc.createAttribute("Title");
				title.setValue(myThreads.get(i).getTitle());
				Attr description = doc.createAttribute("Description");
				description.setValue(myThreads.get(i).getDescription());
				
				thread.setAttributeNode(title);
				thread.setAttributeNode(description);
				
				for (int j = 0; j < myThreads.get(i).getNumCandidates(); j++) {
					Element option = doc.createElement("Option");
					option.appendChild(doc.createTextNode(myThreads.get(i).getCandidates()[j]));
					
					thread.appendChild(option);
				}
				
				root.appendChild(thread);
			}
			
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("Database/Threads.xml"));
            
            transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
