package com.talent.mode;

import java.sql.ResultSet;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Put;

import com.talent.job.serial.CrowdDetails;
import com.talent.util.TimeUtil;

public class CrowdDetailsDB implements ITable {
    CrowdDetails data = new CrowdDetails();

    public CrowdDetails getData() {
        return data;
    }

    public void setData(CrowdDetails data) {
        this.data = data;
    }

    @Override
    public void setResultSet(ResultSet rs) throws Exception {
        int i = 1;
        data.crowId = rs.getString(i++);

        data.crowdName = rs.getString(i++);

        String dateBefore = rs.getString(i++);
        if (dateBefore.contains(",")) {
            String[] arrs = dateBefore.split(",");
            if (arrs.length > 1) {
                data.dateBeforeDate = TimeUtil.convertToYandM(arrs[0]);
                data.dateBeforeExtDate = TimeUtil.convertToYandM(arrs[1]);
            }
        } else {
            data.dateBeforeDate = TimeUtil.convertToYandM(dateBefore);
        }

        String dateAfter = rs.getString(i++);
        if (dateAfter.contains(",")) {
            String[] arrs = dateAfter.split(",");
            if (arrs.length > 1) {
                data.dateAfterDate = TimeUtil.convertToYandM(arrs[0]);
                data.dateAfterExtDate = TimeUtil.convertToYandM(arrs[1]);
            }
        } else {
            data.dateAfterDate = TimeUtil.convertToYandM(dateAfter);
        }

        data.inRelationStr = rs.getString(i++);

        data.organList = Arrays.asList(rs.getString(i++).split(","));

        String time = rs.getString(i++);
        if (StringUtils.isNotEmpty(time)) {
            data.followUpYear = TimeUtil.convertToYandM(time);
        }

        time = rs.getString(i++);
        if (StringUtils.isNotEmpty(time)) {
            data.timeWindow = TimeUtil.convertToYandM(time);
        }

        data.type = rs.getString(i++);

        data.outRelationStr = rs.getString(i);

    }

    @Override
    public Put getPut() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDisease(ResultSet rs) throws Exception {
        // TODO Auto-generated method stub

    }

}
