package com.javacourse.test.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.utils.BeanValidatorConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

public class EditHeaderCommand implements Command {

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}

