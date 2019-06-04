package br.com.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference   reference= FirebaseDatabase.getInstance().getReference();

    private EditText nome;
    private EditText sobrenome;
    private EditText idade;
    DatabaseReference usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome=findViewById(R.id.nome);
        sobrenome=findViewById(R.id.sobrenome);
        idade=findViewById(R.id.idade);
        usuario=reference.child("usuarios");

        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("Firebase",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }public void enviar (View v){
        if (nome.getText().toString().equals("")){
                Toast.makeText(this,"Este campo não deve estar vazio!",Toast.LENGTH_SHORT).show();
        }else if (sobrenome.getText().toString().equals(""))
                Toast.makeText(this,"Este campo não deve estar vazio!",Toast.LENGTH_SHORT).show();
        else if (idade.getText().toString().equals(""))
            Toast.makeText(this,"Este campo não deve estar vazio!",Toast.LENGTH_SHORT).show();
        else
            salvar();



    }private void salvar(){
        int idade2=Integer.parseInt(idade.getText().toString());
        Usuario usuarios = new Usuario();
        usuarios.setNome(nome.getText().toString());
        usuarios.setSobrenome(sobrenome.getText().toString());
        usuarios.setIdade(idade2);
        usuario.push().setValue(usuarios);

        Toast.makeText(this,"Cadastro feito com sucesso",Toast.LENGTH_SHORT).show();

    }
}

