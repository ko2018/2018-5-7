/**
 * Project Name:cloudPlat-front
 * File Name:Test.java
 * Package Name:com.talent.front.util.elasticsearch
 * Date:2018年1月30日下午5:12:24
 * Copyright (c) 2018, curefun.com All Rights Reserved.
 *
*/

package com.talent.front.util.elasticsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Description: <br/>
 * Date: 2018年1月30日 下午5:12:24 <br/>
 * 
 * @author dell
 * @version
 * @see
 */
public class Test {
    public static void main(String[] args) {

        String s = "(a=1 | (b<2  | c>3 &f>6)) & (d=4  | e<5) | m >9";// 分割成对象，如果遇到左括号或者是｜开始分割
        // s = "(a=1 | (b<2 | c>3 &f>6)) & (d=4 | e<5) & m >9";//
        // s = "(a=1 | (b<2 | c>3 &f>6 | e^3) | (d=4 & e<5) & m >9)";//
        // s = "a=1 | (b<2 | c>3 &f>6 | e^3) | (d=4 | e<5) & m >9";//
        // s = "a=1 & (b<2 | c>3 &f>6) | ((d=4 & e<5) | m >9)";//
        List list = getSplit(s);
        BoolQueryBuilder bool = doBra(list);
        System.out.println(bool.toString());

    }

    private static BoolQueryBuilder doBra(List listO) {

        LinkedList<String> opraStack = new LinkedList<String>();
        LinkedList boolStack = new LinkedList();
        Object obj;
        String s;
        List list;
        for (int i = 0; i < listO.size(); i++) {
            obj = listO.get(i);
            if (obj instanceof String) {
                s = (String) obj;
                if (s.equals("&") || s.equals("|")) {// 符号入栈
                    opraStack.push(s);
                } else if (s.equals(")")) {// 如果碰到右括号
                    int n = 0;
                    list = new ArrayList<String>();
                    int f = 0;

                    for (int j = 0; j < boolStack.size(); j++) {// 循环以前的栈，直到左括号为止，并把未遇到左括号之前的都加入一个list中。
                        Object obj2 = boolStack.get(j);
                        if (obj2 instanceof String) {
                            if (obj2.toString().equals("(")) {
                                boolStack.pop();
                                break;
                            }
                        }
                        n++;
                        list.add(obj2);
                    }

                    for (int m = 0; m < n; m++) {// 清理栈
                        boolStack.pop();
                    }

                    boolStack.push(doBool(list, opraStack));// 运算后把左括号的内容加入到bool栈中
                } else {
                    boolStack.push(obj);
                }
            } else {
                boolStack.push(obj);
            }
        }

        BoolQueryBuilder bool3 = doBool(boolStack, opraStack);
        return bool3;
    }

    private static BoolQueryBuilder doBool(List list, LinkedList<String> opraStack) {
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        String temp = null;
        int n = 1;
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);

            if (list.size() == 1 || list.size() == 2) {
                if (obj instanceof Expression)
                    obj = doCon((Expression) obj);
                if (opraStack.size() == 0)
                    return (BoolQueryBuilder) obj;
                bool = doRela(bool, (QueryBuilder) obj, opraStack.getFirst());
                if (i == 1)
                    opraStack.pop();
            } else {
                if (opraStack.size() == 0) {
                    flag = true;
                    opraStack.push(temp);
                }

                if (obj instanceof Expression) {
                    bool = doRela(bool, doCon((Expression) obj), opraStack.getFirst());
                } else if (obj instanceof BoolQueryBuilder) {
                    bool = doRela(bool, (BoolQueryBuilder) obj, opraStack.getFirst());
                    temp = opraStack.getFirst();
                }
                if (n < list.size()) {
                    opraStack.pop();
                    n++;
                }

            }

        }

        if (flag)
            opraStack.pop();

        return bool;
    }

    private static QueryBuilder doCon(Expression exp) {
        QueryBuilder qb = null;
        String field = exp.getValueA();
        String value = exp.getValueB();
        String con = exp.getCal();
        if ("=".equals(con))
            qb = QueryBuilders.termQuery(field, value);
        else if (">".equals(con))
            qb = QueryBuilders.rangeQuery(field).gt(value);
        else if ("<".equals(con))
            qb = QueryBuilders.rangeQuery(field).lt(value);
        else if ("^".equals(con))// 代替不等于
            qb = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(field, value));
        return qb;
    }

    private static BoolQueryBuilder doRela(BoolQueryBuilder bool, QueryBuilder subBool, String opra) {
        if ("&".equals(opra)) {
            bool.must(subBool);
        } else if ("|".equals(opra)) {
            bool.should(subBool);
        }
        return bool;
    }

    private static List getSplit(String s) {

        String[] ss = s.replace(" ", "").split("");
        List list = new ArrayList();
        List<String> listExp = new ArrayList<String>();
        for (int i = 1; i < ss.length; i++) {
            if (ss[i].equals("&") || (ss[i]).equals("|")) {
                if (listExp.size() > 0) {
                    doList(list, listExp);
                }
                list.add(ss[i]);
            } else if (ss[i].equals(")") || ss[i].equals("(")) {//
                if (ss[i].equals(")") && listExp.size() > 0) {
                    doList(list, listExp);
                }
                list.add(ss[i]);
            } else {
                listExp.add(ss[i]);
            }
        }
        if (listExp.size() > 0) {
            doList(list, listExp);
        }
        return list;
    }

    private static void doList(List list, List<String> listExp) {
        list.add(new Expression(listExp.get(0), listExp.get(2), listExp.get(1)));
        listExp.clear();
    }
}
