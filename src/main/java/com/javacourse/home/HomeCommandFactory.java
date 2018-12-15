package com.javacourse.home;

import com.javacourse.exceptions.UnexistingUrlException;
import com.javacourse.home.commands.HomeCommandEnum;
import com.javacourse.security.command.LoginCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.HttpMethod;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;

public class HomeCommandFactory extends CommandFactory{

    public HomeCommandFactory(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request.getRequestURI());
        String action = marshaller.getAction();
        switch (action){
            case "Index":
                return HomeCommandEnum.INDEX.getCommand();
            case "About":
                return HomeCommandEnum.ABOUT.getCommand();
            case "Rules":
                return HomeCommandEnum.RULES.getCommand();
            default:
                return HomeCommandEnum.INDEX.getCommand();
        }
    }
}
