package com.epam.abmyotka.hr.command.impl.adminImpl;

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
 * Implementation of the "admin_candidate_edit" command.
 *
 * <p>
 *     According to the candidate id from the request is searched in the database and the formation of the object,
 *     which is stored in an attribute request to display on a new page of editing the information of the candidate.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminCandidateEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminCandidateEditCommand.class);

    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'admin_candidate_edit'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
    public AdminCandidateEditCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE_EDIT, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_ADMIN_CANDIDATE_EDIT);
        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidate id to integer! Detail: " +
                    e.getMessage());
        }

        Candidate candidate = service.findById(candidateId);
        if (candidate != null) {
            request.setAttribute("candidate", candidate);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
