package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class ProductTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 3319455229671412995L;

    private Integer id;

    private String no;

    private String name;

    private Integer productDeptId;

    private String climit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductDeptId() {
        return productDeptId;
    }

    public void setProductDeptId(Integer productDeptId) {
        this.productDeptId = productDeptId;
    }

    public String getClimit() {
        return climit;
    }

    public void setClimit(String climit) {
        this.climit = climit;
    }

}
