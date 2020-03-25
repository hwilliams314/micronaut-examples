package views.and.forms.java.model;

import io.micronaut.core.annotation.Introspected;
import java.util.List;
import javax.validation.constraints.*;

@Introspected
public class FormData {

    @Size(min=2, message="Name must be at least 2 characters long.")
    private String userName;
    private String userNameErrorMessage;
    private String chocolate;
    private String banana;
    private String mango;
    private String apple;
    private String orange;
    private String grapes;
    private String star;
    private List<String> fruit;
    private String[] fruitChoices;

    public FormData() {
    }

    public FormData(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }

    public FormData(@Size(min = 2, message = "Name must be at least 2 characters long.") String userName, String userNameErrorMessage, String chocolate, String banana, String mango, String apple, String orange, String grapes, String star, List<String> fruit, String[] fruitChoices) {
        this.userName = userName;
        this.userNameErrorMessage = userNameErrorMessage;
        this.chocolate = chocolate;
        this.banana = banana;
        this.mango = mango;
        this.apple = apple;
        this.orange = orange;
        this.grapes = grapes;
        this.star = star;
        this.fruit = fruit;
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

    public String getBanana() {
        return banana;
    }

    public void setBanana(String banana) {
        this.banana = banana;
    }

    public String getMango() {
        return mango;
    }

    public void setMango(String mango) {
        this.mango = mango;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getOrange() {
        return orange;
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }

    public String getGrapes() {
        return grapes;
    }

    public void setGrapes(String grapes) {
        this.grapes = grapes;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public List<String> getFruit() {
        return fruit;
    }

    public void setFruit(List<String> fruit) {
        this.fruit = fruit;
    }

    public String[] getFruitChoices() { return fruitChoices; }

    public void setFruitChoices(String[] fruitChoices) {
        this.fruitChoices = fruitChoices;
    }
}
