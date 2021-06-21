package com.example.apirest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apirest.Model.UserModel;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel> {

    private Context context;
    private  List<UserModel> Users;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<UserModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.Users = objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.IdPersona);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);;
        TextView txtApellidos=(TextView)rowView.findViewById(R.id.Apellidos);;

        txtNombre.setText(String.format("First name: %s", Users.get(position).getFirstName()));
        txtApellidos.setText(String.format("Email: %s", Users.get(position).getEmail()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, UserActivity.class);
               intent.putExtra("firstName", Users.get(position).getFirstName());
               intent.putExtra("lastName", Users.get(position).getLastName());
               intent.putExtra("email", Users.get(position).getEmail());
//               intent.putExtra("password", Users.get(position).getPassword());
               context.startActivity(intent);
            }
        });

        return rowView;

    }

}
