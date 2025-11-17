package usman.l1f21bscs0840.madassignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class profileView extends Fragment {

    private TextView usernameView, emailView, passwordView, themeView, notificationView;
private Button clearPrefBtn;
    public profileView() {
        // Required empty public constructor
    }

    public static profileView newInstance() {
        return new profileView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_view, container, false);

        // Initialize TextViews
        usernameView = view.findViewById(R.id.usernameDisplay);
        emailView = view.findViewById(R.id.emailDisplay);
        passwordView = view.findViewById(R.id.passwordDisplay);
        themeView = view.findViewById(R.id.themeDisplay);
        notificationView = view.findViewById(R.id.notificationDisplay);
        clearPrefBtn = view.findViewById(R.id.clearPrefBtn);

        loadSavedData();



        clearPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();

                // Reset TextViews
                usernameView.setText("Username: ");
                emailView.setText("Email: ");
                passwordView.setText("Password: ");
                themeView.setText("Theme: ");
                notificationView.setText("Notifications: ");

                Toast.makeText(getActivity(), "Preferences Cleared", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loadSavedData() {
        SharedPreferences prefs = getActivity().getSharedPreferences("userPrefs", Context.MODE_PRIVATE);

        usernameView.setText("Username: " + prefs.getString("username", ""));
        emailView.setText("Email: " + prefs.getString("email", ""));
        passwordView.setText("Password: " + prefs.getString("password", ""));
        themeView.setText("Theme: " + prefs.getString("theme", "Light"));
        notificationView.setText("Notifications: " + prefs.getString("notifications", "Yes"));
    }
}
