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

public class unit extends AppCompatActivity {
    public static Intent intent;
    public static String message ;
    public static String[] datas ;
    MediaPlayer buy,miau;
    ImageView cicaKatt,cicaImg1,cicaImg2,cicaImg3,cicaImg4,cicaImg5,cicaImg6,cicaImg7,back;
    TextView textView,cica1,cica2,cica3,cica4,cica5,cica6,cica7,cicaar1,cicaar2,cicaar3,cicaar4,cicaar5,cicaar6,cicaar7;
    databaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit);
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
        penzNovel();
        userDB = new databaseHelper(this);
        cica1 = findViewById(R.id.cica1);
        cica2 = findViewById(R.id.cica2);
        cica3 = findViewById(R.id.cica3);
        cica4 = findViewById(R.id.cica4);
        cica5 = findViewById(R.id.cica5);
        cica6 = findViewById(R.id.cica6);
        cica7 = findViewById(R.id.cica7);
        cicaImg1 = findViewById(R.id.cicaImg1);
        cicaImg2 = findViewById(R.id.cicaImg2);
        cicaImg3 = findViewById(R.id.cicaImg3);
        cicaImg4 = findViewById(R.id.cicaImg4);
        cicaImg5 = findViewById(R.id.cicaImg5);
        cicaImg6 = findViewById(R.id.cicaImg6);
        cicaImg7 = findViewById(R.id.cicaImg7);
        cicaKatt = findViewById(R.id.cicaImage);
        back = findViewById(R.id.back);
        cicaar1 = findViewById(R.id.cicaar1);
        cicaar2 = findViewById(R.id.cicaar2);
        cicaar3 = findViewById(R.id.cicaar3);
        cicaar4 = findViewById(R.id.cicaar4);
        cicaar5 = findViewById(R.id.cicaar5);
        cicaar6 = findViewById(R.id.cicaar6);
        cicaar7 = findViewById(R.id.cicaar7);
        cica1.setText(datas[3]);
        cica2.setText(datas[4]);
        cica3.setText(datas[5]);
        cica4.setText(datas[6]);
        cica5.setText(datas[7]);
        cica6.setText(datas[8]);
        cica7.setText(datas[9]);

        cicaar1.setText(datas[15].charAt(0)+""+datas[15].charAt(1)+"ezer");
        cicaar2.setText(datas[16].charAt(0)+""+datas[16].charAt(1)+""+datas[16].charAt(2)+"ezer");
        cicaar3.setText(datas[17].charAt(0)+"mil");
        cicaar4.setText(datas[18].charAt(0)+""+datas[18].charAt(1)+"mil");
        cicaar5.setText(datas[19].charAt(0)+""+datas[19].charAt(1)+""+datas[19].charAt(2)+"mil");
        cicaar6.setText(datas[20].charAt(0)+"bil");
        cicaar7.setText(datas[21].charAt(0)+""+datas[21].charAt(1)+"bil");
        buy = new MediaPlayer();
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
        },100);
    }
    public void cicaVesz(String id,String ar,Integer data)
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
            textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + datas[2] + " pénzed van!");
            switch (id) {
                case "1":
                    cica1.setText(datas[data]);
                    break;
                case "2":
                    cica2.setText(datas[data]);
                    break;
                case "3":
                    cica3.setText(datas[data]);
                    break;
                case "4":
                    cica4.setText(datas[data]);
                    break;
                case "5":
                    cica5.setText(datas[data]);
                    break;
                case "6":
                    cica6.setText(datas[data]);
                    break;
                case "7":
                    cica7.setText(datas[data]);
                    break;
            }
        }
        else
        {
            display("Nem sikerült","nincs elég pénzed hogy meg vedd ezt a cicát");
        }
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
    public void lisztenerek()
    {
        cicaImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("1",datas[15],3);
            }
        });
        cicaImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("2",datas[16],4);
            }
        });
        cicaImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("3",datas[17],5);
            }
        });
        cicaImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("4",datas[18],6);
            }
        });
        cicaImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("5",datas[19],7);
            }
        });
        cicaImg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("6",datas[20],8);
            }
        });
        cicaImg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cicaVesz("7",datas[21],9);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB.saveData(datas);
                Cursor data = userDB.backLogin(datas[0]);
                final Intent intent;
                intent = new Intent(unit.this, start_screen.class);
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
                unit.this.finish();
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


