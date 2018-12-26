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
                return HomeCommandEnum.INDEX.getCommand();
            case "About":
                return HomeCommandEnum.ABOUT.getCommand();
            case "Rules":
                return HomeCommandEnum.RULES.getCommand();
            case "Stats":
                return HttpMethod.isGet(request.getMethod()) ?
                        HomeCommandEnum.STATS.getCommand() :
                        getPOSTStatsCommand();
            case "StatsDetails":
                return HomeCommandEnum.STATS_DETAILS.getCommand();
            default:
                return HomeCommandEnum.INDEX.getCommand();
        }
    }

    private Command getPOSTStatsCommand() {
        String param = Optional
                .ofNullable(request.getParameter(COMMAND))
                .orElse("");

        if(param.equals(EDIT_CMD))
            return HomeCommandEnum.STATS_EDIT.getCommand();
        else if(param.equals(DELETE_CMD))
            return HomeCommandEnum.STATS_DELETE.getCommand();
        else return HomeCommandEnum.STATS.getCommand();
    }
}
