package com.yuanpeiyu.fangdai;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.yuanpeiyu.mdm.R;
import com.example.yuanpeiyu.mdm.databinding.ActivityMainBinding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TestAdapter adapter;
    private CalculateModal modal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<DataBindTestBean> beans = new ArrayList<>();
        adapter = new TestAdapter(this, beans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
        binding.setActivity(this);
        modal = new CalculateModal();
    }

    public void calculate() {
        Log.d("ypy", "开始计算");
        int totalA = Integer.parseInt(binding.editText.getText().toString()) * 10000;
        int totalB = Integer.parseInt(binding.editText2.getText().toString()) * 10000;
        double dk = Double.parseDouble(binding.editText4.getText().toString()) / 12 / 100;
        double dt = Double.parseDouble(binding.editText5.getText().toString()) / 12 / 100;
        int time = Integer.parseInt(binding.editText3.getText().toString()) * 12;
        List<Double> dxA = modal.calculateDX(totalA, time, dk);
        List<Double> dxB = modal.calculateDX(totalB, time, dk);

        List<Double> deA = modal.calculateDE(totalA, time, dk);
        List<Double> deB = modal.calculateDE(totalB, time, dk);

        List<Double> dtdxA = modal.calculateDT(dxA, time, dt);
        List<Double> dtdxB = modal.calculateDT(dxB, time, dt);

        List<Double> dtdeA = modal.calculateDT(deA, time, dt);
        List<Double> dtdeB = modal.calculateDT(deB, time, dt);

        double adx = modal.addAll(dxA);
        double ade = modal.addAll(deA);
        double adtdx = modal.addAll(dtdxA);
        double adtde = modal.addAll(dtdeA);
        double bdx = modal.addAll(dxB);
        double bde = modal.addAll(deB);
        double bdtdx = modal.addAll(dtdxB);
        double bdtde = modal.addAll(dtdeB);
        binding.textView8.setText("方案A等息总额" + new BigDecimal(adx).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案A等金总额" + new BigDecimal(ade).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案A等息等金差" + new BigDecimal((adx - ade)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案A等息赚取" + new BigDecimal(adtdx).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案A等额赚取" + new BigDecimal(adtde).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案A等息等金赚取差" + new BigDecimal((adtdx - adtde)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等息总额" + new BigDecimal(bdx).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等金总额" + new BigDecimal(bde).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等息等金差" + new BigDecimal((bdx - bde)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等息赚取" + new BigDecimal(bdtdx).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等额赚取" + new BigDecimal(bdtde).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案B等息等金赚取差" + new BigDecimal((bdtdx - bdtde)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案AB等息差" + new BigDecimal((adx - bdx)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案AB等息赚取差" + new BigDecimal((adtdx - bdtdx)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案AB等额差" + new BigDecimal((ade - bde)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "方案AB等额赚取差" + new BigDecimal((adtde - bdtde)).setScale(2, BigDecimal.ROUND_UP) + "\r\n"
                + "AB差价利息总额" + new BigDecimal((totalB - totalA) * Math.pow(1 + dt, time)).setScale(2, BigDecimal.ROUND_UP) + "\r\n");

        List<DataBindTestBean> beans = new ArrayList<>();

        for (int i = 0; i < time; i++) {
            DataBindTestBean bean = new DataBindTestBean();
            bean.time = i + "";
            bean.resultA1 = new BigDecimal(dxA.get(i)).setScale(2, BigDecimal.ROUND_UP).toString();
            bean.resultA2 = new BigDecimal(deA.get(i)).setScale(2, BigDecimal.ROUND_UP).toString();
            bean.resultB1 = new BigDecimal(dxB.get(i)).setScale(2, BigDecimal.ROUND_UP).toString();
            bean.resultB2 = new BigDecimal(deB.get(i)).setScale(2, BigDecimal.ROUND_UP).toString();
            beans.add(bean);
        }

        adapter.setData(beans);
    }

}
