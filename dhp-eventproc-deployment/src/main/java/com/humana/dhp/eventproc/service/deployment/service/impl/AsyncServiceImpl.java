package com.humana.dhp.eventproc.service.deployment.service.impl;

import com.humana.dhp.eventproc.service.deployment.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

  @Override
  @Async("flowDeploymentAsyncExecutor")
  public void run(Runnable runnable) {
    log.info("Runnable thread: " + Thread.currentThread().getName());
    runnable.run();
  }
}
