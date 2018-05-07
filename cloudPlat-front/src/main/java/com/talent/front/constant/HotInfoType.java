package com.talent.front.constant;

/**
 * 一句话简要描述
 * @author wangdj
 * 2018年1月8日
 */
public enum HotInfoType
{
    NEWS("455264bc69a1431baab0a598b482a05e","新闻"), LITERATURE("2aa592f80afc4c8a9dbec0acf3046ca4", "文献");
    
    private String code;
    private String name;
    
    private HotInfoType(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
    
    public String getCode()
    {
        return this.code;
    }
    
    public String getName()
    {
        return this.name;
    }
}
