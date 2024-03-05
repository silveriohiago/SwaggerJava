package net.atos.beerapi.service;

import net.atos.beerapi.model.Beer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BeerValidatorAdapter extends BeerValidator implements Validator {

        /**
         * This Validator validates only Person instances
         */

        public boolean supports(Class clazz) {
            return Beer.class.equals(clazz);
        }

        public void validate(Object obj, Errors e) {
            ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
            Beer p = (Beer) obj;
            if (p.getId() < 0) {
                e.rejectValue("ID", "negativevalue");
            }
        }
}
