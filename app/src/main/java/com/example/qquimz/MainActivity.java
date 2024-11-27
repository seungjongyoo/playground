package com.example.qquimz;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.qquimz.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.page_1){
                    transferTo(MainFragment.newInstance("param1","param2"));
                    return true;
                }
                if(itemId==R.id.page_2){
                    transferTo(QnaFragment.newInstance("param1","param2"));
                    return true;
                }
                if(itemId==R.id.page_3){
                    transferTo(RankingFragment.newInstance("param1","param2"));
                    return true;
                }if(itemId==R.id.page_4){
                    transferTo(HistoryFragment.newInstance("param1","param2"));
                    return true;
                }if(itemId==R.id.page_5){
                    transferTo(SettingFragment.newInstance("param1","param2"));
                    return true;
                }
                return false;
            }
        });
        // 아무 내용도 없는 setOnItemReselectedListener가 필요한 이유 :
        // 그게 없으면 선택된 메뉴를 (실수로) 또 선택할 때 화면을 또 초기화하게 되므로
        binding.bottomNavigation.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {}
        });

        // 초기 화면은 메인화면
        transferTo(MainFragment.newInstance("param1","param2"));
    }

    // 실제로 fragment의 교체를 담당하는 구간
    private void transferTo(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }
}