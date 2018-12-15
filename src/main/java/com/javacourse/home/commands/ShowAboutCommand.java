package com.javacourse.home.commands;

import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowAboutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/about.jsp";
    }
}
