package com.talent.job.serial;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.talent.util.UUIDUtil;

/**
 * ���ڷϵ�
 */
public class BaseDataN implements Serializable {
    private static final long serialVersionUID = 1L;

    public String examTime = "";

    public String organID = "";

    public String idCode = "";

    public String examID = "";

    public String type = "0";

    public String typeM = "";

    public int rank;

    public void replaceToDB(PreparedStatement stat, String crowdID) throws Exception {
        int i = 1;
        // key_id,
        stat.setString(i++, UUIDUtil.getCode(crowdID + idCode + examID + examTime));

        // crowd_pId,
        stat.setString(i++, crowdID);
        // crowd_user_code,
        stat.setString(i++, idCode);

        if (examID != null && !"".equals(examID)) {
            stat.setString(i++, examID);
        } else {
            stat.setString(i++, "");
        }

        if (examTime != null) {
            stat.setString(i++, examTime);
        } else {
            stat.setString(i++, "");
        }
        if (this.organID != null) {
            stat.setString(i++, organID);
        } else {
            stat.setString(i++, "");
        }

        stat.setString(i++, "" + rank);

        stat.setTimestamp(i, new Timestamp(new Date().getTime()));
        stat.addBatch();

    }

}
