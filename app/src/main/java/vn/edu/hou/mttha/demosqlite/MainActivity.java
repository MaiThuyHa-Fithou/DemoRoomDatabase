package vn.edu.hou.mttha.demosqlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private EditText edtName, edtAge;
    private Button btnAdd;
    private ListView lvStudents;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        btnAdd = findViewById(R.id.btnAdd);
        lvStudents = findViewById(R.id.lvStudents);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, studentList);
        lvStudents.setAdapter(adapter);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAllStudents().observe(this, this::onChanged);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.insert(new Student(edtName.getText().toString(),
                        Integer.valueOf(edtAge.getText().toString().trim())));
            }
        });
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String st = studentList.get(position);
                String student[]= st.split("_");
                Log.i("student","name: "+ student[0] +", age: "
                +student[1]);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xoa student!");
                builder.setMessage("Ban co chac chan muon xoa student co ten: "+ student[0]);
                builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentViewModel.delete(new Student(student[0],
                                Integer.valueOf(student[1].trim())));
                    }
                });
                builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void onChanged(@NonNull List<Student> students) {
        studentList.clear();
        for (Student student : students) {
            studentList.add(student.getName() + " _ " + student.getAge());
        }
        adapter.notifyDataSetChanged();
    }

}