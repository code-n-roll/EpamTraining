package androidlab2017.epam.com;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final static int PERMISSION_WES = 1;
    private final static int PICK_FILE_REQUEST = 1;
    private Button mButtonOpen;
    private Button mButtonCreateFile;
    private Button mButtonSave;
    private EditText mEditText;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FILE_REQUEST){
            if (resultCode == RESULT_OK){
                showMessage(this, "file picked!");
                try {
                    FileReader fr = new FileReader(data.getData().getPath());
                    BufferedReader br = new BufferedReader(fr);
                    mEditText.setText(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonOpen = (Button) findViewById(R.id.button_open);
        mButtonOpen.setOnClickListener((ignored)->clickOnOpenFile("test.txt"));

        mButtonCreateFile = (Button) findViewById(R.id.button_create_file);
        mButtonCreateFile.setOnClickListener((ignored)->clickOnCreateFile());

        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonSave.setOnClickListener((ignored)->clickOnSaveFile());

        mEditText = (EditText) findViewById(R.id.edittext);
    }

    private void clickOnSaveFile(){

    }

    private void clickOnCreateFile(){
        getPermissions();
        String filename = createFile("test.txt");
        showMessage(this, getResources().getString(R.string.create_file_toast) + " " + filename);
    }

    private void showMessage(Context context, String message){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }

    private void getPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_WES);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_WES:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                } else {

                }
            break;
        }
    }

    private String createFile(String filename){
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + Environment.DIRECTORY_DOWNLOADS +
                File.separator + filename);
        try {
            if (file.createNewFile()){
                FileWriter fr = new FileWriter(file);
                fr.write("hello world!");
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    private void clickOnOpenFile(String filename){
        Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() +
                File.separator + Environment.DIRECTORY_DOWNLOADS +
                File.separator + filename);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(selectedUri, "resource/folder");

        Intent chooser = Intent.createChooser(intent, "Open file");
        if (intent.resolveActivityInfo(getPackageManager(),0) != null){
            startActivityForResult(chooser, PICK_FILE_REQUEST);
        } else {

        }
    }
}
