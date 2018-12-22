package com.javacourse.home.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowIndexCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ApplicationResources.getIndexPage();
    }
}
