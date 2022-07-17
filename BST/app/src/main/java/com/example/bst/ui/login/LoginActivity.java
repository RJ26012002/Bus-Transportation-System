package com.example.bst.ui.login;

import static com.example.bst.settings.Settings.BUS_COLLECTION;
import static com.example.bst.settings.Settings.PROFILE_COLLECTION;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bst.MapLocation;
import com.example.bst.documents.Bus;
import com.example.bst.documents.Profile;
import com.example.bst.databinding.ActivityLoginBinding;
import com.example.bst.documents.ProfileType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


  private FirebaseFirestore firebaseFireStore;

  private ActivityLoginBinding binding;
  private Spinner spinner;
  private Button buttonLogin, buttonRegister;
  private RadioButton radioStudent, radioDriver;

  private Bus busSelected = null;
  private Profile profile;

  private List<Bus> buses;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    assignments();
    listeners();
  }

  private void listeners() {
    buttonRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        profile = new Profile();
        profile.setNumber(Integer.parseInt(binding.number1.getText() + ""));
        profile.setPassword(Integer.parseInt(binding.password1.getText() + ""));
        int type = ProfileType.DRIVER;
        if (binding.radioStudent.isSelected()) {
          type = ProfileType.STUDENT;
        }
        profile.setType(type);
        profile.setBus(busSelected);
        DocumentReference documentReference = firebaseFireStore.collection(PROFILE_COLLECTION).document();
        profile.setId(documentReference.getId());
        documentReference.set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
              Toast.makeText(getApplicationContext(), "Pushed Success", Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    });

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "Selected : " + i, Toast.LENGTH_SHORT).show();

        //Todo check edge conditions
        busSelected = buses.get(i);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "Nothing ", Toast.LENGTH_LONG).show();
      }
    });

    //Fetch all bus details from Firesatore
    firebaseFireStore.collection(BUS_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
          for (DocumentSnapshot snapshot : task.getResult()) {
            Bus bus = snapshot.toObject(Bus.class);
            buses.add(bus);
          }

          ArrayAdapter<Bus> adapter1 = new ArrayAdapter<Bus>(getApplicationContext(), android.R.layout.simple_spinner_item, buses);
          spinner.setAdapter(adapter1);
        }
      }
    });

    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    });
  }

  private void assignments() {
    firebaseFireStore = FirebaseFirestore.getInstance();

    spinner = binding.spinnerBus;
    radioStudent = binding.radioStudent;
    radioDriver = binding.radioDriver;
    buttonLogin = binding.buttonLogin;
    buttonRegister = binding.buttonRegister;

    buses = new ArrayList<>();
  }

  private List<Bus> getDummyBus() {
    List<Bus> buses = new ArrayList<>();
    buses.add(new Bus(null, "MANIT", new MapLocation(77.6421572, 12.838366462991598)));
    buses.add(new Bus(null, "Mata Mandir", new MapLocation(77.6421572, 12.838366462991598)));
    buses.add(new Bus(null, "New Market", new MapLocation(79.6421572, 11.838366462991598)));
    buses.add(new Bus(null, "Karond", new MapLocation(70.6421572, 12.038366462991598)));
    buses.add(new Bus(null, "DB City", new MapLocation(74.6421572, 10.838366462991598)));
    return buses;
  }

}