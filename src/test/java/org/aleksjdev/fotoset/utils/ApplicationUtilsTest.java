package org.aleksjdev.fotoset.utils;

import org.aleksjdev.fotoset.model.ServiceConnectionPreference;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.aleksjdev.fotoset.util.ApplicationUtils.validateConnectionPreference;

/**
 * Тестирование вспомогательных утилит приложения
 */
public class ApplicationUtilsTest {

    @Test
    public void testValidationConnectionPreference() {
        ServiceConnectionPreference connectionPreference = new ServiceConnectionPreference();
        connectionPreference.setUserName("aleksjdev@yandex.ru");
        connectionPreference = validateConnectionPreference(connectionPreference);

        assertEquals("aleksjdev", connectionPreference.getUserName());
    }
}
