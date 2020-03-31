package views.and.forms.java.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.validator.Validator;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.and.forms.java.model.FormData;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller("/")
public class SurveyController {

    private static final Logger LOG = LoggerFactory.getLogger(SurveyController.class);

    @Inject
    Validator validator;

    @Get
    @View("home")
    public FormData home() {

        if (LOG.isInfoEnabled()) LOG.info("Sending home page " );
        return new FormData();

    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/survey")
    public ModelAndView processHomeScreen(@Body FormData formData) {

        if (LOG.isInfoEnabled()) {
            LOG.info(formData.getUserName() + ": has a chocolate preference of: " + formData.getChocolate());
        }

        Set<ConstraintViolation<FormData>> constraintViolations = validator.validate(formData);

        if (LOG.isInfoEnabled()) {
            LOG.info("We have " + constraintViolations.size() + " constraintViolation(s) " );
        }

        if (constraintViolations.size() > 0) {
            List<String> errors = new ArrayList<String>();
            for (ConstraintViolation<FormData> violation : constraintViolations) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(violation.getMessage());
                }
                errors.add(violation.getMessage());
            }
            Collections.reverse(errors);
            formData.setErrors(errors);
            return new ModelAndView("home", formData);
        }
        else {
            return new ModelAndView("thankyou", formData);
        }

    }

}
