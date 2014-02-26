package com.rj.di_social.common;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.rj.di_social.model.FiInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: rjain
 * Date: 01/02/14
 * Time: 12:55 PM
 */
public class FbHelper {
    FacebookClient facebookClient = new DefaultFacebookClient("CAAHK6dI2FwUBAERZB6R4YgkgapzMuILv22S1LVz9St8vHwJ8U3ZAFWthq63g309OiEtC1SXF4GfviWEFtrGGl7MQH156ZByn9UJzKsVXZC9qx6HfUiGAgbbijWdRec3g4iDlnxGTAqZBpckzyWqbU9XZBhhuCahM2ZBgBZABeJzSyZA2hKVjk745J");

    public void getFbInfo(FiInfo fiInfo) {
        Connection<Page> publicSearch = facebookClient.fetchConnection("search", Page.class, Parameter.with("q", fiInfo.getName()), Parameter.with("type", "page"));
        for(int i=0;i<publicSearch.getData().size();i++)
        {
            boolean found= false;
            String id=publicSearch.getData().get(i).getId();
            Page publicSearch2 = facebookClient.fetchObject("/"+id, Page.class);
            String phoneNumbers= publicSearch2.getPhone();
            if(phoneNumbers!=null && !phoneNumbers.trim().isEmpty()
                    && fiInfo.getPhoneNumber()!=null) {
                String[] phoneNumber= phoneNumbers.split("or");

                for (String aPhoneNumber : phoneNumber) {
                    aPhoneNumber= aPhoneNumber.trim();
                    aPhoneNumber = aPhoneNumber.replaceAll("[^\\d]", "");
                    String dbFiPhoneNumbers= fiInfo.getPhoneNumber();
                    String[] dbFiPhoneNumber= dbFiPhoneNumbers.split(";");
                    for (String aDbFiPhoneNumber : dbFiPhoneNumber) {
                        aDbFiPhoneNumber= aDbFiPhoneNumber.replaceAll("[^\\d]", "");
                        if(aPhoneNumber.contains(aDbFiPhoneNumber) ||
                                aDbFiPhoneNumber.contains(aPhoneNumber)) {
//                        System.out.println("Exact match with phone number");
                            System.out.println(fiInfo.getFiId()+":"+publicSearch2.getId());
                            found= true;
                            break;
                        }
                    }
                    if(found)
                        break;
                }
            }
            else if (publicSearch2.getLocation()!=null && publicSearch2.getLocation().getState()!=null
                    && publicSearch2.getLocation().getStreet().equals(fiInfo.getState())) {
//                System.out.println("Half match with state");
            }

            if(found)
                break;
        }
    }

    public List<Post> fetchFeeds(String fbId, long lastUpdateUnixDate) {
        List<Post> postList= new ArrayList<Post>();
        Connection<Post> posts= facebookClient.fetchConnection(fbId+"/feed", Post.class, Parameter.with("limit", 10000));
        postList.addAll(getAfterTimePosts(posts.getData(), lastUpdateUnixDate));
        while(posts.hasNext()) {
            posts= facebookClient.fetchConnectionPage(posts.getNextPageUrl(), Post.class);
            postList.addAll(getAfterTimePosts(posts.getData(), lastUpdateUnixDate));
        }
        return postList;
    }

    private List<Post> getAfterTimePosts(List<Post> postList, long lastUpdateUnixDate){
        if(lastUpdateUnixDate== 0){
            return postList;
        }
        Date lastUpdateDate= new Date(lastUpdateUnixDate*1000L);
        List<Post> validPostList= new ArrayList<Post>();
        for (Post post : postList) {
            if(post.getCreatedTime().compareTo(lastUpdateDate) < 0)
                break;
            else validPostList.add(post);
        }
        return validPostList;
    }
}
