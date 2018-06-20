package com.cotton.abmallback.model.vo.front;

/**
 * LoginMemberVO
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/16
 */
public class LoginMemberVO {

    /**
     * 会员名
     */
    private String name;

    /**
     * 性别：male,female,unknown
     */
    private String sex;

    /**
     * 头像
     */
    private String photo;

    /**
     * 会员等级
     */
    private String level;

    /**
     * 是否绑定手机
     */
    private boolean isBindPhone;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 登录凭证
     */
    private String ticket;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isBindPhone() {
        return isBindPhone;
    }

    public void setBindPhone(boolean bindPhone) {
        isBindPhone = bindPhone;
    }
}
