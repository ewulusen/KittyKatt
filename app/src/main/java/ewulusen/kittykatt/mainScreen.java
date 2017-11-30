package ewulusen.kittykatt;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class mainScreen extends AppCompatActivity {
    //* A globális változók deklarálása*//
    public static Intent intent;
    public static String message ;
    public static String[] datas ;
    MediaPlayer buy,miau;
    ImageView cicaKatt,cicaImg1,cicaImg2,cicaImg3,cicaImg4,cicaImg5,cicaImg6,cicaImg7;
    TextView textView,cica1,cica2,cica3,cica4,cica5,cica6,cica7,cicaar1,cicaar2,cicaar3,cicaar4,cicaar5,cicaar6,cicaar7;
    Button v1,v2,v3,v4,v5,sms,PVE,PVP;
    databaseHelper userDB;
    LinearLayout tab3;
    EditText sms_t;
    String sms_sms;
    ListView sms_display;
    DecimalFormat df = new DecimalFormat("0.00##");
    List<String> initialList;
    private ArrayAdapter mAdapter;
    URL url = new URL("http://ewulusen.uw.hu/uzi.php");
    URL url2 = new URL("http://ewulusen.uw.hu/uzi2.php");
    public mainScreen() throws MalformedURLException {
        buy = null;
        miau = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_land);

        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");

        TabHost tb=(TabHost) findViewById(R.id.tabok);
        tb.setup();
        //tab1
        TabHost.TabSpec spec1=tb.newTabSpec("tab One");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Egységek");
        tb.addTab(spec1);
        //tab2
        TabHost.TabSpec spec2=tb.newTabSpec("tab Two");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Gyárak");
        tb.addTab(spec2);
        //tab3
        TabHost.TabSpec spec3=tb.newTabSpec("tab three");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Cicaaa");
        tb.addTab(spec3);
        //tab4
        TabHost.TabSpec spec4=tb.newTabSpec("tab four");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator("specialitások");
        tb.addTab(spec4);
        //tab5
        TabHost.TabSpec spec5=tb.newTabSpec("tab five");
        spec5.setContent(R.id.tab5);
        spec5.setIndicator("harc");
        tb.addTab(spec5);
        sms_display=(ListView) findViewById(R.id.sms_kap);
        cicaKatt=(ImageView) findViewById(R.id.cicaImage);
        textView = findViewById(R.id.welcommeVar);
        textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + String.format("%.0f",Double.parseDouble(datas[2])) + " pénzed van!");
        penzNovel();
        userDB = new databaseHelper(this);
        sms_t=findViewById(R.id.sms_m);
        initialList = new ArrayList<String>(); //load these
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        getmessage();
        sms_display.setAdapter(mAdapter);
        tab3=findViewById(R.id.tab3);
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
        cicaar1 = findViewById(R.id.cicaar1);
        cicaar2 = findViewById(R.id.cicaar2);
        cicaar3 = findViewById(R.id.cicaar3);
        cicaar4 = findViewById(R.id.cicaar4);
        cicaar5 = findViewById(R.id.cicaar5);
        cicaar6 = findViewById(R.id.cicaar6);
        cicaar7 = findViewById(R.id.cicaar7);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        PVE=findViewById(R.id.PVE);
        PVP=findViewById(R.id.PVP);
        PVP.setEnabled(false);
        sms = findViewById(R.id.sms);
        cica1.setText(datas[3]);
        cica2.setText(datas[4]);
        cica3.setText(datas[5]);
        cica4.setText(datas[6]);
        cica5.setText(datas[7]);
        cica6.setText(datas[8]);
        cica7.setText(datas[9]);
        cicaar1.setText(datas[15]+"|");
        cicaar2.setText(datas[16]+"|");
        cicaar3.setText(datas[17]+"|");
        cicaar4.setText(datas[18]+"|");
        cicaar5.setText(datas[19]+"|");
        cicaar6.setText(datas[20]+"|");
        cicaar7.setText(datas[21]);
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
                    textView.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + String.format("%.0f",Double.parseDouble(datas[2])) + " pénzed van!");
                    getmessage();
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
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms_sms=sms_t.getText().toString();
                if (sms_sms.equals(""))
                {
                    display("Hiba","Üres mező kérle töltsd ki");
                }
                else
                {
                   sendmessage();
                }
            }
        });
        PVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent1;
                intent1 = new Intent(mainScreen.this, pve_battlefield.class);
            String message = datas[0]+","+datas[3]+ ","+datas[4]+ ","+datas[5]+ ","+datas[6]+ ","+datas[7]+ ","+datas[8]+ ","+datas[9];
                intent1.putExtra("units", message);
                startActivity(intent1);
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
    private void sendmessage()
    {
        Thread background1 = new Thread(new Runnable() {
            public void run() {
                try {

                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    String data = URLEncoder.encode("name", "UTF-8")
                            + "=" + URLEncoder.encode(datas[1], "UTF-8");

                    data += "&" + URLEncoder.encode("message", "UTF-8") + "="
                            + URLEncoder.encode(sms_sms, "UTF-8");

                    data += "&" + URLEncoder.encode("chanel", "UTF-8")
                            + "=" + URLEncoder.encode("1", "UTF-8");
                    String text = "";
                    wr.write(data);
                    wr.flush();
                    wr.close();
                    BufferedReader reader = null;
                    // Get the server response
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        // Append server response in string
                        sb.append(line + "\n");
                        //Log.d("lsit",line);
                    }
                    text = sb.toString();
                    reader.close();
                    initialList.clear();
                    initialList.add(text);
                }

                catch(Exception ex) {
                    Log.d("hiba",ex.toString());
                }

            }
        });
        // Start Thread
        background1.start();  //After call start method thread called run Method
        sms_t.setText("");
        getmessage();
    }
private void getmessage()
    {
         Thread background2 = new Thread(new Runnable() {
            public void run() {
                try {

                    URLConnection conn = url2.openConnection();
                    conn.setDoOutput(true);
                    BufferedReader reader = null;
                    // Get the server response
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        // Append server response in string
                        sb.append(line + "\n");
                       // Log.d("lsit", line);
                    }
                    String text = "";
                    text = sb.toString();
                    reader.close();
                    initialList.clear();
                    initialList.add(text);
                   // Log.d("hiba", initialList.toString());
                } catch (Exception ex) {
                    Log.d("hiba", ex.toString());
                }
            }
        });
        // Start Thread
        background2.start();  //After call start method thread called run Method
        mAdapter.notifyDataSetChanged();
        sms_display.setAdapter(mAdapter);
    }



}

