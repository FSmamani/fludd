package com.freher.fludd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentPerfil extends Fragment {
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        return   inflater.inflate(R.layout.fragment_perfil,container,false);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
       /*TextView text = (TextView) view.findViewById(R.id.txt_name);
        text.setText(user.getEmail());
        if (user != null) {
            text.setText("test");
            //txt_name.setText(getString(R.string.txt_name, user.getEmail()));
            //txt_name.setText(getString(R.string.txt_name, user.getUid()));
        } else {
          02
        }*/
    }
}
