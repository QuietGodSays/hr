package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "index" command.
 *
 * It is intended to update the "Authorization" page without any logic
 *
 * @see Command#execute(HttpServletRequest)
 */
public class IndexCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PathConstant.PATH_PAGE_MAIN, Router.RouteType.FORWARD);
    }
}
