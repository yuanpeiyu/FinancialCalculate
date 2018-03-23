package com.yuanpeiyu.fangdai;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Yuan Peiyu on 2018/3/19.
 */

public class Test {
    public static void main(String[] args) {
        CalculateModal modal = new CalculateModal();
        List<Double> res = modal.calculateDE(580000, 120, new BigDecimal(5.39/12/100).setScale(2, BigDecimal.ROUND_UP).doubleValue());
        System.out.println("total is " + modal.addAll(res));
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < res.size(); i++) {
            System.out.println("    " + i + "    " + res.get(i) + "    ");

        }
        System.out.println("-------------------------------------------------");
    }
}
