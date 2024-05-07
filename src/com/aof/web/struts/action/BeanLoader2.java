package com.aof.web.struts.action;

import com.aof.service.UniversalManager;
import com.shcnc.struts.form.beanloader.BeanLoader;
import com.shcnc.struts.form.beanloader.UnableToLoadException;
import java.io.Serializable;

public class BeanLoader2 implements BeanLoader {
    private UniversalManager universalManager = null;

    public Object load(Class clazz, String idKey, Serializable idValue) throws UnableToLoadException {
        return this.universalManager.load(clazz, idValue);
    }

    public void setUniversalManager(UniversalManager universalManager) {
        this.universalManager = universalManager;
    }
}
