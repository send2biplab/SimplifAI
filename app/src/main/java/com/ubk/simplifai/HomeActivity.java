package com.ubk.simplifai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    LinearLayout llHomeContent, llCatImage, llCatPolygon, llCatText, llCatAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        llHomeContent = findViewById(R.id.llHomeContent);
        llCatImage = findViewById(R.id.llCatImage);
        llCatPolygon = findViewById(R.id.llCatPolygon);
        llCatText = findViewById(R.id.llCatText);
        llCatAudio = findViewById(R.id.llCatAudio);
        //getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, new FragmentHome()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        llHomeContent.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menu_history:
                        llHomeContent.setVisibility(View.GONE);
                        temp = new FragmentHistory();
                        break;
                    case R.id.menu_profile:
                        llHomeContent.setVisibility(View.GONE);
                        temp = new FragmentProfile();
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.llHomeContent, temp).commit();

                return true;
            }
        });

        llCatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                intent.putExtra("CatId", "63ac4c034d9adfa4be0f523c");
                startActivity(intent);
            }
        });

        llCatPolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                intent.putExtra("CatId", "63ac4c7a4d9adfa4be0f523f");
                startActivity(intent);
            }
        });

        llCatText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                intent.putExtra("CatId", "63ac4c4d4d9adfa4be0f523e");
                startActivity(intent);
            }
        });

        llCatAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                intent.putExtra("CatId", "63ac4d404d9adfa4be0f5240");
                startActivity(intent);
            }
        });
    }
}
