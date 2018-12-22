package com.javacourse.shared;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic abstract class for implementing command classes
 * as part of Front Controller J2EE pattern
 */
public interface Command {
    String execute(HttpServletRequest request);
}
