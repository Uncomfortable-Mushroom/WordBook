package com.example.lenovo.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.newword);
        b2=(Button)findViewById(R.id.checkword);
        b3=(Button)findViewById(R.id.begin);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.begin:
                Intent intent1=new Intent("com.example.wordbook.ACTION1");
                startActivity(intent1);
                break;
            case R.id.newword:
                Intent intent2=new Intent("com.example.wordbook.ACTION2");
                startActivity(intent2);
                break;
            case R.id.checkword:
                Intent intent3=new Intent("com.example.wordbook.ACTION3");
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
