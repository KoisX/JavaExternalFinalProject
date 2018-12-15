package com.javacourse.utils;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnableToParseUriException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Arrays;

/**
 * Utility class for marshalling uri's of type /Controller/Action/...
 * Allows us to get the controller name and the action name from the uri
 */
public class UriMarshaller {

    private String requestUri;
    private String action = null;
    /*Supposing we have uri /Login/SignIn, then SignIn action has index 1
    * in an array splitted by '/' */
    private static final int INDEX_OF_ACTION_IN_URL_PARTS_ARRAY = 1;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UriMarshaller.class);
    }

    public UriMarshaller(String requestUri) {
        this.requestUri = requestUri;
    }

    /**
     * Returns action part of the current URI.
     * If no action is specified, <code>null</code> is returned
     * @return action based on URI
     */
    public String getAction(){
        //lazy initialization
        if(action!=null)
            return action;
        else{
            try {
                action = parseUri();
            } catch (UnableToParseUriException e) {
                logger.error(e.getMessage());
            }
        }
        return action;
    }

    String parseUri() {
        if(requestUri==null)
            throw new UnableToParseUriException("Request uri can't be null");

        String[] uriParts = requestUri.split("/");
        uriParts = Arrays.stream(uriParts).filter(x -> !x.equals("")).toArray(String[]::new);
        if(uriParts.length>=2)
            return uriParts[INDEX_OF_ACTION_IN_URL_PARTS_ARRAY];
        else throw new UnableToParseUriException("No action part found in uri");
    }
}
