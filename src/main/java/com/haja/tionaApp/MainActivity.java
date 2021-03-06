
package com.haja.tionaApp;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.haja.tionaApp.utils.StorageManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    private int mode = 0; // pour le mode de recherche selon le tab

    // Tab titles
    private String[] tabs = { "Fihirana", "Fihirana Fanampiny", "Antema", "Liturgique", "Chorale" };

    private String pdfName;
    public String[] buttonsNames ={"1","2","3","4","5","6","7","8","9","OK"};

    @ViewById(R.id.number_param)
    EditText numberEditText;

    @AfterViews
    void afterViews() {

    }

    @Click(R.id.btn_search)
    public void btnClick(){
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

        String number = numberEditText.getText().toString();
        pdfName = pdfName + number + ".pdf";
        if(songExist(number)) {
            showPdf(pdfName);
        }
        else{
            Toast.makeText(this, R.string.not_found, Toast.LENGTH_SHORT).show();
        }

    }

    @Click(R.id.btn_fihirana)
    public void btnFihirana(){
        mode = 0;
    }

    @Click(R.id.btn_fihirana_fanampiny)
    public void btnFihiranaFanmpiny(){
        mode = 1;
    }

    @Click(R.id.btn_antema)
    public void btnAntema(){
        mode = 2;
    }

    @Click(R.id.btn_liturgique)
    public void btnLiturgique(){
        mode = 3;
    }

    public void showPdf(String name){
        Intent i = new Intent(this, PdfActivity_.class);
        i.putExtra("fileName", pdfName);
        this.startActivity(i);
    }

    public boolean songExist(String song){
        boolean exist = false;
        String [] list;
        try {
            list = getAssets().list("");
            if (list.length > 0) {
                for (String aList : list) {
                    if(aList.equals(song+".pdf")){
                        exist = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exist;
    }

}
