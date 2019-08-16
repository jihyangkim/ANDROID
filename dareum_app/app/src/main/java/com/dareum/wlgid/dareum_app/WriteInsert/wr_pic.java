package com.dareum.wlgid.dareum_app.WriteInsert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.R;

public class wr_pic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wr_pic);

        //화면크기 얻기
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int displayWidth = display.getWidth();
        int displayHeight = display.getHeight();

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        final ImageView img = (ImageView)findViewById(R.id.imageview);
        gridview.setAdapter(new ImageAdapter(this,displayWidth));

      /* final Intent intent1 = getIntent();*/
        final Intent intent2 = new Intent();

        /*Bitmap bm=(Bitmap)intent1.getExtras().get("image");
        img.setImageBitmap(bm);*/

        //해당 위치의 이미지를 셋함
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                img.setImageResource((int)parent.getAdapter().getItemId(position));
                intent2.putExtra("image ID",position);
            }
        });

        //뒤로가기
        findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //완료버튼을 누르면 이동
        ImageView check_btn = (ImageView) findViewById(R.id.check);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(img.getDrawable()==null){
                    Toast.makeText(getApplicationContext(),"사진을 선택하여 주십시오",Toast.LENGTH_SHORT).show();
                }
                else {
                    setResult(RESULT_OK,intent2);
                    finish();
                }
            }
        });
    }
}