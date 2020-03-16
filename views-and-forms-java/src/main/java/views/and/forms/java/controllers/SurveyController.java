package views.and.forms.java.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.and.forms.java.model.FormData;
import views.and.forms.java.model.FruitChoices;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller("/")
public class SurveyController {

    private static final Logger LOG = LoggerFactory.getLogger(SurveyController.class);

    @Get
    @View("home")
    public FruitChoices home() {

        LOG.info("Sending home page " );
        String[] yummyFruits = new String[] {"banana", "mango", "apple", "orange", "grapes", "star"};
        return new FruitChoices(yummyFruits);

    }

    @View("thankyou")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/survey")
    public FormData processHomeScreen(@Body FormData formData) {

        String[] allFruits = {formData.getBanana(), formData.getMango(),
                formData.getApple(), formData.getOrange(), formData.getGrapes(),
                formData.getStar()};
        List<String> checkedFruits = Stream.of(allFruits)
                .filter(e -> e != null)
                .collect(Collectors.toList());

        LOG.info(formData.getUserName() + ": has a chocolate preference of: " + formData.getChocolate());
        LOG.info(formData.getUserName() + ": checkedFruits: " + checkedFruits);

        formData.setFruit(checkedFruits);

        return formData;

    }
}
