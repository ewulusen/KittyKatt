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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class factory extends AppCompatActivity {
    //* A globális változók deklarálása*//
    public static Intent intent;
    public static String message ;
    public static String[] datas ;
    MediaPlayer buy,miau;
    ImageView cicaKatt,back;
    TextView textView;
    Button v1,v2,v3,v4,v5;
    databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factory);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");
        cicaKatt=(ImageView) findViewById(R.id.cicaImage);
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
        penzNovel();
        userDB = new databaseHelper(this);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        back=findViewById(R.id.back);
        v1.setText("tejgyár lvl: " + datas[10] + " termel: " + String.format("%.0f",Double.parseDouble(datas[22]))  + " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[22])*1.13));
        v2.setText("játék gyár lvl: " + datas[11] + " termel: " + String.format("%.0f",Double.parseDouble(datas[23]))  + " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[23])*1.13));
        v3.setText("doboz gyár lvl: " + datas[12] + " termel: " + String.format("%.0f",Double.parseDouble(datas[24]))  + " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[24])*1.13));
        v4.setText("kaparófa gyár lvl: " + datas[13] + " termel: " + String.format("%.0f",Double.parseDouble(datas[25])) + " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[25])*1.13));
        v5.setText("lézer pointer gyár lvl: " + datas[14] + " termel: " + String.format("%.0f",Double.parseDouble(datas[26]))  + " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[26])*1.13));
        buy = new MediaPlayer();
        miau = new MediaPlayer();
        cicaTapp();
        lisztenerek();
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
    public void gyarVesz(String id,String ar,Integer data)
    {

        if((Double.parseDouble(datas[2])-Double.parseDouble(ar))>=0.0) {
            if(buy.isPlaying())
            {
                buy.stop();
            }

            try {
                buy.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("buy.mp3");
                buy.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                buy.prepare();
                buy.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            datas[2] = Double.toString(Double.parseDouble(datas[2]) - Double.parseDouble(ar));
            datas[data] =Double.toString(Double.parseDouble(datas[data]) + 1);
            textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + String.format("%.0f",Double.parseDouble(datas[2])) + " pénzed van!");
            switch (id) {
                case "1":
                    v1.setText("tejgyár lvl: " + datas[data] + " termel: " + String.format("%.0f",Double.parseDouble(datas[22])+(Double.parseDouble(datas[22])*0.13))+ " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[22])*2.10));
                    datas[22]=Double.toString(Math.ceil(Double.parseDouble(datas[22])+(Double.parseDouble(datas[22])*0.13)));
                    break;
                case "2":
                    v2.setText("játék gyár lvl: " + datas[data] + " termel: " + String.format("%.0f",Double.parseDouble(datas[23])+(Double.parseDouble(datas[23])*0.13))+ " pénzt/s, ára: " +String.format("%.0f",Double.parseDouble(datas[23])*2.10));
                    datas[23]=Double.toString(Double.parseDouble(datas[23])+(Double.parseDouble(datas[23])*0.13));
                    break;
                case "3":
                    v3.setText("doboz gyár lvl: " + datas[data] + " termel: " + String.format("%.0f",Double.parseDouble(datas[24])+(Double.parseDouble(datas[24])*0.13))+" pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[24])*2.10));
                    datas[24]=Double.toString(Double.parseDouble(datas[24])+(Double.parseDouble(datas[22])*0.14));
                    break;
                case "4":
                    v4.setText("kaparófa gyár lvl: " + datas[data] + " termel: " + String.format("%.0f",Double.parseDouble(datas[25])+(Double.parseDouble(datas[25])*0.13))+ " pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[25])*2.10));
                    datas[25]=Double.toString(Double.parseDouble(datas[25])+(Double.parseDouble(datas[25])*0.13));
                    break;
                case "5":
                    v5.setText("lézer pointer gyár lvl: " + datas[data] + " termel: " + String.format("%.0f",Double.parseDouble(datas[26])+(Double.parseDouble(datas[26])*0.13))+" pénzt/s, ára: " + String.format("%.0f",Double.parseDouble(datas[26])*2.10));
                    datas[26]=Double.toString(Double.parseDouble(datas[26])+(Double.parseDouble(datas[26])*0.13));
                    break;
            }

        }
        else
        {
            display("Nem sikerült","nincs elég pénzed, hogy fejleszd ezt a gyárat");
        }
    }
    public void lisztenerek()
    {
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gyarVesz("1",Double.toString(Math.ceil(Double.parseDouble(datas[22])*1.13)),10);
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gyarVesz("2",Double.toString(Math.ceil(Double.parseDouble(datas[23])*1.13)),11);
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gyarVesz("3",Double.toString(Math.ceil(Double.parseDouble(datas[24])*1.13)),12);
            }
        });
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gyarVesz("4",Double.toString(Math.ceil(Double.parseDouble(datas[25])*1.13)),13);
            }
        });
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gyarVesz("5",Double.toString(Math.ceil(Double.parseDouble(datas[26])*1.13)),14);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB.saveData(datas);
                Cursor data = userDB.backLogin(datas[0]);
                final Intent intent;
                intent = new Intent(factory.this, start_screen.class);
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
                Thread.interrupted();
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
