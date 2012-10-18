/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mycontactsjavafx;

import java.util.ListIterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author angelacaicedo
 */
public class Contact {
    
    private String name;
    private String lastName;
    private String nickname;
    private String telephone;
    private String occupation;
    private  ObservableList<String> emails;

    
    
    
    public Contact() {
        emails = FXCollections.observableArrayList();
    }

    public Contact(String name, String lastName, String nickname, String telephone, String occupation, ListIterator<String> emailsIter) {
        this.name = name;
        this.lastName = lastName;
        this.nickname = nickname;
        this.telephone = telephone;
        this.occupation = occupation;
        emails = FXCollections.observableArrayList();
        while(emailsIter.hasNext()){
            emails.add(emailsIter.next());
        }
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

        

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

        

    /**
     * Get the value of nickname
     *
     * @return the value of nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the value of nickname
     *
     * @param nickname new value of nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

        

    /**
     * Get the value of telephone
     *
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the value of telephone
     *
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
  

    /**
     * Get the value of occupation
     *
     * @return the value of occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Set the value of occupation
     *
     * @param occupation new value of occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public ListIterator<String> getEmails() {
        return emails.listIterator();
    }

    public void setEmails(ObservableList<String> emails) {
        this.emails = emails;
    }

}
