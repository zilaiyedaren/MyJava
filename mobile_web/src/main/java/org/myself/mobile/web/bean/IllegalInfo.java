package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-10
 * Time: 下午6:46
 * To change this template use File | Settings | File Templates.
 */

public class IllegalInfo {

    private int illegalCount;

    private String illegalDate;

    private String illegalArea;

    private String illegalAct;

    private String illegalAgency;

    private String illegalChuli;

    private String illegalMoney;

    private String illegalOrg_fen;

    public String getIllegalChuli() {
        return illegalChuli;
    }

    public void setIllegalChuli(String illegalChuli) {
        this.illegalChuli = illegalChuli;
    }
    public void setIllegalCount(int illegalCount) {
        this.illegalCount = illegalCount;
    }

    public String getIllegalDate() {
        return illegalDate;
    }

    public void setIllegalDate(String illegalDate) {
        this.illegalDate = illegalDate;
    }



    public String getIllegalAct() {
        return illegalAct;
    }

    public void setIllegalAct(String illegalAct) {
        this.illegalAct = illegalAct;
    }

    public String getIllegalAgency() {
        return illegalAgency;
    }

    public void setIllegalAgency(String illegalAgency) {
        this.illegalAgency = illegalAgency;
    }

    public String getIllegalMoney() {
        return illegalMoney;
    }

    public void setIllegalMoney(String illegalMoney) {
        this.illegalMoney = illegalMoney;
    }

    public String getIllegalOrg_fen() {
        return illegalOrg_fen;
    }

    public void setIllegalOrg_fen(String illegalOrg_fen) {
        this.illegalOrg_fen = illegalOrg_fen;
    }

    public String getIllegalArea() {
        return illegalArea;
    }

    public void setIllegalArea(String illegalArea) {
        this.illegalArea = illegalArea;
    }
    //            count 	int 	未处理的违章数量
//            data 	datetime 	违章时间
//            area 	string 	违章地点
//            act 	string 	违章行为
//            chuli 	string 	处理情况
//            agency 	string 	处理机构
//            money 	int 	罚款
//            org_fen 	int 	扣分
}
