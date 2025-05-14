package com.lms.utility;

import com.lms.model.Learner;

import java.util.ArrayList;
import java.util.*;

public class LearnerUtility {
    List<Learner> learnerList;
    {
        learnerList=new ArrayList<>();
        addSampleData();
    }
    public boolean removeId(int id){
        return learnerList.removeIf(learner -> learner.getId()==id);
    }
    public boolean addLearner(Learner learnerNew){
        for(Learner l:learnerList){
            if(l.getId()==learnerNew.getId()) return false;
        }
        learnerList.add(learnerNew);
        return true;
    }

    void  addSampleData(){
        Learner l1 = new Learner();
        l1.setId(1);
        l1.setName("Virat");
        l1.setEmail("virat@gmail.com");

        Learner l2 = new Learner();
        l2.setId(2);
        l2.setName("Steve");
        l2.setEmail("steve@gmail.com");

        Learner l3 = new Learner();
        l3.setId(3);
        l3.setName("Charlie");
        l3.setEmail("charlie@gmail.com");
        learnerList.add(l1);
        learnerList.add(l2);
        learnerList.add(l3);
    }

    public List<Learner> getSampleData() {
        return learnerList;
    }
}
