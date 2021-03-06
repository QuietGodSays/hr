package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Interview;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.InterviewService;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "employer_interview_edit_save" command.
 *
 * <p>
 *     Accepts all information about the interview to update by HR, having previously passed validation.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewEditSaveCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;
    private InterviewService interviewService;

    /**
     *  Constructs and initialize commands type of 'employer_interview_edit_save'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     */
    public EmployerInterviewEditSaveCommand(AccountService accountService, EmployerService employerService,
                                            InterviewService interviewService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_INTERVIEW_ID);
        String preResult = request.getParameter(ParameterConstant.PARAM_PRE_RESULT);
        String finalResult = request.getParameter(ParameterConstant.PARAM_FINAL_RESULT);

        Interview interview = new Interview(Integer.parseInt(stringInterviewId), preResult, finalResult);
        if (interviewService.update(interview)) {
            Command command = new EmployerInterviewViewCommand(accountService, employerService, interviewService);
            command.execute(request);
        } else {
            router. setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
