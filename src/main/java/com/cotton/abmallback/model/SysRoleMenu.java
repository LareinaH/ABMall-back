package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role_menu")
public class SysRoleMenu extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long role_id;

    private Long menu_id;

    private Boolean is_deleted;

    private Date createAt;

    private Date modifyAt;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return role_id
     */
    public Long getRole_id() {
        return role_id;
    }

    /**
     * @param role_id
     */
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    /**
     * @return menu_id
     */
    public Long getMenu_id() {
        return menu_id;
    }

    /**
     * @param menu_id
     */
    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    /**
     * @return is_deleted
     */
    public Boolean getIs_deleted() {
        return is_deleted;
    }

    /**
     * @param is_deleted
     */
    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    /**
     * @return createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return modifyAt
     */
    public Date getModifyAt() {
        return modifyAt;
    }

    /**
     * @param modifyAt
     */
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}