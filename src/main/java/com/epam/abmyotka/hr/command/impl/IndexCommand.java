package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PathConstant.PATH_PAGE_MAIN, Router.RouteType.FORWARD);
    }
}