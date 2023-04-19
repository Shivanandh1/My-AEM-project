package com.myproject.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * SchedulerConfiguration
 */
@ObjectClassDefinition(
    name = "shiva-scheduler configuration",
    description = "Sling scheduler configuration"

)
public @interface SchedulerConfiguration {
    @AttributeDefinition(
        name ="scheduler name",
        description = "name of the scheduler",
        type = AttributeType.STRING )

public String schedulerName() default "custom sling schduler configuration";

@AttributeDefinition(
    name = "cron Expression",
    description = "cron expression used by the schedulers",
    type = AttributeType.STRING)
    public String cronExpression() default "0/20 * * * * ?";
    
}