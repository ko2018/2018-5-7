package com.talent.mode;

import java.sql.ResultSet;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.talent.common.HBConstant;

public class BaseStdJoinDiagItem extends BaseDataStd {
    // ��ϼ�������Ϸ�ȷ����Ϊ�գ����Ϊ�հ�
    public String item_diag_true = "", item_diag_false = "", item_diag_null = "", item_diag_blank = "";

    public String crowd_list = "";

    public void setResultSet(ResultSet rs) throws Exception {
        super.setResultSet(rs);

    }

    public void setDisease(ResultSet rs) throws Exception {
        if (rs.getString(6) != null)
            this.item_diag_true = rs.getString(6);
        if (rs.getString(7) != null)
            this.item_diag_false = rs.getString(7);
        if (rs.getString(8) != null)
            this.item_diag_null = rs.getString(8);
        if (rs.getString(9) != null)
            this.item_diag_blank = rs.getString(9);
    }

    public Put getPut() {
        Put p1 = new Put(Bytes.toBytes(getRowKey()));

        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_TRUE),
                Bytes.toBytes(this.item_diag_true));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_FALSE),
                Bytes.toBytes(this.item_diag_false));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_BLANK),
                Bytes.toBytes(this.item_diag_blank));
        p1.addColumn(Bytes.toBytes(HBConstant.FAMILY_INFO), Bytes.toBytes(HBConstant.COL_ITEM_NULL),
                Bytes.toBytes(this.item_diag_null));

        return p1;
    }

}
