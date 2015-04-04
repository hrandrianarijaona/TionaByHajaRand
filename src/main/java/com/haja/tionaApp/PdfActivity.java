package com.haja.tionaApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import static java.lang.String.format;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Created by haja on 17/01/15.
 */
@EActivity(R.layout.activity_pdf)
public class PdfActivity extends Activity implements OnPageChangeListener {

    @ViewById(R.id.pdfview)
    PDFView pdfView;

    Intent intent;
    String pdfName;
    Integer pageNumber = 1;

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

}
