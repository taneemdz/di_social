package com.rj.di_social.DAO;

import com.rj.di_social.model.Message;
import com.rj.di_social.model.SocialMediaSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 3:05 PM
 */
public class MessageRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message= new Message();
        message.setId(resultSet.getLong("Id"));
        message.setFiId(resultSet.getString("FiId"));
        message.setText(resultSet.getString("Text"));
        message.setFromId(resultSet.getString("FromId"));
        message.setReferId(resultSet.getLong("ReferId"));
        message.setCreatedTime(resultSet.getLong("CreatedTime"));
        message.setSocialMediaSource(SocialMediaSource.valueOf(resultSet.getString("SocialMediaSource")));
        message.setSentiment(resultSet.getFloat("Sentiment"));

        return message;
    }
}
