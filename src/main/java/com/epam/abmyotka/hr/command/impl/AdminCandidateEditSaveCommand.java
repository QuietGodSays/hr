package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.abmyotka.hr.validator.CandidateEmployerValidator.*;

public class AdminCandidateEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminCandidateEditSaveCommand.class);
    private CandidateService service;

    public AdminCandidateEditSaveCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_CANDIDATE_ID);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String age = request.getParameter(ParameterConstant.PARAM_AGE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String citizenship = request.getParameter(ParameterConstant.PARAM_CITIZENSHIP);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String education = request.getParameter(ParameterConstant.PARAM_EDUCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String skill = request.getParameter(ParameterConstant.PARAM_SKILL);

        if(checkID(stringCandidateId) && checkNames(surname) && checkNames(name) && checkLastname(lastname) &&
                checkAge(age) && checkEmail(email) && checkCitizenship(citizenship) && checkPhone(phone) &&
                checkExperience(experience)) {
            Candidate candidate = new Candidate(Integer.parseInt(stringCandidateId), surname, name, lastname,
                    Integer.parseInt(age), email, address, citizenship, phone, post, education,
                    Integer.parseInt(experience), english, skill);
            if (service.update(candidate)) {
                List<Candidate> candidates = service.takeAll();
                request.setAttribute("candidateList", candidates);
            } else {
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        } else {
            request.setAttribute("errorMessage", MessageConstant.INCORRECT_DATA);
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE_EDIT);
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
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE);
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        }

        return router;
    }
}
