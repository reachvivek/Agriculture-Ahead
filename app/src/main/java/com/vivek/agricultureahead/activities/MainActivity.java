package com.vivek.agricultureahead.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.text.method.LinkMovementMethod;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.vivek.agricultureahead.R;
import com.vivek.agricultureahead.adapters.MainAdapter;
import com.vivek.agricultureahead.models.MainListItem;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<MainListItem> list;
    private RecyclerView recyclerView;
    private MainAdapter adapter;

    private Integer[] imageUrls={R.raw.production,R.raw.treat,R.raw.shc2,R.drawable.horticulture_main,R.raw.govp};

    private Integer[] hindiTexts={R.string.crop_production_card_title_hi,R.string.treatment_card_title_hi,
            R.string.storage_card_title_hi,R.string.horticulture_card_title_hi,R.string.policy_card_title_hi};

    private Integer[] englishTexts={R.string.crop_production_card_title_en,R.string.treatment_card_title_en,
            R.string.storage_card_title_en,R.string.horticulture_card_title_en,R.string.policy_card_title_en};

    private String[] backgroundColors={"#35e372","#a4f075","#ffff4d","#70dbdb","#cef63c","#ff9f80"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent[] links={
                new Intent(MainActivity.this, CropProductionActivity.class),
                new Intent(MainActivity.this, SelectProblem.class),
                new Intent(MainActivity.this, SoilHealthActivity.class),
                new Intent(MainActivity.this, HorticultureActivity.class),
                new Intent(MainActivity.this, Select_Policy.class)
        };

        list = new ArrayList<>();
        for(int i=0;i<imageUrls.length;i++){
            MainListItem item=new MainListItem();

            item.setImageUrl(imageUrls[i]);
            item.setHindiText(hindiTexts[i]);
            item.setEnglishText(englishTexts[i]);
            item.setBackgroundColor(backgroundColors[i]);
            item.setIntent(links[i]);

            list.add(item);
        }

        adapter = new MainAdapter(this,list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.progress).setVisibility(View.GONE);
    }

    int count=0;
    @Override
    public void onBackPressed() {
        count++;
        if(count == 2)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        }
        else if (count==1)
        {
            Toast.makeText(this, "Press the back button once again to close the application.",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    count=0;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_helpcentre) {
            if(Build.VERSION.SDK_INT<23){
                Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91" + "18001801551"));

                try{
                    startActivity(in);
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                }

            }else{
                int REQUEST_CODE_ASK_PERMISSIONS = 123;

                int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {Manifest.permission.CALL_PHONE},
                            REQUEST_CODE_ASK_PERMISSIONS);

                }

                Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91" + "18001801551"));

                try{
                    startActivity(in);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Permissions required", Toast.LENGTH_SHORT).show();
                }

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schemes) {
            startActivity(new Intent(MainActivity.this,Select_Policy.class));
        }
        else if (id == R.id.nav_agricultural) {
            // Switches to Agriculture
            //TODO

        } else if (id == R.id.nav_about) {
            // Switches to About
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(String.format("%1$s", getString(R.string.about)));
            builder.setMessage(getResources().getText(R.string.about_text));
            builder.setPositiveButton("OK", null);
            builder.setIcon(R.mipmap.ic_launcher);
            AlertDialog welcomeAlert = builder.create();
            welcomeAlert.show();
            ((TextView) welcomeAlert.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());

        } else if (id == R.id.nav_contactus) {
            // Switches to Mail
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode("reachvandd@gmail.com") + "?subject=" +
                    Uri.encode("Feedback") + "&body=" + Uri.encode("");

            Uri uri = Uri.parse(uriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "Send Email"));

        } else if (id == R.id.nav_helpcentre) {
            // Switches to Call
            if(Build.VERSION.SDK_INT<23){
                Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91" + "18001801551"));

                try{
                    startActivity(in);
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                }

            }else{
                int REQUEST_CODE_ASK_PERMISSIONS = 123;

                int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {Manifest.permission.CALL_PHONE},
                            REQUEST_CODE_ASK_PERMISSIONS);

                }

                Intent in=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+91" + "18001801551"));

                try{
                    startActivity(in);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Permissions required", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
