package truegamers.queaula;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by giovanni on 19/08/2015.
 */
public class TelaLogin extends Activity {
    //http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/
    //http://stackoverflow.com/questions/18917620/how-to-create-log-in-page-in-android-app
    //http://www.androidhive.info/2014/05/android-working-with-volley-library-1/
    //http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/
    //http://www.androidhive.info/2011/10/android-login-and-registration-screen-design/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_login);

        // Listening to Login Screen link
        ImageView img_logo = (ImageView) findViewById(R.id.img_logo);
        TextView loginScreen = (TextView) findViewById(R.id.btnLogin);

        img_logo.setImageResource(R.drawable.logo);

        // Listening to register new account link
        loginScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MainActivity.class); //Inicio
                startActivity(i);
            }
        });
    }


}
