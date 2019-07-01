package com.freher.fludd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class FragmentAdmin extends Fragment {

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    /**
     *
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin,container,false);

        /**
         * fragments tab
         */

        tabLayout = view.findViewById(R.id.tabLayout_admin);
        frameLayout = view.findViewById(R.id.frame_Layout_admin);
        fragment = new FragmentAdminGlobal();
        fragmentManager =  getFragmentManager ();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layout_admin, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FragmentAdminGlobal();
                        break;
                    case 1:
                        fragment = new FragmentAdminGrado();
                        break;
                    case 2:
                        fragment = new FragmentAdminSeccion();
                        break;
                    case 3:
                        fragment = new FragmentAdminPersonal();
                        break;
                }
                FragmentManager fm = getFragmentManager ();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_Layout_admin, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;

    }
}
