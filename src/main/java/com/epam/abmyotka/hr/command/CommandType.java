package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.command.impl.*;
import com.epam.abmyotka.hr.command.impl.accountImpl.AccountDeleteCommand;
import com.epam.abmyotka.hr.command.impl.accountImpl.AccountEditCommand;
import com.epam.abmyotka.hr.command.impl.adminImpl.*;
import com.epam.abmyotka.hr.command.impl.candidateImpl.*;
import com.epam.abmyotka.hr.command.impl.employerImpl.*;
import com.epam.abmyotka.hr.service.*;

public enum CommandType {
    INDEX(new IndexCommand()),
    REGISTER_RELOAD(new RegisterReloadCommand()),
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new AccountEditCommand(new AccountService())),
    DELETE_ACCOUNT(new AccountDeleteCommand(new AccountService())),
    ADMIN_CANDIDATE_VIEW(new AdminCandidateViewCommand(new CandidateService())),
    ADMIN_CANDIDATE_DELETE(new AdminCandidateDeleteCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT(new AdminCandidateEditCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT_SAVE(new AdminCandidateEditSaveCommand(new CandidateService())),
    ADMIN_EMPLOYER_VIEW(new AdminEmployerViewCommand(new EmployerService())),
    ADMIN_EMPLOYER_DELETE(new AdminEmployerDeleteCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT(new AdminEmployerEditCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT_SAVE(new AdminEmployerEditSaveCommand(new EmployerService())),
    ADMIN_VACANCY_VIEW(new AdminVacancyViewCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_DELETE(new AdminVacancyDeleteCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_EDIT(new AdminVacancyEditCommand(new VacancyService())),
    ADMIN_VACANCY_EDIT_SAVE(new AdminVacancyEditSaveCommand(new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_VIEW(new AdminInterviewViewCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_DELETE(new AdminInterviewDeleteCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    ADMIN_INTERVIEW_EDIT(new AdminInterviewEditCommand(new InterviewService())),
    ADMIN_INTERVIEW_EDIT_SAVE(new AdminInterviewEditSaveCommand(new InterviewService(), new CandidateService(),
            new VacancyService(), new EmployerService())),
    CANDIDATE_ADD(new CandidateAddCommand(new AccountService(), new CandidateService())),
    CANDIDATE_ADD_SAVE(new CandidateAddSaveCommand(new CandidateService())),
    CANDIDATE_VIEW_EDIT(new CandidateViewEditCommand(new AccountService(), new CandidateService())),
    CANDIDATE_VIEW_EDIT_SAVE(new CandidateViewEditSaveCommand(new CandidateService())),
    CANDIDATE_VACANCY_VIEW(new CandidateVacancyViewCommand(new VacancyService(), new EmployerService())),
    CANDIDATE_VACANCY_APPLY(new CandidateVacancyApplyCommand(new AccountService(), new CandidateService(),
            new VacancyService(), new EmployerService(), new InterviewService())),
    CANDIDATE_VACANCY_SEARCH(new CandidateVacancySearchCommand(new VacancyService(),new EmployerService())),
    EMPLOYER_ADD_INFORMATION(new EmployerAddInformationCommand(new AccountService(), new EmployerService())),
    EMPLOYER_ADD_INFORMATION_SAVE(new EmployerAddInformationSaveCommand(new EmployerService())),
    EMPLOYER_ADD_VACANCY(new EmployerAddVacancyCommand(new AccountService(), new EmployerService(),
            new VacancyService())),
    EMPLOYER_ADD_VACANCY_SAVE(new EmployerAddVacancySaveCommand(new EmployerService(), new VacancyService())),
    EMPLOYER_VIEW_EDIT_INFORMATION(new EmployerViewEditInformationCommand(new AccountService(), new EmployerService())),
    EMPLOYER_VIEW_EDIT_INFORMATION_SAVE(new EmployerViewEditInformationSaveCommand(new EmployerService())),
    EMPLOYER_VIEW_VACANCY(new EmployerViewVacancyCommand(new AccountService(), new EmployerService(),
            new VacancyService())),
    EMPLOYER_VACANCY_DELETE(new EmployerVacancyDeleteCommand(new AccountService(), new EmployerService(),
            new VacancyService())),
    EMPLOYER_VACANCY_EDIT(new EmployerVacancyEditCommand(new VacancyService())),
    EMPLOYER_VACANCY_EDIT_SAVE(new EmployerVacancyEditSaveCommand(new AccountService(), new EmployerService(),
            new VacancyService())),
    EMPLOYER_INTERVIEW(new EmployerInterviewViewCommand(new AccountService(), new EmployerService(),
            new InterviewService())),
    EMPLOYER_INTERVIEW_DELETE(new EmployerInterviewDeleteCommand(new AccountService(), new EmployerService(),
            new InterviewService())),
    EMPLOYER_INTERVIEW_EDIT(new EmployerInterviewEditCommand(new InterviewService())),
    EMPLOYER_INTERVIEW_EDIT_SAVE(new EmployerInterviewEditSaveCommand(new AccountService(), new EmployerService(),
            new InterviewService())),
    EMPLOYER_INTERVIEW_VIEW_CANDIDATE(new EmployerInterviewViewCandidateCommand(new CandidateService())),
    EMPLOYER_INTERVIEW_VIEW_CANDIDATE_SEND_EMAIL(new EmployerInterviewViewCandidateSendEmailCommand(
            new CandidateService())),
    EMPLOYER_INTERVIEW_VIEW_CANDIDATE_SEND_EMAIL_SUBMIT(new EmployerInterviewViewCandidateSendEmailSubmitCommand(
            new AccountService(), new CandidateService(), new EmployerService(), new InterviewService())),
    EMPLOYER_INTERVIEW_VIEW_VACANCY(new EmployerInterviewViewVacancyCommand(new VacancyService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
