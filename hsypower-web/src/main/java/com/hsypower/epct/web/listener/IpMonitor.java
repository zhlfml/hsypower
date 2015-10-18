package com.hsypower.epct.web.listener;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hsypower.epct.utils.FileUtil;
import com.hsypower.epct.utils.IpUtil;
import com.hsypower.epct.utils.MailUtil;
import com.hsypower.epct.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpMonitor implements ServletContextListener {

    String localIp = "";
    String ipFile = "ip.out";
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        int interval =  PropertyUtil.getInteger("mail.interval");
        final String receiver = PropertyUtil.getString("mail.receiver");
        final String content = PropertyUtil.getString("mail.content");
        try {
            localIp = FileUtil.read(ipFile).trim();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                String latest = IpUtil.getLocalIp2();
                if (!latest.equals(localIp)) {
                    localIp = latest;
                    try {
                        FileUtil.write(ipFile, localIp);
                    } catch (IOException e) {
                        logger.info(e.getMessage());
                    }
                    MailUtil.send(receiver, localIp, content);
                }
            }
        }, 10 * 1000L, interval * 60 * 1000L);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
