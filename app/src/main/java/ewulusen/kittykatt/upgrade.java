package ewulusen.kittykatt;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class upgrade extends AppCompatActivity {
    public static Intent intent;
    public static String message ;
    public static String[] datas ;

    ImageView back;
    TextView textView;
    databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");
        textView = findViewById(R.id.welcommeVar);
        String money=String.format("%.0f", Double.parseDouble(datas[2]));
        String dumymoney=String.format("%.0f", Double.parseDouble(datas[2]));
        if(dumymoney.length()>6 && dumymoney.length()<9)
        {
            if(dumymoney.length()==7) {
                String money1 = String.valueOf(money.charAt(0));
                String money2 = String.valueOf(money.charAt(1));
                money = money1 + "." + money2 + " mil";
            }
            if(dumymoney.length()==8) {
                String money1 = String.valueOf(money.charAt(0));
                String money2 = String.valueOf(money.charAt(1));
                String money3 = String.valueOf(money.charAt(2));
                money = money1 + "" + money2 + "."+money3+" mil";
            } if(dumymoney.length()==9) {
            String money1 = String.valueOf(money.charAt(0));
            String money2 = String.valueOf(money.charAt(1));
            String money3 = String.valueOf(money.charAt(2));
            String money4 = String.valueOf(money.charAt(3));
            money = money1 + "" + money2 + ""+money3+ "."+money4+" mil";
        }
        }
       textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + money + " pénzed van!");
        back=findViewById(R.id.back);
        userDB = new databaseHelper(this);
        mentes();
        lisztenerek();
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

    public void lisztenerek()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB.saveData(datas);
                Cursor data = userDB.backLogin(datas[0]);
                final Intent intent;
                intent = new Intent(upgrade.this, start_screen.class);
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
    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }




}
