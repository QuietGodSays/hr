package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.AccountAttachmentConstant;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;
import com.epam.abmyotka.hr.validator.VerifyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "register" command.
 *
 * <p>
 *     Accepts from the page login, password, re-password, role and value of the ReCaptcha for registration of a new
 *     user of the system. The validation of the above fields for validity occurs, in case of failure, the user
 *     receives a corresponding message on the page. Also, registration will fail for the user if the login is
 *     already use.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class RegisterCommand implements Command {
    private AccountService service;


    /**
     *  Constructs and initialize commands type of 'register'.
     *
     * @param service - instance of the service type of "Account" to access the database table "account".
     */
    public RegisterCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_REGISTER, Router.RouteType.FORWARD);
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String repeatPassword = request.getParameter(ParameterConstant.PARAM_REPEAT_PASSWORD);
        String attachment = request.getParameter(ParameterConstant.PARAM_ATTACHMENT);
        String gRecaptchaResponse = request.getParameter(ParameterConstant.PARAM_RECAPTCHA);

        HttpSession session = request.getSession(true);

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(password) &&
                AccountValidator.checkAttachment(attachment) && password.equals(repeatPassword)) {
            if (VerifyValidator.verify(gRecaptchaResponse)) {
                Account user = new Account(login, password, attachment);
                if (!service.checkCoincidenceByLogin(login)) {
                    if (service.add(user)) {
                        session.setAttribute("role", user);
                        if (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT)) {
                            router.setRoute(Router.RouteType.REDIRECT);
                            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                        } else {
                            router.setRoute(Router.RouteType.REDIRECT);
                            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                        }
                    } else {
                        Object language = request.getSession(true).getAttribute("language");
                        String message = MessageManager.getMessage(language.toString(),
                                MessageConstant.ERROR_ON_WEBSITE);
                        request.setAttribute("errorMessage", message);
                    }
                } else {
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(), MessageConstant.USED_LOGIN_MESSAGE);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.INVALID_RECAPTCHA);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(),
                    MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
