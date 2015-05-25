package by.leonovich.notizieportale.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by alexanderleonovich on 18.04.15.
 * экземпляр request не должен свободно передаваться в бизнес-логику приложения во избежание некорректной модификации,
 * поэтому следует предварительно извлекать необходимую информацию из экземпляра-запроса и сохранять ее в объекте класса.
 * Специально для этого и предназначен данный класс.
 */
public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private HttpServletRequest innerRequestInstance;
    private HttpSession session;
    // конструкторы


    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    // метод извлечения информации из запроса
    public void extractValues(HttpServletRequest request) {
        /** Правильно ли я использую данную инициализацию !?!?!?!?!?!?!!?!?!?!?!!? */
        //innerRequestInstance = request;
        session = request.getSession();

        Enumeration<String> requestAtributesNames = request.getAttributeNames();
        while (requestAtributesNames.hasMoreElements()) {
            String elementFromEnum = requestAtributesNames.nextElement();
            requestAttributes.put(elementFromEnum, request.getAttribute(elementFromEnum));
        }

        Enumeration<String> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String elementFromEnum = requestParamNames.nextElement();
            requestParameters.put(elementFromEnum, request.getParameter(elementFromEnum));
        }


        Enumeration<String> sessionAtributesNames = request.getSession().getAttributeNames();
        while (sessionAtributesNames.hasMoreElements()) {
            String elementFromEnum = sessionAtributesNames.nextElement();
            sessionAttributes.put(elementFromEnum, request.getSession().getAttribute(elementFromEnum));
        }


    }
    // метод добавления в запрос данных для передачи в jsp
    public void insertAttributes(HttpServletRequest request) { // реализация
        Set<String> keysReqAtrib = requestAttributes.keySet();
        for (String element : keysReqAtrib) {
            request.setAttribute(element, requestAttributes.get(element));
        }

        Set<String> keysReqParams = requestParameters.keySet();
        for (String element : keysReqParams) {
            request.setAttribute(element, requestAttributes.get(element));
        }

        Set<String> keysSesAtrib = sessionAttributes.keySet();
        for (String element : keysSesAtrib) {
            request.getSession().setAttribute(element, sessionAttributes.get(element));
        }
    }

    // some methods
    public String getParameter(String parameterName) {
        return requestParameters.get(parameterName);
    }

    public void setRequestAttribute(String parameter, String value) {
        requestAttributes.put(parameter, value);
    }

    public void setSessionAttribute(String objectKey, Object object) {
        sessionAttributes.put(objectKey, object);
    }

    public Object getSessionAttribute(String objectKey) {
        return sessionAttributes.get(objectKey);
    }

    public void removeRequestParameter(String attributeName) {
        innerRequestInstance.removeAttribute(attributeName);
        requestParameters.remove(attributeName);
    }

    public void invalidateSession() {
        requestAttributes.clear();
        requestParameters.clear();
        sessionAttributes.clear();
    }
    /** Правильно ли я использую данный метод !?!?!?!?!?!?!!?!?!?!?!!?
     * без использования request объект из сессии не удаляется, касательно commentary */
    public void removeSessionAttribute(String attributeName) {
        //innerRequestInstance.getSession().removeAttribute(attributeName);
        session.removeAttribute(attributeName);
        sessionAttributes.remove(attributeName);
    }
}
