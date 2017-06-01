package androidlab2017.epam.com;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final static int PERMISSION_RWC = 1;
    private final static String LOADER_FRAGMENT_TAG = "loader_fragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();
    }

    private void getPermissions(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS)
                == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_CONTACTS},
                    PERMISSION_RWC);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_RWC:
                if (grantResults.length > 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED){

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new CursorLoaderListFragment(),
                                    LOADER_FRAGMENT_TAG)
                            .commit();
                } else {

                }
                break;
        }
    }
}
