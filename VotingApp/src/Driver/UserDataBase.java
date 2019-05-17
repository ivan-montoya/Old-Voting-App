package Driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UserDataBase {
	private List<RegisteredUser> myUsers;
	
	public UserDataBase() {
		myUsers = new ArrayList<RegisteredUser>();
		loadUsersFromDatabase();
	}
	
	private void loadUsersFromDatabase() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("Database/Users.xml");
			parseUsersInDocument(doc);

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
	
	private void parseUsersInDocument(Document databaseDoc) {
		NodeList userList = databaseDoc.getElementsByTagName("RegisteredUser");
		for(int i = 0; i < userList.getLength(); i++) {
			Node userNode = userList.item(i);
			if(userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userElement = (Element)userNode;
				getUserFromElement(userElement);
			}
		}
	}
	
	private void getUserFromElement(Element userElement) {
		NodeList userInfo = userElement.getChildNodes();
		String username = userInfo.item(0).getTextContent();
		String email = userInfo.item(1).getTextContent();
		String password = userInfo.item(2).getTextContent();
		String name = userInfo.item(3).getTextContent();
		String DOB = userInfo.item(4).getTextContent();
		myUsers.add(new RegisteredUser(username, email, password, name, DOB, null, 0));
	}
	
	/**
	 * Returns list of Registered Users inside the database.
	 * @return Copy of myUsers List.
	 */
	public List<RegisteredUser> getUsers() {
		return new ArrayList<RegisteredUser>(myUsers);
	}
	
	public void addUser(RegisteredUser user) {
		myUsers.add(user);
	}
	
	public void saveNewUsers() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element root = doc.createElement("Users");
			
			doc.appendChild(root);
			
			for (int i = 0; i < myUsers.size(); i++) {
				Element user = doc.createElement("RegisteredUser");
				Element username = doc.createElement("Username");
				username.appendChild(doc.createTextNode(myUsers.get(i).getUser()));
				Element email = doc.createElement("Email");
				email.appendChild(doc.createTextNode(myUsers.get(i).getEmail()));
				Element password = doc.createElement("Password");
				password.appendChild(doc.createTextNode(myUsers.get(i).getPassword()));
				Element name = doc.createElement("Name");
				name.appendChild(doc.createTextNode(myUsers.get(i).getName()));
				Element birthday = doc.createElement("Birthday");
				birthday.appendChild(doc.createTextNode(myUsers.get(i).getBirthday()));
				
				user.appendChild(username);
				user.appendChild(email);
				user.appendChild(password);
				user.appendChild(name);
				user.appendChild(birthday);
				root.appendChild(user);
				

			}
			
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File("Database/Users.xml"));
            
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
	
	public boolean checkIfUsernameAlreadyExists(String username) {
		boolean userExists = false;
		for(int i = 0; i < myUsers.size(); i++) {
			if (username.equals(myUsers.get(i).getUser())) {
				userExists = true;
			}
		}
		return userExists;
	}
	
	public RegisteredUser getUser(String username) {
		for(int i = 0; i < myUsers.size(); i++) {
			if (username.equals(myUsers.get(i).getUser())) {
				return myUsers.get(i);
			}
		}
		return null;
	}
}
