package com.yuanpeiyu.fangdai;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan Peiyu on 2018/3/19.
 */

public class CalculateModal {

    private int sa = 11000;


    //每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
    public List<Double> calculateDX(int total, int time, double dk) {
        double ret = (total * dk * Math.pow(1+dk,time)) / (Math.pow(1+dk,time) - 1);
        Log.d("ypy", "dx ret = " + ret );
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            res.add(ret);
        }
        return res;
    }
    //每月月供额=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
    public List<Double> calculateDE(int total, int time, double dk) {
        List<Double> res = new ArrayList<>();
        double per = total / time;
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

    public double addAll(List<Double> number) {
        double total = 0;
        for (double d : number) {
            total += d;
        }
        return total;
    }
}
