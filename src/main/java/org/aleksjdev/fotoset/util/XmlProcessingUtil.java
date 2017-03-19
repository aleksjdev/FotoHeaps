package org.aleksjdev.fotoset.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Вспомогательный класс для упрощения работы с xml документами
 */
public class XmlProcessingUtil {

    /**
     * Создание SAX парсера
     *
     * @return SAX парсер
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static SAXParser createSAXParser() throws SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        return factory.newSAXParser();
    }

    /**
     * Построение xml документа из входного потока для дальнейшей обработки
     *
     * @param stream входной поток
     * @return результирующий xml документ
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document buildXmlDocument(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(stream);
    }

    /**
     * Получение значения одиночного тега по его названию
     *
     * @param element xml элемент, из которого получаем значение тега
     * @param tagName имя тега
     * @return значение одиночного тега
     */
    public static String getSingleElementValueByTagName(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            return null;
        }
        return nodeList.item(0).getTextContent();
    }

    /**
     * Получение содержимого сложного тега (содержащего дочерние теги) по его названию
     *
     * @param element родительский xml элемент, из которого получаем значение тега
     * @param tagName имя тега
     * @return содержимое тега
     */
    public static Element getElementByTagName(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            return null;
        }
        Node node = nodeList.item(0);
        return (Element)node;
    }

    /**
     * Получение содержимого тега по его названию и значению аттрибута
     *
     * @param element родительский xml элемент, из которого получаем значение тега
     * @param tagName имя тега
     * @param attrName имя аттрибута
     * @param attrValue значение аттрибута
     * @return содержимое тега
     */
    public static Element getElementByTagNameAndAttributeValue(Element element,
                                                               String tagName,
                                                               String attrName,
                                                               String attrValue) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            return null;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element)node;
                if (attrValue.equals(getAttributeValueByName(nodeElement, attrName))) {
                    return nodeElement;
                }
            }
        }
        return null;
    }

    /**
     * Получение коллекции элементов по их названию
     *
     * @param element родительская xml структура, из которой получаем коллекцию дочерних элементов
     * @param tagName имя тега дочерних элементов
     * @return коллекция элементов
     */
    public static List<Element> getElementCollectionByTagName(Element element, String tagName) {
        List<Element> result = new LinkedList<Element>();
        NodeList nodeList = element.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                result.add((Element)node);
            }
        }
        return result;
    }

    /**
     * Получение коллекции элементов из документа
     *
     * @param document xml документ
     * @param tagName имя тега дочерних элементов
     * @return коллекция элементов
     */
    public static List<Element> getElementCollectionFromDocument(Document document, String tagName) {
        List<Element> res = new LinkedList<Element>();
        NodeList entries = document.getElementsByTagName(tagName);
        for (int i = 0; i < entries.getLength(); i++) {
            Node entry = entries.item(i);
            if (entry.getNodeType() == Node.ELEMENT_NODE) {
                res.add((Element) entry);
            }
        }
        return res;
    }


    /**
     * Получение значения аттрибута из xml элемента
     *
     * @param element xml элемент
     * @param attrName название аттрибута
     * @return значение аттрибута
     */
    public static String getAttributeValueByName(Element element, String attrName) {
        return element.getAttribute(attrName);
    }
}
