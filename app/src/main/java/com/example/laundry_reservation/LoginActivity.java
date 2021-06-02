package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.LogInButton).setOnClickListener(onClickListener);
        findViewById(R.id.PasswordReset).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v)-> {
            switch(v.getId()){
                case R.id.LogInButton:
                    login();
                    break;
                case R.id.PasswordReset:
                    GotoActivity(PasswordReset.class);
                    break;
            }
    };

    private void login() {
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, (task) -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startToast("로그인 되었습니다.");
                            ///////////////////////////////////로그인이 되면 자동으로 메인 화면으로 가게됨
                            GotoActivity(MainActivity.class);
                        } else {
                            if (task.getException() != null) {
                                startToast(task.getException().toString());
                            }
                        }
                    });
        }
        else {
            startToast("이메일 또는 비밀번호를 입력해 주세요");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    private void GotoActivity(Class c){
        Intent intent = new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
