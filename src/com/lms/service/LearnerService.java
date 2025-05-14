package com.lms.service;

import com.lms.dao.LearnerDao;
import com.lms.dao.LearnerDaoImpl;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

import java.util.List;

public class LearnerService {
    LearnerDao dao=new LearnerDaoImpl();
    public List<Learner> getAllLearners(){
        return dao.getAll();
    }
    public Learner getById(int id) throws InvalidIdException {
        return dao.getById(id);
    }
    public void deleteById(int id) throws InvalidIdException {
        dao.deleteById(id);
    }
    public Learner update(int id,Learner updatedLearner) throws InvalidInputException, InvalidIdException {
        if (updatedLearner.getName() == null || updatedLearner.getEmail() == null) {
            throw new InvalidInputException("Learner details cannot be null");
        }
        return dao.update(id,updatedLearner);
    }


    public void insert(Learner learner)throws InvalidInputException{
        if (learner.getName() == null || learner.getEmail() == null || learner.getName()=="null") {
            throw new InvalidInputException("Learner details cannot be null");
        }
        if(learner.getName().trim() == "" ||learner.getEmail().trim() == "") throw new InvalidInputException("Learner details cannot be empty");
        learner.setId((int)(Math.random()*1000));
        dao.insert(learner);
    }
}
