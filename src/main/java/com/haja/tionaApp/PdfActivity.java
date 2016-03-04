package com.haja.tionaApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    String[] choraleSongs;
    Intent intent;
    String pdfName;
    Integer pageNumber = 1;
    private int mode = 0; // pour le mode de recherche selon le tab : 0 => fihirana, 1 => ff, 2 => antema, 3 => lt, 4 default

    AlertDialog.Builder builderChoraleBox;
    Dialog choraleDialog;

    @AfterInject
    public void afterInject(){
    }

    @AfterViews
    public void afterViews(){
        intent = getIntent();
        pdfName = intent.getStringExtra("fileName");
        Log.d("haja", "current pdf: " + pdfName);

        // Initialisation des boutons
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
        btnLiturgie.setOnClickListener(onClickListener);

        choraleSongs = listingChoraleSongs();


        // Mis en place de la dialogue box pour le listing des chants de chorale
        builderChoraleBox = new AlertDialog.Builder(this);
        builderChoraleBox
                .setTitle(R.string.chorale_songs)
                .setItems(choraleSongs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pdfName = choraleSongs[which];
                        display(pdfName, true);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        choraleDialog = builderChoraleBox.create();

        // Affichage du pdf
        if(pdfName!=null && pdfName.length()>0) {
            display(pdfName, true);
        }

    }

    private String[] listingChoraleSongs(){
        List<String> tempList = new ArrayList<String>();
        String[] result = new String[0];
        String [] list;
        try {
            list = getAssets().list("");
            result = new String[list.length];
            if (list.length > 0) {
                for (String aList : list) {
                    if (aList.contains("chr_")) { // on ajoute les chants de chorale
                        Log.d("haja", "TEST: " + aList);
                        tempList.add(aList.replace("\\.pdf", ""));
//                        result[i] = list[i].replace("\\.pdf", "");
                    }
                }
                result = new String[tempList.size()];
                for (int i = 0; i < result.length; i++) {
                    result[i] = tempList.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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

    /**
     * Fonction d'affichage d'un pdf
     * @param assetFileName
     * @param jumpToFirstPage
     */
    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = assetFileName);
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }

    /**
     * Fonction d'affichage d'un pdf
     * @param file
     * @param jumpToFirstPage
     */
    private void display(File file, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(file.getName());
        pdfView.fromFile(file)
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
            String number = editText.getText().toString();
            pdfName = pdfName + editText.getText().toString() + ".pdf";
            if(songExist(number)) {
                display(pdfName, true);
            }
            else{
                Toast.makeText(this, R.string.not_found, Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "Vous n\'avez rien tapé...", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.btn_chorale)
    public void showChoraleSongs(){
//        Log.d("haja", "liste: ");
//        for (String choraleSong : choraleSongs) {
//            Log.d("haja", choraleSong);
//        }
        choraleDialog.show();
    }

}
