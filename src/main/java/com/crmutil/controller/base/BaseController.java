package com.crmutil.controller.base;

import com.crmutil.util.LoggerUtil;
import com.crmutil.util.Util;

/**
 * Created by My on 2017/5/7.
 */
public class BaseController {
    protected LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    public void logBefore (String interfaceName) {
        logger.info(interfaceName + " -------- start" );
    }

    public void logAfter () {
        logger.info("log -------- end");
    }

    public String getUuid() {
        return Util.getUuid();
    }
}
