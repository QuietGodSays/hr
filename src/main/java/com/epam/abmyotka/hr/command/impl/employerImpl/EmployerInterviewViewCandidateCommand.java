package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "employer_interview_view_candidate" command.
 *
 * <p>
 *     Retrieves from request candidate id, finds candidate in the database, writes it into the request attribute and
 *     throws to the page view information about the candidate from the view of the interview page.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerInterviewViewCandidateCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerInterviewViewCandidateCommand.class);

    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'employer_interview_view_candidate'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public EmployerInterviewViewCandidateCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_INTERVIEW_VIEW_CANDIDATE, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_INTERVIEW_VIEW_CANDIDATE);
        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidateId to integer! Detail: " +
                    e.getMessage());
        }

        Candidate candidate = service.findById(candidateId);
        if (candidate != null) {
            request.setAttribute("candidate", candidate);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
