package com.example.lenovo.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.newword);
        b2=(Button)findViewById(R.id.checkword);
        b3=(Button)findViewById(R.id.begin);
        b4=(Button)findViewById(R.id.internet);
        b5=(Button)findViewById(R.id.NEWS);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
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
            case R.id.internet:
                Intent intent4=new Intent("com.example.wordbook.ACTION4");
                startActivity(intent4);
                break;
            case R.id.NEWS:
                Intent intent5=new Intent("com.example.wordbook.ACTION5");
                startActivity(intent5);
                break;
            default:
                break;
        }
    }
}
