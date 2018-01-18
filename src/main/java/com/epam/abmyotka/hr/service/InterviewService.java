package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.dao.InterviewDAO;
import com.epam.abmyotka.hr.entity.Interview;

import java.sql.Connection;
import java.util.List;

public class InterviewService {

    public List<Interview> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        List<Interview> interviews = interviewDAO.findAll();
        pool.putConnection(connection);
        return interviews;
    }

    public Interview findById(int interviewId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        Interview interview = interviewDAO.findById(interviewId);
        pool.putConnection(connection);
        return interview;
    }

    public boolean delete(int interviewId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        int countRowsAffected = interviewDAO.delete(interviewId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Interview interview) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        InterviewDAO interviewDAO = new InterviewDAO(connection);
        int countRowsAffected = interviewDAO.update(interview);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }
}