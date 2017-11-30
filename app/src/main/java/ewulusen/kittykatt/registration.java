package ewulusen.kittykatt;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {
    databaseHelper userDB;
    Button regButton;
    EditText passReg,fhnevReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userDB = new databaseHelper(this);
        fhnevReg=(EditText) findViewById( R.id.fhnevReg);
        passReg=(EditText) findViewById( R.id.passReg);
        regButton=(Button) findViewById(R.id.regButton);
        addData();
    }
    public void addData()
    {
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=fhnevReg.getText().toString();
                String password=passReg.getText().toString();

                boolean insertData=userDB.addData(name,password,"0");

                if(insertData == true)
                {
                    Toast.makeText(registration.this,"Sikeres regisztráció",Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 100);
                }

                else
                {
                    Toast.makeText(registration.this,"Gond van! Nem sikerült :(",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
