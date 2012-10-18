/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mycontactsjavafx;

import java.net.URL;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author angelacaicedo
 */
public class SampleController implements Initializable {
    
    @FXML
    private TextField nameTxtF;
    @FXML
    private TextField lastNameTxtF;
    @FXML
    private TextField nicknameTxtF;
    @FXML
    private TextField phoneTxtF;
    @FXML
    private TextField occupationTxtF;
    @FXML
    private TextField emailTxtF;
    @FXML
    private ListView emailList;
    @FXML
    private Label msgBarLbl;
    @FXML
    private ListView contactList;
    @FXML
    private Label contactsMsg;
    
    private  ObservableList<String> emailObservable = FXCollections.observableArrayList();
    private  ObservableList<String> contactsObservable = FXCollections.observableArrayList();
    private  FadeTransition labelFading;
    private  FadeTransition labelContactsFading;
    private  HashMap<String, Contact> contacts = new HashMap<>();
    
    @FXML
    private void addAction(ActionEvent event) {
        if(!emailTxtF.getText().isEmpty()){
            emailObservable.add(emailTxtF.getText());
            msgBarLbl.setText(emailTxtF.getText() + " has been Added!!!");
            emailTxtF.setText("");
        }else{
            msgBarLbl.setText("Type Your Email First!...");
        }
        labelFading.play();
    }
    
    @FXML
    private void removeAction(ActionEvent event) {
        ObservableList<String> email = emailList.getSelectionModel().getSelectedItems();
        if(email.size() > 0){
            msgBarLbl.setText(email.get(0) + " has been Deleted!...");
            emailObservable.removeAll(email.get(0));  
        }else{
            msgBarLbl.setText("Select an E-mail to be Deleted!...");
        }
        labelFading.play();
    }
    
    @FXML
    private void editAction(ActionEvent event) {
        ObservableList<String> email = emailList.getSelectionModel().getSelectedItems();
        if(email.size() > 0){
            msgBarLbl.setText(email.get(0) + " seleted for editing!...");
            emailTxtF.setText(email.get(0));  
            emailObservable.removeAll(email.get(0));  
        }else{
            msgBarLbl.setText("Select an E-mail to be Edit!...");
        }
        labelFading.play();
    }
    
    
    @FXML
    private void saveContact(ActionEvent event) {
        if(!nicknameTxtF.getText().isEmpty()){
            if(contacts.get(nicknameTxtF.getText()) == null){
                contacts.put(nicknameTxtF.getText(), new Contact(nameTxtF.getText(), lastNameTxtF.getText(), 
                                                                 nicknameTxtF.getText(), phoneTxtF.getText(), 
                                                                 occupationTxtF.getText(), emailObservable.listIterator()));
                contactsObservable.add(nicknameTxtF.getText());
                contactsMsg.setText("Contact " + nicknameTxtF.getText() + " has been saved!...");
                labelContactsFading.play();
                newContact(event);
            }
        }else{
            contactsMsg.setText("Contact requires a nickname!...");
        }
        labelContactsFading.play();
    }
    
    @FXML
    private void newContact(ActionEvent event) {
            
            nameTxtF.setText("");
            lastNameTxtF.setText("");
            nicknameTxtF.setText("");
            phoneTxtF.setText("");
            occupationTxtF.setText("");
            emailObservable.clear();
            
    }
    
    
    @FXML
    private void deleteContact(ActionEvent event) {
        ObservableList<String> selectedContact = contactList.getSelectionModel().getSelectedItems();
        String contactToDelete = selectedContact.get(0);
        
        
        if(selectedContact.size() > 0){
            contacts.remove(contactToDelete);
            contactsObservable.removeAll(contactToDelete);
            contactsMsg.setText("Contact " + contactToDelete+ " has been removed");
            labelContactsFading.play();
            newContact(event);
        }
        
    }
    
    @FXML
    private void actionContact(MouseEvent event) {
        ObservableList<String> selectedContact = contactList.getSelectionModel().getSelectedItems();
        Contact details;
        ListIterator<String> emailListIter;
        if(selectedContact.size() > 0){
            details = contacts.get(selectedContact.get(0));
            nameTxtF.setText(details.getName());
            lastNameTxtF.setText(details.getLastName());
            nicknameTxtF.setText(details.getNickname());
            phoneTxtF.setText(details.getTelephone());
            occupationTxtF.setText(details.getOccupation());
            emailListIter = details.getEmails();
            emailObservable.clear();
            while(emailListIter.hasNext()){
                emailObservable.add(emailListIter.next());
            }
            
        }else{
            
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             assert nameTxtF != null : "fx:id=\"nameTxtF\" was not injected: check your FXML file.";
             assert lastNameTxtF != null : "fx:id=\"lastNameTxtF\" was not injected: check your FXML file.";
             assert nicknameTxtF != null : "fx:id=\"nicknameTxtF\" was not injected: check your FXML file.";
             assert phoneTxtF != null : "fx:id=\"phoneTxtF\" was not injected: check your FXML file.";
             assert occupationTxtF != null : "fx:id=\"occupationTxtF\" was not injected: check your FXML file.";
             assert emailTxtF != null : "fx:id=\"emailTxtF\" was not injected: check your FXML file.";
             assert emailList != null : "fx:id=\"emailTxtA\" was not injected: check your FXML file.";
             assert msgBarLbl != null : "fx:id=\"msgBarLbl\" was not injected: check your FXML file.";
             assert contactsMsg != null : "fx:id=\"contactsMsg\" was not injected: check your FXML file.";
             assert contactList != null : "fx:id=\"contactList\" was not injected: check your FXML file.";
             
             labelFading = new FadeTransition(Duration.millis(3000), msgBarLbl);
             labelFading.setFromValue(1.0);
             labelFading.setToValue(0.0);
             
             
             labelContactsFading = new FadeTransition(Duration.millis(3000), contactsMsg);
             labelContactsFading.setFromValue(1.0);
             labelContactsFading.setToValue(0.0);
             
             
             msgBarLbl.setTextFill(Color.RED);
             emailList.setItems(emailObservable);
             contactList.setItems(contactsObservable);
    }    
}
