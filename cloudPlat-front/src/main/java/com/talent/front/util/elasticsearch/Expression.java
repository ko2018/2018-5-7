/**
 * Project Name:cloudPlat-front
 * File Name:Expression.java
 * Package Name:com.talent.front.util.elasticsearch
 * Date:2018年1月30日上午10:49:19
 * Copyright (c) 2018, curefun.com All Rights Reserved.
 *
*/

package com.talent.front.util.elasticsearch;

/**
 * Description: <br/>
 * Date: 2018年1月30日 上午10:49:19 <br/>
 * 
 * @author fwp
 * @version
 * @see
 */
public class Expression {
    String valueA;

    String valueB;

    String cal;

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public Expression() {
        super();
    }

    public Expression(String valueA, String valueB, String cal) {
        super();
        this.valueA = valueA;
        this.valueB = valueB;
        this.cal = cal;
    }
}
