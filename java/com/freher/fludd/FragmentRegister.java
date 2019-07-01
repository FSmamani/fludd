package com.freher.fludd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;

public class FragmentRegister extends Fragment implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    private FirebaseAuth firebaseAuth;

    public FragmentRegister() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        //fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
         //mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
       //location = (EditText) view.findViewById(R.id.location);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        //terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

        // Setting text selector over textviews
         XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            //terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    private long lastClickTime = 0;
    @Override
    public void onClick(View v) {
        // preventing double, using threshold of 1000 ms
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }

        lastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.signUpBtn:
                checkValidation();
                break;

            case R.id.already_user:
                new MainActivity().replaceLoginFragment();
                break;
        }

    }

    // Check Validation Method
    private void checkValidation() {
        Boolean is_valid = false;
        String getEmailId = emailId.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        String getConfirmPassword = confirmPassword.getText().toString().trim();
        is_valid =  Utils.validate(getEmailId);

        if ( getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0){

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");
        }
        // Check if email id valid or not
        else if (!is_valid) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
        } // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");
        }   /*         // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");
        }*/ // Else do signup or do your stuff
        else{

            firebaseAuth.createUserWithEmailAndPassword(getEmailId,getPassword)
                    .addOnCompleteListener(getActivity() , new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                //Intent homepage = new Intent(getActivity(), Noticias.class);
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                new CustomToast().Show_Toast(getActivity(), view,
                                        user.toString());
                                Toast.makeText(getActivity(), "Cuenta Creada.", Toast.LENGTH_SHORT)
                                        .show();
                                Runnable r = new Runnable() {

                                    @Override
                                    public void run() {
                                        // if you are redirecting from a fragment then use getActivity() as the context.

                                        /*Fragment fragment = new FragmentPerfil();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();*/


                                        startActivity(new Intent(getActivity(), Noticias.class));

                                    }
                                };

                                Handler h = new Handler();
                                // The Runnable will be executed after the given delay time
                                h.postDelayed(r, 1500); // will be delayed for 1.5 seconds
                            } else {
                                new CustomToast().Show_Toast(getActivity(), view,
                                        "FireBase error.");
                            }
                        }
                    });


        }








    }
}
