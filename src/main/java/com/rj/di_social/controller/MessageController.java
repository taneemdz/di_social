package com.rj.di_social.controller;

import com.rj.di_social.model.Message;
import com.rj.di_social.resource.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 1:46 PM
 */

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageResource messageResource;

    @RequestMapping(value = "/{fiId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Message> getMessages(@PathVariable(value = "fiId") String fiId) {
        if(fiId==null || fiId.trim().isEmpty()){
            return null;
        }
        return messageResource.getMessage(fiId);
    }
}
