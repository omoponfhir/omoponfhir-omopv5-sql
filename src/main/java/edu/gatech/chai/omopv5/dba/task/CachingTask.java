package edu.gatech.chai.omopv5.dba.task;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.scheduling.annotation.Scheduled;
 import org.springframework.stereotype.Component;

 import edu.gatech.chai.omopv5.dba.service.FCacheService;

 /*
  * Created Date: Monday, February 27th 2023, 5:44:38 pm
  * Author: Myung Choi
  * 
  * Copyright (c) 2023 GTRI - Health Emerging and Advanced Technologies (HEAT)
  */

 @Component
 public class CachingTask {
 	private static final Logger logger = LoggerFactory.getLogger(CachingTask.class);

     @Autowired
 	private FCacheService fCacheService;

     @Scheduled(fixedDelay = 86400000)
 	public void cachingTask() {
         fCacheService.updateCache();
     }
 }