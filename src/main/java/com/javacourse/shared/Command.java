package com.javacourse.shared;

import javax.servlet.http.HttpServletRequest;

/**
 * Basic abstract class for implementing command classes
 * as part of Front Controller J2EE pattern
 */
public interface Command {
    String execute(HttpServletRequest request);
}
