package com.dareum.wlgid.dareum_app.SetUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.R;
import com.dareum.wlgid.dareum_app.core.AppLock;
import com.dareum.wlgid.dareum_app.core.AppLockActivity;
import com.dareum.wlgid.dareum_app.core.BaseActivity;
import com.dareum.wlgid.dareum_app.core.LockManager;

public class PwSetup extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "PwSetup";

    private Switch btOnOff;
    private LinearLayout btChange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw_setup);

                btOnOff = (Switch) findViewById(R.id.bt_on_off);
                btOnOff.setOnClickListener(this);

                btChange = (LinearLayout) findViewById(R.id.bt_change);
                //btChange.setText(R.string.change_passcode);
                btChange.setOnClickListener(this);

                updateUI();

                findViewById(R.id.pw_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btOnOff)) {
            if(LockManager.getInstance().getAppLock().isPasscodeSet()){
                    setResult(RESULT_OK);
                    LockManager.getInstance().getAppLock().setPasscode(null);//비번 초기화
            }
            else {
                int type = AppLock.ENABLE_PASSLOCK;//스위치 on
                Intent intent = new Intent(this, AppLockActivity.class);
                intent.putExtra(AppLock.TYPE, type);
                startActivityForResult(intent, type);
            }

        /*   int type = LockManager.getInstance().getAppLock().isPasscodeSet() ? AppLock.DISABLE_PASSLOCK
                    : AppLock.ENABLE_PASSLOCK;
            Intent intent = new Intent(this, AppLockActivity.class);
            intent.putExtra(AppLock.TYPE, type);
            startActivityForResult(intent, type);*/
        }

        else if (view.equals(btChange)) {
            Intent intent = new Intent(this, AppLockActivity.class);
            intent.putExtra(AppLock.TYPE, AppLock.CHANGE_PASSWORD);
            intent.putExtra(AppLock.MESSAGE,
                    getString(R.string.enter_old_passcode));
            startActivityForResult(intent, AppLock.CHANGE_PASSWORD);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case AppLock.DISABLE_PASSLOCK:
                break;
            case AppLock.ENABLE_PASSLOCK:
            case AppLock.CHANGE_PASSWORD:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, getString(R.string.setup_passcode),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        updateUI();
    }

    private void updateUI() {
        if (LockManager.getInstance().getAppLock().isPasscodeSet()) {
            btOnOff.setText(R.string.disable_passcode);
            btOnOff.setChecked(true);
            btChange.setEnabled(true);
        } else {
            btOnOff.setText(R.string.enable_passcode);
            btOnOff.setChecked(false);
            btChange.setEnabled(false);
        }
    }
}
