package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.login.fragment.BaoCaoFragment;
import com.example.login.fragment.LapKeHoachFragment;
import com.example.login.fragment.SoGiaoDichFragment;
import com.example.login.fragment.TaiKhoanFragment;
import com.example.login.fragment.ThemGiaoDichFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.PropertyPermission;

public class FirstPage extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private Fragment  fragment =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.sogiaodich,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SoGiaoDichFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.sogiaodich:
                        fragment = new SoGiaoDichFragment();
                        break;
                    case R.id.baocao:
                        fragment = new BaoCaoFragment();
                        break;
                    case R.id.themgiaodich:
                        fragment = new ThemGiaoDichFragment();
                        break;
                    case R.id.lapkehoach:
                        fragment = new LapKeHoachFragment();
                        break;
                    case R.id.taikhoan:
                        fragment = new TaiKhoanFragment();
                        break;
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }

            }
        });




    }

}