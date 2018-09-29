package com.albenyuan.mybatis.model;

import com.albenyuan.core.data.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @Author Alben Yuan
 * @Date 2018-09-29 12:05
 */
public class Menu extends BaseModel {

    private String name;

    private String icon;

    private String url;

    private String parentId;

    private Boolean enabled;

    private List<Menu> children;

    private Menu parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append(", enabled=").append(enabled);
        sb.append(", children=").append(children);
        sb.append(", parent=").append(parent);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}