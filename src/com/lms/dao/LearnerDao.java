package com.lms.dao;

import com.lms.exception.*;
import com.lms.model.Learner;
import java.util.List;

public interface LearnerDao {
    /* crud operation*/
    List<Learner> getAll();
    Learner getById(int id) throws InvalidIdException;
    void deleteById(int id) throws InvalidIdException;
    Learner update(int id,Learner learner) throws InvalidIdException,InvalidInputException;
    void insert(Learner learner) throws InvalidInputException;
}
