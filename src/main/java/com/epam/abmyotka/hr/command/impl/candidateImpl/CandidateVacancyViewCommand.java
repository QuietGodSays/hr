package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.command.impl.adminImpl.AdminVacancyViewCommand;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "candidate_vacancy_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the vacancies, and brief information about the HR for each
 *     interview and puts in a request attribute to display on the page to view information about the interviews.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class CandidateVacancyViewCommand implements Command {
    private VacancyService vacancyService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'candidate_vacancy_view'
     *
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public CandidateVacancyViewCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Command command = new AdminVacancyViewCommand(vacancyService, employerService);
        Router router = command.execute(request);
        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VACANCY);
        return router;
    }
}
