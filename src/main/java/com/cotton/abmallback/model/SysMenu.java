package com.cotton.abmallback.model;

import com.cotton.base.model.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单显示名
     */
    private String display_name;

    private Long parent_id;

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
     * 获取菜单显示名
     *
     * @return display_name - 菜单显示名
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * 设置菜单显示名
     *
     * @param display_name 菜单显示名
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name == null ? null : display_name.trim();
    }

    /**
     * @return parent_id
     */
    public Long getParent_id() {
        return parent_id;
    }

    /**
     * @param parent_id
     */
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
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