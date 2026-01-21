package com.skr1l.lifecycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LifecycleBean {

    private static final Log log = LogFactory.getLog(LifecycleBean.class);

    public void init() {
        log.info("Initializing LifecycleBean");
    }
    public void destroy() {
        log.info("Destroying LifecycleBean");
    }
}
