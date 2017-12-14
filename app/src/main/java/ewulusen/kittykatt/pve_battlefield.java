package ewulusen.kittykatt;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class pve_battlefield extends AppCompatActivity {
    Intent intent;
    String message;
    TextView mecs;
    TextView a_1, a_2, a_3, a_4, a_5, a_6;
    TextView b_1, b_2, b_3, b_4, b_5, b_6;
    TextView c_1, c_2, c_3, c_4, c_5, c_6;
    TextView d_1, d_2, d_3, d_4, d_5, d_6;
    TextView e_1, e_2, e_3, e_4, e_5, e_6;
    TextView f_1, f_2, f_3, f_4, f_5, f_6;
    TextView g_1, g_2, g_3, g_4, g_5, g_6;
    String[] units;
    TextView[] filds = new TextView[42];
    String[] eunits = {"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
    int battlefild[][] = new int[7][6];
    Double yPower;
    double pluszMoney=0.0;
    int[] hpower, ehpower;
    ImageView back;
    databaseHelper userDB;
    int[] images = {R.mipmap.green, R.mipmap.c1, R.mipmap.c2, R.mipmap.c3, R.mipmap.c4,
            R.mipmap.c5, R.mipmap.c6, R.mipmap.c7, R.mipmap.e1, R.mipmap.e2, R.mipmap.e3, R.mipmap.e4,
            R.mipmap.e5, R.mipmap.e6, R.mipmap.e7, R.mipmap.c1_j, R.mipmap.c2_j, R.mipmap.c3_j,
            R.mipmap.c4_j, R.mipmap.c5_j, R.mipmap.c6_j, R.mipmap.c7_j, R.mipmap.ec1_j, R.mipmap.ec2_j,
            R.mipmap.ec3_j, R.mipmap.ec4_j, R.mipmap.ec5_j, R.mipmap.ec6_j, R.mipmap.ec7_j,R.mipmap.green_j};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pve__battlefield);
        /*r1 = findViewById(R.id.lathato1);
        r1.setVisibility(View.INVISIBLE);*/
        filds[0] = a_1 = findViewById(R.id.a_1);
        filds[1] = a_2 = findViewById(R.id.a_2);
        filds[2] = a_3 = findViewById(R.id.a_3);
        filds[3] = a_4 = findViewById(R.id.a_4);
        filds[4] = a_5 = findViewById(R.id.a_5);
        filds[5] = a_6 = findViewById(R.id.a_6);
        filds[6] = b_1 = findViewById(R.id.b_1);
        filds[7] = b_2 = findViewById(R.id.b_2);
        filds[8] = b_3 = findViewById(R.id.b_3);
        filds[9] = b_4 = findViewById(R.id.b_4);
        filds[10] = b_5 = findViewById(R.id.b_5);
        filds[11] = b_6 = findViewById(R.id.b_6);
        filds[12] = c_1 = findViewById(R.id.c_1);
        filds[13] = c_2 = findViewById(R.id.c_2);
        filds[14] = c_3 = findViewById(R.id.c_3);
        filds[15] = c_4 = findViewById(R.id.c_4);
        filds[16] = c_5 = findViewById(R.id.c_5);
        filds[17] = c_6 = findViewById(R.id.c_6);
        filds[18] = d_1 = findViewById(R.id.d_1);
        filds[19] = d_2 = findViewById(R.id.d_2);
        filds[20] = d_3 = findViewById(R.id.d_3);
        filds[21] = d_4 = findViewById(R.id.d_4);
        filds[22] = d_5 = findViewById(R.id.d_5);
        filds[23] = d_6 = findViewById(R.id.d_6);
        filds[24] = e_1 = findViewById(R.id.e_1);
        filds[25] = e_2 = findViewById(R.id.e_2);
        filds[26] = e_3 = findViewById(R.id.e_3);
        filds[27] = e_4 = findViewById(R.id.e_4);
        filds[28] = e_5 = findViewById(R.id.e_5);
        filds[29] = e_6 = findViewById(R.id.e_6);
        filds[30] = f_1 = findViewById(R.id.f_1);
        filds[31] = f_2 = findViewById(R.id.f_2);
        filds[32] = f_3 = findViewById(R.id.f_3);
        filds[33] = f_4 = findViewById(R.id.f_4);
        filds[34] = f_5 = findViewById(R.id.f_5);
        filds[35] = f_6 = findViewById(R.id.f_6);
        filds[36] = g_1 = findViewById(R.id.g_1);
        filds[37] = g_2 = findViewById(R.id.g_2);
        filds[38] = g_3 = findViewById(R.id.g_3);
        filds[39] = g_4 = findViewById(R.id.g_4);
        filds[40] = g_5 = findViewById(R.id.g_5);
        filds[41] = g_6 = findViewById(R.id.g_6);
        userDB = new databaseHelper(this);
        mecs = findViewById(R.id.mecspve);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        back = findViewById(R.id.back);
        units = message.split(",");
        yPower = getPower(units);
        generate_enemy(yPower);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (j == 0 && !units[i + 1].equals("0")) {
                    battlefild[i][j] = i + 1;
                }
                if (j == 5 && !eunits[i + 1].equals("0.0")) {
                    //Log.d("enemy",eunits[i]);
                    battlefild[i][j] = i + 10;
                }
            }
        }
        hpower = getHeighestPower(units,0);
        ehpower = getHeighestPower(eunits,9);
        mecs.setText("A csata elkezdődött, te kezdesz!");
        drawBF();
        getBFTargets();
        lisztenerek();

    }

    public void drawBF() {
        Log.d("művelet","rajzolok");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (battlefild[i][j] == 0) {
                    int elem = (i * 6) + j;
                    filds[elem].setBackgroundResource(R.mipmap.green);
                } else if (battlefild[i][j] > 0 && battlefild[i][j] < 10) {
                    int elem = (i * 6) + j;
                    filds[elem].setBackgroundResource(images[battlefild[i][j]]);
                    filds[elem].setText(units[battlefild[i][j]]);
                    // Log.d("powwe",Integer.toString(hpower));
                    if (battlefild[i][j]==hpower[0]) {
                        // Log.d("bf",Integer.toString(elem));
                        filds[elem].setBackgroundResource(images[(battlefild[i][j] + 14)]);
                    }
                } else if (battlefild[i][j] >= 10) {
                    int elem = (i * 6) + j;
                    filds[elem].setBackgroundResource(images[((battlefild[i][j] - 9) + 7)]);
                    // Log.d("enemy2",eunits[(battlefild[i][j]-10)]);
                    filds[elem].setText(eunits[(battlefild[i][j] - 9)]);
                }
            }
        }
    }

    public void turn(int oldal)
    {
        Log.d("művelet","körváltás");
        if(oldal==0)
        {
           int igen= haveIWin(oldal);
           if(igen==0) {
               getBFTargets();
           }
        }
        if(oldal==1)
        {
            int igen= haveIWin(oldal);
            if(igen==0) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getBFTargetsEnemy();
                    }
                }, 2000);
            }
            drawBF();
        }

    }

    public void getBFTargets() {
        Log.d("művelet","keresem magam");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (battlefild[i][j] == hpower[0]) {
                    setTargets(i, j, battlefild[i][j]);
                }
            }
        }
    }

    public void getBFTargetsEnemy() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
               // Log.d("ellensegmuvelet",Integer.toString(ehpower[0]));
                if (battlefild[i][j] == ehpower[0]) {
                    setTargets(i, j, battlefild[i][j]);
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
    public void lisztenerek() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitBack();
            }
        });
    }
    public void generate_enemy(Double power) {
        while (power > 1) {
            Random rand = new Random();
            int i = (rand.nextInt(6));
            i = i + 1;
            // Log.d("enemy",Integer.toString(i));
            Cursor seged = userDB.getUnit(Integer.toString(i));
            seged.moveToNext();
            //Log.d("seged",seged.toString());
            eunits[i] = Double.toString(Double.parseDouble(eunits[i]) + 1);
           // Log.d("enemyk","i="+i+" ki="+eunits[i]);
            power = power - (Double.parseDouble(seged.getString(3)));
        }
    }
    public int[] getHeighestPower(String[] datas,int plus) {
        double[][] power = new double[8][2];
       // Log.d("power","hossz="+datas.length);
        int[] hely = new int[8];
        int j = 1;

        if(plus==0)
        {
            for (int i = 1; i <datas.length; i++) {
                if (!datas[i].equals("0")) {
                    //Log.d("power","i="+i);
                    Cursor seged = userDB.getUnit(Integer.toString(i));
                    seged.moveToNext();
                    power[i][0] = Double.parseDouble(seged.getString(3)) * Double.parseDouble(units[i]);
                    power[i][1] = i+plus;
                }
            }
        }
        else
        {
            for (int i = 1; i <datas.length; i++) {
                if (!datas[i].equals("0.0")) {
                    //Log.d("power","i="+i);
                    Cursor seged = userDB.getUnit(Integer.toString(i));
                    seged.moveToNext();
                    power[i][0] = Double.parseDouble(seged.getString(3)) * Double.parseDouble(eunits[i]);
                    power[i][1] = i+plus;
                }
            }
        }

        Arrays.sort(power, new Comparator<double[]>() {
            @Override
            public int compare(double[] doubles, double[] t1) {
                final Double d1 = doubles[0];
                final Double d2 = t1[0];
                return Double.compare(d2, d1);
            }
        });
        int i = 0;
        for (final double[] s : power) {
            if (s[1] == 0.0) {
                break;
            }
            hely[i] = (int) s[1];
           // Log.d("power",Integer.toString(hely[i]));
            i++;
        }
       // kiir(hely);
        return hely;
    }

    public void setTargets(int y, int x, int faj) {
        Log.d("művelet","lehetőségek kiválasztása");
        Log.d("fajok","fajid: "+faj);
        if (faj<8) {
            Cursor seged = userDB.getUnit(Integer.toString(faj));
            seged.moveToNext();
            int move = Integer.parseInt(seged.getString(4));
            String type = seged.getString(6);
           // Log.d("fajok","fajid: "+Integer.toString(faj)+" típusa: "+type );
            if (type.equals("A") || type.equals("B")) {
                getTargets(faj, false);
                whereToMove(y,x,move,faj,0);
            }
            else if (type.equals("C"))
            {
                whereToMove(y,x,move,faj,0);
            }
        }
        else
        {
            int efaj=faj-9;
            Cursor seged = userDB.getUnit(Integer.toString(efaj));
            seged.moveToNext();
            int move = Integer.parseInt(seged.getString(4));
            String type = seged.getString(6);
            if (type.equals("A")) {
            //    Log.d("ott","ott");
                attackTarget(faj,hpower[0],1);
            }
            else if (type.equals("B")) {
               // Log.d("ott","ott");
                attackTarget(faj,hpower[0],1);
            }
            else if (type.equals("C"))
            {
             //   Log.d("itt","itt");
                whereToMove(y,x,move,faj,1);
            }

        }


    }
    public void getTargets(final int faj,boolean remove) {
        if(!remove) {
            Log.d("művelet","ellenfél színezése");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                   // Log.d("bf"," y: "+Integer.toString(i)+" x: "+Integer.toString(j)+" : "+Integer.toString(battlefild[i][j]));
                    if(faj==1 || faj==3) {
                        if (battlefild[i][j] > 7) {
                            final int finalI = i;
                            final int finalJ = j;
                            int elem = (i * 6) + j;
                            filds[elem].setBackgroundResource(images[((battlefild[i][j] - 9) + 21)]);
                            filds[elem].setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    attackTarget(faj, battlefild[finalI][finalJ], 0);
                                }
                            });
                        }
                    }
                }
            }
        }
        else
        {
            Log.d("művelet","radír jelölés");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    //Log.d("bf"," y: "+Integer.toString(i)+" x: "+Integer.toString(j)+" : "+Integer.toString(battlefild[i][j]));
                        int elem = (i * 6) + j;
                        filds[elem].setOnClickListener(null);
                    }
                }
        }
    }

    public void attackTarget(int ki, int kit,int oldal) {
       Log.d("ki",Integer.toString(ki));
        Log.d("kit",Integer.toString(kit));
        if (oldal == 0) {
            double dmg = 0;
            double hp = 0;
            double hp_1 = 0;
            Cursor seged = userDB.getUnit(Integer.toString(ki));
            seged.moveToNext();
            dmg = Double.parseDouble(seged.getString(2)) * Double.parseDouble(units[ki]);
            seged = userDB.getUnit(Integer.toString((kit - 9)));
            seged.moveToNext();
            hp = Double.parseDouble(seged.getString(1)) * Double.parseDouble(eunits[(kit - 9)]);
            hp_1 = Double.parseDouble(seged.getString(1));
            if ((hp - dmg) <= 0) {
                mecs.setText("Támadtál, sebzésed: " + dmg + " az egység meghalt");
                charDie(kit);
            }
            else {
                double kulombseg = dmg / hp_1;
                String halottak = Double.toString(kulombseg);
                String[] exp = halottak.split("\\.");
                // Log.d("halottak", exp.toString());
                mecs.setText("Támadtál, sebzésed: " + Double.toString(dmg) + " az egységből  " + exp[0] + " halt meg ");
                charSurvived(kit, exp[0]);
            }
            getTargets(1, true);
            hpower = changeArray(hpower);
            //kiir((hpower));
            turn(1);
        }
        else
        {
            double dmg = 0;
            double hp = 0;
            double hp_1 = 0;
            Cursor seged = userDB.getUnit(Integer.toString(ki - 9));
            seged.moveToNext();
            dmg = Double.parseDouble(seged.getString(2)) * Double.parseDouble(eunits[ki-9]);
            seged = userDB.getUnit(Integer.toString((kit)));
            seged.moveToNext();
            hp = Double.parseDouble(seged.getString(1)) * Double.parseDouble(units[(kit)]);
            hp_1 = Double.parseDouble(seged.getString(1));
            if ((hp - dmg) <= 0) {
                mecs.setText("Támadtak, sebzés: " + dmg + " az egységed meghalt");
                charDie(kit);
            } else {
                double kulombseg = dmg / hp_1;
                String halottak = Double.toString(kulombseg);
                String[] exp = halottak.split("\\.");
                // Log.d("halottak", exp.toString());
                mecs.setText("Támadtak, sebzés: " + Double.toString(dmg) + " az egységedből  " + exp[0] + " halt meg ");
                charSurvived(kit, exp[0]);
            }
                ehpower = changeArray(ehpower);
                turn(0);

        }
    }
    public void charDie(int kit) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (battlefild[i][j] == kit) {
                    int elem = (i * 6) + j;
                    filds[elem].setBackgroundResource(images[0]);
                    filds[elem].setText("");
                    if (kit > 9) {
                        eunits[kit - 9] = "0.0";
                       // kiir(ehpower);
                        ehpower=changeArrayCatDie(ehpower,kit);
                       // kiir(ehpower);
                    } else {
                        units[kit] = "0";
                       // kiir(hpower);
                        hpower=changeArrayCatDie(hpower,kit);
                       // kiir(hpower);
                    }
                    battlefild[i][j]=0;
                }
            }
        }
    }
    public void charSurvived(int kit,String halott) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (battlefild[i][j] == kit) {
                    int elem = (i * 6) + j;
                    if (kit > 8) {
                        double kulombseg=(Double.parseDouble(eunits[(kit - 9)])-Double.parseDouble(halott));
                        eunits[kit - 9] = Double.toString(kulombseg);
                        filds[elem].setText(Double.toString(kulombseg));
                    } else {
                        double kulombseg=(Double.parseDouble(units[(kit)])-Double.parseDouble(halott));
                        units[kit] = Double.toString(kulombseg);
                        filds[elem].setText(Double.toString(kulombseg));
                    }
                }
            }
        }
    }
    public int[] changeArray(int[] powerek)
    {
        int hely=0;
        for(int i=0;i<powerek.length;i++)
        {
            if(powerek[i]==0)
            {hely=i-1;break;}
        }
        int seged=powerek[0];
        for(int i=0;i<(hely+1);i++)
        {
            if(i==hely)
            {
                powerek[hely]=seged;
                break;
            }
            powerek[i]=powerek[(i+1)];

        }
        return powerek;
    }
    public int[] changeArrayCatDie(int[] powerek,int kit)
    {
        int hely=0;
        for(int i=0;i<powerek.length;i++)
        {
            if(powerek[i]==kit)
            {hely=i;break;}
        }
        //Log.d("kit öltek","kit="+kit+" hol="+hely);
        for(int i=hely;i<powerek.length-1;i++)
        {
            powerek[i]=powerek[(i+1)];
         //   Log.d("array","kit="+powerek[i]+" i="+i);
        }
        powerek[powerek.length-1]=0;
        return powerek;
    }
    public void whereToMove(final int y, final int x, final int move, final int faj, final int oldal) {
        if (oldal == 0) {
            Log.d("művelet","hová menjek");
            if ((y - 1) > -1) {
                if (battlefild[y - 1][x] == 0) {
                   // Log.d("bejött","y-1 "+y+" "+x);
                    int elem = ((y - 1) * 6) + x;
                    filds[elem].setBackgroundResource(images[29]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            moveToThere(faj,(y - 1), x, 0,move,y,x);
                                                    }
                    });
                }
                else if((battlefild[y - 1][x]>9))
                {
                    int elem = ((y - 1) * 6) + x;
                    filds[elem].setBackgroundResource(images[((battlefild[y-1][x] - 9) + 21)]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            attackTarget(faj, battlefild[y-1][x], 0);
                        }
                    });

                }
            }
            if ((y + 1) < 6) {
                if (battlefild[y + 1][x] == 0) {
                   // Log.d("bejött","y+1 "+y+" "+x);
                   int elem = ((y + 1) * 6) + x;
                    filds[elem].setBackgroundResource(images[29]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            moveToThere(faj, (y + 1), x, 0,move,y,x);

                        }
                    });
                }
                else if((battlefild[y + 1][x]>9))
                {
                    int elem = ((y + 1) * 6) + x;
                    filds[elem].setBackgroundResource(images[((battlefild[y+1][x] - 9) + 21)]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            attackTarget(faj, battlefild[y+1][x], 0);
                        }
                    });

                }
            }
            if ((x - 1) > -1) {
                if (battlefild[y][x - 1] == 0) {
                   // Log.d("bejött","x-1 "+x+" "+y);
                    int elem = (y * 6) + (x - 1);
                    filds[elem].setBackgroundResource(images[29]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            moveToThere(faj, y, (x - 1), 0,move,y,x);

                        }
                    });
                }
                else if((battlefild[y][x-1]>9))
                {
                    int elem = ((y) * 6) + x-1;
                    filds[elem].setBackgroundResource(images[((battlefild[y][x-1] - 9) + 21)]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            attackTarget(faj, battlefild[y][x-1], 0);
                        }
                    });

                }
            }
            if ((x + 1) < 6) {
                if (battlefild[y][x + 1] == 0) {
                  //  Log.d("bejött","x+1 "+x+" "+y);
                    int elem = (y * 6) + (x + 1);
                    filds[elem].setBackgroundResource(images[29]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            moveToThere(faj, y, (x + 1), 0,move,y,x);

                        }
                    });
                }
                else if((battlefild[y][x+1]>9))
                {
                    int elem = ((y) * 6) + x+1;
                    filds[elem].setBackgroundResource(images[((battlefild[y][x+1] - 9) + 21)]);
                    filds[elem].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            attackTarget(faj, battlefild[y][x+1], 0);
                        }
                    });

                }
            }
           }
        else {
            Log.d("művelet","ellenfél hová megy");
                       moveEnemyThere(y, x,faj,move);
              }
    }
    public void moveToThere(int faj,int y,int x,int oldal,int move,int i,int j)
    {
        //Log.d("move","x="+x+" y="+y+" i="+i+" j="+j+" move="+move);
       // Log.d("ki lép","s"+faj+" mozgás="+move);
        Log.d("művelet","ide megyek");
       battlefild[y][x]=faj;
       battlefild[i][j]=0;
        int elem = 0;
        elem = (i * 6) + j ;
        filds[elem].setBackgroundResource(images[0]);
        filds[elem].setText("");
        if(i-1!=y && i-1>-1)
        {
            elem = ((i-1) * 6) + j ;
            filds[elem].setBackgroundResource(images[0]);
            filds[elem].setText("");
        }
        if(i+1!=y && i+1<6)
        {
            elem = ((i+1) * 6) + j ;
            filds[elem].setBackgroundResource(images[0]);
            filds[elem].setText("");
        }
        if(j+1!=x && j+1<6)
        {
            elem = (i * 6) + (j+1) ;
            filds[elem].setBackgroundResource(images[0]);
            filds[elem].setText("");
        } if(j-1!=x && j-1>-1)
        {
            elem = (i * 6) + (j-1) ;
            filds[elem].setBackgroundResource(images[0]);
            filds[elem].setText("");
        }
     if(oldal==0) {
         elem = (y * 6) + x;
         filds[elem].setBackgroundResource(images[faj]);
         filds[elem].setText(units[faj]);
     }
     else{
         elem = (y * 6) + x;
         filds[elem].setBackgroundResource(images[(faj- 9) + 7]);
         filds[elem].setText(eunits[faj-9]);
     }
        drawBF();
        move--;

       if (move>0)
       {
           mecs.setText("még "+move+" lépésed van");
           getTargets(1, true);
           getTargets(faj, false);
           whereToMove(y,x,move,faj,oldal);
       }
       else
       {
           if(oldal==0) {
               getTargets(1, true);
               hpower = changeArray(hpower);
               turn(1);
           }
           else {
               getTargets(1, true);
               ehpower = changeArray(ehpower);
               turn(0);
           }
       }
    }
    public void moveEnemyThere(int y,int x,int faj,int move)
    {
        Log.d("művelet","ellenfél mozgása");
        //fel,lejobbra,balra
        int[] irany={1,2,3,4};
        Random rand = new Random();
        int i = (rand.nextInt(3));

        if(irany[i]==1) {
            if ((y - 1) > -1) {
                if (battlefild[y - 1][x] == 0) {
                    // Log.d("bejött","y-1 "+y+" "+x);
                    int elem = ((y - 1) * 6) + x;
                    moveToThere(faj, (y - 1), x, 1, move, y, x);
                } else if (battlefild[y - 1][x] < 9 && battlefild[y - 1][x] > 0) {
                    attackTarget(faj, battlefild[y - 1][x], 1);
                }
                else
                {
                    moveEnemyThere(y,x,faj,move);
                }
            }
            else
            {
                moveEnemyThere(y,x,faj,move);
            }
        }
        if(irany[i]==2)
        {
        if ((y + 1) < 6) {
            if (battlefild[y + 1][x] == 0) {
                // Log.d("bejött","y+1 "+y+" "+x);
                int elem = ((y + 1) * 6) + x;
                moveToThere(faj, (y + 1), x, 1,move,y,x);

                    }
            else if(battlefild[y + 1][x] < 9 && battlefild[y + 1][x]>0)
            {
                attackTarget(faj,battlefild[y + 1][x],1);
            }
            else
            {
                moveEnemyThere(y,x,faj,move);
            }
        }
        else
        {
            moveEnemyThere(y,x,faj,move);
        }
        }
        if(irany[i]==3)
        {
        if ((x - 1) > -1) {
            if (battlefild[y][x - 1] == 0) {
                moveToThere(faj, y, (x - 1), 1,move,y,x);
                    }
            else if(battlefild[y][x-1] < 9 && battlefild[y][x-1]>0)
            {
                attackTarget(faj,battlefild[y][x-1],1);
            }
            else
            {
                moveEnemyThere(y,x,faj,move);
            }
        }
        else
        {
            moveEnemyThere(y,x,faj,move);
        }
        }
        if(irany[i]==4)
        {
        if ((x + 1) < 6) {
            if (battlefild[y][x + 1] == 0) {
                 moveToThere(faj, y, (x + 1), 1,move,y,x);

                    }
            else if(battlefild[y][x+1] < 9 && battlefild[y][x+1]>0)
            {
                attackTarget(faj,battlefild[y][x+1],1);
            }
            else
            {
                moveEnemyThere(y,x,faj,move);
            }
        }
        else
        {
            moveEnemyThere(y,x,faj,move);
        }
        }
    }
    public void kiir(int[] valami )
    {
        for (int i=0;i<valami.length;i++)
        {
         Log.d("array","ime="+valami[i]);
        }
        Log.d("array","vége=");
    }
    public int haveIWin(int oldal)
    {
        int igen=0;
        Log.d("művelet","nyertem?");

            Log.d("művelet","én oldalam"+ehpower[0]);
            if(ehpower[0]==0) {
                Log.d("művelet", "vége");
                int power = yPower.intValue() * 1000;
                Random rand = new Random();
                int i = (rand.nextInt(power));
                Toast.makeText(pve_battlefield.this, "Gratulálok győztél, jutalmad: " + i + " pénz", Toast.LENGTH_LONG).show();
                pluszMoney = (double) i;
                exitBack();
                igen=1;
            }
                if (hpower[0] == 0) {
                    Toast.makeText(pve_battlefield.this,"Sajnálom ezt a meccset elvesztetted!",Toast.LENGTH_LONG).show();
                    exitBack();
                    igen=1;
                }
                return igen;

    }
    public void exitBack()
    {
        Cursor data = userDB.backLogin(units[0]);
        final Intent intent;
        intent = new Intent(pve_battlefield.this, start_screen.class);
        data.moveToNext();
        String message = data.getString(0) + "," + data.getString(1) + "," + Double.toString(Double.parseDouble(data.getString(3))+pluszMoney) + "," + units[1] +
                "," + units[2] + "," + units[3] + "," + units[4] + "," + units[5] + "," + units[6]
                + "," + units[7] + "," + data.getString(11) + "," + data.getString(12) + "," + data.getString(13) + "," + data.getString(14) +
                "," + data.getString(15) + "," + data.getString(16) +
                "," + data.getString(17) + "," + data.getString(18) + "," + data.getString(19) + "," + data.getString(20) + "," + data.getString(21) +
                "," + data.getString(22) + "," + data.getString(23) + "," + data.getString(24) + "," + data.getString(25) + "," + data.getString(26) +
                "," + data.getString(27);
        intent.putExtra("datas", message);
        startActivity(intent);
    }
}
