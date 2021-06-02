package com.example.project;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class member_init extends AppCompatActivity {
    private static final String TAG = "member_init";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = (v)-> {
            switch(v.getId()){
                case R.id.checkButton:
                    infoUpdate();
                    break;
            }
    };

    private void infoUpdate() {
        String name = ((EditText) findViewById(R.id.NameEditText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.PhoneEditText)).getText().toString();
        String birth = ((EditText) findViewById(R.id.BirthEditText)).getText().toString();
        String hakbun = ((EditText) findViewById(R.id.Hakbun)).getText().toString();

        if (name.length() > 0 && phone.length()> 4 && birth.length()==6 && hakbun.length()==10) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            MemberInfo memberInfo = new MemberInfo(name, phone, birth, hakbun);

            //////////////////맨 처음 한번만 회원 정보를 입력하면 됨
            if(user != null){
                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("회원정보 등록 완료");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("회원정보 등록 실패");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }

           }
        else {
            if(phone.length()<=4){
                startToast("핸드폰 번호는 5자리 이상입니다.");
            }
            if(birth.length()!=6){
                startToast("생년월일은 6자리 입니다.");
            }
            if(hakbun.length()!=4){
                startToast("학번은 10자리 입니다.");
            }

        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

}
