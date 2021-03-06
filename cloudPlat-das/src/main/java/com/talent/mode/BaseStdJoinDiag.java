package com.talent.mode;

import java.sql.ResultSet;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.talent.common.HBConstant;
import com.talent.util.JacksonUtil;

public class BaseStdJoinDiag extends BaseDataStd {
    // ��ϼ�������Ϸ�ȷ����Ϊ�գ����Ϊ�հ�
    public String multiple_diag_true = "", multiple_diag_false = "", multiple_diag_null = "", multiple_diag_blank = "";

    public String crowd_list = "";

    public void setResultSet(ResultSet rs) throws Exception {
        super.setResultSet(rs);

    }

    public void setDisease(ResultSet rs) throws Exception {
        if (rs.getString(6) != null)
            this.multiple_diag_true = rs.getString(6);
        if (rs.getString(7) != null)
            this.multiple_diag_false = rs.getString(7);
        if (rs.getString(8) != null)
            this.multiple_diag_null = rs.getString(8);
        if (rs.getString(9) != null)
            this.multiple_diag_blank = rs.getString(9);
        if (rs.getString(10) != null)
            this.crowd_list = rs.getString(10);
    }

    public Put getPut() {
        Put p1 = new Put(Bytes.toBytes(getRowKey()));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_JSON),
                Bytes.toBytes(JacksonUtil.toJSon(this.dataMaps)));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_TRUE),
                Bytes.toBytes(this.multiple_diag_true));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_FALSE),
                Bytes.toBytes(this.multiple_diag_false));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_BLANK),
                Bytes.toBytes(this.multiple_diag_blank));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_DISEASE_NULL),
                Bytes.toBytes(this.multiple_diag_null));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_CROWD_LIST),
                Bytes.toBytes(this.crowd_list));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_STD_KEY_ID),
                Bytes.toBytes(this.datastdId));

        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_TRUE), Bytes.toBytes(""));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_FALSE),
                Bytes.toBytes(""));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_NULL), Bytes.toBytes(""));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_BLANK),
                Bytes.toBytes(""));

        return p1;
    }

}
