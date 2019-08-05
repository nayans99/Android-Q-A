package com.example.android.androidinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Tough_questions extends AppCompatActivity implements View.OnClickListener {
    TextToSpeech ttsobj;
    String[] diffq, diffa;
    int index,result;
    TextView tq, ta, tx, ty;
    Button bleft, bshow, bnext, bsp, bst;
    private static final String def_ans = "Press A for answer";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.questions);

        LinearLayout sqll = (LinearLayout)findViewById(R.id.questionstitle) ;
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title);

        bsp = (Button)findViewById(R.id.bspeak);
        bst = (Button)findViewById(R.id.bstop);

        TextView tvc = (TextView) findViewById(R.id.textView);
        tvc.setText("Tough Questions");

        index = 0;

        tq = (TextView) findViewById(R.id.que);
        ta = (TextView) findViewById(R.id.ans);
        tx = (TextView) findViewById(R.id.curr);
        ty = (TextView) findViewById(R.id.total);

        bleft = (Button) findViewById(R.id.bleft) ;
        bshow = (Button) findViewById(R.id.bans) ;
        bnext = (Button) findViewById(R.id.bright) ;

        diffq = getResources().getStringArray(R.array.dif_ques);
        diffa = getResources().getStringArray(R.array.dif_ans);

        bleft.setOnClickListener(this);
        bshow.setOnClickListener(this);
        bnext.setOnClickListener(this);
        bsp.setOnClickListener(this);
        bst.setOnClickListener(this);

        tq.setText(diffq[index]);
        ta.setText("Press A for answer");
        tx.setText(String.valueOf(index+1));
        tx.setText(String.valueOf(diffq.length));

        ttsobj = new TextToSpeech(Tough_questions.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    result = ttsobj.setLanguage(Locale.ENGLISH);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device!", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.bleft:
            {
                ta.setText(def_ans);
                index=(index-1);
                if(index  == -1)
                    index = diffq.length-1;
                tq.setText(diffq[index]);
                tx.setText(String.valueOf(index+1));
                break;
            }
            case R.id.bans:
            {
                ta.setText(diffa[index]);
                break;
            }
            case R.id.bright:
            {
                ta.setText(def_ans);
                index=(index+1)%diffq.length;
                tq.setText(diffq[index]);
                tx.setText(String.valueOf(index+1));
                break;
            }
            case R.id.bspeak:
            {
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(ta.getText().toString().equals(def_ans))
                    {
                        ttsobj.speak(def_ans,TextToSpeech.QUEUE_FLUSH,null);
                    }
                    else
                    {
                        ttsobj.speak(diffa[index],TextToSpeech.QUEUE_FLUSH,null);
                    }

                }
                break;
            }
            case R.id.bstop :
            {
                if(ttsobj != null)
                {
                    ttsobj.stop();

                }
                break;
            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ttsobj != null)
        {
            ttsobj.stop();
            ttsobj.shutdown();
        }
    }
}
