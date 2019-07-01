package com.freher.fludd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Noticias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentNoticias()).commit();
        navigationView.setCheckedItem(R.id.nav_noticias);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.nav_noticias:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentNoticias()).commit();
                break;
            case R.id.nav_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentPerfil()).commit();
                break;

            case R.id.nav_asistencia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentAsistencias()).commit();
                break;

            case R.id.nav_cursos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentCursos()).commit();
                break;
            case R.id.nav_horario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentHorario()).commit();
                break;
            case R.id.nav_admin:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentAdmin()).commit();

                break;
            case R.id.nav_close:
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(this,"Log Out",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, MainActivity.class));

                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

/*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }*/


}
