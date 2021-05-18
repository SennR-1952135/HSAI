package com.example.project.ui.shopping_cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project.DataBase.DataBasee;
import com.example.project.DataBase.UserEntity;
import com.example.project.R;

/**
 * @author Melih Demirel
 */

public class paymentFragment extends Fragment {

    public paymentFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_payment, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        setupFragment(view);
    }
    public void setupFragment(@NonNull View view){

        Button app = view.findViewById(R.id.appPayment);
        Button card = view.findViewById(R.id.cardPayment);

        Button goHome = view.findViewById(R.id.backBtn);
        TextView orderDone = view.findViewById(R.id.orderReceived);
        app.setOnClickListener(v -> {
            app.setVisibility(View.INVISIBLE);
            card.setVisibility(View.INVISIBLE);
            orderDone.setVisibility(View.VISIBLE);
            goHome.setVisibility(View.VISIBLE);
        });
        card.setOnClickListener(v -> {
            app.setVisibility(View.INVISIBLE);
            card.setVisibility(View.INVISIBLE);
            orderDone.setVisibility(View.VISIBLE);
            goHome.setVisibility(View.VISIBLE);
        });

        goHome.setOnClickListener(v -> {
            DataBasee db = DataBasee.getDb(getActivity());
            db.mAppDao().removeAllInCart();
            (NavHostFragment.findNavController(this)).navigate(R.id.navigation_home);
        });

    }
}
