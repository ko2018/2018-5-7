package com.talent.mode;

import java.sql.ResultSet;

import org.apache.hadoop.hbase.client.Put;

public interface ITable {

    void setResultSet(ResultSet rs) throws Exception;

    Put getPut();

    void setDisease(ResultSet rs) throws Exception;

}
