package com.example.project.ui.shopping_cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

public class UserFragment extends Fragment {


    private final UserFragment m_self_ref = this;
    public UserFragment(){}
    private DataBasee db;
    static UserFragment newInstance(int itemID){
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DataBasee.getDb(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_user_info, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        setupFragment(view);
    }
    public void setupFragment(@NonNull View view){

        ImageView pay = view.findViewById(R.id.payBtnUser);

        EditText fname = view.findViewById(R.id.editFname);
        EditText lname = view.findViewById(R.id.editLname);
        EditText email = view.findViewById(R.id.editEmail);
        EditText phone = view.findViewById(R.id.editPhone);

        boolean userExists = false;
        if(!(db.mAppDao().getUser()==null)){
            userExists = true;
            fname.setText(db.mAppDao().getUser().getName());
            lname.setText(db.mAppDao().getUser().getLastname());
            email.setText(db.mAppDao().getUser().getEmail());
            phone.setText(db.mAppDao().getUser().getPhone());
        }
        boolean finalUserExists = userExists;
        pay.setOnClickListener(v -> {
            if(fname.length()==0){
                fname.setError("Enter First Name");
                fname.requestFocus();
            }
            else if(lname.length()==0){
                lname.setError("Enter Last Name");
                lname.requestFocus();
            }
            else if(email.length()==0){
                email.setError("Enter Email");
                email.requestFocus();
            }
            else if(phone.length()==0){
                phone.setError("Enter Phone");
                phone.requestFocus();
            }
            else{
                if(!finalUserExists){
                    db.mAppDao().insertUser(new UserEntity(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),phone.getText().toString()));
                }
                (NavHostFragment.findNavController(this)).navigate(R.id.paymentFragment);
            }
        });

    }
}
