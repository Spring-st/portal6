package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.query.PortalShipOrderQueryOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by st137 on 2024-05-07.
 */
public interface PortalShipOrderImportRecordDAO extends DAO {

    int getCount(Map paramMap);

    List getList(Map paramMap, int paramInt1, int paramInt2, PortalShipOrderQueryOrder paramPortalShipOrderQueryOrder, boolean paramBoolean);

}
