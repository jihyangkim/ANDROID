package com.dareum.wlgid.dareum_app.Tutorial;

import android.os.Bundle;

import com.dareum.wlgid.dareum_app.SampleSlide;
import com.dareum.wlgid.dareum_app.R;
import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by wlgid on 2017-10-18.
 */

public class AgainIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Down here, we add the xml layouts into sample slide inflater.
        addSlide(SampleSlide.newInstance(R.layout.intro1));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro4));

        // Skip / Done 버튼을 숨 깁니다.
        showSkipButton(false);
        setProgressButtonEnabled(false);

        showStatusBar(true);
        setDepthAnimation();
    }
}

