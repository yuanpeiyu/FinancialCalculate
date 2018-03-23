package com.yuanpeiyu.fangdai;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan Peiyu on 2018/3/19.
 */

public class CalculateModal {

    private int sa = 11000;

    public static void main(String[] args) {
        CalculateModal modal = new CalculateModal();
       /* List<Double> res = modal.calculateDE(580000, 120, 5.39/12/100);
        System.out.println("total is " + modal.addAll(res));
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < res.size(); i++) {
            System.out.println("    " + i + "    " + res.get(i) + "    ");
        }
        System.out.println("-------------------------------------------------");*/
        System.out.println("15 is " +modal.addAll(modal.calculateDT(modal.calculateDX(1000000, 240, 5.39 / 12 / 100), 240, 2.4 / 12 / 100)));
        System.out.println("total1 is " +modal.addAll(modal.calculateDT2(3000.0, 240, 2.4 / 12 / 100)));
        System.out.println("total is " + new BigDecimal((200000) * Math.pow(1 + 4.0 / 12 / 100, 12)).setScale(2, BigDecimal.ROUND_UP));
    }

    //每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
    public List<Double> calculateDX(int total, int time, double dk) {
        double ret = (total * dk * Math.pow(1+dk,time)) / (Math.pow(1+dk,time) - 1);
        //Log.d("ypy", "dx ret = " + ret );
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            res.add(ret);
        }
        return res;
    }
    //每月月供额=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
    public List<Double> calculateDE(int total, int time, double dk) {
        List<Double> res = new ArrayList<>();
        double per = new BigDecimal(total / time).doubleValue();
        for (int i = 0; i < time; i++) {
            res.add(per + (total - per * i) * dk);
        }
        return res;
    }

    public List<Double> calculateDT(List<Double> base, int time, double dt) {
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            res.add((sa - base.get(i)) * (Math.pow(1+dt, time - i)));
        }
        return res;
    }

    public List<Double> calculateDT2(double base, int time, double dt) {
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            res.add(base * (Math.pow(1+dt, time - i)));
        }
        return res;
    }

    public double addAll(List<Double> number) {
        double total = 0;
        for (double d : number) {
            total += d;
        }
        return total;
    }
}
