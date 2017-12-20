package ewulusen.kittykatt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class login
        extends AppCompatActivity
        implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener
{
    final int RC_SIGN_IN = 7;
    final String TAG = login.class.getSimpleName();
    SignInButton btnSignIn;
    GoogleApiClient mGoogleApiClient;
    databaseHelper userDB;

    private void handleSignInResult(GoogleSignInResult paramGoogleSignInResult)
    {
        Log.d(this.TAG, "sikerült bejelentkezni?" + paramGoogleSignInResult.isSuccess());
        if (paramGoogleSignInResult.isSuccess())
        {
            GoogleSignInAccount localGoogleSignInAccount = paramGoogleSignInResult.getSignInAccount();
            String str1 = localGoogleSignInAccount.getId();
            String str2 = localGoogleSignInAccount.getGivenName();
            Log.d("name and id", str1 + str2);
            loginUser(str1, str2);
            updateUI(true);
            return;
        }
        updateUI(false);
    }

    public void signIn()
    {
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), 7);
    }

    private void updateUI(boolean paramBoolean)
    {
        if (paramBoolean)
        {
            this.btnSignIn.setVisibility(View.GONE);
            return;
        }
        this.btnSignIn.setVisibility(View.VISIBLE);
    }

    public void display(String paramString1, String paramString2)
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setCancelable(true);
        localBuilder.setTitle(paramString1);
        localBuilder.setMessage(paramString2);
        localBuilder.show();
    }

    public void loginUser(String paramString1, String paramString2)
    {
        Intent localIntent = new Intent(this, start_screen.class);
        Log.d("id", paramString1);
        Log.d("name", paramString2);
        Cursor localCursor = this.userDB.login(paramString2, paramString1, "0");
        Log.d("db", Double.toString(localCursor.getColumnCount()));
        if (localCursor.getCount() == 0)
        {
            this.userDB.addData(paramString2, paramString1, "0");
            localCursor = this.userDB.login(paramString2, paramString1, "0");
        }
        localCursor.moveToNext();
        String money="0.0";
        if(localCursor.getString(3).equals("0")) {
        }
        else
             {
                 Log.d("moneny1",localCursor.getString(3));
                 long millis = System.currentTimeMillis();
                String times=localCursor.getString(28);
                 SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                 Date date = null;
                 try {
                     date = sdf.parse(times);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
                 long timeInMillisSinceEpoch = date.getTime();
                 long kulonbseg=(millis-timeInMillisSinceEpoch)/1000;
                 if (!localCursor.getString(12).equals("0")) {
                     money = Double.toString(Double.parseDouble(money) + (Double.parseDouble(localCursor.getString(24)) * 0.1));
                 }
                 if (!localCursor.getString(13).equals("0")) {
                     money = Double.toString(Double.parseDouble(money) + (Double.parseDouble(localCursor.getString(25)) * 0.1));
                 }
                 if (!localCursor.getString(14).equals("0")) {
                     money = Double.toString(Double.parseDouble(money) + (Double.parseDouble(localCursor.getString(26)) * 0.1));
                 }
                 if (!localCursor.getString(15).equals("0")) {
                     money = Double.toString(Double.parseDouble(money) + (Double.parseDouble(localCursor.getString(27)) * 0.1));
                 }
                money = Double.toString(Double.parseDouble(money) + (Double.parseDouble(localCursor.getString(23)) * 0.1));
                 money=Double.toString(Double.parseDouble(money)* kulonbseg*0.1);
                 Log.d("moneny2",money);
                 //display("rendszerüzenet","távolléted alatt "+money+" pénzed gyűlt össze");
                 money=Double.toString((Double.parseDouble(money)+Double.parseDouble(localCursor.getString(3))));
                 Context context = getApplicationContext();
                 CharSequence text = "távolléted alatt "+String.format("%.0f",Double.parseDouble(money))+" pénzed gyűlt össze";
                 int duration = Toast.LENGTH_LONG;
                 Toast toast = Toast.makeText(context, text, duration);
                 toast.show();
             }

        localIntent.putExtra("datas", localCursor.getString(0) + "," + localCursor.getString(1) + "," + money + "," + localCursor.getString(4) + "," + localCursor.getString(5) + "," + localCursor.getString(6) + "," + localCursor.getString(7) + "," + localCursor.getString(8) + "," + localCursor.getString(9) + "," + localCursor.getString(10) + "," + localCursor.getString(11) + "," + localCursor.getString(12) + "," + localCursor.getString(13) + "," + localCursor.getString(14) + "," + localCursor.getString(15) + "," + localCursor.getString(16) + "," + localCursor.getString(17) + "," + localCursor.getString(18) + "," + localCursor.getString(19) + "," + localCursor.getString(20) + "," + localCursor.getString(21) + "," + localCursor.getString(22) + "," + localCursor.getString(23) + "," + localCursor.getString(24) + "," + localCursor.getString(25) + "," + localCursor.getString(26) + "," + localCursor.getString(27));
        startActivity(localIntent);
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt1 == 7) {
            handleSignInResult(Auth.GoogleSignInApi.getSignInResultFromIntent(paramIntent));
        }
    }

    public void onClick(View paramView)
    {
       /* switch (paramView.getId())
        {
            default:
                return;
        }*/
        signIn();
    }

    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
        Log.d(this.TAG, "onConnectionFailed:" + paramConnectionResult);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_login);
        this.userDB = new databaseHelper(this);
        this.btnSignIn = ((SignInButton)findViewById(R.id.sign_in_button));
        this.btnSignIn.setOnClickListener(this);
        GoogleSignInOptions localGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestId().requestProfile().build();
        this.mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, localGoogleSignInOptions).build();
        this.btnSignIn.setScopes(localGoogleSignInOptions.getScopeArray());
    }

    public void onStart()
    {
        super.onStart();
        OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(this.mGoogleApiClient);
        if (opr.isDone())
        {
            handleSignInResult((GoogleSignInResult)opr.get());
            return;
        }
        opr.setResultCallback(new ResultCallback()
        {
            @Override
            public void onResult(@NonNull Result result) {

            }

            public void onResult(GoogleSignInResult paramAnonymousGoogleSignInResult)
            {
                login.this.handleSignInResult(paramAnonymousGoogleSignInResult);
            }
        });
    }
}
