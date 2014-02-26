package com.rj.di_social.controller;

import com.rj.di_social.model.FiInfo;
import com.rj.di_social.resource.FiInfoResource;
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
 * Time: 1:42 PM
 */

@Controller
@RequestMapping("/fiInfo")
public class FiInfoController {

    @Autowired
    FiInfoResource fiInfoResource;

    @RequestMapping(value = "/{fiId}", method = RequestMethod.GET)
    public @ResponseBody
    FiInfo getFiInfo(@PathVariable(value = "fiId") String fiId) {
        if(fiId==null || fiId.trim().isEmpty()){
            return null;
        }
        return fiInfoResource.getFiInfo(fiId);
    }


    @RequestMapping(value = "/allFiInfo", method = RequestMethod.GET)
    public @ResponseBody
    List<FiInfo> getAllFiInfo(){
        return fiInfoResource.getAllFiInfo();
    }
}
