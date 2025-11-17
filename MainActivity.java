package usman.l1f21bscs0840.madassignment2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button userSettingbtn, userProfilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        userSettingbtn=findViewById(R.id.userSettingbtn);
        userProfilebtn=findViewById(R.id.userProfilebtn);

        if (savedInstanceState==null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer,userSettings.newInstance())
                    .commit();

        userSettingbtn.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                         getSupportFragmentManager().beginTransaction()
                                 .replace(R.id.fragmentContainer,userSettings.newInstance())
                                 .addToBackStack(null)
                                 .commit();

            }
            });

        userProfilebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer,profileView.newInstance())
                        .addToBackStack(null)
                        .commit();

            }
        });





    }
}