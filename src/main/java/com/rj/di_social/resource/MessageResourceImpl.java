package com.rj.di_social.resource;

import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.rj.di_social.DAO.MessageDAO;
import com.rj.di_social.common.FbHelper;
import com.rj.di_social.model.FiInfo;
import com.rj.di_social.model.Message;
import com.rj.di_social.model.SocialMediaSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 3:41 PM
 */

@Service
public class MessageResourceImpl implements MessageResource {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    SentimentAnalysis sentimentAnalysis;

    @Autowired
    FiInfoResource fiInfoResource;

    @Override
    public List<Message> getMessage(String fiId) {
        if(fiId== null || fiId.trim().isEmpty()){
            System.out.println("FiId invalid");
            return null;
        }

        return messageDAO.getMessages(fiId);
    }


    @Override
    public void fetchAndPopulateMessages(String fiId) {

    }

    @Override
    public void fetchAndPopulateMessagesFromFb(String fiId) {
        if(fiId== null || fiId.trim().isEmpty()){
            System.out.println("FiId invalid");
            return;
        }
        FiInfo fiInfo= fiInfoResource.getFiInfo(fiId);
        if(fiInfo== null || fiInfo.getFbId()== null)
            return;

        try {
            FbHelper fbHelper= new FbHelper();
            List<Post> posts= fbHelper.fetchFeeds(fiInfo.getFbId(), fiInfo.getLastFbUpdateTime());
            for (Post post : posts) {
                Message messagePost= getMessageFromPost(post, fiId);
                long messageId= 0;
                if(messagePost!= null) {
                    messageId= messageDAO.postMessage(messagePost);
                }

                Post.Comments comments= post.getComments();
                if(comments!=null){
                    List<Comment> commentList= comments.getData();
                    for(Comment comment: commentList){
                        Message messageComment= getMessageFromComment(comment, fiId, messageId);
                        if(messageComment!= null){
                            messageDAO.postMessage(messageComment);
                        }
                    }
                }
            }
            fiInfoResource.updateLastFbUpdateTime(fiId, System.currentTimeMillis()/1000L);
        } catch (Exception ex) {
            System.out.println("Not able to fetch and populate facebook feeds for: "+ fiId);
        }
    }

    @Override
    public void fetchAndUpdateMessageSentiment(String fiId) {
        if(fiId== null || fiId.trim().isEmpty()){
            System.out.println("FiId invalid");
            return;
        }
        List<Message> messageList= messageDAO.getMessages(fiId);
        for(Message message:messageList) {
            if(message.getReferId()!=0) {
                message.setSentiment(sentimentAnalysis.analyseSentiment(message.getText()));
                messageDAO.updateSentiment(message);
            }
        }

    }

    private Message getMessageFromPost(Post post, String fiId) {
        if(post.getMessage()== null)
            return null;

        Message message= new Message();
        message.setFiId(fiId);
        message.setText(post.getMessage());
        if(post.getFrom()!=null)
            message.setFromId(post.getFrom().getId());
        message.setReferId(0);
        message.setCreatedTime(post.getCreatedTime().getTime() / 1000L);
        message.setSocialMediaSource(SocialMediaSource.FACEBOOK);

        return message;
    }

    private Message getMessageFromComment(Comment comment, String fiId, long referId) {
        if(comment.getMessage()== null)
            return null;

        Message message= new Message();
        message.setFiId(fiId);
        message.setText(comment.getMessage());
        if(comment.getFrom()!=null)
            message.setFromId(comment.getFrom().getId());
        message.setReferId(referId);
        message.setCreatedTime(comment.getCreatedTime().getTime() / 1000L);
        message.setSocialMediaSource(SocialMediaSource.FACEBOOK);

        return message;
    }


}
