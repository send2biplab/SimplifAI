package com.ubk.simplifai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TaskTypeTutorialActivity extends AppCompatActivity {

    ImageView ivBack, ivPrevious, ivNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_type_tutorial);

        ivBack = findViewById(R.id.ivBack);
        ivPrevious = findViewById(R.id.ivBack);
        ivNext = findViewById(R.id.ivNext);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskTypeTutorialActivity.this, ControlInstructionActivity.class);
                startActivity(intent);
            }
        });

        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskTypeTutorialActivity.this, ControlInstructionActivity.class);
                startActivity(intent);
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskTypeTutorialActivity.this, QuestionType4Activity.class);
                startActivity(intent);
            }
        });
    }
}