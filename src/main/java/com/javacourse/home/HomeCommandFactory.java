package com.javacourse.home;

import com.javacourse.home.commands.HomeCommandEnum;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.web.HttpMethod;
import com.javacourse.utils.UriMarshaller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class HomeCommandFactory extends CommandFactory{

    private static final String COMMAND = "command";
    private static final String EDIT_CMD = "edit";
    private static final String DELETE_CMD = "delete";

    public HomeCommandFactory(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public Command defineCommand() {
        UriMarshaller marshaller = new UriMarshaller(request);
        String action = marshaller.getAction();
        switch (action){
            case "Index":
                return HomeCommandEnum.SHOW_INDEX.getCommand();
            case "About":
                return HomeCommandEnum.SHOW_ABOUT.getCommand();
            case "Rules":
                return HomeCommandEnum.SHOW_RULES.getCommand();
            case "Stats":
                return HttpMethod.isGet(request.getMethod()) ?
                        HomeCommandEnum.SHOW_STATS.getCommand() :
                        getPOSTStatsCommand();
            case "StatsDetails":
                return HomeCommandEnum.SHOW_STATS_DETAILS.getCommand();
            default:
                return HomeCommandEnum.SHOW_INDEX.getCommand();
        }
    }

    private Command getPOSTStatsCommand() {
        String param = Optional
                .ofNullable(request.getParameter(COMMAND))
                .orElse("");

        if(param.equals(EDIT_CMD))
            return HomeCommandEnum.EDIT_STATS.getCommand();
        else if(param.equals(DELETE_CMD))
            return HomeCommandEnum.DELETE_STATS.getCommand();
        else return HomeCommandEnum.SHOW_STATS.getCommand();
    }
}
