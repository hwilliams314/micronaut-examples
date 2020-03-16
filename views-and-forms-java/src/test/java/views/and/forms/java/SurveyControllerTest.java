package views.and.forms.java;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import views.and.forms.java.controllers.SurveyController;
import views.and.forms.java.model.FormData;
import views.and.forms.java.model.FruitChoices;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class SurveyControllerTest {

    @Inject
    SurveyController itemUnderTest;

    @Test
    void testHome() {

        FruitChoices expectedFruitChoices = new FruitChoices(new String[] {"banana", "mango", "apple", "orange", "grapes", "star"});

        FruitChoices actual = itemUnderTest.home();

        assertArrayEquals(actual.getFruits(), expectedFruitChoices.getFruits());
    }

    @Test
    void testThankYou_userName_chocolate() {

        FormData fakeFormData = new FormData("fakeUser", "like", "banana", "mango", null, null, null, "star", null);

        FormData result = itemUnderTest.processHomeScreen(fakeFormData);

        assertEquals(result.getUserName(), "fakeUser");
        assertEquals(result.getChocolate(), "like");
        assertTrue(result.getFruit().indexOf("banana") != -1);
        assertTrue(result.getFruit().indexOf("mango") != -1);
    }

    @Test
    void testThankYou_chosenFruit() {

        FormData fakeFormData = new FormData("fakeUser", "like", "banana", "mango", null, null, null, "star", null);

        FormData actual = itemUnderTest.processHomeScreen(fakeFormData);

        List<String> fakeFruitList = new ArrayList<String>(
                Arrays.asList("banana", "mango", "star"));

        assertEquals(actual.getFruit().size(), fakeFruitList.size());
        assertArrayEquals(actual.getFruit().toArray(), fakeFruitList.toArray());

    }

    @Test
    void testThankYou_noChosenFruit() {

        FormData fakeFormData = new FormData("fakeUser", "like", null, null, null, null, null, null, null);

        FormData actual = itemUnderTest.processHomeScreen(fakeFormData);

        assertEquals(actual.getFruit().size(), 0);
    }

}
