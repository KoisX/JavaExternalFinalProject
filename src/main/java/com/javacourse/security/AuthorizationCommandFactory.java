package com.javacourse.security;

import com.javacourse.exceptions.UnexistingUrlException;
import com.javacourse.security.command.LoginCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.web.HttpMethod;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class, responsible for defining a proper command
 * for a particular URL query
 */
public class AuthorizationCommandFactory extends CommandFactory {

    public AuthorizationCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        if(action==null)
            throw new UnexistingUrlException();
        switch (action){
            case "SignIn":
                return HttpMethod.isGet(request.getMethod()) ?
                        LoginCommandEnum.SHOW_SIGN_IN.getCommand() :
                        LoginCommandEnum.SIGN_IN.getCommand();
            case "SignUp":
                return HttpMethod.isGet(request.getMethod()) ?
                        LoginCommandEnum.SHOW_SIGN_UP.getCommand() :
                        LoginCommandEnum.SIGN_UP.getCommand();
            case "Logout":
                return LoginCommandEnum.LOGOUT.getCommand();
            default:
                throw new UnexistingUrlException();
        }
    }
}
