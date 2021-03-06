package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of the "admin_vacancy_edit" command.
 *
 * <p>
 *     According to the vacancy id from the request is searched in the database and the formation of the object,
 *     which is stored in an attribute request to display on a new page of editing the information of the vacancy.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminVacancyEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminVacancyEditCommand.class);

    private VacancyService service;

    /**
     *  Constructs and initialize commands type of 'admin_vacancy_edit'
     *
     * @param service - instance of the service type of "Vacancy" to access the database table "vacancy"
     */
    public AdminVacancyEditCommand(VacancyService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_VACANCY_EDIT, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_ADMIN_VACANCY_EDIT);
        int vacancyId = 0;
        try {
            vacancyId = Integer.parseInt(stringVacancyId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                    e.getMessage());
        }

        Vacancy vacancy = service.findById(vacancyId);
        if (vacancy != null) {
            request.setAttribute("vacancy", vacancy);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_VACANCY);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
