package hbase;

import java.util.regex.Pattern;

import com.talent.core.global.Constant;

public class Test1 {
    public static void main1(String[] args) {
        String script = "b007e857356b44c684e8eb21e3482d89 || 470fbdececf14bd2bfafb6c69c953787 || ec92df2344564279a339799a715a0a12 || dad35bf7ac3d4ecfbd73d59374f6149f";
        String reg = ".*[0-9].*";

        boolean isMatch = Pattern.matches(reg, script);
        System.out.println(isMatch);
        script = "false1";

        isMatch = Pattern.matches(reg, script);
        System.out.println(isMatch);

    }

    public static void main(String[] args) {
        String script = "_7";
        boolean flag = Pattern.matches(Constant.REGEX_MATCH_KAFKA, script);
        System.out.println(flag);
    }
}
