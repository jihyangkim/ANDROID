package com.dareum.wlgid.dareum_app.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.MainActivity;
import com.dareum.wlgid.dareum_app.SampleSlide;
import com.dareum.wlgid.dareum_app.R;
import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by MINTIE on 2017-05-03.
 */

public class DefaultIntro extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Down here, we add the xml layouts into sample slide inflater.
        addSlide(SampleSlide.newInstance(R.layout.intro1));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));

        // Skip / Done 버튼을 숨 깁니다.
        showSkipButton ( false );
        setProgressButtonEnabled ( false );




        showStatusBar(true);

        setDepthAnimation();

    }



    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        loadMainActivity();
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip_button), Toast.LENGTH_SHORT).show();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}