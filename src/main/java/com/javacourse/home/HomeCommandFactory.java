package com.javacourse.home;

import com.javacourse.home.commands.HomeCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommandFactory extends CommandFactory{

    public HomeCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        switch (action){
            case "Index":
                return HomeCommandEnum.INDEX.getCommand();
            case "About":
                return HomeCommandEnum.ABOUT.getCommand();
            case "Rules":
                return HomeCommandEnum.RULES.getCommand();
            case "Stats":
                return HomeCommandEnum.STATS.getCommand();
            default:
                return HomeCommandEnum.INDEX.getCommand();
        }
    }
}
