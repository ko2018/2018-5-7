package com.talent.mode;

import java.sql.ResultSet;

import org.apache.hadoop.hbase.client.Put;

import com.talent.job.serial.GroupDetails;

public class GroupDetailsDB implements ITable {
    GroupDetails data = new GroupDetails();

    public GroupDetails getData() {
        return data;
    }

    public void setData(GroupDetails data) {
        this.data = data;
    }

    @Override
    public void setResultSet(ResultSet rs) throws Exception {
        int i = 1;
        data.groupId = rs.getString(i++);
        data.crowId = rs.getString(i++);
        data.groupName = rs.getString(i++);
        data.saveRelationStr = rs.getString(i++);
        data.filterType = rs.getString(i++);
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
