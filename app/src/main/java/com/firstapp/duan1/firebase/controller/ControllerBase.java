package com.firstapp.duan1.firebase.controller;

import android.util.Log;

import com.firstapp.duan1.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

public abstract class ControllerBase<T> {
    protected final String table;

    public ControllerBase(String table) {
        this.table = table;

        Firebase.database
                .child(table)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult().getValue() == null) {
                        Firebase.database
                                .child(table)
                                .setValue(0)
                                .addOnCompleteListener(task2 ->
                                        Log.i("Controller - " + this.table, task2.isSuccessful() ? "Created table" : task2.getException().getMessage())
                                );
                    } else {
                        if (task.getException() != null)
                            task.getException().printStackTrace();
                    }
                });
    }

    // Success listener
    public static abstract class SuccessListener {
        public void run() {}

        public void run(Object unused) {}

        public void run(DataSnapshot dataSnapshot) {}
    }

    // Failure listener
    public static abstract class FailureListener {
        public abstract void run(Exception error);
    }

    // Set new value or update existing one
    public abstract boolean set(T value, boolean update);
    public abstract void set(T value, boolean update, SuccessListener successListener, FailureListener failureListener);

    // Remove value at index
    public abstract boolean remove(String id);
    public abstract void remove(String id, SuccessListener successListener, FailureListener failureListener);

    // Get value at index
    public abstract T get(String id);
    public abstract void get(String id, SuccessListener successListener, FailureListener failureListener);

    // Get all values from table
    public abstract List<T> getAll();
    public abstract void getAll(SuccessListener successListener, FailureListener failureListener);
}
