package com.aof.web.struts.action;

import com.shcnc.struts.action.CustomActionMapping;

public class SecureActionMapping extends CustomActionMapping {
    private Integer functionId;
    private String functionDescription;
    private String level;
    private String functionType;

    public String getAttribute() {
        if (this.attribute == null) {
            if (getScope().equals("session")) {

                String path = getPath().trim();
                if (path.startsWith("/")) path = path.substring(1);
                int index = path.indexOf(".do");
                if (index != -1) path = path.substring(0, index);
                return String.valueOf(path) + "_" + this.name;
            }

            return this.name;
        }
        return this.attribute;
    }

    public String getFunctionDescription() {
        return this.functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public Integer getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}