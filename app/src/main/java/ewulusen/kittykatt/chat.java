package ewulusen.kittykatt;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity {
    public static Intent intent;
    public static String message ;
    public static String[] datas ;
    MediaPlayer miau;
    ImageView cicaKatt,back;
    TextView textView;
    Button sms;
    EditText sms_t;
    String sms_sms;
    ListView sms_display;
    databaseHelper userDB;
    Integer sorszam=0;
    List<String> initialList;
    private ArrayAdapter mAdapter;
    URL url = new URL("http://ewulusen.uw.hu/uzi.php");
    URL url2 = new URL("http://ewulusen.uw.hu/uzi2.php");

    public chat() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");
        sms_display=(ListView) findViewById(R.id.sms_kap);
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
        sms = findViewById(R.id.sms);
        userDB = new databaseHelper(this);
        sms_t=findViewById(R.id.sms_m);
        initialList = new ArrayList<String>(); //load these
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, initialList);
        getmessage();
        mAdapter.notifyDataSetChanged();
        sms_display.setAdapter(mAdapter);
        back=findViewById(R.id.back);
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
    public void lisztenerek()
    {
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB.saveData(datas);
                Cursor data = userDB.backLogin(datas[0]);
                final Intent intent;
                intent = new Intent(chat.this, start_screen.class);
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
        final Integer[] sorszam_teszt = {0};
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
                    sorszam_teszt[0] =conn.getContentLength();
                   // Log.d("Sorszam",sorszam_teszt[0].toString());
                    // Read Server Response
                    if(sorszam_teszt[0]==sorszam)
                    {}
                    else {
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
                        mAdapter.notifyDataSetChanged();
                    }
                    sorszam=conn.getContentLength();
                    // Log.d("hiba", initialList.toString());
                } catch (Exception ex) {
                    Log.d("hiba", ex.toString());
                }
            }
        });
        // Start Thread
        background2.start();  //After call start method thread called run Method

        //sms_display.setAdapter(mAdapter);

       //
    }

}
