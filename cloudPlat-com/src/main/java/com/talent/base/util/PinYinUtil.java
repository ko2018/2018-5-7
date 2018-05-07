package com.talent.base.util;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 获取汉字的拼音
 * @author wangdj
 * 2017年11月23日
 */
public class PinYinUtil
{
    /** 
     * 获取汉字串拼音首字母，英文字符不变 
     * 
     * @param chinese 汉字串 
     * @return 汉语拼音首字母 
     */ 
    public static String cn2FirstSpell(String chinese) 
    { 
        if (StringUtils.isEmpty(chinese))
        {
            return "";
        }
        StringBuffer pybf = new StringBuffer(); 
        char[] arr = chinese.toCharArray(); 
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        for (int i = 0; i < arr.length; i++) 
        { 
            if (java.lang.Character.toString(arr[i]).matches("[\\u4E00-\\u9FA5]+")) 
            { 
                try 
                { 
                    String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (_t != null) 
                    { 
                        pybf.append(_t[0].charAt(0)); 
                    }
                } 
                catch (Exception e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            else 
            { 
                pybf.append(java.lang.Character.toString(arr[i])); 
            } 
        } 
        return pybf.toString().replaceAll("\\W", "").trim(); 
    } 

    /** 
     * 获取汉字串拼音，英文字符不变 
     * 
     * @param chinese 汉字串 
     * @return 汉语拼音 
     */ 
    public static String cn2Spell(String chinese) 
    { 
        if (StringUtils.isEmpty(chinese))
        {
            return "";
        }
        StringBuffer pybf = new StringBuffer(); 
        char[] arr = chinese.toCharArray(); 
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        for (int i = 0; i < arr.length; i++) 
        { 
            if (java.lang.Character.toString(arr[i]).matches("[\\u4E00-\\u9FA5]+")) 
            { 
                try 
                { 
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } 
                catch (Exception e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            else 
            { 
                pybf.append(java.lang.Character.toString(arr[i])); 
            } 
        } 
        return pybf.toString(); 
    } 

    public static void main(String[] args)
    {
        String info = "生殖器官、及乳腺类检查（不包括激素分析）";
        System.out.println(info);
        System.out.println(cn2Spell(info));
        System.out.println(cn2FirstSpell(info));
    }
}
