package ewulusen.kittykatt;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class pve_battlefield extends AppCompatActivity {
    Intent intent;
    String message;
    TextView c1,c2, c3, c4, c5, c6, c7, e1, e2, e3, e4, e5, e6, e7,green,mecs;
    String[] units;
    String[] eunits={"0.0","0.0","0.0","0.0","0.0","0.0","0.0"};
    int battlefild[][]=new int[6][5];
    Double yPower;
    Integer hpower,ehpower;
    ImageView back;
    databaseHelper userDB;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pve__battlefield);
        userDB = new databaseHelper(this);
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<6;j++)
            {
                battlefild[i][j]=0;
            }
        }
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);
        e7 = findViewById(R.id.e7);
        mecs=findViewById(R.id.mecspve);
        green = findViewById(R.id.a_2);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        back=findViewById(R.id.back);
        units = message.split(",");
        yPower = getPower(units);
        generate_enemy(yPower);
        hpower=getHeighestPower(units);
        startBF();
        lisztenerek();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startBF() {

        for (int i = 1; i < units.length; i++) {
            if (units[i].equals("0")) {
                switch (i) {
                    case 1:
                        c1.setBackgroundResource(R.mipmap.green);
                        break;
                    case 2:
                        c2.setBackgroundResource(R.mipmap.green);

                        break;
                    case 3:
                        c3.setBackgroundResource(R.mipmap.green);
                        break;
                    case 4:
                        c4.setBackgroundResource(R.mipmap.green);
                        break;
                    case 5:
                        c5.setBackgroundResource(R.mipmap.green);
                        break;
                    case 6:
                        c6.setBackgroundResource(R.mipmap.green);
                        break;
                    case 7:
                        c7.setBackgroundResource(R.mipmap.green);
                        break;

                }
            }
            // ha nem 0 van ott akkor textbe ki írjuk menyni van
            else
            {
                switch (i) {
                    case 1:
                        c1.setBackgroundResource(R.mipmap.c1);
                        c1.setText(units[i]);
                        battlefild[0][0]=1;
                        if(i==hpower)
                        {
                            c1.setBackgroundResource(R.mipmap.c1_j);
                            mecs.setText("Kezdődik a mecs Cica ijászod kezd");
                            setTargets(0,0,1,1);
                        }
                        break;
                    case 2:
                        c2.setBackgroundResource(R.mipmap.c2);
                        c2.setText(units[i]);
                        battlefild[1][0]=2;
                        if(i==hpower)
                        {
                            c2.setBackgroundResource(R.mipmap.c2_j);
                            mecs.setText("Kezdődik a mecs Cica lovagod kezd");
                            setTargets(1,0,2,1);
                        }
                        break;
                    case 3:
                        c3.setBackgroundResource(R.mipmap.c3);
                        c3.setText(units[i]);
                        battlefild[2][0]=3;
                        if(i==hpower)
                        {
                            c3.setBackgroundResource(R.mipmap.c3_j);
                            mecs.setText("Kezdődik a mecs Cica kentaurod kezd");
                        }
                        break;
                    case 4:
                        c4.setBackgroundResource(R.mipmap.c4);
                        c4.setText(units[i]);
                        battlefild[3][0]=4;
                        if(i==hpower)
                        {
                            c4.setBackgroundResource(R.mipmap.c4_j);
                            mecs.setText("Kezdődik a mecs Cica vikinged kezd");
                        }
                        break;
                    case 5:
                        c5.setBackgroundResource(R.mipmap.c5);
                        c5.setText(units[i]);
                        battlefild[4][0]=5;
                        if(i==hpower)
                        {
                            c5.setBackgroundResource(R.mipmap.c5_j);
                            mecs.setText("Kezdődik a mecs Cica bandíta kezd");
                        }
                        break;
                    case 6:
                        c6.setBackgroundResource(R.mipmap.c6);
                        c6.setText(units[i]);
                        battlefild[5][0]=6;
                        if(i==hpower)
                        {
                            c6.setBackgroundResource(R.mipmap.c6_j);
                            mecs.setText("Kezdődik a mecs Cica kalózod kezd");
                        }
                        break;
                    case 7:
                        c7.setBackgroundResource(R.mipmap.c7);
                        c7.setText(units[i]);
                        battlefild[6][0]=7;
                        if(i==hpower)
                        {
                            c7.setBackgroundResource(R.mipmap.c7_j);
                            mecs.setText("Kezdődik a mecs Cica cyborgod kezd");
                        }
                        break;

                }
            }
        }//most az ellenséggel csináljuk meg ugyan ezt
        for (int i = 0; i < 7; i++) {
            if (eunits[i].equals("0.0")) {
                switch (i) {
                    case 0:
                        e1.setBackgroundResource(R.mipmap.green);
                        break;
                    case 1:
                        e2.setBackgroundResource(R.mipmap.green);
                        break;
                    case 2:
                        e3.setBackgroundResource(R.mipmap.green);
                        break;
                    case 3:
                        e4.setBackgroundResource(R.mipmap.green);
                        break;
                    case 4:
                        e5.setBackgroundResource(R.mipmap.green);
                        break;
                    case 5:
                        e6.setBackgroundResource(R.mipmap.green);
                        break;
                    case 6:
                        e7.setBackgroundResource(R.mipmap.green);
                        break;

                }
            }
            // ha nem 0 van ott akkor textbe ki írjuk menyni van
            else
            {
                switch (i) {
                    case 0:
                        e1.setBackgroundResource(R.mipmap.e1);
                        e1.setText(eunits[i]);
                        battlefild[0][5]=11;
                        break;
                    case 1:
                        e2.setBackgroundResource(R.mipmap.e2);
                        e2.setText(eunits[i]);
                        battlefild[1][5]=12;
                        break;
                    case 2:
                        e3.setBackgroundResource(R.mipmap.e3);
                        e3.setText(eunits[i]);
                        battlefild[2][5]=13;
                        break;
                    case 3:
                        e4.setBackgroundResource(R.mipmap.e4);
                        e4.setText(eunits[i]);
                        battlefild[3][5]=14;
                        break;
                    case 4:
                        e5.setBackgroundResource(R.mipmap.e5);
                        e5.setText(eunits[i]);
                        battlefild[4][5]=15;
                        break;
                    case 5:
                        e6.setBackgroundResource(R.mipmap.e6);
                        e6.setText(eunits[i]);
                        battlefild[5][5]=16;
                        break;
                    case 6:
                        e7.setBackgroundResource(R.mipmap.e7);
                        e7.setText(eunits[i]);
                        battlefild[6][5]=17;
                        break;

                }
            }
        }
    }

    public Double getPower(String[] unit) {
        double power = 0;
        for (int i = 1; i < units.length; i++) {
            if (!units[i].equals("0")) {
                Cursor seged = userDB.getUnit(Integer.toString(i));
                seged.moveToNext();
                power = power + (Double.parseDouble(seged.getString(3)) * Double.parseDouble(units[i]));
            }
        }

        return power;
    }
    public void lisztenerek()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = userDB.backLogin(units[0]);
                final Intent intent;
                intent = new Intent(pve_battlefield.this, start_screen.class);
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
            }
        });
    }
    public void generate_enemy(Double power)
    {
       while (power>1)
       {
           Random rand = new Random();
           int i = (rand.nextInt(6));
           i=i+1;
           Log.d("enemy",Integer.toString(i));
           Cursor seged = userDB.getUnit(Integer.toString(i));
                seged.moveToNext();
                //Log.d("seged",seged.toString());
                eunits[(i-1)]=Double.toString(Double.parseDouble(eunits[(i-1)])+1.0);
                power = power - (Double.parseDouble(seged.getString(3)));
        }
    }
    public Integer getHeighestPower(String[] datas)
    {
        double power=0;
        int hely=0;
        for (int i = 1; i < datas.length; i++) {
            if (!units[i].equals("0")) {
                Cursor seged = userDB.getUnit(Integer.toString(i));
                seged.moveToNext();
                if (power < Double.parseDouble(seged.getString(3)) * Double.parseDouble(units[i])) {
                    power = Double.parseDouble(seged.getString(3)) * Double.parseDouble(units[i]);
                    hely=i;
                }
            }
        }
       return hely;
    }
    public void setTargets(int y,int x,int faj,int oldal)
    {
        Cursor seged = userDB.getUnit(Integer.toString(faj));
        seged.moveToNext();
        int move=Integer.parseInt(seged.getString(4));
        String type=seged.getString(6);
        if(type.equals("A") || type.equals("C"))
        {
            getTargets(1);
        }



    }
    public void getTargets(int type)
    {
        
    }
}
