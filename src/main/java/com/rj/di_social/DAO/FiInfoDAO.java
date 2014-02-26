package com.rj.di_social.DAO;

import com.rj.di_social.model.FiInfo;

import java.util.List;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 10:56 AM
 */
public interface FiInfoDAO {
    public FiInfo getFiInfo(String fiId);
    public void postFiInfo(FiInfo fiInfo);
    public void updateLastFbUpdateTime(String fiId, long fbUpdateTime);
    public List<FiInfo> getAllFiInfo();
    public List<String> getAllFiId();
}
