package ru.job4j.security.controller.authorization;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Class MockHttpSession реализует объект заглушку HttpSession.
 * @author Timur Kapkaev (timur.kap@yandex.ru)
 * @version $ID$
 * @since 20.11.2017
 * */
public class MockHttpSession implements HttpSession {

    /** атрибуты сессии. */
    private final Map<String, Object> attributes = new HashMap<>();
    /**
     * Returns the time when this session was created, measured in milliseconds since midnight January 1, 1970 GMT.
     * @return a long specifying when this session was created, expressed in milliseconds since 1/1/1970 GMT
     * */
    @Override
    public long getCreationTime() {
        return 0;
    }

    /**
     * Returns a string containing the unique identifier assigned to this session. The identifier is assigned by the servlet container and is implementation dependent.
     * @return a string specifying the identifier assigned to this session
     * */
    @Override
    public String getId() {
        return null;
    }

    /**
     * Returns the last time the client sent a request associated with this session, as the number of milliseconds since midnight January 1, 1970 GMT, and marked by the time the container received the request.
     * Actions that your application takes, such as getting or setting a value associated with the session, do not affect the access time.
     * @return a long representing the last time the client sent a request associated with this session, expressed in milliseconds since 1/1/1970 GMT
     * */
    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    /**
     * Returns the ServletContext to which this session belongs.
     * The ServletContext object for the web application
     * */
    @Override
    public ServletContext getServletContext() {
        return null;
    }

    /**
     * Specifies the time, in seconds, between client requests before the servlet container will invalidate this session.
     * An interval value of zero or less indicates that the session should never timeout.
     * */
    @Override
    public void setMaxInactiveInterval(int i) {

    }

    /**
     * Returns the maximum time interval, in seconds, that the servlet container will keep this session open between client accesses. After this interval, the servlet container will invalidate the session. The maximum time interval can be set with the setMaxInactiveInterval method.
     * A return value of zero or less indicates that the session will never timeout.
     * @return an integer specifying the number of seconds this session remains open between client requests
     * */
    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    /**
     * Deprecated. As of Version 2.1, this method is deprecated and has no replacement. It will be removed in a future version of the Java Servlet API.
     * */
    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    /**
     * Returns the object bound with the specified name in this session, or null if no object is bound under the name.
     * @param s - a string specifying the name of the object
     * @return the object with the specified name
     * */
    @Override
    public Object getAttribute(String s) {
        return attributes.get(s);
    }

    /**
     * Deprecated. As of Version 2.2, this method is replaced by getAttribute(java.lang.String).
     * @param s - a string specifying the name of the object
     * @return the object with the specified name
     * */
    @Override
    public Object getValue(String s) {
        return null;
    }

    /**
     * Returns an Enumeration of String objects containing the names of all the objects bound to this session.
     * @return an Enumeration of String objects specifying the names of all the objects bound to this session
     * */
    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    /**
     * Deprecated. As of Version 2.2, this method is replaced by getAttributeNames().
     * @return an array of String objects specifying the names of all the objects bound to this session
     * */
    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    /**
     * Binds an object to this session, using the name specified. If an object of the same name is already bound to the session, the object is replaced.
     * After this method executes, and if the new object implements HttpSessionBindingListener, the container calls HttpSessionBindingListener.valueBound. The container then notifies any HttpSessionAttributeListeners in the web application.
     * If an object was already bound to this session of this name that implements HttpSessionBindingListener, its HttpSessionBindingListener.valueUnbound method is called.
     * If the value passed in is null, this has the same effect as calling removeAttribute().
     * @param s - the name to which the object is bound; cannot be null
     * @param o - the object to be bound
     * */
    @Override
    public void setAttribute(String s, Object o) {
        attributes.put(s, o);

    }

    /**
     * Deprecated. As of Version 2.2, this method is replaced by setAttribute(java.lang.String, java.lang.Object).
     * @param s - the name to which the object is bound; cannot be null
     * @param o - the object to be bound; cannot be null
     * */
    @Override
    public void putValue(String s, Object o) {

    }

    /**
     * Removes the object bound with the specified name from this session. If the session does not have an object bound with the specified name, this method does nothing.
     * After this method executes, and if the object implements HttpSessionBindingListener, the container calls HttpSessionBindingListener.valueUnbound. The container then notifies any HttpSessionAttributeListeners in the web application.
     * @param s - the name of the object to remove from this session
     * */
    @Override
    public void removeAttribute(String s) {

    }

    /**
     * Deprecated. As of Version 2.2, this method is replaced by removeAttribute(java.lang.String).
     * @param s - the name of the object to remove from this session
     * */
    @Override
    public void removeValue(String s) {

    }

    /**
     * Invalidates this session then unbinds any objects bound to it.
     * */
    @Override
    public void invalidate() {

    }

    /**
     * Returns true if the client does not yet know about the session or if the client chooses not to join the session. For example, if the server used only cookie-based sessions, and the client had disabled the use of cookies, then a session would be new on each request.
     * @return true if the server has created a session, but the client has not yet joined
     * */
    @Override
    public boolean isNew() {
        return false;
    }
}
