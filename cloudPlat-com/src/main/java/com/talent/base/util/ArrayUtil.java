package com.talent.base.util;

/**
 * 数组工具类
 * @author wangdj
 * 2017年11月23日
 */
public class ArrayUtil
{
    public static boolean isEmpty(String[] args)
    {
        if (args == null || args.length == 0)
        {
            return true;
        }
        
        return false;
    }
}
