package com.talent.mode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.job.serial.CrowdFeature;

/**
 * @author zhangqian
 * @time 2017年12月12日 下午7:56:19
 * @version 1.0v
 */
public class CrowdFeatureDB implements ITable {
    protected static Logger logger = LoggerFactory.getLogger("CrowdFeatureDB");

    private CrowdFeature data = new CrowdFeature();

    public CrowdFeature getData() {
        return data;
    }

    public void setData(CrowdFeature data) {
        this.data = data;
    }

    // 数值型，等级型，分类型，文本型
    public final String INT_TYPE = "1", LV_TYPE = "2", PAIR_TYPE = "3", TXT_TYPE = "4";

    @Override
    public void setResultSet(ResultSet rs) throws SQLException {

        data.crowId = rs.getString(1);
        data.flag = rs.getString(2);
        data.featureID = rs.getString(3);
        String val = rs.getString(4);
        if (val != null)
            data.featureVal = val;
        String id = rs.getString(5);
        if (id != null)
            data.dictionaryID = "_" + id;

        data.featureType = rs.getString(6);
        if (data.featureType != null) {
            if (INT_TYPE.equals(data.featureType)) {
                String[] arrs = data.featureVal.split("-");
                if (arrs.length < 2) {
                    data.featureVal = null;
                    return;
                }

                data.lowLimit = Double.parseDouble(arrs[0]);
                data.upLimit = Double.parseDouble(arrs[1]);

            } else if (LV_TYPE.equals(data.featureType) || PAIR_TYPE.equals(data.featureType)) {
                String[] arrs = data.featureVal.split("-");
                data.valList = new ArrayList<String>();
                for (String arr : arrs) {
                    data.valList.add(arr);
                }

            }
        } else {
            String[] arrs = data.featureVal.split("-");
            data.valList = new ArrayList<String>();
            for (String arr : arrs) {
                data.valList.add(arr);
            }
        }

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
