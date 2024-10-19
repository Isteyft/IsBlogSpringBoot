package com.isteyft.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    @Autowired
    private MailUtils mailUtils;

    @Value("${spring.mail.username")
    private String mailto;

    @Scheduled(cron="0 0 12 1 * ?")
    public void sendEmail() {
        StringBuffer content = new StringBuffer();
        content.append("服务器运行正常").append("\n");
        mailUtils.sendSimpleEmail(mailto,"个人博客系统流量统计情况",content.toString());
    }
}
