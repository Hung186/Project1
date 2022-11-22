package com.firstapp.duan1.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    public static DatabaseReference database = FirebaseDatabase
            .getInstance("https://duan1-a7273-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference();
}
