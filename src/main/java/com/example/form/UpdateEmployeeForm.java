package com.example.form;


/**
 * 従業員情報をUpdateするときに使用するForm
 *
 * @author koki.kurihara
 */
public class UpdateEmployeeForm {
    /** 従業員ID */
    private String id;
    /** 扶養人数 */
    private String dependentsCount;

    public String getId() {
        return id;
    }

    public int getIntId(){
        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }
    public int getIntDependentsCount() {
        return Integer.parseInt(dependentsCount);
    }
    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeForm{" +
                "id='" + id + '\'' +
                ", dependentsCount='" + dependentsCount + '\'' +
                '}';
    }
}
