package com.humana.dhp.eventproc.service.deployment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  @Bean(name = "flowDeploymentAsyncExecutor")
  public AsyncTaskExecutor getFlowDeploymentAsyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(20);
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setQueueCapacity(100);
    taskExecutor.setThreadNamePrefix("FlowDeploymentAsyncThread-");
    taskExecutor.initialize();
    return taskExecutor;
  }

}
