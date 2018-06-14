package com.example.administrator.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();

        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);

        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String content = inputText.getText().toString();
                if(!content.equals("")){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });

    }

    private void initMsgs(){
        Msg msg;
        msg = new Msg("你好兄弟",Msg.TYPE_RECEIVED);
        msgList.add(msg);
        msg = new Msg("嗨！你是谁",Msg.TYPE_SENT);
        msgList.add(msg);
        msg = new Msg("我是L,很高兴和你聊天",Msg.TYPE_RECEIVED);
        msgList.add(msg);
    }
}
