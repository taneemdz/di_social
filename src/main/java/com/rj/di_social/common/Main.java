package com.rj.di_social.common;

import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.rj.di_social.model.FiInfo;
import com.rj.di_social.resource.SentimentAnalysisImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * User: rjain
 * Date: 01/02/14
 * Time: 1:45 PM
 */
public class Main {
    public static void main(String[] args) {

        SentimentAnalysisImpl sentimentAnalysis= new SentimentAnalysisImpl();
        System.out.println(sentimentAnalysis.analyseSentiment("wow it works"));
//        Helper helper= new Helper();
//        helper.populateDBForFbId(helper.loadFbId());

//
//        FbHelper fbHelper= new FbHelper();
//        List<Post> temp= fbHelper.fetchFeeds("125611337226", 0);
//        for (Post aTemp : temp) {
//            System.out.println(aTemp.getMessage());
//            Post.Comments comments= aTemp.getComments();
//            if(comments!=null){
//                List<Comment> commentList= comments.getData();
//                for(Comment comment: commentList){
//                    System.out.println("Comment: "+comment.getMessage());
//                }
//
//            }
//        }
//        List<FiInfo> fiInfoList= helper.getFiInfoFromDB();
//
//        int i=0;
//        for (FiInfo aFiInfo : fiInfoList) {
//            if(i==3){
//                break;
//            }
////            i++;
//            fbHelper.getFbInfo(aFiInfo);
//        }

//        compare(getNewFBId(), getOldFBId());
//        findMissing(getNewFBId(), getOldFBId());
    }

    public static Map<String, String> getNewFBId(){
        HashMap<String, String> newFiFbIdMap= new HashMap<String, String>();

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/rjain/projects/work/DI_Social/NEW_Fi_FB.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values= sCurrentLine.split(":");
                String fiId= values[0];
                String fbId= values[1];
                newFiFbIdMap.put(fiId, fbId);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFiFbIdMap;
    }

    public static Map<String, String> getOldFBId(){
        HashMap<String, String> oldFiFbIdMap= new HashMap<String, String>();

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/rjain/projects/work/DI_Social/OLD_Fi_FB.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values= sCurrentLine.split(",");
                String fiId= values[0];
                String fbId= values[1];
                oldFiFbIdMap.put(fiId, fbId);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldFiFbIdMap;
    }

    public static void compare(Map<String,String> newMap, Map<String,String> oldMap) {
        Iterator it = newMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();

            String fiId= (String)pairs.getKey();
            if(oldMap.containsKey(fiId)){
                String temp= oldMap.get(fiId);
                if(!temp.equals((String)pairs.getValue())){
                    System.out.println("FB ID differs: "+ fiId);
                }
            }

            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public static void findMissing(Map<String,String> newMap, Map<String,String> oldMap) {
        Iterator it = oldMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();

            String fiId= (String)pairs.getKey();
            if(!newMap.containsKey(fiId)){
                System.out.println("Old fiId not in new: "+ fiId);
            }

            it.remove(); // avoids a ConcurrentModificationException
        }
    }


}
