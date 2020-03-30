package views.and.forms.java.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.validator.Validator;
import io.micronaut.views.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.and.forms.java.model.FormData;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller("/")
public class SurveyController {

    private static final Logger LOG = LoggerFactory.getLogger(SurveyController.class);
    String[] yummyFruits = new String[] {"banana", "mango", "apple", "orange", "grapes", "star"};

    @Inject
    Validator validator;

    @Get
    public ModelAndView home() {

        if (LOG.isInfoEnabled()) LOG.info("Sending home page " );
        return new ModelAndView("home", new FormData(yummyFruits));
//        return new ModelAndView("home", new FormData(setUpFruitChoices()));

    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/survey")
    public ModelAndView processHomeScreen(@Body FormData formData) {

//        String[] allFruits = {formData.getBanana(), formData.getMango(),
//                formData.getApple(), formData.getOrange(), formData.getGrapes(),
//                formData.getStar()};
//        List<String> checkedFruits = Stream.of(allFruits)
//                .filter(e -> e != null)
//                .collect(Collectors.toList());

        if (LOG.isInfoEnabled()) {
            LOG.info(formData.getUserName() + ": has a chocolate preference of: " + formData.getChocolate());
            LOG.info(formData.getUserName() + ": has a chocolate preference of: " + formData.getChocolate());
        }
//        if (LOG.isInfoEnabled()) LOG.info(formData.getUserName() + ": checkedFruits: " + checkedFruits);

//        formData.setFruit(checkedFruits);
        formData.setUserNameErrorMessage("");

        Set<ConstraintViolation<FormData>> constraintViolations = validator.validate(formData);

        if (LOG.isInfoEnabled()) {
            LOG.info("We have " + constraintViolations.size() + " constraintViolation(s) " );
        }

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<FormData> violation : constraintViolations) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(violation.getMessage());
                }
                formData.setUserNameErrorMessage(violation.getMessage());
            }
            formData.setFruitChoices(yummyFruits);
            return new ModelAndView("home", formData);
        }
        else
          return new ModelAndView("thankyou", formData);

    }

}
