package com.apartment.resource.datatable;

import com.apartment.resource.datatable.base.BaseResource;

public class RoleResource extends BaseResource {

    private String roleTitle;
    private String roleCode;

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
