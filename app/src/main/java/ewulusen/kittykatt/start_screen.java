package ewulusen.kittykatt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class start_screen extends AppCompatActivity {
    public static Intent intent;
    public static String message;
    public static String[] datas;
    TextView kiir;
    String menu_names[] = {"Egysegek", "Harc", "Cica", "Gyárak", "Chat", "Fejlesztés", "Harc player ellen"};
    String menu_names2[] = {"Egysegek", "Cica", "Gyárak", "Chat", "Fejlesztés"};
    databaseHelper userDB;
    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);
        intent = getIntent();
        message = intent.getStringExtra("datas");
        datas = message.split(",");
        userDB = new databaseHelper(this);
        kiir = findViewById(R.id.kiir);
        String money = String.format("%.0f", Double.parseDouble(datas[2]));
        String dumymoney = String.format("%.0f", Double.parseDouble(datas[2]));
        if (dumymoney.length() > 6 && dumymoney.length() < 9) {
            if (dumymoney.length() == 7) {
                String money1 = String.valueOf(money.charAt(0));
                String money2 = String.valueOf(money.charAt(1));
                money = money1 + "." + money2 + " mil";
            }
            if (dumymoney.length() == 8) {
                String money1 = String.valueOf(money.charAt(0));
                String money2 = String.valueOf(money.charAt(1));
                String money3 = String.valueOf(money.charAt(2));
                money = money1 + "" + money2 + "." + money3 + " mil";
            }
            if (dumymoney.length() == 9) {
                String money1 = String.valueOf(money.charAt(0));
                String money2 = String.valueOf(money.charAt(1));
                String money3 = String.valueOf(money.charAt(2));
                String money4 = String.valueOf(money.charAt(3));
                money = money1 + "" + money2 + "" + money3 + "." + money4 + " mil";
            }
        }
        kiir.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + money + " pénzed van!");
        penzNovel();
        int unitok = 0;
        for (int i = 4; i < 11; i++) {
            if (datas[i].equals("0")) {
                unitok++;
            }

        }
        Log.d("unitok","unitok"+unitok);
        if (unitok < 6) {
            circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
            circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.plus, R.drawable.x)
                    .addSubMenu(Color.parseColor("#258CFF"), R.drawable.unit)
                    .addSubMenu(Color.parseColor("#30A400"), R.drawable.battle)
                    .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.cat)
                    .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.factory_m)
                    .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.message)
                    .addSubMenu(Color.parseColor("#AF6A00"), R.drawable.upgrade)
                    .addSubMenu(Color.parseColor("#F6b1A3"), R.drawable.vs)
                    .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                        @Override
                        public void onMenuSelected(int index) {
                            Toast.makeText(start_screen.this, "kiválasztottad a " + menu_names[index] + " menüt", Toast.LENGTH_LONG).show();
                            Intent intent2 = null;
                            switch (index) {
                                case 0:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, unit.class);
                                    break;
                                case 1:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, pve_battlefield.class);
                                    message = datas[0] + "," + datas[3] + "," + datas[4] + "," + datas[5] + "," + datas[6] + "," + datas[7] + "," + datas[8] + "," + datas[9];
                                    break;
                                case 2:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, cica.class);
                                    break;
                                case 3:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, factory.class);
                                    break;
                                case 4:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, chat.class);
                                    break;

                                case 5:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, upgrade.class);
                                    break;

                                case 6:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, pvp_battlefield.class);
                                    message = datas[0] + "," + datas[3] + "," + datas[4] + "," + datas[5] + "," + datas[6] + "," + datas[7] + "," + datas[8] + "," + datas[9];
                                    break;
                            }
                            intent2.putExtra("datas", message);
                            startActivity(intent2);
                            finish();
                        }

                    }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {


                @Override
                public void onMenuOpened() {

                }

                @Override
                public void onMenuClosed() {
                }

            });
        } else {

            circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
            circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.plus, R.drawable.x)
                    .addSubMenu(Color.parseColor("#30A400"), R.drawable.unit)
                    .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.cat)
                    .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.factory_m)
                    .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.message)
                    .addSubMenu(Color.parseColor("#AF6A00"), R.drawable.upgrade)
                    .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                        @Override
                        public void onMenuSelected(int index) {
                            Toast.makeText(start_screen.this, "kiválasztottad a " + menu_names2[index] + " menüt", Toast.LENGTH_LONG).show();
                            Intent intent2 = null;
                            Log.d("index", "mi" + index);
                            switch (index) {
                                case 0:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, unit.class);
                                    break;
                                case 1:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, cica.class);
                                    break;
                                case 2:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, factory.class);
                                    break;
                                case 3:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, chat.class);
                                    break;

                                case 4:
                                    userDB.saveData(datas);
                                    intent2 = new Intent(start_screen.this, upgrade.class);
                                    break;
                            }
                            intent2.putExtra("datas", message);
                            startActivity(intent2);
                            finish();
                        }

                    }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {


                @Override
                public void onMenuOpened() {

                }

                @Override
                public void onMenuClosed() {
                }

            });
        }
        mentes();
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        circleMenu.openMenu();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onBackPressed() {
        circleMenu.closeMenu();
    }

    public void penzNovel() {
        final Handler mHandler = new Handler();
        final boolean wasRun = true;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (wasRun) {
                    if (!datas[11].equals("0")) {
                        datas[2] = Double.toString(Double.parseDouble(datas[2]) + (Double.parseDouble(datas[23]) * 0.1));
                    }
                    if (!datas[12].equals("0")) {
                        datas[2] = Double.toString(Double.parseDouble(datas[2]) + (Double.parseDouble(datas[24]) * 0.1));
                    }
                    if (!datas[13].equals("0")) {
                        datas[2] = Double.toString(Double.parseDouble(datas[2]) + (Double.parseDouble(datas[25]) * 0.1));
                    }
                    if (!datas[14].equals("0")) {
                        datas[2] = Double.toString(Double.parseDouble(datas[2]) + (Double.parseDouble(datas[26]) * 0.1));
                    }
                    datas[2] = Double.toString(Double.parseDouble(datas[2]) + (Double.parseDouble(datas[22]) * 0.1));
                    String money = String.format("%.0f", Double.parseDouble(datas[2]));
                    String dumymoney = String.format("%.0f", Double.parseDouble(datas[2]));
                    if (dumymoney.length() > 6 && dumymoney.length() < 9) {
                        if (dumymoney.length() == 7) {
                            String money1 = String.valueOf(money.charAt(0));
                            String money2 = String.valueOf(money.charAt(1));
                            money = money1 + "." + money2 + " mil";
                        }
                        if (dumymoney.length() == 8) {
                            String money1 = String.valueOf(money.charAt(0));
                            String money2 = String.valueOf(money.charAt(1));
                            String money3 = String.valueOf(money.charAt(2));
                            money = money1 + "" + money2 + "." + money3 + " mil";
                        }
                        if (dumymoney.length() == 9) {
                            String money1 = String.valueOf(money.charAt(0));
                            String money2 = String.valueOf(money.charAt(1));
                            String money3 = String.valueOf(money.charAt(2));
                            String money4 = String.valueOf(money.charAt(3));
                            money = money1 + "" + money2 + "" + money3 + "." + money4 + " mil";
                        }
                    }
                    kiir.setText("Üdvözöllek " + datas[1] + "! Jelenleg ennyi " + money + " pénzed van!");

                }
                mHandler.postDelayed(this, 2500);
            }
        }, 1000);
    }

    public void mentes() {
        final Handler mHandlerSave = new Handler();

        mHandlerSave.postDelayed(new Runnable() {
            @Override
            public void run() {
                userDB.saveData(datas);
                mHandlerSave.postDelayed(this, 10000);
            }
        }, 1000);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        userDB.destroyData(datas);
    }
}