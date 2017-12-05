package ewulusen.kittykatt;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class cica extends AppCompatActivity {
    public static Intent intent;
    public static String message ;
    public static String[] datas ;
    MediaPlayer miau;
    ImageView cicaKatt,back;
    TextView textView;
    databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cica);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");
        textView = findViewById(R.id.welcommeVar);
        textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + String.format("%.0f",Double.parseDouble(datas[2])) + " pénzed van!");
        penzNovel();
        userDB = new databaseHelper(this);
        cicaKatt = findViewById(R.id.cicaImage);
        back = findViewById(R.id.back);
        miau = new MediaPlayer();
        lisztenerek();
        cicaTapp();
        mentes();

    }
    public void penzNovel()
    {
        final Handler mHandler = new Handler();
        final boolean wasRun = true;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(wasRun){
                    if(!datas[11].equals("0"))
                    {
                        datas[2]=Double.toString(Double.parseDouble(datas[2])+(Double.parseDouble(datas[23])*0.1));
                    }
                    if(!datas[12].equals("0"))
                    {
                        datas[2]=Double.toString(Double.parseDouble(datas[2])+(Double.parseDouble(datas[24])*0.1));
                    }
                    if(!datas[13].equals("0"))
                    {
                        datas[2]=Double.toString(Double.parseDouble(datas[2])+(Double.parseDouble(datas[25])*0.1));
                    }
                    if(!datas[14].equals("0"))
                    {
                        datas[2]=Double.toString(Double.parseDouble(datas[2])+(Double.parseDouble(datas[26])*0.1));
                    }
                    datas[2]=Double.toString(Double.parseDouble(datas[2])+(Double.parseDouble(datas[22])*0.1));
                    textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + String.format("%.0f",Double.parseDouble(datas[2])) + " pénzed van!");

                }
                mHandler.postDelayed(this, 2500);
            }
        },1000);
    }
    public void mentes()
    {
        final Handler mHandlerSave = new Handler();

        mHandlerSave.postDelayed(new Runnable() {
            @Override
            public void run() {
                userDB.saveData(datas);
                mHandlerSave.postDelayed(this, 10000);
            }
        },1000);
    }
    public void cicaTapp() {
        cicaKatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(miau.isPlaying())
                {
                    miau.reset();
                }

                try {
                    miau.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("miau.mp3");
                    miau.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    miau.prepare();
                    miau.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!datas[11].equals("0"))
                {
                    datas[2]=Double.toString(Math.ceil(Double.parseDouble(datas[2])+Double.parseDouble(datas[23])*0.001));
                }
                if(!datas[12].equals("0"))
                {
                    datas[2]=Double.toString(Math.ceil(Double.parseDouble(datas[2])+Double.parseDouble(datas[24])*0.001));
                }
                if(!datas[13].equals("0"))
                {
                    datas[2]=Double.toString(Math.ceil(Double.parseDouble(datas[2])+Double.parseDouble(datas[25])*0.001));
                }
                if(!datas[14].equals("0"))
                {
                    datas[2]=Double.toString(Math.ceil(Double.parseDouble(datas[2])+Double.parseDouble(datas[26])*0.001));
                }
                datas[2]=Double.toString(Math.ceil(Double.parseDouble(datas[2])+Double.parseDouble(datas[22])*0.001));
                textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + datas[2] + " pénzed van!");

            }
        });

    }
    public void lisztenerek() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB.saveData(datas);
                Cursor data = userDB.backLogin(datas[0]);
                final Intent intent;
                intent = new Intent(cica.this, start_screen.class);
                data.moveToNext();
                String message = data.getString(0) + "," + data.getString(1) + "," + data.getString(3) + "," + data.getString(4) +
                        "," + data.getString(5) + "," + data.getString(6) + "," + data.getString(7) + "," + data.getString(8) + "," + data.getString(9)
                        + "," + data.getString(10) + "," + data.getString(11) + "," + data.getString(12) + "," + data.getString(13) + "," + data.getString(14) +
                        "," + data.getString(15) + "," + data.getString(16) +
                        "," + data.getString(17) + "," + data.getString(18) + "," + data.getString(19) + "," + data.getString(20) + "," + data.getString(21) +
                        "," + data.getString(22) + "," + data.getString(23) + "," + data.getString(24) + "," + data.getString(25) + "," + data.getString(26) +
                        "," + data.getString(27);
                intent.putExtra("datas", message);
                startActivity(intent);
                finish();
            }
        });
    }
}
