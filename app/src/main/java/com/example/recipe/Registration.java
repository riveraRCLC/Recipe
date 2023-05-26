package com.example.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Registration extends AppCompatActivity {

    ImageView top_curve;
    EditText name, email, password, confirmpassword;
    TextView name_text, email_text, password_text, login_title;
    TextView logo;
    LinearLayout already_have_account_layout;
    CardView register_card;
    Button register_button;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //INITIALIZE THE VARIABLES
        top_curve = findViewById(R.id.top_curve);
        name = findViewById(R.id.name);
        name_text = findViewById(R.id.name_text);
        email = findViewById(R.id.password);
        email_text = findViewById(R.id.confirm_text);
        password = findViewById(R.id.confirmpassword);
        password_text = findViewById(R.id.conpassword_text);
        logo = findViewById(R.id.logo);
        login_title = findViewById(R.id.registration_title);
        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);
        register_button = findViewById(R.id.register_button);
        confirmpassword = findViewById(R.id.confirmpassword); // Add this line to initialize the confirmpassword EditText
        DB = new DBHelper(this);
        Animation top_curve_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_down);
        top_curve.startAnimation(top_curve_anim);

        Animation editText_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.edittext_anim);
        name.startAnimation(editText_anim);
        email.startAnimation(editText_anim);
        password.startAnimation(editText_anim);

        Animation field_name_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.field_name_anim);
        name_text.startAnimation(field_name_anim);
        email_text.startAnimation(field_name_anim);
        password_text.startAnimation(field_name_anim);
        logo.startAnimation(field_name_anim);
        login_title.startAnimation(field_name_anim);

        Animation center_reveal_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.center_reveal_anim);
        register_card.startAnimation(center_reveal_anim);

        Animation new_user_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down_top);
        already_have_account_layout.startAnimation(new_user_anim);

        //CREATE LISTENER FOR BUTTONS

        //Listener for Sign Up
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = name.getText().toString();
                String pass = password.getText().toString();
                String confirmpass = confirmpassword.getText().toString();

                //IF ANY TEXTBOXES IS EMPTY
                if(user.equals("") || pass.equals("") || confirmpass.equals(""))
                {
                    Toast.makeText(Registration.this, "Please input all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //IF TWO PASSWORD MATCHES
                    if(pass.equals(confirmpass))
                    {
                        Boolean checkuser = DB.checkUsername(user);

                        if(checkuser == false)  //IF USERNAME DOESN'T EXISTS
                        {
                            Boolean insert = DB.insertData(user, pass);

                            if(insert == true) //IF THE DATABASE HAS BEEN WRITTEN
                            {
                                Toast.makeText(Registration.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Registration.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Registration.this, "Username already exists!", Toast.LENGTH_SHORT).show();

                        }

                    }

                    else
                    {
                        Toast.makeText(Registration.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void login(View view) { startActivity(new Intent(this, Login.class));}
}