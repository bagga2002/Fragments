package usman.l1f21bscs0840.madassignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class userSettings extends Fragment {

    private EditText username, email, password;
    private RadioGroup theme, notification;
    private Button save,clear;

    public userSettings() {
        // Required empty public constructor
    }

    public static userSettings newInstance(){
        return new userSettings();
    }

    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_settings,container,false);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.mail);
        password = view.findViewById(R.id.pass);
        theme = view.findViewById(R.id.themePref);
        notification = view.findViewById(R.id.notification);
        save = view.findViewById(R.id.savePref);
        clear = view.findViewById(R.id.clearPref);

        loadPreferences();

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                savePreferences();
            }

        });
        //clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();

                username.setText("");
                email.setText("");
                password.setText("");
                theme.check(R.id.themeLight);
                notification.check(R.id.NotificationYes);

                Toast.makeText(getActivity(), "Preferences Cleared", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void savePreferences() {
        String uname = username.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if (uname.length() < 8) {
            Toast.makeText(getActivity(), "Username must be at least 8 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mail.contains("@") || !mail.endsWith(".com")) {
            Toast.makeText(getActivity(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return; // stop saving
        }

        if (pass.length() < 8 || !pass.matches(".*\\d.*") || !pass.matches(".*[!@#$%^&*].*")) {
            Toast.makeText(getActivity(), "Password must be at least 8 characters and include a number and special character", Toast.LENGTH_SHORT).show();
            return; // stop saving
        }

        String selectedTheme = (theme.getCheckedRadioButtonId() == R.id.themeDark) ? "Dark" : "Light";
        String selectedNotification = (notification.getCheckedRadioButtonId() == R.id.NotificationYes) ? "Yes" : "No";

        SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", uname);
        editor.putString("email", mail);
        editor.putString("password", pass);
        editor.putString("theme", selectedTheme);
        editor.putString("notifications", selectedNotification);
        editor.apply();

        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();

    }

    private void loadPreferences() {
        SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);

        username.setText(prefs.getString("username", ""));
        email.setText(prefs.getString("email", ""));
        password.setText(prefs.getString("password", ""));

        String savedTheme = prefs.getString("theme", "Light");
        if (savedTheme.equals("Dark")) {
            theme.check(R.id.themeDark);
        } else {
            theme.check(R.id.themeLight);
        }

        String savedNotif = prefs.getString("notifications", "Yes");
        if (savedNotif.equals("Yes")) {
            notification.check(R.id.NotificationYes);
        } else {
            notification.check(R.id.NotificationNo);
        }
    }


}
