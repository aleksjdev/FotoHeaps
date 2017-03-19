package org.aleksjdev.fotoset.validator;

import org.aleksjdev.fotoset.mail.EmailSenderContent;
import org.aleksjdev.fotoset.util.validation.FieldNotNullValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Тестирование валидатора, который проверяет объект на поля со значением null
 */
public class FieldNotNullValidatorTest {

    private FieldNotNullValidator validator;

    @Before
    public void setUp() {
        validator = new FieldNotNullValidator();
    }

    @Test
    public void testFieldNotNullValidation() {
        EmailSenderContent content = new EmailSenderContent();
        content.setUserName("aleksjdev");
        content.setTextContent("Text content");

        List<String> notValidatedFields = validator.validate(content);
        assertNotNull(notValidatedFields);
        assertEquals(1, notValidatedFields.size());
        assertEquals("email", notValidatedFields.get(0));
    }

    @Test
    public void testBlankFieldValidation() {
        EmailSenderContent content = new EmailSenderContent();
        content.setUserName("     ");
        content.setEmail("aleksjdev@gmail.com");
        content.setTextContent("           ");

        List<String> notValidatedFields = validator.validate(content);
        assertNotNull(notValidatedFields);
        assertEquals(2, notValidatedFields.size());
        assertEquals("userName", notValidatedFields.get(0));
        assertEquals("textContent", notValidatedFields.get(1));
    }

}
