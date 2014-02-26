package com.rj.di_social.DAO;

import com.rj.di_social.model.FiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 10:44 AM
 */

@Repository
public class FiInfoDAOImpl implements FiInfoDAO{

    @Autowired
    DataSource dataSource;

    @Override
    public FiInfo getFiInfo(String fiId) {
        String sql = "SELECT * FROM FiInfo WHERE FiId = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
        FiInfo fiInfo = (FiInfo)jdbcTemplate.queryForObject(sql, new Object[]{fiId},
                new BeanPropertyRowMapper(FiInfo.class));
            return fiInfo;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public void postFiInfo(FiInfo fiInfo) {

    }

    @Override
    public void updateLastFbUpdateTime(String fiId, long fbUpdateTime) {
        String sql = "UPDATE FiInfo SET LastFbUpdateTime = ? where FiId = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.update( sql, new Object[]{fbUpdateTime, fiId} );
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<FiInfo> getAllFiInfo() {
        String sql = "SELECT * FROM FiInfo";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<FiInfo> fiInfoList= jdbcTemplate.query(sql, new BeanPropertyRowMapper(FiInfo.class));
            return fiInfoList;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public List<String> getAllFiId() {
        String sql = "SELECT FiId FROM FiInfo";
        List<String> fiIdList= new ArrayList<String>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<Map<String, Object>> fiIdMapList= jdbcTemplate.queryForList(sql);
            for(Map<String, Object> fiIdMap: fiIdMapList) {
                String fiId= (String)fiIdMap.get("FiId");
                if(fiId!= null) {
                    fiIdList.add(fiId);
                }
            }
            return fiIdList;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
