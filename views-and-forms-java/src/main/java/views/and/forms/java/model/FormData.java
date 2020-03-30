package views.and.forms.java.model;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.*;

@Introspected
public class FormData {

    @Size(min=2, message="Name must be at least 2 characters long.")
    private String userName;
    private String userNameErrorMessage;
    private String chocolate;
    private String[] fruitChosen = {};
    private String[] fruitChoices = {};

    public FormData() {
    }

    public FormData(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }

    public FormData(@Size(min = 2, message = "Name must be at least 2 characters long.")
                            String userName, String userNameErrorMessage, String chocolate, String[] fruitChosen, String[] fruitChoices) {
        this.userName = userName;
        this.userNameErrorMessage = userNameErrorMessage;
        this.chocolate = chocolate;
        this.fruitChosen = fruitChosen;
        this.fruitChoices = fruitChoices;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameErrorMessage() { return userNameErrorMessage; }

    public void setUserNameErrorMessage(String userNameErrorMessage) {
        this.userNameErrorMessage = userNameErrorMessage;
    }

    public String getChocolate() {
        return chocolate;
    }

    public void setChocolate(String chocolate) {
        this.chocolate = chocolate;
    }

    public String[] getFruitChosen() {
        return fruitChosen;
    }

    public void setFruitChosen(String[] fruitChosen) {
        this.fruitChosen = fruitChosen;
    }

    public String[] getFruitChoices() { return fruitChoices; }

    public void setFruitChoices(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }
}
