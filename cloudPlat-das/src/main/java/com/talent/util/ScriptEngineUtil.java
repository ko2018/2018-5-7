package com.talent.util;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 一句话简要描述
 * 
 * @author wangdj 2017年12月23日
 */
public class ScriptEngineUtil {

    public static boolean executeScript(String script) throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");
        return (boolean) scriptEngine.eval(script);
    }

    /**
     * @param script "a && b"
     * @param subResult (a, true),(b, false)
     * @return
     * @throws Exception
     */
    public static boolean executeScript(String script, Map<String, Boolean> subResult) throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");
        for (Map.Entry<String, Boolean> entry : subResult.entrySet()) {
            scriptEngine.put(entry.getKey(), entry.getValue());
        }
        return (boolean) scriptEngine.eval(script);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(executeScript("( true && false && (true || false) ) || (true && false)"));
        String script = "T1W && !QW";
        Map<String, Boolean> map = new HashMap();
        map.put("T1W", true);
        map.put("QW", true);
        map.put("e", false);
        System.out.println(executeScript(script, map));
    }
}
