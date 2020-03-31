package views.and.forms.java.model;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class FormData {

    @Size(min=2, message="Name must be at least 2 characters long.")
    private String userName;
    private String userNameErrorMessage;
    private String chocolate;
    private String[] fruitChoices = {"banana", "mango", "apple", "orange", "grapes", "star"};;
    @Size(max=3, message="Please choose a maximum of 3 fruits.")
    private List<String> fruitChosen = new ArrayList<String>();
    private String fruitChosenErrorMessage;

    public FormData() {
    }


    public FormData(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameErrorMessage() {
        return userNameErrorMessage;
    }

    public void setUserNameErrorMessage(String userNameErrorMessage) {
        this.userNameErrorMessage = userNameErrorMessage;
    }

    public String getChocolate() {
        return chocolate;
    }

    public void setChocolate(String chocolate) {
        this.chocolate = chocolate;
    }

    public List<String> getFruitChosen() {
        return fruitChosen;
    }

    public void setFruitChosen(List<String> fruitChosen) {
        this.fruitChosen = fruitChosen;
    }

    public String[] getFruitChoices() {
        return fruitChoices;
    }

    public void setFruitChoices(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }

    public String getFruitChosenErrorMessage() {
        return fruitChosenErrorMessage;
    }

    public void setFruitChosenErrorMessage(String fruitChosenErrorMessage) {
        this.fruitChosenErrorMessage = fruitChosenErrorMessage;
    }

}
