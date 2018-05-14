package com.talent.mode.parm;import java.sql.PreparedStatement;import java.sql.Timestamp;import java.util.Date;/** * @author zhangqian * @time 2017年12月11日 下午5:32:54 * @version 1.0v */public class StaticsYearTable extends TableParm {    /** 主键id */    private String staticsId;    /** 统计年 */    private int staticsYear;    /** 统计 疾病 */    private String staticsDisease;    /** 统计 患此病 */    private Long staticsHas;    /** 统计不 患此病 */    private Long staticsHasNO;    @Override    public void replaceToDB(PreparedStatement stat) throws Exception {        int i = 1;        if (staticsId == null)            staticsId = staticsDisease + "_" + staticsYear;        stat.setString(i++, staticsId);        stat.setString(i++, staticsDisease);        stat.setLong(i++, staticsHasNO);        stat.setLong(i++, staticsHas);        stat.setInt(i++, staticsYear);        stat.setTimestamp(i, new Timestamp(new Date().getTime()));    }    public void setStaticsId(String staticsId) {        this.staticsId = staticsId;    }    public int getStaticsYear() {        return staticsYear;    }    public void setStaticsYear(int staticsYear) {        this.staticsYear = staticsYear;    }    public String getStaticsDisease() {        return staticsDisease;    }    public void setStaticsDisease(String staticsDisease) {        this.staticsDisease = staticsDisease;    }    public Long getStaticsHas() {        return staticsHas;    }    public void setStaticsHas(Long staticsHas) {        this.staticsHas = staticsHas;    }    public Long getStaticsHasNO() {        return staticsHasNO;    }    public void setStaticsHasNO(Long staticsHasNO) {        this.staticsHasNO = staticsHasNO;    }    public String getStaticsId() {        return staticsId;    }}