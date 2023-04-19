package com.myproject.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfiguration.class)
public class SchedulerMaker implements Runnable{
private static final Logger LOG=LoggerFactory.getLogger(SchedulerMaker.class);
 private int schedulerId;

 @Reference
 private Scheduler scheduler;

 @Activate
 protected void Activate(SchedulerConfiguration config){
    schedulerId=config.schedulerName().hashCode(); 
    addScheduler(config);
 }
 @Deactivate
 protected void Deactivate(SchedulerConfiguration config){
    removeScheduler(config);
    
 }

    private void removeScheduler(SchedulerConfiguration config) {
        scheduler.unschedule(String.valueOf(schedulerId));
}
    private void addScheduler(SchedulerConfiguration config) {
        ScheduleOptions scheduleOptions=scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduler.schedule(this, scheduleOptions);
}
    @Override
    public void run() {
        LOG.info("run method.....");;
    }
    
}
