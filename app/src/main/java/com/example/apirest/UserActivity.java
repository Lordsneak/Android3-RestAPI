package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.Model.UserModel;
import com.example.apirest.Utils.Retro;
import com.example.apirest.Utils.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        TextView userIdText = (TextView) findViewById(R.id.userIdText);
        EditText idInput = (EditText) findViewById(R.id.idInput);

//        TextView firstNameText = (TextView) findViewById(R.id.firstNameText);
        final EditText firstNameInput = (EditText) findViewById(R.id.firstNameInput);

//        TextView lastNameText = (TextView) findViewById(R.id.lastNameText);
        final EditText lastNameInput = (EditText) findViewById(R.id.lastNameInput);

//        TextView emailText = (TextView) findViewById(R.id.emailText);
        final EditText emailInput = (EditText) findViewById(R.id.emailInput);

//        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        final EditText passwordInput = (EditText) findViewById(R.id.passwordInput);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("ID");
        String fn = bundle.getString("firstName");
        String ln = bundle.getString("lastName");
        String em = bundle.getString("email");
//        String pwd = bundle.getString("password");

        idInput.setText(id);
        firstNameInput.setText(fn);
        lastNameInput.setText(ln);
        emailInput.setText(em);
//        passwordInput.setText(pwd);

        if(id.trim().length() == 0 || id.equals("")){
            userIdText.setVisibility(View.INVISIBLE);
            idInput.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel user = new UserModel();
                user.setFirstName(firstNameInput.getText().toString());
                user.setLastName(lastNameInput.getText().toString());
                user.setEmail(emailInput.getText().toString());
                user.setPassword(passwordInput.getText().toString());

                // handle many options ( CREATE / UPDATE )
                if(id.trim().length() == 0 || id.equals("")){
                    addUser(user);
                    Log.d("Add", "AddUser");
                    Intent intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    updateUser(user, id);
                    Log.d("Update", "updateUser");
                    Intent intent = new Intent(UserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Deleting user
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(id);
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // return to main window
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addUser(UserModel p){
        Log.d("User", p.toString());
        service = Retro.Api.getUserService();
        Call<UserModel> call = service.addUser(p);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.d("Response", response.toString());
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this,"User added successfully", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });

        Intent intent = new Intent(UserActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void updateUser(UserModel p, String id){
        service = Retro.Api.getUserService();
        Call<UserModel>call = service.updateUser(p, id);
        Log.d("U_user", p.toString());
        Log.d("U_Id", id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.d("Call", call.toString());
                if(response.isSuccessful()){
                    Log.d("onResponse: ", response.toString());
                    Toast.makeText(UserActivity.this,"User updated successfully",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent = new Intent(UserActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void deleteUser(String id){
        service = Retro.Api.getUserService();
        Call<UserModel>call = service.deleteUser(id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.d("Delete_onResponse", response.toString());
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this,"User deleted successfully !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });

        Intent intent = new Intent(UserActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
