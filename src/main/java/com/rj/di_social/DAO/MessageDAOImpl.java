package com.rj.di_social.DAO;

import com.rj.di_social.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 8:17 AM
 */

@Repository
public class MessageDAOImpl implements MessageDAO{

    @Autowired
    DataSource dataSource;

    @Override
    public List<Message> getMessages(String fiId) {
        String sql = "SELECT * FROM Message WHERE FiId = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            return jdbcTemplate.query(sql, new Object[]{fiId}, new MessageRowMapper());

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public long postMessage(final Message message) {
        final String sql = "INSERT INTO Message (FiId, Text, FromId, ReferId, CreatedTime, SocialMediaSource) VALUES(?,?,?,?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"Id"});
                        ps.setString(1, message.getFiId());
                        ps.setString(2, message.getText());
                        ps.setString(3, message.getFromId());
                        ps.setLong(4, message.getReferId());
                        ps.setLong(5, message.getCreatedTime());
                        ps.setString(6, message.getSocialMediaSource().getValue());
                        return ps;
                    }
                },
                keyHolder);
             return keyHolder.getKey().longValue();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return 0;
        }
    }

    @Override
    public void updateSentiment(Message message) {
        String sql = "UPDATE Message SET Sentiment = ? where Id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.update( sql, new Object[]{message.getSentiment(), message.getId()} );
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Map<String, Long> getMessageCount() {
        String sql = "SELECT FiId,count(*) as messageCount FROM Message where ReferId!=0 and Sentiment!=0 group by FiId";
        HashMap<String,Long> messageCountMap= new HashMap<String, Long>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<Map<String, Object>> messageCountList= jdbcTemplate.queryForList(sql);
            for(Map<String, Object> messageCount: messageCountList) {
                String fiId= (String)messageCount.get("FiId");
                Long messageCountValue= (Long)messageCount.get("messageCount");
                if(fiId!= null) {
                    messageCountMap.put(fiId, messageCountValue);
                }
            }
            return messageCountMap;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public Map<String, Long> getPositiveMessageCount() {
        String sql = "SELECT FiId,count(*) as messageCount FROM Message where ReferId!=0 and Sentiment>0 group by FiId";
        HashMap<String,Long> messageCountMap= new HashMap<String, Long>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<Map<String, Object>> messageCountList= jdbcTemplate.queryForList(sql);
            for(Map<String, Object> messageCount: messageCountList) {
                String fiId= (String)messageCount.get("FiId");
                Long messageCountValue= (Long)messageCount.get("messageCount");
                if(fiId!= null) {
                    messageCountMap.put(fiId, messageCountValue);
                }
            }
            return messageCountMap;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public Map<String, Long> getNegativeMessageCount() {
        String sql = "SELECT FiId,count(*) as messageCount FROM Message where ReferId!=0 and Sentiment<0 group by FiId";
        HashMap<String,Long> messageCountMap= new HashMap<String, Long>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<Map<String, Object>> messageCountList= jdbcTemplate.queryForList(sql);
            for(Map<String, Object> messageCount: messageCountList) {
                String fiId= (String)messageCount.get("FiId");
                Long messageCountValue= (Long)messageCount.get("messageCount");
                if(fiId!= null) {
                    messageCountMap.put(fiId, messageCountValue);
                }
            }
            return messageCountMap;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
