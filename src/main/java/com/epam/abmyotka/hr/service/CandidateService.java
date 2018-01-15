package com.epam.abmyotka.hr.service;

import com.epam.abmyotka.hr.dao.CandidateDAO;
import com.epam.abmyotka.hr.dao.DBPool;
import com.epam.abmyotka.hr.entity.Candidate;

import java.sql.Connection;
import java.util.List;

public class CandidateService {

    public List<Candidate> takeAll() {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        List<Candidate> candidates = candidateDAO.findAll();
        pool.putConnection(connection);
        return candidates;
    }

    public Candidate findById(int candidateId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        Candidate candidate = candidateDAO.findById(candidateId);
        pool.putConnection(connection);
        return candidate;
    }

    public boolean delete(int candidateId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        int countRowsAffected = candidateDAO.delete(candidateId);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean update(Candidate candidate) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        int countRowsAffected = candidateDAO.update(candidate);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean add(Candidate candidate) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        int countRowsAffected = candidateDAO.add(candidate);
        pool.putConnection(connection);
        return countRowsAffected != 0;
    }

    public boolean isExistCandidateByAccountId(int accountId) {
        DBPool pool = DBPool.getInstance();
        Connection connection = pool.getConnection();
        CandidateDAO candidateDAO = new CandidateDAO(connection);
        Candidate candidate = candidateDAO.findByAccountId(accountId);
        pool.putConnection(connection);
        return candidate != null;
    }
}
