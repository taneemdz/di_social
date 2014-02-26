package com.rj.di_social.resource;

import com.rj.di_social.DAO.FiInfoDAO;
import com.rj.di_social.model.FiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 10:44 AM
 */

@Service
public class FiInfoResourceImpl implements FiInfoResource{

    @Autowired
    FiInfoDAO fiInfoDAO;

    @Override
    public FiInfo getFiInfo(String fiId) {
        if(fiId== null || fiId.trim().isEmpty()){
            System.out.println("FiId invalid");
            return null;
        }
        return fiInfoDAO.getFiInfo(fiId);
    }

    @Override
    public void postFiInfo(FiInfo fiInfo) {

    }

    @Override
    public void updateLastFbUpdateTime(String fiId, long fbUpdateTime) {
        if(fiId== null || fiId.trim().isEmpty()){
            System.out.println("FiId invalid");
            return;
        }
        fiInfoDAO.updateLastFbUpdateTime(fiId, fbUpdateTime);
    }

    @Override
    public List<FiInfo> getAllFiInfo() {
        return fiInfoDAO.getAllFiInfo();
    }

    @Override
    public List<String> getAllFiId() {
        return fiInfoDAO.getAllFiId();
    }
}
