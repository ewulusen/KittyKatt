package ewulusen.kittykatt;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

public class pve_battlefield extends AppCompatActivity {
    Intent intent;
    String message;
    ImageView c1, c2, c3, c4, c5, c6, c7, e1, e2, e3, e4, e5, e6, e7, green;
    String[] units;
    Double yPower, ePower;
    databaseHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pve__battlefield);
        userDB = new databaseHelper(this);
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
        green = findViewById(R.id.a_2);
        intent = getIntent();
        message = intent.getStringExtra("units");
        Log.d("unitok", message);
        units = message.split(",");
        checkBF();
        yPower = getPower(units);
        Log.d("power", Double.toString(yPower));
    }

    public void checkBF() {
        for (int i = 1; i < units.length; i++) {
            if (units[i].equals("0")) {
                switch (i) {
                    case 1:
                        c1.setImageResource(R.mipmap.green);
                        break;
                    case 2:
                        c2.setImageResource(R.mipmap.green);
                        break;
                    case 3:
                        c3.setImageResource(R.mipmap.green);
                        break;
                    case 4:
                        c4.setImageResource(R.mipmap.green);
                        break;
                    case 5:
                        c5.setImageResource(R.mipmap.green);
                        break;
                    case 6:
                        c6.setImageResource(R.mipmap.green);
                        break;
                    case 7:
                        c7.setImageResource(R.mipmap.green);
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
    @Override
    protected void onPause() {
        super.onPause();  // Always call the superclass method first
        Cursor data = userDB.backLogin(units[0]);
        final Intent intent;
        intent = new Intent(pve_battlefield.this, mainScreen.class);
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
}
