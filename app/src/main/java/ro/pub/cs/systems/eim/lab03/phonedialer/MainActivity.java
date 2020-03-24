package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
    }


    private class ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText coronvarius = (EditText)findViewById(R.id.number_edit);

            switch (((Button)v).getId()) {

                case R.id.backspace_button:
                            if (!coronvarius.getText().toString().matches("")) {
                                coronvarius.setText(coronvarius.toString().substring(0, coronvarius.length() - 1));
                            }

                case R.id.call_button:
                            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(
                                        PhoneDialerActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        SyncStateContract.Constants.PERMISSION_REQUEST_CALL_PHONE);
                            } else {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                                startActivity(intent);
                            }

                default:    coronvarius.append(((Button) v).getText());
                            break;
            }
        }
    }
}
