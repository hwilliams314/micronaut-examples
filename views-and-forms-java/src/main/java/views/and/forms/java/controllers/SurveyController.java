package views.and.forms.java.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Error;
import io.micronaut.validation.validator.Validator;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.and.forms.java.model.FormData;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Controller("/")
public class SurveyController {

    private static final Logger LOG = LoggerFactory.getLogger(SurveyController.class);

    @Inject
    Validator validator;

    @Get
    @View("home")
    public FormData home() {

        if (LOG.isInfoEnabled()) {
            LOG.info("Sending home page ");
        }
        return new FormData();

    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/survey")
    public ModelAndView processHomeScreen(@Body @Valid FormData formData) {

        if (LOG.isInfoEnabled()) {
            LOG.info( "{} has a chocolate preference of: {}", formData.getUserName(), formData.getChocolate());
        }

        Set<ConstraintViolation<FormData>> constraintViolations = validator.validate(formData);

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

    @View("home")
    @Error(exception = ConstraintViolationException.class)
    public FormData constraintsErrors(HttpRequest request, ConstraintViolationException cv) {

        if (LOG.isInfoEnabled()) {
            LOG.info("In @Error meth ");
        }

        Set<ConstraintViolation<?>> constraintViolations = cv.getConstraintViolations();
        AtomicReference<FormData> formDataToReturn = new AtomicReference<>(new FormData());
        Optional<FormData> optionalFormData = request.getBody(FormData.class);

        optionalFormData.ifPresent(formData -> {
            List<String> errors = new ArrayList<String>();

            for (ConstraintViolation<?> violation : constraintViolations) {
                if (LOG.isInfoEnabled()) {
                    LOG.info("Error: {}", violation.getMessage());
                }
                errors.add(violation.getMessage());

            }
            Collections.reverse(errors);
            formData.setErrors(errors);
            formDataToReturn.set(formData);

        });

        return formDataToReturn.get();

    }

}
