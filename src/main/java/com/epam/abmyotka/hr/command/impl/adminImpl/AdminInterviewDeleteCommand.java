package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.InterviewService;
import com.epam.abmyotka.hr.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "admin_interview_delete" command.
 *
 * <p>
 *     Removes the interview by it's id by Administrator.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminInterviewDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminInterviewDeleteCommand.class);

    private InterviewService interviewService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'admin_interview_delete'
     *
     * @param interviewService - instance of the service type of "Interview" to access the database table "interview"
     * @param candidateService - instance of the service type of "Candidate" to access the database table "candidate"
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminInterviewDeleteCommand(InterviewService interviewService, CandidateService candidateService,
                                       VacancyService vacancyService, EmployerService employerService) {
        this.interviewService = interviewService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_INTERVIEW, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_ADMIN_INTERVIEW_DELETE);

        int interviewId = 0;
        try {
            interviewId = Integer.parseInt(stringInterviewId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value interviewId to integer! Detail: " +
                    e.getMessage());
        }

        if (!interviewService.delete(interviewId)) {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        Command command = new AdminInterviewViewCommand(interviewService, candidateService, vacancyService,
                employerService);
        command.execute(request);

        return router;
    }
}
