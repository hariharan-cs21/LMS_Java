package com.lms.dao;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;
import com.lms.utility.LearnerUtility;

import java.util.List;

public class LearnerDaoImpl implements LearnerDao{
    LearnerUtility learnerUtility=new LearnerUtility();

    @Override
    public List<Learner> getAll() {
        return learnerUtility.getSampleData();
    }

    @Override
    public Learner getById(int id) throws InvalidIdException {
        List<Learner> list = learnerUtility.getSampleData()
                .stream()
                .filter(l -> l.getId() == id)
                .toList();
        if(list.isEmpty()) throw  new InvalidIdException("Invalid id: "+id);
        return list.get(0);
    }

    @Override
    public void deleteById(int id) throws InvalidIdException {
        boolean removed=learnerUtility.removeId(id);
        if(!removed) throw new InvalidIdException("Id not found");
    }

    @Override
    public Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException {
        List<Learner>learnerList=getAll();
        for(Learner l:learnerList){
            if(l.getId()==id){
                l.setName(learner.getName());
                l.setEmail(learner.getEmail());
                return l;
            }
        }
        throw new InvalidIdException("Invalid Id");
    }

    @Override
    public void insert(Learner learner) throws InvalidInputException {
        boolean added = learnerUtility.addLearner(learner);
        if (!added) {
            throw new InvalidInputException("Learner with this ID already exists.");
        }
    }

    public static void main(String[] args) {
        LearnerDaoImpl dao=new LearnerDaoImpl();
        for(Learner lu:dao.getAll()){
            System.out.println(lu);
        }
        try{
            int id=2;
            Learner l1=dao.getById(id);
            System.out.println(l1);
        }
        catch (InvalidIdException e){
            System.out.println(e.getMessage());
        }
        try{
            int id=5;
            Learner l2=dao.getById(id);
            System.out.println(l2);
        }
        catch (InvalidIdException e){
            System.out.println(e.getMessage());
        }
    }
}
