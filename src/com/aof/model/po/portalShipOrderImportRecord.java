package com.aof.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by st137 on 2024-05-07.
 */
public class portalShipOrderImportRecord implements Serializable {

    private Integer id;
    private Integer supplier;
    private String supplierName;
    private Date shipmentsDate;
    private Integer quantity;
    private Integer halfChestQuantity;
    private String orderNo;
    private Integer state;
    private String createBy;
    private Date createTime;

    public portalShipOrderImportRecord(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getShipmentsDate() {
        return shipmentsDate;
    }

    public void setShipmentsDate(Date shipmentsDate) {
        this.shipmentsDate = shipmentsDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getHalfChestQuantity() {
        return halfChestQuantity;
    }

    public void setHalfChestQuantity(Integer halfChestQuantity) {
        this.halfChestQuantity = halfChestQuantity;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
