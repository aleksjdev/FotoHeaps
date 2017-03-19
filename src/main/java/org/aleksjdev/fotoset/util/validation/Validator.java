package org.aleksjdev.fotoset.util.validation;

import java.util.List;

/**
 * Интерфейс для валидаторов объектов
 */
public interface Validator {

    /**
     * Валидация полей объекта
     *
     * @param object объект
     * @return список полей, не прошедших валидацию
     */
    List<String> validate(Object object);
}
