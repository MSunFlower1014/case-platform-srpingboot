package com.ma.vue.boot.entity;

import java.util.Set;

public class RoleEntity {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<PermissionsEntity> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<PermissionsEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionsEntity> permissions) {
        this.permissions = permissions;
    }
}
