package com.dareum.wlgid.dareum_app.WriteInsert;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.R;
import com.dareum.wlgid.dareum_app.SwipeActivity;
import com.dareum.wlgid.dareum_app.Worker;

public class wr_main extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;

    public static final int REQUEST_CODE = 1;

    private int[] imageIDs = new int[]{
            R.mipmap.bg_img1,R.mipmap.bg_img2,R.mipmap.bg_img3,
            R.mipmap.bg_img4,R.mipmap.bg_img5,R.mipmap.bg_img6,
            R.mipmap.bg_img7,R.mipmap.bg_img8,R.mipmap.bg_img9,
            R.mipmap.bg_img10,R.mipmap.bg_img11,R.mipmap.bg_img12,
            R.mipmap.bg_img13,R.mipmap.bg_img14,R.mipmap.bg_img15,
            R.mipmap.bg_img16,R.mipmap.bg_img17,R.mipmap.bg_img18,
            R.mipmap.bg_img19,R.mipmap.bg_img20
    };

    int i;

    TextView wr_go, wr_sgo, wr_wo, wr_mn, wr_evr, wr_sel_img, wr_im, wr_com, wr_lo, wr_img_b;
    EditText wr_title, wr_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wr_main);

        //imei 불러오기
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }

        final Intent intent = new Intent(getApplicationContext(), wr_pic.class);
        final EditText editText = (EditText) findViewById(R.id.wr_content_edit);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);

        final Intent intent1 = getIntent();

        //댓글 분류 데이터 받아오기
        String Gomin, SGomin, WOnly, MOnly, Every;

        Gomin = intent1.getStringExtra("Gomin");
        SGomin = intent1.getStringExtra("SGomin");
        WOnly = intent1.getStringExtra("WOnly");
        MOnly = intent1.getStringExtra("MOnly");
        Every = intent1.getStringExtra("Every");

        wr_go = (TextView)findViewById(R.id.wr_go);
        wr_sgo = (TextView)findViewById(R.id.wr_sgo);
        wr_wo = (TextView)findViewById(R.id.wr_wo);
        wr_mn = (TextView)findViewById(R.id.wr_mn);
        wr_evr = (TextView)findViewById(R.id.wr_evr);

        wr_go.setText(Gomin);
        wr_sgo.setText(SGomin);
        wr_wo.setText(WOnly);
        wr_mn.setText(MOnly);
        wr_evr.setText(Every);

        findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        findViewById(R.id.wr_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*   intent.putExtra("image",tmp);*/
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        /*findViewById(R.id.pro_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"글을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        //이미지 선택 데이터 전송(default값)
        wr_sel_img = (TextView)findViewById(R.id.wr_sel_img);
        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img1.png");

        //editText 데이터
        wr_title = (EditText)findViewById(R.id.wr_title_edit);
        wr_content = (EditText)findViewById(R.id.wr_content_edit);

        //댓글수, 하트수
        wr_com = (TextView)findViewById(R.id.wr_com);
        wr_lo = (TextView)findViewById(R.id.wr_love);

        wr_com.setText("0");
        wr_lo.setText("0");

        //댓글 제한 이미지 출력
        wr_img_b = (TextView)findViewById(R.id.wr_img_b);
        if (wr_wo.getText().toString().equals("1")){
            wr_img_b.setText("http://awse.dothome.co.kr/picture/user_1.png");
        }
        if (wr_evr.getText().toString().equals("1")){
            wr_img_b.setText("http://awse.dothome.co.kr/picture/user_2.png");
        }
        if (wr_mn.getText().toString().equals("1")){
            wr_img_b.setText("http://awse.dothome.co.kr/picture/user_3.png");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                ImageView imageView = (ImageView) findViewById(R.id.wr_main_img);
                imageView.setImageResource(imageIDs[data.getIntExtra("image ID",-1)]);
                i = data.getIntExtra("image ID",-1);
                //이미지 선택 데이터 전송
                switch (i){
                    case 0:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img1.png");
                        break;
                    case 1:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img2.png");
                        break;
                    case 2:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img3.png");
                        break;
                    case 3:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img4.png");
                        break;
                    case 4:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img5.png");
                        break;
                    case 5:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img6.png");
                        break;
                    case 6:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img7.png");
                        break;
                    case 7:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img8.png");
                        break;
                    case 8:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img9.png");
                        break;
                    case 9:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img10.png");
                        break;
                    case 10:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img11.png");
                        break;
                    case 11:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img12.png");
                        break;
                    case 12:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img13.png");
                        break;
                    case 13:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img14.png");
                        break;
                    case 14:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img15.png");
                        break;
                    case 15:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img16.png");
                        break;
                    case 16:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img17.png");
                        break;
                    case 17:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img18.png");
                        break;
                    case 18:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img19.png");
                        break;
                    case 19:
                        wr_sel_img.setText("http://awse.dothome.co.kr/picture/bg_img20.png");
                        break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    private String getDeviceImei() {
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);

        wr_im = (TextView)findViewById(R.id.wr_im);
        wr_im.setText(deviceid);
        return deviceid;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput((EditText) findViewById(R.id.wr_content_edit),InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);
    }

    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("AlertDialog Title");
        builder.setMessage("뒤로가기 버튼 클릭시 작성된 내용이 모두 삭제됩니다.");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(wr_main.this,WrActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        finish();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    public void OnInsert2(View view) {
        String imei = wr_im.getText().toString();
        String gominca = wr_go.getText().toString();
        String sgominca = wr_sgo.getText().toString();
        String w_com = wr_wo.getText().toString();
        String m_com = wr_mn.getText().toString();
        String a_com = wr_evr.getText().toString();
        String b_img = wr_sel_img.getText().toString();
        String image = wr_img_b.getText().toString();
        String comgo = wr_com.getText().toString();
        String likego = wr_lo.getText().toString();
        String title = wr_title.getText().toString();
        String contgo = wr_content.getText().toString();
        String type = "insert2";
        Worker backgroundWorker = new Worker(this);
        backgroundWorker.execute(type, imei, gominca, sgominca, w_com, m_com, a_com, b_img, image, comgo, likego, title, contgo);
        Toast.makeText(getApplicationContext(),"글작성 완료",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), SwipeActivity.class));
    }

}
