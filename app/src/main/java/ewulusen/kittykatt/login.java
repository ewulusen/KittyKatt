package ewulusen.kittykatt;


import android.content.Intent;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class login extends AppCompatActivity {
    databaseHelper userDB;
    Button regButton,viewDartas,loginButton;
    EditText    username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regButton=(Button) findViewById(R.id.regViewButton);

        loginButton=(Button) findViewById(R.id.loginButton);
        username=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.pass);
        userDB = new databaseHelper(this);
        showReg();

        loginUser();

    }
    public void showReg()
    {
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,registration.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(){
        final Intent intent;
        intent = new Intent(login.this, mainScreen.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String password = pass.getText().toString();
                Cursor data = userDB.login(name,password);
                data.moveToNext();
                Log.d("db",Double.toString(data.getColumnCount()));
                if (data.getCount() == 0) {
                    display("Hiba", "Rossz felhasználónév vagy jelszó, kérlek ellenőrizd amit beírtál, vagy regisztrálj!");
                    return;
                } else
                {

                String message = data.getString(0)+ ","+ data.getString(1)+","+data.getString(3)+","+data.getString(4)+
                        ","+data.getString(5)+","+data.getString(6)+","+data.getString(7)+","+data.getString(8)+","+data.getString(9)
                        +","+data.getString(10)+","+data.getString(11)+","+data.getString(12)+","+data.getString(13)+","+data.getString(14)+
                        ","+data.getString(15)+","+data.getString(16)+
                        ","+data.getString(17)+","+data.getString(18)+","+data.getString(19)+","+data.getString(20)+","+data.getString(21)+
                        ","+data.getString(22)+","+data.getString(23)+","+data.getString(24)+","+data.getString(25)+","+data.getString(26)+
                        ","+data.getString(27);
                intent.putExtra("datas", message);
                startActivity(intent);
                }
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
