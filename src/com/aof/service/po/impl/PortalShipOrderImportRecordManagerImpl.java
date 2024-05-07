package com.aof.service.po.impl;

import com.aof.dao.po.PortalShipOrderDAO;
import com.aof.dao.po.PortalShipOrderImportRecordDAO;
import com.aof.model.po.query.PortalShipOrderQueryOrder;
import com.aof.service.BaseManager;
import com.aof.service.po.PortalShipOrderImportRecordManager;

import java.util.List;
import java.util.Map;

/**
 * Created by st137 on 2024-05-07.
 */
public class PortalShipOrderImportRecordManagerImpl extends BaseManager
        implements PortalShipOrderImportRecordManager {

    private PortalShipOrderImportRecordDAO dao;

    @Override
    public int getListCount(Map conditions) {
        return this.dao.getCount(conditions);
    }

    @Override
    public List getList(Map conditions, int pageNo, int pageSize, PortalShipOrderQueryOrder order, boolean descend) {
        return this.dao.getList(conditions, pageNo, pageSize, order, descend);
    }
}
