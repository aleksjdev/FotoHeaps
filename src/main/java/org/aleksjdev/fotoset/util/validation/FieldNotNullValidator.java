package org.aleksjdev.fotoset.util.validation;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Валидатор, который проверяет объект на поля со значением null
 */
@Component("fieldNotNullValidator")
public class FieldNotNullValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger(FieldNotNullValidator.class.getName());

    @Override
    public List<String> validate(Object object) {
        List<String> result = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (!validateValue(value)) {
                    result.add(field.getName());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private boolean validateValue(Object value) {
        if (value == null) return Boolean.FALSE;
        return validateStringValue(value.toString());
    }

    private boolean validateStringValue(String value) {
        String trimString = value.trim();
        if (trimString.isEmpty()) return Boolean.FALSE;

        return Boolean.TRUE;
    }

}
