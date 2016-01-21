package com.haja.tionaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import static java.lang.String.format;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * Created by haja on 17/01/15.
 */
@EActivity(R.layout.activity_pdf)
public class PdfActivity extends Activity implements OnPageChangeListener {

    @ViewById(R.id.pdfview)
    PDFView pdfView;
    @ViewById(R.id.nb_edit)
    EditText editText;
    @ViewById(R.id.btn_fihirana)
    Button btnFihirana;
    @ViewById(R.id.btn_antema)
    Button btnAntema;
    @ViewById(R.id.btn_fihirana_fanampiny)
    Button btnFF;
    @ViewById(R.id.btn_liturgique)
    Button btnLiturgie;
    @ViewById(R.id.btn_chorale)
    Button btnChorale;
    @ViewById(R.id.search_type)
    TextView searchType;

    Intent intent;
    String pdfName;
    Integer pageNumber = 1;
    private int mode = 0; // pour le mode de recherche selon le tab : 0 => fihirana, 1 => ff, 2 => antema, 3 => lt, 4 default

    @AfterInject
    public void afterInject(){
    }

    @AfterViews
    public void afterViews(){
        intent = getIntent();
        pdfName = intent.getStringExtra("fileName");

        if(pdfName!=null && pdfName.length()>0) {
//            String tmp = retrieveFilePath();
//            if(tmp!=null && tmp.length()>0){
//                tmp = tmp + pdfName;
//                display(tmp, true);
//            }
              display(pdfName, true);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.btn_fihirana:
                        mode = 0;
                        searchType.setText("Fihirana");
                        Log.d("haja", "fihirana");
                        break;
                    case R.id.btn_fihirana_fanampiny:
                        mode = 1;
                        searchType.setText("Fihirana Fanampiny");
                        Log.d("haja", "ff");
                        break;
                    case R.id.btn_antema:
                        mode = 2;
                        searchType.setText("Antema");
                        Log.d("haja", "antema");
                        break;
                    case R.id.btn_liturgique:
                        mode = 3;
                        searchType.setText("Liturgie");
                        Log.d("haja", "lt");
                        break;
                    default:
                        mode = 0;
                        searchType.setText("Default");
                        Log.d("haja", "default");
                        break;

                }

            }
        };

        btnFF.setOnClickListener(onClickListener);
        btnFihirana.setOnClickListener(onClickListener);
        btnAntema.setOnClickListener(onClickListener);
        btnChorale.setOnClickListener(onClickListener);
        btnLiturgie.setOnClickListener(onClickListener);

    }

    public String retrieveFilePath(){
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            return Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Tiona/pdf/";
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            return Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Tiona/pdf/";
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            return "";
        }
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = assetFileName);
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }

    @Click(R.id.btn_go)
    public void go(){
        if(!editText.getText().toString().isEmpty()){
            pdfName = "";
            switch(mode){
                case 0:
                    break;
                case 1:
                    pdfName = "ff";
                    break;
                case 2:
                    pdfName = "antema";
                    break;
                case 3:
                    pdfName = "lt";
                    break;
                default:
                    break;
            }
            pdfName = pdfName + editText.getText().toString() + ".pdf";
            display(pdfName, true);
        }
        else{
            Toast.makeText(this, "Vous n\'avez rien tapé...", Toast.LENGTH_SHORT);
        }
    }

}
