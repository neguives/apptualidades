package vostore.apptualidade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import vostore.apptualidade.Fragments.FavoritosFragment;
import vostore.apptualidade.Fragments.SairFragment;
import vostore.apptualidade.Fragments.SimuladoFragment;
import vostore.apptualidade.Fragments.SobreFragment;

public class Inicio extends AppCompatActivity {

    BottomNavigationView mNavigation;
    private FrameLayout mFrame;

    private FavoritosFragment favoritosFragment;
    private SimuladoFragment simuladoFragment;
    private SobreFragment sobreFragment;
    private SairFragment sairFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        simuladoFragment = new SimuladoFragment();
        favoritosFragment = new FavoritosFragment();
        sobreFragment = new SobreFragment();
        sairFragment = new SairFragment();



        mFrame = findViewById(R.id.main_frame);
        mNavigation = findViewById(R.id.navigation);





        //Método importantíssimo
        //Usado para aumentar o tamanho dos icones do Navigation
        BottomNavigationMenuView menuView = (BottomNavigationMenuView)
                mNavigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView =
                    menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams =
                    iconView.getLayoutParams();
            final DisplayMetrics displayMetrics =
                    getResources().getDisplayMetrics();
            layoutParams.height = (int)
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                            displayMetrics);
            layoutParams.width = (int)
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                            displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.nav_simulado:
                        selectedFragment = SimuladoFragment.newInstance();
                        break;

                    case R.id.nav_sobre:
                        selectedFragment = sobreFragment.newInstance();
                        break;

                    case R.id.nav_sair:
                        selectedFragment = simuladoFragment.newInstance();
                        break;

                }


                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, selectedFragment);
                fragmentTransaction.commit();


                return true;


            }

        });

        setDefaultFragment();


    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, SimuladoFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Inicio.this, Login.class);
        startActivity(intent);
        finish();


    }
}
